package io.github.nil256.irradiated_generator.item;

import io.github.nil256.irradiated_generator.registry.ModBlockRegistry;
import net.minecraft.world.item.BlockItem;

public class RadiationGenerator extends BlockItem {
    public RadiationGenerator() {
        super(ModBlockRegistry.RADIATION_GENERATOR.get(), new Properties());
    }
}
