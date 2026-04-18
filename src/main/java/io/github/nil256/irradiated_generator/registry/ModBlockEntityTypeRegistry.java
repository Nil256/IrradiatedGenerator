package io.github.nil256.irradiated_generator.registry;

import io.github.nil256.IrradiatedGenerator;
import io.github.nil256.irradiated_generator.block_entity.RadiationGeneratorBE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypeRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IrradiatedGenerator.MODID);

    public static final RegistryObject<BlockEntityType<RadiationGeneratorBE>> RADIATION_GENERATOR = BLOCK_ENTITY_TYPES.register("radiation_generator", () -> BlockEntityType.Builder.of(RadiationGeneratorBE::new, ModBlockRegistry.RADIATION_GENERATOR.get()).build(null));

    public static void Register(IEventBus eventBus){
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
