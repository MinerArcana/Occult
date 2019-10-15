package com.minerarcana.occult.blocks.tileentity.tilecontainer;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

import static com.minerarcana.occult.util.lib.OccultNameLib.RITUALBASECONTAINER;


public class RitualBaseContainer extends Container {

    public RitualBaseContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory){
        super(RITUALBASECONTAINER, windowId);
       TileEntity tileEntity = world.getTileEntity(pos);
       tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return false;
    }


}
