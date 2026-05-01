package io.github.nil256.irradiated_generator.block_entity;

import net.minecraftforge.energy.IEnergyStorage;

public class RadiationEnergy implements IEnergyStorage {
    private final RadiationGeneratorBE blockEntity;

    public RadiationEnergy(RadiationGeneratorBE be){
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
        return blockEntity.GetCurrentEnergy().intValue();
    }

    @Override
    public int getMaxEnergyStored() {
        return 2147483647;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int energy = getEnergyStored();
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
