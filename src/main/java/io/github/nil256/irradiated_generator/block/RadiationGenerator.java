package io.github.nil256.irradiated_generator.block;

import io.github.nil256.irradiated_generator.block_entity.RadiationGeneratorBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RadiationGenerator extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public RadiationGenerator() {
        super(Properties.of().strength(3.5f, 100f).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY));
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new RadiationGeneratorBE(pos, state);
    }

    /*
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> be) {
        if (level.isClientSide()){
            return null;
        }
        return (l, p, s, blockEntity) ->{
            if (blockEntity instanceof RadiationGeneratorBE gbe){
                gbe.tick();
            }
        };
    }
    */

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos position, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        if (level.isClientSide()){
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        BlockEntity be = level.getBlockEntity(position);
        if (be instanceof RadiationGeneratorBE blcokEntity){
            NetworkHooks.openScreen((ServerPlayer)player, blcokEntity, position);
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}
