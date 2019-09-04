package com.minerarcana.occult.tileentity.ritualfire;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class RitualFireScreen extends ContainerScreen<RitualFireContainer> {


    public RitualFireScreen(RitualFireContainer container, PlayerInventory inventory, ITextComponent text) {
        super(container, inventory, text);
    }


}
