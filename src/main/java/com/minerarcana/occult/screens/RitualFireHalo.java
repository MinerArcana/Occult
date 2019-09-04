package com.minerarcana.occult.screens;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.WorkbenchContainer;

import javax.annotation.Nonnull;

public class RitualFireHalo extends WorkbenchContainer {

	public RitualFireHalo(int windowId, PlayerInventory playerInv)
	{
		super(windowId, playerInv);
	}

	@Override
	public boolean canInteractWith(@Nonnull PlayerEntity player)
	{
		return true;
	}
}