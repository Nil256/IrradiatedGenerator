package io.github.nil256.irradiated_generator.block_entity;

import io.github.nil256.Config;
import mekanism.api.Coord4D;
import mekanism.api.math.FloatingLong;
import mekanism.api.radiation.IRadiationManager;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class RadiationEnergyUtil {
    public static @NotNull FloatingLong GetCurrentEnergy(BlockEntity blockEntity){
        double radiation = IRadiationManager.INSTANCE.getRadiationLevel(new Coord4D(blockEntity));
        return FloatingLong.create(radiation * Config.conversionMultiplier);
    }

    public static @NotNull FloatingLong GetMaxEnergy(){
        return FloatingLong.create(999999999999999999L);
    }
}
