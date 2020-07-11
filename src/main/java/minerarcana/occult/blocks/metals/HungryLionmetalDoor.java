package minerarcana.occult.blocks.metals;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HungryLionmetalDoor extends DoorBlock {

    protected HungryLionmetalDoor(Properties builder) {
        super(builder);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn) {
        if(state.get(OPEN)){
            entityIn.setFire(0);
            entityIn.setFireTimer(0);
        }
        super.onEntityCollision(state, world, pos, entityIn);
    }
}
