package io.github.nil256.irradiated_generator.registry;

import io.github.nil256.IrradiatedGenerator;
import io.github.nil256.irradiated_generator.block.RadiationGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockRegistry {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IrradiatedGenerator.MODID);

    public static final RegistryObject<Block> RADIATION_GENERATOR = BLOCKS.register("radiation_generator", RadiationGenerator::new);

    public static void Register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
