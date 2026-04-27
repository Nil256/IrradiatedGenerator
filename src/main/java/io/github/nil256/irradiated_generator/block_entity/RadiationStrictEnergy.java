package io.github.nil256.irradiated_generator.block_entity;

import io.github.nil256.Config;
import mekanism.api.Action;
import mekanism.api.Coord4D;
import mekanism.api.energy.IStrictEnergyHandler;
import mekanism.api.math.FloatingLong;
import mekanism.api.radiation.IRadiationManager;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class RadiationStrictEnergy implements IStrictEnergyHandler {
    private final BlockEntity blockEntity;

    public RadiationStrictEnergy(BlockEntity be){
        blockEntity = be;
    }

    private @NotNull FloatingLong GetCurrentEnergy(){
        double radiation = IRadiationManager.INSTANCE.getRadiationLevel(new Coord4D(blockEntity));;
        return FloatingLong.create(radiation * Config.conversionMultiplier);
    }

    @Override
    public int getEnergyContainerCount() {
        return 1;
    }

    @Override
    public @NotNull FloatingLong getEnergy(int i) {
        return GetCurrentEnergy();
    }

    @Override
    public void setEnergy(int i, @NotNull FloatingLong floatingLong) {}

    @Override
    public @NotNull FloatingLong getMaxEnergy(int i) {
        return FloatingLong.create(999999999999999999L);
    }

    @Override
    public @NotNull FloatingLong getNeededEnergy(int i) {
        return FloatingLong.create(0);
    }

    @Override
    public @NotNull FloatingLong insertEnergy(int i, @NotNull FloatingLong floatingLong, @NotNull Action action) {
        return FloatingLong.create(0);
    }

    @Override
    public @NotNull FloatingLong extractEnergy(int i, @NotNull FloatingLong floatingLong, @NotNull Action action) {
        return GetCurrentEnergy();
    }
}
