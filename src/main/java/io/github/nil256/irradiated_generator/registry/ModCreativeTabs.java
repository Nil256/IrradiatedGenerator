package io.github.nil256.irradiated_generator.registry;

import io.github.nil256.IrradiatedGenerator;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IrradiatedGenerator.MODID);

    public static final RegistryObject<CreativeModeTab> ALL = CREATIVE_TAB.register("irradiated_generator",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.all"))
                    .icon(() -> new ItemStack(ModItemRegistry.RADIATION_GENERATOR.get()))
                    .displayItems((param, output)-> {
                        for (Item item : ModContents.AllItems){
                            output.accept(item);
                        }
                    })
                    .build()
    );

    public static void Register(IEventBus eventBus){
        CREATIVE_TAB.register(eventBus);
    }
}
