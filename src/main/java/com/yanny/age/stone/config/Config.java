package com.yanny.age.stone.config;

import com.google.common.collect.Sets;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static net.minecraft.world.level.biome.Biomes.*;
import static net.minecraft.world.level.levelgen.structure.structures.MineshaftStructure.Type.MESA;
import static net.minecraftforge.common.BiomeManager.BiomeType.ICY;

public class Config {
    static final Set<Biome> DEFAULT_DEER_BIOMES;
    static final Set<Biome> DEFAULT_BOAR_BIOMES;
    static final Set<Biome> DEFAULT_AUROCH_BIOMES;
    static final Set<Biome> DEFAULT_FOWL_BIOMES;
    static final Set<Biome> DEFAULT_MOUFLON_BIOMES;
    static final Set<Biome> DEFAULT_MAMMOTH_BIOMES;
    static final Set<Biome> DEFAULT_TIGER_BIOMES;
    static final Set<Biome> DEFAULT_RHINO_BIOMES;
    static final Set<Biome> DEFAULT_TERROR_BIRD_BIOMES;
    static final Set<Biome> DEFAULT_STRUCTURE_BIOMES;
    static final Set<Biome> DEFAULT_COELACANTH_BIOMES;
    static final Set<Biome> DEFAULT_INFINITY_WATER_SOURCE_BIOMES;
    static final Set<Block> DEFAULT_DISABLED_USE_BLOCKS;
//
    static {
        DEFAULT_DEER_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(FOREST, PLAINS, TAIGA, WINDSWEPT_HILLS, SAVANNA, BEACH, SWAMP, JUNGLE, MESA, ICY)
                .contains(biome)).collect(Collectors.toSet());
        DEFAULT_BOAR_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(FOREST, PLAINS, TAIGA, WINDSWEPT_HILLS, SAVANNA, SWAMP, JUNGLE)
                .contains(biome)).collect(Collectors.toSet());
        DEFAULT_AUROCH_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(FOREST, PLAINS, TAIGA, WINDSWEPT_HILLS, SAVANNA, BEACH)
                .contains(biome)).collect(Collectors.toSet());
        DEFAULT_FOWL_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(FOREST, PLAINS, TAIGA, WINDSWEPT_HILLS, SAVANNA, SWAMP, JUNGLE, BEACH, MESA)
                .contains(biome)).collect(Collectors.toSet());
        DEFAULT_MOUFLON_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(FOREST, PLAINS, TAIGA, WINDSWEPT_HILLS, SWAMP, MESA)
                .contains(biome)).collect(Collectors.toSet());
        DEFAULT_MAMMOTH_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(PLAINS, SAVANNA, ICY, TAIGA, WINDSWEPT_HILLS, DESERT, SAVANNA)
                .contains(biome)).collect(Collectors.toSet());
        DEFAULT_TIGER_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(PLAINS, SAVANNA, ICY, TAIGA, DESERT, FOREST, BEACH, JUNGLE, MUSHROOM_FIELDS)
                .contains(biome)).collect(Collectors.toSet());
        DEFAULT_RHINO_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(PLAINS, SAVANNA, ICY, TAIGA, BEACH, SAVANNA, MUSHROOM_FIELDS, RIVER, SWAMP)
                .contains(biome)).collect(Collectors.toSet());
        DEFAULT_TERROR_BIRD_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(PLAINS, SAVANNA, FOREST, JUNGLE, MUSHROOM_FIELDS)
                .contains(biome)).collect(Collectors.toSet());
        DEFAULT_STRUCTURE_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> !Set.of(OCEAN, RIVER, THE_END, NETHER_WASTES)
                .contains(biome)).collect(Collectors.toSet());
        DEFAULT_COELACANTH_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> !Set.of(OCEAN, RIVER, SWAMP)
                .contains(biome)).collect(Collectors.toSet());
        DEFAULT_INFINITY_WATER_SOURCE_BIOMES = ForgeRegistries.BIOMES.getValues().stream().filter(biome -> Set.of(OCEAN, RIVER, SWAMP)
                .contains(biome)).collect(Collectors.toSet());
        DEFAULT_DISABLED_USE_BLOCKS = Sets.newHashSet(Blocks.CRAFTING_TABLE);
    }

    public static boolean removeVanillaRecipes = true;
    public static boolean removeVanillaGeneratedAnimals = true;
    public static int domesticateAfterGenerations = 3;
    public static double tanningRackFinishChance = 0.1;
    public static int feederTickChanceBreedAnimalEffect = 200;
    public static int feederEffectRange = 4;
    public static boolean forceToolForWood = true;
    public static boolean givePlayerManualOnFirstConnect = true;
    public static int fishingNetChance = 3600;
    public static boolean disableVanillaCraftingTable = true;
    public static final Set<Block> disabledUseInStoneAgeList = DEFAULT_DISABLED_USE_BLOCKS;

    public static double abandonedCampSpawnChance = 0.001f;
    public static double burialPlaceSpawnChance = 0.001f;
    public static final Set<Biome> abandonedCampAllowedBiomes = DEFAULT_STRUCTURE_BIOMES;
    public static final Set<Biome> burialPlaceAllowedBiomes = DEFAULT_STRUCTURE_BIOMES;

    public static boolean spawnDeerEnable = true;
    public static int spawnDeerWeight = 20;
    public static int spawnDeerMinCount = 4;
    public static int spawnDeerMaxCount = 10;
    public static boolean spawnDeerAllowedBiomesBlacklist = false;
    public static final Set<Biome> spawnDeerAllowedBiomes = DEFAULT_DEER_BIOMES;

    public static boolean spawnBoarEnable = true;
    public static int spawnBoarWeight = 10;
    public static int spawnBoarMinCount = 4;
    public static int spawnBoarMaxCount = 6;
    public static boolean spawnBoarAllowedBiomesBlacklist = false;
    public static final Set<Biome> spawnBoarAllowedBiomes = DEFAULT_BOAR_BIOMES;
    public static String boarBreedingResult = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(EntityType.PIG)).toString();

    public static boolean spawnAurochEnable = true;
    public static int spawnAurochWeight = 10;
    public static int spawnAurochMinCount = 4;
    public static int spawnAurochMaxCount = 8;
    public static boolean spawnAurochAllowedBiomesBlacklist = false;
    public static final Set<Biome> spawnAurochAllowedBiomes = DEFAULT_AUROCH_BIOMES;
    public static String aurochBreedingResult = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(EntityType.COW)).toString();

    public static boolean spawnFowlEnable = true;
    public static int spawnFowlWeight = 10;
    public static int spawnFowlMinCount = 6;
    public static int spawnFowlMaxCount = 8;
    public static boolean spawnFowlAllowedBiomesBlacklist = false;
    public static final Set<Biome> spawnFowlAllowedBiomes = DEFAULT_FOWL_BIOMES;
    public static String fowlBreedingResult = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(EntityType.CHICKEN)).toString();

    public static boolean spawnMouflonEnable = true;
    public static int spawnMouflonWeight = 10;
    public static int spawnMouflonMinCount = 4;
    public static int spawnMouflonMaxCount = 8;
    public static boolean spawnMouflonAllowedBiomesBlacklist = false;
    public static final Set<Biome> spawnMouflonAllowedBiomes = DEFAULT_MOUFLON_BIOMES;
    public static String mouflonBreedingResult = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(EntityType.SHEEP)).toString();

    public static boolean spawnMammothEnable = true;
    public static int spawnMammothWeight = 10;
    public static int spawnMammothMinCount = 2;
    public static int spawnMammothMaxCount = 4;
    public static boolean spawnMammothAllowedBiomesBlacklist = false;
    public static final Set<Biome> spawnMammothAllowedBiomes = DEFAULT_MAMMOTH_BIOMES;

    public static boolean spawnSaberToothTigerEnable = true;
    public static int spawnSaberToothTigerWeight = 5;
    public static int spawnSaberToothTigerMinCount = 1;
    public static int spawnSaberToothTigerMaxCount = 2;
    public static boolean spawnSaberToothTigerAllowedBiomesBlacklist = false;
    public static final Set<Biome> spawnSaberToothTigerAllowedBiomes = DEFAULT_TIGER_BIOMES;

    public static boolean spawnWoollyRhinoEnable = true;
    public static int spawnWoollyRhinoWeight = 10;
    public static int spawnWoollyRhinoMinCount = 2;
    public static int spawnWoollyRhinoMaxCount = 6;
    public static boolean spawnWoollyRhinoAllowedBiomesBlacklist = false;
    public static final Set<Biome> spawnWoollyRhinoAllowedBiomes = DEFAULT_RHINO_BIOMES;

    public static boolean spawnTerrorBirdEnable = true;
    public static int spawnTerrorBirdWeight = 5;
    public static int spawnTerrorBirdMinCount = 1;
    public static int spawnTerrorBirdMaxCount = 3;
    public static boolean spawnTerrorBirdAllowedBiomesBlacklist = false;
    public static final Set<Biome> spawnTerrorBirdAllowedBiomes = DEFAULT_TERROR_BIRD_BIOMES;

    public static boolean spawnCoelacanthEnable = true;
    public static int spawnCoelacanthWeight = 15;
    public static int spawnCoelacanthMinCount = 3;
    public static int spawnCoelacanthMaxCount = 6;
    public static boolean spawnCoelacanthAllowedBiomesBlacklist = false;
    public static final Set<Biome> spawnCoelacanthAllowedBiomes = DEFAULT_COELACANTH_BIOMES;

    public static final Set<Biome> infinityWaterSourceBiomeList = DEFAULT_INFINITY_WATER_SOURCE_BIOMES;

    public static boolean GrassBedSleep = true;
    public static boolean DryingRackNeedDaytime = true;
    public static boolean MakeFire = true;
    public static boolean LitTorche = true;
}
