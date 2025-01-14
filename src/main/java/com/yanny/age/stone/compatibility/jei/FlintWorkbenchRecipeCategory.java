package com.yanny.age.stone.compatibility.jei;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.yanny.age.stone.Reference;
import com.yanny.age.stone.recipes.DryingRackRecipe;
import com.yanny.age.stone.recipes.FlintWorkbenchRecipe;
import com.yanny.age.stone.subscribers.BlockSubscriber;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class FlintWorkbenchRecipeCategory implements IRecipeCategory<FlintWorkbenchRecipe> {
    static final ResourceLocation UID = new ResourceLocation(Reference.MODID, "flint_workbench_recipe_category");

    private final String localizedName;
    private final IDrawableStatic background;
    private final IDrawable icon;
    private IDrawableAnimated ARROW;
    public static final RecipeType<FlintWorkbenchRecipe> FLINETWORKBENCH_RECIPE_TYPE = new RecipeType<>(UID,FlintWorkbenchRecipe.class);

    FlintWorkbenchRecipeCategory(@Nonnull IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Reference.MODID, "textures/gui/jei/gui_layouts.png");
        background = guiHelper.createDrawable(location, 0, 0, 120, 60);
        localizedName = I18n.get("block.stone_age.flint_workbench");
        icon = guiHelper.createDrawableItemStack(new ItemStack(BlockSubscriber.flint_workbench));
        IDrawableStatic arrow = guiHelper.createDrawable(new ResourceLocation(Reference.MODID, "textures/gui/jei/gui_layouts.png"), 123, 24, 25, 14);
        ARROW = guiHelper.createAnimatedDrawable(arrow, 80, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(FlintWorkbenchRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        ARROW.draw(guiGraphics, 64, 22);
    }

/*    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Nonnull
    @Override
    public Class<? extends FlintWorkbenchRecipe> getRecipeClass() {
        return FlintWorkbenchRecipe.class;
    }*/

    @Override
    public RecipeType<FlintWorkbenchRecipe> getRecipeType() {
        return FLINETWORKBENCH_RECIPE_TYPE;
    }

    @Nonnull
    @Override
    public Component getTitle() {
        return Component.literal(localizedName);
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Nonnull
    @Override
    public IDrawable getIcon() {
        return icon;
    }

//    @Override
//    public void setIngredients(@Nonnull FlintWorkbenchRecipe flintWorkbenchRecipe, @Nonnull IIngredients ingredients) {
//        ImmutableList.Builder<List<ItemStack>> inputBuilder = ImmutableList.builder();
//        ImmutableList.Builder<ItemStack> outputBuilder = ImmutableList.builder();
//        int width = flintWorkbenchRecipe.getWidth();
//        int height = flintWorkbenchRecipe.getHeight();
//
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                inputBuilder.add(Arrays.asList(flintWorkbenchRecipe.getIngredients().get(x + y * width).getItems()));
//            }
//        }
//
//        inputBuilder.add(Arrays.asList(flintWorkbenchRecipe.getTool().getItems()));
//        outputBuilder.add(flintWorkbenchRecipe.getResultItem());
//        ingredients.setInputLists(VanillaTypes.ITEM, inputBuilder.build());
//        ingredients.setOutputLists(VanillaTypes.ITEM, ImmutableList.of(outputBuilder.build()));
//    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull FlintWorkbenchRecipe flintWorkbenchRecipe, @Nonnull IFocusGroup focuses) {
        int width = flintWorkbenchRecipe.getWidth();
        int height = flintWorkbenchRecipe.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
//                recipeLayout.getItemStacks().init(x + y * width, true, 3 + x * 18, 3 + y * 18);
//                recipeLayout.getItemStacks().set(x + y * width, ingredients.getInputs(VanillaTypes.ITEM).get(x + y * width));
                builder.addSlot(RecipeIngredientRole.INPUT, 4 + x * 18, 4 + y * 18).addIngredients(flintWorkbenchRecipe.getIngredients().get(x + y * width));
            }
        }

//        recipeLayout.getItemStacks().init(width * height, false, 97, 21);
//        recipeLayout.getItemStacks().set(width * height, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 98, 22).addItemStack(flintWorkbenchRecipe.getResultItem(null));

//        recipeLayout.getItemStacks().init(width * height + 1, false, 65, 38);
//        recipeLayout.getItemStacks().set(width * height + 1, ingredients.getInputs(VanillaTypes.ITEM).get(width * height));
        builder.addSlot(RecipeIngredientRole.INPUT, 66, 39).addIngredients(flintWorkbenchRecipe.getTool());
    }
}
