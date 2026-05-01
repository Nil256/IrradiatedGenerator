package io.github.nil256.irradiated_generator.block_entity;

import io.github.nil256.Config;
import mekanism.api.Coord4D;
import mekanism.api.math.FloatingLong;
import mekanism.api.radiation.IRadiationManager;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class RadiationEnergyUtil {
    public static double GetCurrentRadiation(BlockEntity blockEntity){
        return IRadiationManager.INSTANCE.getRadiationLevel(new Coord4D(blockEntity));
    }

    public static @NotNull FloatingLong GetCurrentEnergy(BlockEntity blockEntity, boolean simulate){
        double radiation = GetCurrentRadiation(blockEntity);
        double energy = radiation * Config.conversionMultiplier;
        if (!simulate && Config.removeUnknownGapBetweenGenerationAndTransfer){
            energy *= 2.5;
        }
        return FloatingLong.create(energy);
    }

    public static @NotNull FloatingLong GetMaxEnergy(){
        return FloatingLong.create(999999999999999999L);
    }
}
