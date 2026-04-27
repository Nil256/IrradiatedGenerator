package io.github.nil256.irradiated_generator.block_entity;

import io.github.nil256.irradiated_generator.block.RadiationGenerator;
import io.github.nil256.irradiated_generator.menu.RadiationGeneratorMenu;
import io.github.nil256.irradiated_generator.registry.ModBlockEntityTypeRegistry;
import mekanism.api.Action;
import mekanism.api.energy.IStrictEnergyHandler;
import mekanism.common.capabilities.Capabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class RadiationGeneratorBE extends BlockEntity implements MenuProvider {
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

    /*
    public final RadiationEnergy energyStorage = new RadiationEnergy(this);
    private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> energyStorage);
    */

    public final RadiationStrictEnergy radiationStrictEnergy = new RadiationStrictEnergy(this);
    private final LazyOptional<IStrictEnergyHandler> radiationStrictEnergyHandler = LazyOptional.of(() -> radiationStrictEnergy);

    public RadiationGeneratorBE(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypeRegistry.RADIATION_GENERATOR.get(), pos, state);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (getBlockState().getValue(RadiationGenerator.FACING) != side){
            return super.getCapability(cap, side);
        }
        if (cap == Capabilities.STRICT_ENERGY){
            return radiationStrictEnergyHandler.cast();
        }
        /*
        else if (cap == ForgeCapabilities.ENERGY){
            return energyHandler.cast();
        }
        */
        return super.getCapability(cap, side);
    }

    public void tick(){
        Direction dir = getBlockState().getValue(RadiationGenerator.FACING);
        BlockEntity be = Objects.requireNonNull(level).getBlockEntity(getBlockPos().relative(dir));
        if (be == null){
            return;
        }
        // LazyOptional<IEnergyStorage> cap = be.getCapability(ForgeCapabilities.ENERGY, dir.getOpposite());
        LazyOptional<IStrictEnergyHandler> cap = be.getCapability(Capabilities.STRICT_ENERGY, dir.getOpposite());
        cap.ifPresent(handler -> {
            /*
            if (handler.canReceive()){
                handler.receiveEnergy(energyStorage.getEnergyStored(), false);
            }
            */
            handler.insertEnergy(radiationStrictEnergy.getEnergy(0), Action.EXECUTE);
        });
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        // energyHandler.invalidate();
        radiationStrictEnergyHandler.invalidate();
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        // energyHandler.invalidate();
        radiationStrictEnergyHandler.invalidate();
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
