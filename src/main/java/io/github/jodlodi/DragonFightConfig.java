package io.github.jodlodi;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DragonFightConfig.MOD_ID)
@Mod.EventBusSubscriber(modid = DragonFightConfig.MOD_ID)
public class DragonFightConfig
{
    public static final String MOD_ID = "dragonfightconfig";

    public DragonFightConfig() {
        IEventBus BUS = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        BUS.addListener(this::configSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void configSetup(final FMLCommonSetupEvent event) {
        Config.refresh();
    }
}