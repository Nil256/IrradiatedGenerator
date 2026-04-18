package io.github.nil256.irradiated_generator.registry;

import io.github.nil256.IrradiatedGenerator;
import io.github.nil256.irradiated_generator.screen.RadiationGeneratorScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = IrradiatedGenerator.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModScreenRegistry {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event){
        MenuScreens.register(ModMenuRegistry.RADIATION_GENERATOR_MENU.get(), RadiationGeneratorScreen::new);
    }
}
