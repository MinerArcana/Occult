package minerarcana.occult.blocks.metals;

import minerarcana.occult.tileentities.HungryLionmetalTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class HungryLionMetalBlock extends Block {

    public HungryLionMetalBlock() {
        super(Properties.create(Material.ANVIL));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new HungryLionmetalTile();
    }
}
