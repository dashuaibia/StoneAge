package com.yanny.age.stone.blocks;

import com.yanny.age.stone.ExampleMod;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import com.yanny.age.stone.subscribers.ContainerSubscriber;
import com.yanny.age.stone.utils.ContainerUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

import java.util.Objects;

import static com.yanny.age.stone.blocks.MillstoneTileEntity.ITEMS;

public class MillstoneContainer extends Container {
    private final MillstoneTileEntity tile;
    private final PlayerEntity player;
    private final IIntArray data;

    public MillstoneContainer(int windowId, @Nonnull PlayerInventory inv, @Nonnull PacketBuffer extraData) {
        this(windowId, extraData.readBlockPos(), Objects.requireNonNull(ExampleMod.proxy.getClientWorld()), inv, Objects.requireNonNull(ExampleMod.proxy.getClientPlayer()),
                new IntArray(1));
    }

    MillstoneContainer(int id, @Nonnull BlockPos pos, @Nonnull World world, @Nonnull PlayerInventory inventory, @Nonnull PlayerEntity player, @Nonnull IIntArray data) {
        super(ContainerSubscriber.millstone, id);
        tile = (MillstoneTileEntity) world.getBlockEntity(pos);
        this.player = player;
        this.data = data;

        if (tile == null) {
            throw new IllegalStateException("TileEntity does not exists!");
        }

        tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(new SlotItemHandler(h, 0, 62, 35));
            addSlot(new SlotItemHandler(h, 1, 98, 24));
            addSlot(new SlotItemHandler(h, 2, 98, 46));
        });

        ContainerUtils.layoutPlayerInventorySlots(((slot, x, y) -> addSlot(new Slot(inventory, slot, x, y))), 8, 84);

        addDataSlots(data);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean stillValid(@Nonnull PlayerEntity playerIn) {
        if (tile == null || tile.getLevel() == null) {
            throw new IllegalStateException("Null pointer");
        }

        return stillValid(IWorldPosCallable.create(tile.getLevel(), tile.getBlockPos()), player, BlockSubscriber.millstone);
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index) {
        Slot slot = slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            ItemStack itemstack = stack.copy();

            if (index < ITEMS) {
                if (!moveItemStackTo(stack, ITEMS + 1, ITEMS + 36, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, itemstack);
            } else {
                if (tile.isItemValid(stack) && !moveItemStackTo(stack, 0, ITEMS, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return ItemStack.EMPTY;
    }

    int getProgress() {
        return data.get(0);
    }
}
