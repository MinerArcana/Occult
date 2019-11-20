package com.minerarcana.occult.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.minerarcana.occult.util.lib.OccultTagLib.Blocks.SALTABLEGROUND;

public class SaltBlock extends Block {

    private List<Entity> entities = new ArrayList<>();
    private Entity randomEntity = null;

    public SaltBlock(Properties properties) {
        super(properties.tickRandomly());
    }

    @Override
    public void tick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(2000) == 9) {
            spawnAnimals(world, pos, random);
        }
    }

    private void spawnAnimals(World world, BlockPos pos, Random random) {
        int alive = 0;
        if (alive == 0) {
            for (int x = -2; x < 2; ++x) {
                for (int y = -2; y < 2; ++y) {
                    for (int z = -2; z < 2; ++z) {
                        BlockPos checkedPos = pos.add(x, y, z);
                        BlockState targetBlock = world.getBlockState(checkedPos);
                        if (targetBlock.isIn(SALTABLEGROUND)) {
                            alive++;
                        }
                    }
                }
            }
        }
        if(alive == 1 && this.randomEntity != null){
            Entity entity = getRandomEntity(world, random);
            entity.setPositionAndRotation(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 0);
            world.addEntity(entity);
            alive++;
        }
    }

    private Entity getRandomEntity(World world, Random random) {
        for (EntityType type : ForgeRegistries.ENTITIES.getValues()) {
            Entity entity = type.create(world);
            if (entity instanceof AnimalEntity) {
                entities.add(entity);
            }
        }
        int size = entities.size();
        int randomEntityNumber = random.nextInt(size);
        randomEntity = entities.get(randomEntityNumber);

        return randomEntity;
    }


}
