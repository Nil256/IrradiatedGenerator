package io.github.nil256.irradiated_generator.block_entity;

import io.github.nil256.irradiated_generator.block.RadiationGenerator;
import io.github.nil256.irradiated_generator.menu.RadiationGeneratorMenu;
import io.github.nil256.irradiated_generator.registry.ModBlockEntityTypeRegistry;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.energy.IMekanismStrictEnergyHandler;
import mekanism.api.energy.IStrictEnergyHandler;
import mekanism.api.math.FloatingLong;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.util.CableUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class RadiationGeneratorBE extends BlockEntity implements MenuProvider, IMekanismStrictEnergyHandler {
    /*
    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index){
                case 0 -> energyStorage.getEnergyStored();
                case 1 -> energyStorage.getMaxEnergyStored();
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
        }

        @Override
        public int getCount() {
            return 2;
        }
    };
    */

    public final RadiationEnergy energyStorage = new RadiationEnergy(this);
    private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> energyStorage);

    private final LazyOptional<IStrictEnergyHandler> strictEnergyHandler = LazyOptional.of(() -> this);

    private final RadiationEnergyContainer energyContainer = new RadiationEnergyContainer(this);

    private FloatingLong _currentEnergy;

    public RadiationGeneratorBE(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypeRegistry.RADIATION_GENERATOR.get(), pos, state);
        _currentEnergy = FloatingLong.ZERO;
    }

    public @NotNull FloatingLong GetCurrentEnergy(){
        return FloatingLong.create(_currentEnergy.longValue());
    }

    private boolean isEnergyExtractableDirection(@Nullable Direction direction){
        return direction == getBlockState().getValue(RadiationGenerator.FACING) || direction == Direction.DOWN;
    }

    @Override
    public @NotNull List<IEnergyContainer> getEnergyContainers(@Nullable Direction direction) {
        if (isEnergyExtractableDirection(direction)){
            return List.of(energyContainer);
        }
        return List.of();
    }

    @Override
    public void onContentsChanged() { }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (!isEnergyExtractableDirection(side)){
            return super.getCapability(cap, side);
        }
        if (cap == Capabilities.STRICT_ENERGY){
            return strictEnergyHandler.cast();
        }
        if (cap == ForgeCapabilities.ENERGY){
            return energyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    public void tick(){
        _currentEnergy = RadiationEnergyUtil.GetCurrentEnergy(this, false);
        Set<Direction> directions = EnumSet.of(getBlockState().getValue(RadiationGenerator.FACING), Direction.DOWN);
        CableUtils.emit(directions, energyContainer, this, RadiationEnergyUtil.GetMaxEnergy());
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energyHandler.invalidate();
        strictEnergyHandler.invalidate();
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        energyHandler.invalidate();
        strictEnergyHandler.invalidate();
    }

    private static final Component TITLE = Component.translatable("block.irradiated_generator.radiation_generator");

    @Override
    public @NotNull Component getDisplayName() {
        return TITLE;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInventory, @NotNull Player player) {
        return new RadiationGeneratorMenu(id, playerInventory, this);
    }
}
