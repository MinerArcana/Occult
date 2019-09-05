package com.minerarcana.occult.screens;

import com.minerarcana.occult.util.lib.GuiIDs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	Object getServerGuiElement(int ID, PlayerEntity player, World world, int x, int y, int z);
	{

		switch(ID) {
			case GuiIDs.CRAFTING_HALO:
				return new RitualFireHalo(player.inventory, world);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, PlayerEntity player, World world, int handId, int unused1, int unused2) {

		switch(ID) {

		case GuiIDs.CRAFTING_HALO :
			return new GUIH(player.inventory, world);

		}

		return null;
	}

}