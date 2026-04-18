package io.github.nil256.irradiated_generator.registry;

import io.github.nil256.IrradiatedGenerator;
import io.github.nil256.irradiated_generator.item.RadiationGenerator;
import io.github.nil256.irradiated_generator.item.TestItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemRegistry {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IrradiatedGenerator.MODID);

    public static final RegistryObject<Item> TEST = ITEMS.register("test_item", TestItem::new);
    public static final RegistryObject<Item> RADIATION_GENERATOR = ITEMS.register("radiation_generator", RadiationGenerator::new);

    public static void Register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
