package com.minerarcana.occult.blocks.vegetation;

import net.minecraft.block.BlockState;
import net.minecraft.block.VineBlock;
import net.minecraft.block.material.PushReaction;

public class SlickMoss extends VineBlock {

    public SlickMoss(Properties properties) {
        super(properties);
    }

    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.NORMAL;
    }

}
