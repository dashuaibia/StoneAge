package com.yanny.age.stone.config;

import com.yanny.age.stone.Reference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class ServerConfig {
    final ForgeConfigSpec.BooleanValue removeVanillaRecipes;
    final ForgeConfigSpec.BooleanValue removeVanillaGeneratedAnimals;
    final ForgeConfigSpec.IntValue domesticateAfterGenerations;
    final ForgeConfigSpec.DoubleValue tanningRackFinishChance;
    final ForgeConfigSpec.IntValue feederTickChanceBreedAnimalEffect;
    final ForgeConfigSpec.IntValue feederEffectRange;
    final ForgeConfigSpec.BooleanValue forceToolForWood;
    final ForgeConfigSpec.BooleanValue givePlayerManualOnFirstConnect;
    final ForgeConfigSpec.IntValue fishingNetChance;
    final ForgeConfigSpec.BooleanValue disableVanillaCraftingTable;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> disabledUseInStoneAgeList;

    final ForgeConfigSpec.DoubleValue abandonedCampSpawnChance;
    final ForgeConfigSpec.DoubleValue burialPlaceSpawnChance;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> abandonedCampAllowedBiomes;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> burialPlaceAllowedBiomes;

    final ForgeConfigSpec.BooleanValue spawnDeerEnable;
    final ForgeConfigSpec.IntValue spawnDeerWeight;
    final ForgeConfigSpec.IntValue spawnDeerMinCount;
    final ForgeConfigSpec.IntValue spawnDeerMaxCount;
    final ForgeConfigSpec.BooleanValue spawnDeerAllowedBiomesBlacklist;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnDeerAllowedBiomes;

    final ForgeConfigSpec.BooleanValue spawnBoarEnable;
    final ForgeConfigSpec.IntValue spawnBoarWeight;
    final ForgeConfigSpec.IntValue spawnBoarMinCount;
    final ForgeConfigSpec.IntValue spawnBoarMaxCount;
    final ForgeConfigSpec.BooleanValue spawnBoarAllowedBiomesBlacklist;
    final ForgeConfigSpec.ConfigValue<String> boarBreedingResult;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnBoarAllowedBiomes;

    final ForgeConfigSpec.BooleanValue spawnAurochEnable;
    final ForgeConfigSpec.IntValue spawnAurochWeight;
    final ForgeConfigSpec.IntValue spawnAurochMinCount;
    final ForgeConfigSpec.IntValue spawnAurochMaxCount;
    final ForgeConfigSpec.BooleanValue spawnAurochAllowedBiomesBlacklist;
    final ForgeConfigSpec.ConfigValue<String> aurochBreedingResult;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnAurochAllowedBiomes;

    final ForgeConfigSpec.BooleanValue spawnFowlEnable;
    final ForgeConfigSpec.IntValue spawnFowlWeight;
    final ForgeConfigSpec.IntValue spawnFowlMinCount;
    final ForgeConfigSpec.IntValue spawnFowlMaxCount;
    final ForgeConfigSpec.BooleanValue spawnFowlAllowedBiomesBlacklist;
    final ForgeConfigSpec.ConfigValue<String> fowlBreedingResult;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnFowlAllowedBiomes;

    final ForgeConfigSpec.BooleanValue spawnMouflonEnable;
    final ForgeConfigSpec.IntValue spawnMouflonWeight;
    final ForgeConfigSpec.IntValue spawnMouflonMinCount;
    final ForgeConfigSpec.IntValue spawnMouflonMaxCount;
    final ForgeConfigSpec.BooleanValue spawnMouflonAllowedBiomesBlacklist;
    final ForgeConfigSpec.ConfigValue<String> mouflonBreedingResult;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnMouflonAllowedBiomes;

    final ForgeConfigSpec.BooleanValue spawnMammothEnable;
    final ForgeConfigSpec.IntValue spawnMammothWeight;
    final ForgeConfigSpec.IntValue spawnMammothMinCount;
    final ForgeConfigSpec.IntValue spawnMammothMaxCount;
    final ForgeConfigSpec.BooleanValue spawnMammothAllowedBiomesBlacklist;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnMammothAllowedBiomes;

    final ForgeConfigSpec.BooleanValue spawnSaberToothTigerEnable;
    final ForgeConfigSpec.IntValue spawnSaberToothTigerWeight;
    final ForgeConfigSpec.IntValue spawnSaberToothTigerMinCount;
    final ForgeConfigSpec.IntValue spawnSaberToothTigerMaxCount;
    final ForgeConfigSpec.BooleanValue spawnSaberToothTigerAllowedBiomesBlacklist;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnSaberToothTigerAllowedBiomes;

    final ForgeConfigSpec.BooleanValue spawnWoollyRhinoEnable;
    final ForgeConfigSpec.IntValue spawnWoollyRhinoWeight;
    final ForgeConfigSpec.IntValue spawnWoollyRhinoMinCount;
    final ForgeConfigSpec.IntValue spawnWoollyRhinoMaxCount;
    final ForgeConfigSpec.BooleanValue spawnWoollyRhinoAllowedBiomesBlacklist;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnWoollyRhinoAllowedBiomes;

    final ForgeConfigSpec.BooleanValue spawnTerrorBirdEnable;
    final ForgeConfigSpec.IntValue spawnTerrorBirdWeight;
    final ForgeConfigSpec.IntValue spawnTerrorBirdMinCount;
    final ForgeConfigSpec.IntValue spawnTerrorBirdMaxCount;
    final ForgeConfigSpec.BooleanValue spawnTerrorBirdAllowedBiomesBlacklist;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnTerrorBirdAllowedBiomes;

    final ForgeConfigSpec.BooleanValue spawnCoelacanthEnable;
    final ForgeConfigSpec.IntValue spawnCoelacanthWeight;
    final ForgeConfigSpec.IntValue spawnCoelacanthMinCount;
    final ForgeConfigSpec.IntValue spawnCoelacanthMaxCount;
    final ForgeConfigSpec.BooleanValue spawnCoelacanthAllowedBiomesBlacklist;
    final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnCoelacanthAllowedBiomes;

    final ForgeConfigSpec.ConfigValue<List<? extends String>> infinityWaterSourceBiomeList;

    final ForgeConfigSpec.BooleanValue GrassBedSleep;
    final ForgeConfigSpec.BooleanValue DryingRackNeedDaytime;
    final ForgeConfigSpec.BooleanValue MakeFire;
    final ForgeConfigSpec.BooleanValue LitTorche;

    ServerConfig(@Nonnull final ForgeConfigSpec.Builder builder) {
        builder.push("general");
        removeVanillaRecipes = builder
                .comment("Remove vanilla recipes that are changed by mod or break mod gameplay (wooden tools, torch)")
                .translation(Reference.MODID + ".config.remove_vanilla_recipes")
                .define("removeVanillaRecipes", true);
        removeVanillaGeneratedAnimals = builder
                .comment("Remove spawning of vanilla animals like cows, pigs, sheeps")
                .translation(Reference.MODID + ".config.remove_vanilla_generated_animals")
                .define("removeVanillaGeneratedAnimals", true);
        domesticateAfterGenerations = builder
                .comment("Domesticate wild animal after given generations")
                .translation(Reference.MODID + ".config.domesticate_after_generations")
                .defineInRange("domesticateAfterGenerations", 3, 1, Integer.MAX_VALUE);
        tanningRackFinishChance = builder
                .comment("Chance of finishing recipe in tanning rack")
                .translation(Reference.MODID + ".config.tanning_rack_finish_chance")
                .defineInRange("tanningRackFinishChance", 0.1, 0.001, 1.0);
        feederTickChanceBreedAnimalEffect = builder
                .comment("Chance of breed effect from feeder every 1/X tick")
                .translation(Reference.MODID + ".config.feeder_tick_chance_breed_animal_effect")
                .defineInRange("feederTickChanceBreedAnimal", 200, 1, Integer.MAX_VALUE);
        feederEffectRange = builder
                .comment("Feeder area of breed effect")
                .translation(Reference.MODID + ".config.feeder_effect_range")
                .defineInRange("feederEffectRange", 4, 0, 15);
        forceToolForWood = builder
                .comment("Force tool for wood material - harvestable only with axe")
                .translation(Reference.MODID + ".config.force_tool_for_wood")
                .define("forceToolForWood", true);
        givePlayerManualOnFirstConnect = builder
                .comment("Give player manual on first connect to game")
                .translation(Reference.MODID + ".config.give_player_manual_on_first_connect")
                .define("givePlayerManualOnFirstConnect", true);
        fishingNetChance = builder
                .comment("Chance of successfull fishing every 1/X tick (randomly)")
                .translation(Reference.MODID + ".config.fishing_net_chance")
                .defineInRange("fishingNetChance", 3600, 1, Integer.MAX_VALUE);
        disableVanillaCraftingTable = builder
                .comment("Disable use of vanilla crafting table until end of Stone Age")
                .translation(Reference.MODID + ".config.disable_vanilla_crafting_table")
                .define("disableVanillaCraftingTable", true);
        disabledUseInStoneAgeList = builder
                .comment("List of blocks that have disabled use until end of stone age")
                .translation(Reference.MODID + ".config.disabled_use_in_stone_age_list")
                .defineList("disabledUseInStoneAgeList", Config.DEFAULT_DISABLED_USE_BLOCKS.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BLOCKS.containsKey(new ResourceLocation((String) string)));
        builder.pop();

        builder.push("structures");
        abandonedCampSpawnChance = builder
                .comment("Abandoned camp spawn chance")
                .translation(Reference.MODID + ".config.abandoned_camp_spawn_chance")
                .defineInRange("abandonedCampSpawnChance", 0.001, Double.MIN_VALUE, 1.0);
        abandonedCampAllowedBiomes = builder
                .comment("Abandoned camp allowed biomes")
                .translation(Reference.MODID + ".config.abandoned_camp_allowed_biomes")
                .defineList("abandonedCampAllowedBiomes", Config.DEFAULT_STRUCTURE_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        burialPlaceSpawnChance = builder
                .comment("Burial place spawn chance")
                .translation(Reference.MODID + ".config.burial_place_spawn_chance")
                .defineInRange("burialPlaceSpawnChance", 0.001, Double.MIN_VALUE, 1.0);
        burialPlaceAllowedBiomes = builder
                .comment("Burial place allowed biomes")
                .translation(Reference.MODID + ".config.burial_place_allowed_biomes")
                .defineList("burialPlaceAllowedBiomes", Config.DEFAULT_STRUCTURE_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();

        builder.push("mob spawning");
        builder.push("deer");
        spawnDeerEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_deer_enable")
                .define("spawnDeerEnable", true);
        spawnDeerWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_deer_weight")
                .defineInRange("spawnDeerWeight", 20, 0, 100);
        spawnDeerMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_deer_min_count")
                .defineInRange("spawnDeerMinCount", 4, 1, 100);
        spawnDeerMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_deer_max_count")
                .defineInRange("spawnDeerMaxCount", 10, 1, 100);
        spawnDeerAllowedBiomesBlacklist = builder
                .comment("If spawnDeerAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_deer_allowed_biomes_blacklist")
                .define("spawnDeerAllowedBiomesBlacklist", false);
        spawnDeerAllowedBiomes = builder
                .comment("Spawn deer in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_deer_allowed_biomes")
                .defineList("spawnDeerAllowedBiomes", Config.DEFAULT_DEER_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();
        builder.push("boar");
        spawnBoarEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_boar_enable")
                .define("spawnBoarEnable", true);
        spawnBoarWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_boar_weight")
                .defineInRange("spawnBoarWeight", 10, 0, 100);
        spawnBoarMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_boar_min_count")
                .defineInRange("spawnBoarMinCount", 4, 1, 100);
        spawnBoarMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_boar_max_count")
                .defineInRange("spawnBoarMaxCount", 6, 1, 100);
        spawnBoarAllowedBiomesBlacklist = builder
                .comment("If spawnBoarAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_boar_allowed_biomes_blacklist")
                .define("spawnBoarAllowedBiomesBlacklist", false);
        boarBreedingResult = builder
                .comment("Result of breeding after generations in captivity")
                .translation(Reference.MODID + ".config.boar_breeding_result")
                .define("boarBreedingResult", Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(EntityType.PIG)).toString());
        spawnBoarAllowedBiomes = builder
                .comment("Spawn boar in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_boar_allowed_biomes")
                .defineList("spawnBoarAllowedBiomes", Config.DEFAULT_BOAR_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();
        builder.push("auroch");
        spawnAurochEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_auroch_enable")
                .define("spawnAurochEnable", true);
        spawnAurochWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_auroch_weight")
                .defineInRange("spawnAurochWeight", 10, 0, 100);
        spawnAurochMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_auroch_min_count")
                .defineInRange("spawnAurochMinCount", 4, 1, 100);
        spawnAurochMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_auroch_max_count")
                .defineInRange("spawnAurochMaxCount", 8, 1, 100);
        spawnAurochAllowedBiomesBlacklist = builder
                .comment("If spawnAurochAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_auroch_allowed_biomes_blacklist")
                .define("spawnAurochAllowedBiomesBlacklist", false);
        aurochBreedingResult = builder
                .comment("Result of breeding after generations in captivity")
                .translation(Reference.MODID + ".config.auroch_breeding_result")
                .define("aurochBreedingResult", Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(EntityType.COW)).toString());
        spawnAurochAllowedBiomes = builder
                .comment("Spawn auroch in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_auroch_allowed_biomes")
                .defineList("spawnAurochAllowedBiomes", Config.DEFAULT_AUROCH_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();
        builder.push("fowl");
        spawnFowlEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_fowl_enable")
                .define("spawnFowlEnable", true);
        spawnFowlWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_fowl_weight")
                .defineInRange("spawnFowlWeight", 10, 0, 100);
        spawnFowlMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_fowl_min_count")
                .defineInRange("spawnFowlMinCount", 6, 1, 100);
        spawnFowlMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_fowl_max_count")
                .defineInRange("spawnFowlMaxCount", 8, 1, 100);
        spawnFowlAllowedBiomesBlacklist = builder
                .comment("If spawnFowlAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_fowl_allowed_biomes_blacklist")
                .define("spawnFowlAllowedBiomesBlacklist", false);
        fowlBreedingResult = builder
                .comment("Result of breeding after generations in captivity")
                .translation(Reference.MODID + ".config.fowl_breeding_result")
                .define("fowlBreedingResult", Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(EntityType.CHICKEN)).toString());
        spawnFowlAllowedBiomes = builder
                .comment("Spawn fowl in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_fowl_allowed_biomes")
                .defineList("spawnFowlAllowedBiomes", Config.DEFAULT_FOWL_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();
        builder.push("mouflon");
        spawnMouflonEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_mouflon_enable")
                .define("spawnMouflonEnable", true);
        spawnMouflonWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_mouflon_weight")
                .defineInRange("spawnMouflonWeight", 10, 0, 100);
        spawnMouflonMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_mouflon_min_count")
                .defineInRange("spawnMouflonMinCount", 4, 1, 100);
        spawnMouflonMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_mouflon_max_count")
                .defineInRange("spawnMouflonMaxCount", 8, 1, 100);
        spawnMouflonAllowedBiomesBlacklist = builder
                .comment("If spawnMouflonAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_mouflon_allowed_biomes_blacklist")
                .define("spawnMouflonAllowedBiomesBlacklist", false);
        mouflonBreedingResult = builder
                .comment("Result of breeding after generations in captivity")
                .translation(Reference.MODID + ".config.mouflon_breeding_result")
                .define("mouflonBreedingResult", Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(EntityType.SHEEP)).toString());
        spawnMouflonAllowedBiomes = builder
                .comment("Spawn mouflon in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_mouflon_allowed_biomes")
                .defineList("spawnMouflonAllowedBiomes", Config.DEFAULT_MOUFLON_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();
        builder.push("mammoth");
        spawnMammothEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_saberToothTiger_enable")
                .define("spawnMammothEnable", true);
        spawnMammothWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_mammoth_weight")
                .defineInRange("spawnMammothWeight", 10, 0, 100);
        spawnMammothMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_mammoth_min_count")
                .defineInRange("spawnMammothMinCount", 2, 1, 100);
        spawnMammothMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_mammoth_max_count")
                .defineInRange("spawnMammothMaxCount", 4, 1, 100);
        spawnMammothAllowedBiomesBlacklist = builder
                .comment("If spawnMammothAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_mammoth_allowed_biomes_blacklist")
                .define("spawnMammothAllowedBiomesBlacklist", false);
        spawnMammothAllowedBiomes = builder
                .comment("Spawn mammoth in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_mammoth_allowed_biomes")
                .defineList("spawnMammothAllowedBiomes", Config.DEFAULT_MAMMOTH_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();
        builder.push("saber tooth tiger");
        spawnSaberToothTigerEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_saber_tooth_tiger_enable")
                .define("spawnSaberToothTigerEnable", true);
        spawnSaberToothTigerWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_saber_tooth_tiger_weight")
                .defineInRange("spawnSaberToothTigerWeight", 5, 0, 100);
        spawnSaberToothTigerMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_saber_tooth_tiger_min_count")
                .defineInRange("spawnSaberToothTigerMinCount", 1, 1, 100);
        spawnSaberToothTigerMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_saber_tooth_tiger_max_count")
                .defineInRange("spawnSaberToothTigerMaxCount", 2, 1, 100);
        spawnSaberToothTigerAllowedBiomesBlacklist = builder
                .comment("If spawnSaberToothTigerAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_saber_tooth_tiger_allowed_biomes_blacklist")
                .define("spawnSaberToothTigerAllowedBiomesBlacklist", false);
        spawnSaberToothTigerAllowedBiomes = builder
                .comment("Spawn saber-tooth tiger in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_saber_tooth_tiger_allowed_biomes")
                .defineList("spawnSaberToothTigerAllowedBiomes", Config.DEFAULT_TIGER_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();
        builder.push("woolly rhino");
        spawnWoollyRhinoEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_woolly_rhino_enable")
                .define("spawnWoollyRhinoEnable", true);
        spawnWoollyRhinoWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_woolly_rhino_weight")
                .defineInRange("spawnWoollyRhinoWeight", 10, 0, 100);
        spawnWoollyRhinoMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_woolly_rhino_min_count")
                .defineInRange("spawnWoollyRhinoMinCount", 2, 1, 100);
        spawnWoollyRhinoMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_woolly_rhino_max_count")
                .defineInRange("spawnWoollyRhinoMaxCount", 6, 1, 100);
        spawnWoollyRhinoAllowedBiomesBlacklist = builder
                .comment("If spawnWoollyRhinoAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_woolly_rhino_allowed_biomes_blacklist")
                .define("spawnWoollyRhinoAllowedBiomesBlacklist", false);
        spawnWoollyRhinoAllowedBiomes = builder
                .comment("Spawn woolly rhino in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_woolly_rhino_allowed_biomes")
                .defineList("spawnWoollyRhinoAllowedBiomes", Config.DEFAULT_RHINO_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();
        builder.push("terror bird");
        spawnTerrorBirdEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_terror_bird_enable")
                .define("spawnTerrorBirdEnable", true);
        spawnTerrorBirdWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_terror_bird_weight")
                .defineInRange("spawnTerrorBirdWeight", 5, 0, 100);
        spawnTerrorBirdMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_terror_bird_min_count")
                .defineInRange("spawnTerrorBirdMinCount", 1, 1, 100);
        spawnTerrorBirdMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_terror_bird_max_count")
                .defineInRange("spawnTerrorBirdMaxCount", 3, 1, 100);
        spawnTerrorBirdAllowedBiomesBlacklist = builder
                .comment("If spawnTerrorBirdAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_terror_bird_allowed_biomes_blacklist")
                .define("spawnTerrorBirdAllowedBiomesBlacklist", false);
        spawnTerrorBirdAllowedBiomes = builder
                .comment("Spawn terror bird in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_terror_bird_allowed_biomes")
                .defineList("spawnTerrorBirdAllowedBiomes", Config.DEFAULT_TERROR_BIRD_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();
        builder.push("coelacanth");
        spawnCoelacanthEnable = builder
                .comment("Enable spawning of mob")
                .translation(Reference.MODID + ".config.spawn_coelacanth_enable")
                .define("spawnCoelacanthEnable", true);
        spawnCoelacanthWeight = builder
                .comment("Spawn weight of mob")
                .translation(Reference.MODID + ".config.spawn_coelacanth_weight")
                .defineInRange("spawnCoelacanthWeight", 5, 0, 100);
        spawnCoelacanthMinCount = builder
                .comment("Spawn min count of mob")
                .translation(Reference.MODID + ".config.spawn_coelacanth_min_count")
                .defineInRange("spawnCoelacanthMinCount", 1, 1, 100);
        spawnCoelacanthMaxCount = builder
                .comment("Spawn max count of mob")
                .translation(Reference.MODID + ".config.spawn_coelacanth_max_count")
                .defineInRange("spawnCoelacanthMaxCount", 3, 1, 100);
        spawnCoelacanthAllowedBiomesBlacklist = builder
                .comment("If spawnCoelacanthAllowedBiomes is blacklist")
                .translation(Reference.MODID + ".config.spawn_coelacanth_allowed_biomes_blacklist")
                .define("spawnCoelacanthAllowedBiomesBlacklist", false);
        spawnCoelacanthAllowedBiomes = builder
                .comment("Spawn coelacanth in allowed biomes")
                .translation(Reference.MODID + ".config.spawn_coelacanth_allowed_biomes")
                .defineList("spawnCoelacanthAllowedBiomes", Config.DEFAULT_COELACANTH_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();
        builder.pop();
        builder.push("aqueduct");
        infinityWaterSourceBiomeList = builder
                .comment("List of biomes where aqueduct doesn't remove water source")
                .translation(Reference.MODID + ".config.infinity_water_source_biome_list")
                .defineList("infinityWaterSourceBiomeList", Config.DEFAULT_INFINITY_WATER_SOURCE_BIOMES.stream()
                                .map(value -> Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(value)).toString()).collect(Collectors.toList()),
                        string -> string instanceof String && ForgeRegistries.BIOMES.containsKey(new ResourceLocation((String) string)));
        builder.pop();

        builder.push("Config");
        GrassBedSleep = builder.comment("If grass bed can sleep").define("sleep", true);
        DryingRackNeedDaytime = builder.comment("If Drying rack need daytime").define("need daytime", true);
        MakeFire = builder.define("Make fire", true);
        LitTorche = builder.define("Lit torche", true);
        builder.pop();
    }
}