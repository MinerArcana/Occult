package com.minerarcana.occult.blocks.tileentity.tilescreen;

import com.minerarcana.occult.blocks.tileentity.tilecontainer.RitualBaseContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class RitualBaseScreen extends ContainerScreen<RitualBaseContainer> {


    public RitualBaseScreen(RitualBaseContainer container, PlayerInventory inventory, ITextComponent name) {
        super(container, inventory, name);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
