package com.yanny.age.stone.entities;

import com.yanny.age.stone.compatibility.top.TopEntityInfoProvider;
import com.yanny.age.stone.config.Config;
import com.yanny.age.stone.subscribers.EntitySubscriber;
import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BoarEntity extends WildAnimalEntity implements TopEntityInfoProvider {

    public BoarEntity(EntityType<BoarEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(@Nonnull AgeableEntity ageable) {
        if (Math.min(dataManager.get(GENERATION), ageable.getDataManager().get(GENERATION)) >= Config.domesticateAfterGenerations) {
            EntityType<?> child = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(Config.boarBreedingResult));

            if (child != null) {
                Entity result = child.create(world);

                if (result instanceof AgeableEntity) {
                    return (AgeableEntity) child.create(world);
                } else {
                    LOGGER.warn("'{}' is not instance of Ageable entity! Spawning default PIG entity", Config.boarBreedingResult);
                }
            } else {
                LOGGER.warn("'{}' does not exists! Spawning default PIG entity", Config.boarBreedingResult);
            }

            return EntityType.PIG.create(world);
        } else {
            BoarEntity entity = EntitySubscriber.boar.create(world);

            if (entity != null) {
                entity.setGeneration(dataManager.get(GENERATION) + 1);
            }

            return entity;
        }
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.fromItems(Items.CARROT), false));
        this.goalSelector.addGoal(5, new RaidFarmGoal<>(this, CropsBlock.class, CropsBlock.AGE));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new AgroTargetGoal(this, BoarEntity.class));
        this.targetSelector.addGoal(2, new TargetAggressorGoal<>(this, BoarEntity.class));
    }

    @Override
    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        this.playSound(SoundEvents.ENTITY_PIG_HURT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 4.0F);
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_PIG_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PIG_DEATH;
    }

    @Override
    public void playStepSound(@Nonnull BlockPos pos, @Nonnull BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.CARROT;
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, Entity entity, IProbeHitEntityData data) {
        probeInfo.horizontal().text("Generation: " + dataManager.get(GENERATION));
    }
}
