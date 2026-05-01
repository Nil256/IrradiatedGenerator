package io.github.nil256.irradiated_generator.screen;

import io.github.nil256.IrradiatedGenerator;
import io.github.nil256.irradiated_generator.block_entity.RadiationEnergyUtil;
import io.github.nil256.irradiated_generator.menu.RadiationGeneratorMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class RadiationGeneratorScreen extends AbstractContainerScreen<RadiationGeneratorMenu> {
    @SuppressWarnings("removal")
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(IrradiatedGenerator.MODID, "textures/gui/container/radiation_generator_gui_temp.png");

    public RadiationGeneratorScreen(RadiationGeneratorMenu menu, Inventory playerInventory, Component component) {
        super(menu, playerInventory, component);
        titleLabelX = 8;
        titleLabelY = 4;
        inventoryLabelX = 8;
        inventoryLabelY = imageHeight - 92;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        graphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int w, int h) {
        graphics.drawString(font, title, titleLabelX, titleLabelY, 0x3F3F3F, false);
        graphics.drawString(font, playerInventoryTitle ,inventoryLabelX, inventoryLabelY, 0x3F3F3F, false);
        graphics.drawString(font, GetFEText(),50, 20, 0x00ff00, false);
    }

    private String GetFEText(){
        // int energy = menu.getBlockEntity().energyStorage.getEnergyStored();
        Long energy = RadiationEnergyUtil.GetCurrentEnergy(menu.getBlockEntity()).getValue();
        String result;
        if (energy > 1000000000000000000L){
            double energyForText = energy / 1000000000000000000.0;
            result = String.format("%.2f", energyForText) + "E";
        }
        else if (energy > 1000000000000000L){
            double energyForText = energy / 1000000000000000.0;
            result = String.format("%.2f", energyForText) + "P";
        }
        else if (energy > 1000000000000L){
            double energyForText = energy / 1000000000000.0;
            result = String.format("%.2f", energyForText) + "T";
        }
        else if (energy > 1000000000){
            double energyForText = energy / 1000000000.0;
            result = String.format("%.2f", energyForText) + "G";
        }
        else if (energy > 1000000){
            double energyForText = energy / 1000000.0;
            result = String.format("%.2f", energyForText) + "M";
        }
        else if (energy > 1000){
            double energyForText = energy / 1000.0;
            result = String.format("%.2f", energyForText) + "k";
        }
        else {
            result = String.valueOf(energy);
        }
        result += "FE/t";
        return result;
    }
}
