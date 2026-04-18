package io.github.nil256.irradiated_generator.block_entity;

import io.github.nil256.Config;
import mekanism.api.Coord4D;
import mekanism.api.radiation.IRadiationManager;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.energy.IEnergyStorage;

public class RadiationEnergy implements IEnergyStorage {
    private final BlockEntity blockEntity;

    public RadiationEnergy(BlockEntity be){
        blockEntity = be;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

    @Override
    public int getEnergyStored() {
        double radiation = IRadiationManager.INSTANCE.getRadiationLevel(new Coord4D(blockEntity));;
        int energy = (int)(radiation * Config.conversionMultiplier);
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return 2147483647;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        double radiation = IRadiationManager.INSTANCE.getRadiationLevel(new Coord4D(blockEntity));;
        int energy = (int)(radiation * Config.conversionMultiplier);
        if (energy > maxExtract){
            energy = maxExtract;
        }
        return energy;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }
}
