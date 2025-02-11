package com.yanny.age.stone.datagen;

import org.lwjgl.system.NonnullDefault;

@NonnullDefault
public class TanningRackRecipeBuilder {
    /*private final Item result;
    private final int count;
    private final Ingredient input;
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    @Nullable private String group = null;
    @Nullable private Ingredient tool = null;

    public TanningRackRecipeBuilder(ItemLike resultIn, ItemLike input, int countIn) {
        this.result = resultIn.asItem();
        this.input = Ingredient.of(input);
        this.count = countIn;
    }

    public static TanningRackRecipeBuilder recipe(ItemLike resultIn, ItemLike input) {
        return recipe(resultIn, input, 1);
    }

    public static TanningRackRecipeBuilder recipe(ItemLike resultIn, ItemLike input, int countIn) {
        return new TanningRackRecipeBuilder(resultIn, input, countIn);
    }

    public TanningRackRecipeBuilder tool(Tag<Item> tool) {
        return this.tool(Ingredient.of(tool));
    }

    public TanningRackRecipeBuilder tool(ItemLike tool) {
        return this.tool(Ingredient.of(tool));
    }

    public TanningRackRecipeBuilder tool(Ingredient tool) {
        if (this.tool != null) {
            throw new IllegalArgumentException("Tool is already defined!");
        } else {
            this.tool = tool;
            return this;
        }
    }

    public TanningRackRecipeBuilder addCriterion(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public TanningRackRecipeBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumerIn) {
        this.build(consumerIn, Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this.result)));
    }

    public void build(Consumer<FinishedRecipe> consumerIn, String save) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.result);

        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Shaped Recipe " + save + " should remove its 'save' argument");
        } else {
            this.build(consumerIn, new ResourceLocation(save));
        }
    }

    public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
        if (this.result.getItemCategory() == null) {
            throw new IllegalStateException("Recipe " + id + " has null group!");
        } else if (this.tool == null) {
            throw new IllegalStateException("Tool is not set!");
        }

        this.advancementBuilder.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumerIn.accept(new Result(id, this.result, this.input, this.count, this.group == null ? "" : this.group, this.tool, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + id.getPath())));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final Ingredient input;
        private final Ingredient tool;
        private final Advancement.Builder advancementBuilder;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation idIn, Item resultIn, Ingredient input, int countIn, String groupIn, Ingredient tool, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn) {
            this.id = idIn;
            this.result = resultIn;
            this.count = countIn;
            this.group = groupIn;
            this.tool = tool;
            this.input = input;
            this.advancementBuilder = advancementBuilderIn;
            this.advancementId = advancementIdIn;
        }

        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

            json.add("tool", tool.toJson());
            json.add("ingredient", input.toJson());

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this.result)).toString());

            if (this.count > 1) {
                jsonObject.addProperty("count", this.count);
            }

            json.add("result", jsonObject);
        }

        @SuppressWarnings("ConstantConditions")
        public RecipeSerializer<?> getType() {
            return RecipeSubscriber.tanning_rack;
        }

        *//**
         * Gets the ID for the recipe.
         *//*
        public ResourceLocation getId() {
            return this.id;
        }

        *//**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is no advancement.
         *//*
        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancementBuilder.serializeToJson();
        }

        *//**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {@link #getAdvancementJson}
         * is non-null.
         *//*
        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }*/
}
