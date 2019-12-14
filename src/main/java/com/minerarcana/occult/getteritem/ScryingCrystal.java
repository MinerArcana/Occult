package com.minerarcana.occult;

import com.minerarcana.occult.api.PressureType;
import com.minerarcana.occult.api.pressure.IPressure;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.LazyOptional;

import static com.minerarcana.occult.Occult.LOGGER;
import static com.minerarcana.occult.api.pressure.PressureCap.PRESSURE_STORAGE_CAPABILITY;

public class ScryingCrystal extends Item {

    public ScryingCrystal(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        Block block = world.getBlockState(pos).getBlock();
        pressureCheck(world,pos,block,player);
        return ActionResultType.SUCCESS;
    }

    public void pressureCheck(World world, BlockPos pos, Block block, PlayerEntity player){
        BlockState state = block.getDefaultState();
        if(!world.isRemote) {
            if (block.equals(Blocks.AIR)) {
                final Chunk chunk = world.getChunkAt(new BlockPos(player));
                LazyOptional<?> pressure = chunk.getCapability(PRESSURE_STORAGE_CAPABILITY);
                if(pressure.isPresent()){
                    if(pressure instanceof IPressure){
                        Object2IntMap<PressureType> pressures = ((IPressure) pressure).getAllPressure();
                        if(pressures != null){
                            for(PressureType type : pressures.keySet()){
                                int pressureAmount = pressures.get(type);
                                LOGGER.info("you have" + pressureAmount + "of" + type + "Pressure" );
                            }
                        }
                    }
                }
                else{
                    LOGGER.info("you have 0 Pressure available of any Type" );

                }
            } else if (block.hasTileEntity(state)) {
                TileEntity tileEntity = block.createTileEntity(state, world);
                LazyOptional<?> pressure = tileEntity.getCapability(PRESSURE_STORAGE_CAPABILITY);
                if (pressure.isPresent()) {
                    if(pressure instanceof IPressure){
                        Object2IntMap<PressureType> pressures = ((IPressure) pressure).getAllPressure();
                        if(pressures != null){
                            for(PressureType type : pressures.keySet()){
                                int pressureAmount = pressures.get(type);
                                LOGGER.info("you have" + pressureAmount + "of" + type + "Pressure" );
                            }
                        }
                    }
                }
                else{
                    LOGGER.info("you have 0 Pressure available of any Type" );

                }
            }
        }

    }


}
