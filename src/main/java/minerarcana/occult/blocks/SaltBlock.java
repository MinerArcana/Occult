package minerarcana.occult.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

public class SaltBlock extends Block {

    public SaltBlock() {
        super(Block.Properties.create(Material.SAND).tickRandomly());
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(100) == 0 && isNearbyBlockWaterOrGrass(world,pos)) {
            Biome biome = world.getBiome(pos);
            List<Biome.SpawnListEntry> entityList = biome.getSpawns(EntityClassification.CREATURE);
            if (entityList.size() > 0) {
                int randomEntity = random.nextInt(entityList.size());
                Biome.SpawnListEntry entry = entityList.get(randomEntity);
                Entity toSpawn = entry.entityType.create(world);
                BlockPos posToSpawn = pos.up().add(1, 0, 1);
                if (!world.getBlockState(posToSpawn).isAir()) {
                    posToSpawn.add(1, 0, 1);
                }
                if (world.getBlockState(posToSpawn).isAir() && toSpawn != null) {
                    toSpawn.setPosition(posToSpawn.getX(), posToSpawn.getY(), posToSpawn.getZ());
                    world.addEntity(toSpawn);
                }
            }
        }
    }

    private boolean isNearbyBlockWaterOrGrass(World world, BlockPos pos) {
        for (int x = -2; x <= 2; ++x) {
            for (int y = -2; y <= 2; ++y) {
                for (int z = -2; z <= 2; ++z) {
                    BlockPos newPos = pos.add(x,y,z);
                    if(world.getBlockState(newPos).getBlock() == Blocks.WATER || world.getBlockState(newPos).getBlock() == Blocks.GRASS_BLOCK){
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
