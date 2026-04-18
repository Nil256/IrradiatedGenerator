package io.github.nil256.irradiated_generator.registry;

import io.github.nil256.IrradiatedGenerator;
import io.github.nil256.irradiated_generator.block_entity.RadiationGeneratorBE;
import io.github.nil256.irradiated_generator.menu.RadiationGeneratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuRegistry {
    private static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, IrradiatedGenerator.MODID);

    public static final RegistryObject<MenuType<RadiationGeneratorMenu>> RADIATION_GENERATOR_MENU = MENU_TYPES.register("radiation_generator", ()
        -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos position = data.readBlockPos();
                Level level = inv.player.level();
                BlockEntity be = level.getBlockEntity(position);
                if (be instanceof RadiationGeneratorBE blockEntity){
                    return new RadiationGeneratorMenu(windowId, inv, blockEntity);
                }
                return null;
            })
    );

    public static void Register(IEventBus eventBus){
        MENU_TYPES.register(eventBus);
    }
}
