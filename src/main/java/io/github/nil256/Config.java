package io.github.nil256;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = IrradiatedGenerator.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue CONVERSION_MULTIPLIER = BUILDER
            .comment("Multiplier used in the Generator's conversion Sv to FE\n1 = 1Sv is converted 1FE/t\nint value, 100 by default")
            .defineInRange("svToFEMultiplier", 100, 1, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.BooleanValue REMOVE_UNKNOWN_GAP_BETWEEN_GENERATION_AND_TRANSFER = BUILDER
            .comment("Fix unknown gap between generation and transfer forcibly\ntrue by default")
            .define("removeUnknownGapBetweenGenerationAndTransfer", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int conversionMultiplier;
    public static boolean removeUnknownGapBetweenGenerationAndTransfer;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        conversionMultiplier = CONVERSION_MULTIPLIER.get();
        removeUnknownGapBetweenGenerationAndTransfer = REMOVE_UNKNOWN_GAP_BETWEEN_GENERATION_AND_TRANSFER.get();
    }
}
