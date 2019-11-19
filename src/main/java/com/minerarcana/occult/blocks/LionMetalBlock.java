package com.minerarcana.occult.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static com.minerarcana.occult.util.lib.OccultTagLib.Blocks.LIONMETALFOOD;

public class LionMetalBlock extends Block {

    private BlockPos firePos;
    private boolean isHungry;
    private int fireConsumed = 0;

    public LionMetalBlock(Properties properties) {
        super(properties.lightValue(0));
    }

    @Override
    public void tick(BlockState state, World world, BlockPos pos, Random random) {
        lionMetalFireManager(world,pos,);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(!this.isHungry()) {
            tooltip.add(new TranslationTextComponent("Satiated", 2));
        }
        else {
            tooltip.add(new TranslationTextComponent("Hungry", 2));
        }
    }

    private boolean isHungry(){
        return fireConsumed < 20;
    }

    public void lionMetalFireManager(World world, BlockPos pos, Direction direction){
        if(!this.isHungry){
            this.getLightValue() = 20;
            for(int x = -3; x < 3; ++x){
                for(int y = -3; y < 3; ++y){
                    for(int z = -3; z < 3; ++z){
                        BlockPos targetPos = pos.add(x,y,z);
                        BlockState targetBlock = world.getBlockState(targetPos);
                        BlockPos firePos = firePos(world, targetPos);
                        if(targetBlock.isFlammable(world, targetPos, direction)&& firePos != null){
                            world.setBlockState(firePos, Blocks.FIRE.getDefaultState());
                        }
                    }
                }
            }
        }else{
            this.lightLevel = 0;
            for(int x = -3; x < 3; ++x){
                for(int y = -3; y < 3; ++y){
                    for(int z = -3; z < 3; ++z){
                        BlockPos targetPos = pos.add(x,y,z);
                        BlockState targetBlock = world.getBlockState(targetPos);
                            if(targetBlock.isIn(LIONMETALFOOD)){
                                world.setBlockState(targetPos, Blocks.AIR.getDefaultState());
                                fireConsumed++;
                        }
                    }
                }
            }
        }
    }

    private BlockPos firePos(World world, BlockPos pos){
        BlockPos up = pos.up();
        BlockPos down = pos.up();
        BlockPos east = pos.up();
        BlockPos west = pos.up();
        BlockPos north = pos.up();
        BlockPos south = pos.up();
        if(world.getBlockState(up).isAir()){
            firePos = up;
        }
        else if(world.getBlockState(down).isAir()){
            firePos = down;
        }
        else if(world.getBlockState(east).isAir()){
            firePos = east;
        }
        else if(world.getBlockState(west).isAir()){
            firePos = west;
        }
        else if(world.getBlockState(north).isAir()){
            firePos = north;
        }
        else if(world.getBlockState(south).isAir()){
            firePos = south;
        }
        return firePos;
    }

}
