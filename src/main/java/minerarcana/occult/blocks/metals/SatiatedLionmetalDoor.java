package minerarcana.occult.blocks.metals;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SatiatedLionmetalDoor extends DoorBlock {

    protected SatiatedLionmetalDoor(Properties builder) {
        super(builder);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn) {
        if(!state.get(OPEN)){
            entityIn.setFire(120);
            entityIn.setFireTimer(120);
        }
        super.onEntityCollision(state, world, pos, entityIn);
    }
}
