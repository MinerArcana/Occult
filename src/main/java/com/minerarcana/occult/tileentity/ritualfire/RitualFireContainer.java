package com.minerarcana.occult.tileentity.ritualfire;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

import javax.annotation.Nullable;

public class RitualFireContainer extends Container {

    protected RitualFireContainer(@Nullable ContainerType<?> container, int windowID) {
        super(container, windowID);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return false;
    }
}
