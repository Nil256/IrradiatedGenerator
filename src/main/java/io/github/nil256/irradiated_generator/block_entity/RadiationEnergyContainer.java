package io.github.nil256.irradiated_generator.block_entity;

import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.math.FloatingLong;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

public class RadiationEnergyContainer implements IEnergyContainer {
    private final RadiationGeneratorBE blockEntity;

    public RadiationEnergyContainer(RadiationGeneratorBE be){
        blockEntity = be;
    }

    @Override
    public @NotNull FloatingLong getEnergy() {
        return blockEntity.GetCurrentEnergy();
    }

    @Override
    public @NotNull FloatingLong getNeeded() {
        return FloatingLong.ZERO;
    }

    @Override
    public @NotNull FloatingLong getMaxEnergy() {
        return RadiationEnergyUtil.GetMaxEnergy();
    }

    @Override
    public @NotNull FloatingLong extract(@NotNull FloatingLong amount, @NotNull Action action, @NotNull AutomationType automationType) {
        return getEnergy();
    }

    @Override
    public void setEnergy(@NotNull FloatingLong floatingLong) { }

    @Override
    public @NotNull FloatingLong insert(@NotNull FloatingLong amount, Action action, AutomationType automationType) {
        return FloatingLong.ZERO;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void setEmpty() { }

    @Override
    public void onContentsChanged() {
    }

    @Override
    public @NotNull CompoundTag serializeNBT() {
        return IEnergyContainer.super.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
    }
}
