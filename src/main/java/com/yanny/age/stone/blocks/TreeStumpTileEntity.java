package com.yanny.age.stone.blocks;

import com.yanny.age.stone.recipes.TreeStumpRecipe;
import com.yanny.age.stone.subscribers.TileEntitySubscriber;
import com.yanny.ages.api.utils.ItemStackUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeStumpTileEntity extends TileEntity implements IInventoryInterface {
    private final NonNullList<ItemStack> stacks = NonNullList.withSize(1, ItemStack.EMPTY);
    private final IItemHandlerModifiable nonSidedItemHandler = createNonSidedInventoryHandler(stacks);
    private final LazyOptional<IItemHandlerModifiable> sidedInventoryHandler = LazyOptional.of(() -> createSidedInventoryHandler(stacks));
    private final LazyOptional<IItemHandlerModifiable> nonSidedInventoryHandler = LazyOptional.of(() -> nonSidedItemHandler);
    private final RecipeWrapper inventoryWrapper = new RecipeWrapper(nonSidedItemHandler);
    private final IItemHandlerModifiable tmpItemHandler = new ItemStackHandler(1);
    private final RecipeWrapper tmpItemHandlerWrapper = new RecipeWrapper(tmpItemHandler);

    private int totalChops = 0;
    private int chopLeft = 0;
    private ItemStack recipeResult = ItemStack.EMPTY;
    private final List<Ingredient> tools = new ArrayList<>();

    public TreeStumpTileEntity() {
        //noinspection ConstantConditions
        super(TileEntitySubscriber.tree_stump);
    }

    @Nonnull
    @Override
    public IInventory getInventory() {
        return inventoryWrapper;
    }

    @Override
    public void load(@Nonnull BlockState blockState, CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        ItemStackUtils.deserializeStacks(invTag, stacks);
        chopLeft = tag.getInt("chopLeft");
        totalChops = tag.getInt("totalChops");
        recipeResult = ItemStack.of(tag.getCompound("result"));
        CompoundNBT toolTag = tag.getCompound("tool");
        ItemStackUtils.deserializeIngredients(toolTag, tools);
        super.load(blockState, tag);
    }

    @Override
    @Nonnull
    public CompoundNBT save(CompoundNBT tag) {
        tag.put("inv", ItemStackUtils.serializeStacks(stacks));
        tag.putInt("chopLeft", chopLeft);
        tag.putInt("totalChops", totalChops);
        CompoundNBT resTag = new CompoundNBT();
        recipeResult.save(resTag);
        tag.put("result", resTag);
        tag.put("tool", ItemStackUtils.serializeIngredients(tools));
        return super.save(tag);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(getBlockPos(), getType().hashCode(), getUpdateTag());
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        return save(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        load(getBlockState(), pkt.getTag());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side != null) {
                return sidedInventoryHandler.cast();
            } else {
                return nonSidedInventoryHandler.cast();
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void setRemoved() {
        sidedInventoryHandler.invalidate();
        nonSidedInventoryHandler.invalidate();
        super.setRemoved();
    }

    void onBlockRightClicked(@Nonnull PlayerEntity player) {
        assert level != null;

        if (hasTool(player.getMainHandItem())) {
            if (stacks.get(0).getCount() == getRecipe(stacks.get(0)).getIngredients().get(0).getItems()[0].getCount()) {
                chopLeft--;

                if (chopLeft == 0) {
                    NonNullList<ItemStack> itemStacks = NonNullList.create();
                    itemStacks.add(recipeResult);
                    stacks.set(0, ItemStack.EMPTY);
                    InventoryHelper.dropContents(level, getBlockPos(), itemStacks);
                    recipeResult = ItemStack.EMPTY;
                    tools.clear();
                    player.getMainHandItem().hurtAndBreak(1, player, playerEntity -> playerEntity.broadcastBreakEvent(EquipmentSlotType.MAINHAND));

                    level.playSound(null, getBlockPos(), SoundEvents.WOOD_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                } else {
                    level.playSound(null, getBlockPos(), SoundEvents.WOOD_HIT, SoundCategory.BLOCKS, 1.0f, 1.0f);
                }
            }
        } else {
            level.playSound(null, getBlockPos(), SoundEvents.SHIELD_BLOCK, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
    }

    void blockActivated(@Nonnull PlayerEntity player) {
        assert level != null;
        ItemStack itemStack = player.getMainHandItem();
        TreeStumpRecipe recipe = getRecipe(itemStack);

        if (recipe == null) {
            itemStack = player.getOffhandItem();
            recipe = getRecipe(itemStack);
        }

        if (stacks.get(0).isEmpty() && recipe != null) {
            stacks.set(0, itemStack.split(1));
            totalChops = recipe.getChopTimes();
            chopLeft = recipe.getChopTimes();
            recipeResult = recipe.assemble(null);
            tools.addAll(recipe.getTools());

            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            return;
        } else if (stacks.get(0).getCount() == 1 && recipe != null
                && recipe.getIngredients().get(0).getItems()[0].getCount() == 2
                && stacks.get(0).getItem() == itemStack.getItem()) {
            stacks.get(0).grow(itemStack.split(1).getCount());
            totalChops = recipe.getChopTimes();
            chopLeft = recipe.getChopTimes();
            recipeResult = recipe.assemble(null);
            tools.addAll(recipe.getTools());

            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            return;
        }

        if (itemStack.isEmpty() && !stacks.get(0).isEmpty()) {
            NonNullList<ItemStack> itemStacks = NonNullList.create();
            itemStacks.add(stacks.get(0).copy());
            stacks.set(0, ItemStack.EMPTY);
            InventoryHelper.dropContents(level, getBlockPos(), itemStacks);
            recipeResult = ItemStack.EMPTY;
            tools.clear();

            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            level.playSound(null, getBlockPos(), SoundEvents.ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
    }

    boolean hasTool(@Nonnull ItemStack toolInHand) {
        return tools.stream().anyMatch(ingredient -> Arrays.stream(ingredient.getItems()).anyMatch(itemStack -> itemStack.getItem() == toolInHand.getItem()));
    }

    @Nonnull
    ItemStack getResult() {
        return recipeResult;
    }

    int getProgress() {
        return (int) (100 - chopLeft / (float)totalChops * 100);
    }

    @Nullable
    private TreeStumpRecipe getRecipe(@Nonnull ItemStack item) {
        assert level != null;
        tmpItemHandler.setStackInSlot(0, item);
        return level.getRecipeManager().getRecipeFor(TreeStumpRecipe.tree_stump, tmpItemHandlerWrapper, level).orElse(null);
    }

    @Nonnull
    private IItemHandlerModifiable createNonSidedInventoryHandler(@Nonnull NonNullList<ItemStack> stacks) {
        return new ItemStackHandler(stacks) {
            @Override
            protected void onContentsChanged(int slot) {
                assert level != null;
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }

    @Nonnull
    private IItemHandlerModifiable createSidedInventoryHandler(@Nonnull NonNullList<ItemStack> stacks) {
        return new ItemStackHandler(stacks) {
            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return ItemStack.EMPTY;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (getStackInSlot(slot).isEmpty() && level != null) {
                    TreeStumpRecipe recipe = getRecipe(stack);

                    if (recipe != null && stacks.get(0).isEmpty()) {
                        totalChops = recipe.getChopTimes();
                        chopLeft = recipe.getChopTimes();
                        recipeResult = recipe.assemble(null);
                        tools.addAll(recipe.getTools());

                        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                        return super.insertItem(slot, stack, simulate);
                    }
                }

                return stack;
            }

            @Override
            protected void onContentsChanged(int slot) {
                assert level != null;
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }
}
