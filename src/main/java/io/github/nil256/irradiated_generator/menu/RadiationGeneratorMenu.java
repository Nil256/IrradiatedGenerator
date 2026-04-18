package io.github.nil256.irradiated_generator.menu;

import io.github.nil256.irradiated_generator.block_entity.RadiationGeneratorBE;
import io.github.nil256.irradiated_generator.registry.ModBlockRegistry;
import io.github.nil256.irradiated_generator.registry.ModMenuRegistry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RadiationGeneratorMenu extends AbstractContainerMenu {
    private final RadiationGeneratorBE _blockEntity;
    private final Level _level;

    public RadiationGeneratorMenu(int id, Inventory playerInventory, RadiationGeneratorBE be) {
        super(ModMenuRegistry.RADIATION_GENERATOR_MENU.get(), id);
        _blockEntity = be;
        _level = playerInventory.player.level();
        AddPlayerInventorySlots(playerInventory);
    }

    private void AddPlayerInventorySlots(Inventory playerInventory){
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 9; col++){
                addSlot(new Slot(playerInventory, 9 + row * 9 + col, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++){
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player p_38941_, int p_38942_) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(_level, _blockEntity.getBlockPos()), player, ModBlockRegistry.RADIATION_GENERATOR.get());
    }

    public RadiationGeneratorBE getBlockEntity(){
        return _blockEntity;
    }
}
