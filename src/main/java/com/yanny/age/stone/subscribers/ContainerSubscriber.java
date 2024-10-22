package com.yanny.age.stone.subscribers;

import com.yanny.age.stone.blocks.FeederContainer;
import com.yanny.age.stone.blocks.FishingNetContainer;
import com.yanny.age.stone.blocks.MillstoneContainer;
import com.yanny.age.stone.blocks.StoneChestContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

import static com.yanny.age.stone.Reference.MODID;

@ObjectHolder(MODID)
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerSubscriber {
    public static final MenuType<StoneChestContainer> stone_chest = IForgeContainerType.create(StoneChestContainer::new);
    public static final MenuType<FeederContainer> feeder = IForgeContainerType.create(FeederContainer::new);
    public static final MenuType<MillstoneContainer> millstone = IForgeContainerType.create(MillstoneContainer::new);
    public static final MenuType<FishingNetContainer> fishing_net = IForgeContainerType.create(FishingNetContainer::new);

    @SubscribeEvent
    public static void registerContainer(@Nonnull RegistryEvent.Register<MenuType<?>> event) {
        IForgeRegistry<MenuType<?>> registry = event.getRegistry();
        registry.register(stone_chest.setRegistryName(MODID, "stone_chest"));
        registry.register(feeder.setRegistryName(MODID, "feeder"));
        registry.register(millstone.setRegistryName(MODID, "millstone"));
        registry.register(fishing_net.setRegistryName(MODID, "fishing_net"));
    }
}