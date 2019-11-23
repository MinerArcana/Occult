package com.minerarcana.occult.items.liontools;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.minerarcana.occult.content.OccultItems.*;
import static com.minerarcana.occult.items.liontools.OccultToolTypes.satedlionarmortype;

public class SatedLionMetalArmorItem extends ArmorItem {

    public SatedLionMetalArmorItem(EquipmentSlotType slot, Properties builder) {
        super(satedlionarmortype, slot, builder);
    }



    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("Satiated", 2));
    }

    private Boolean isArmorFull(PlayerEntity player){
        if(player.getEquipmentAndArmor().iterator().next() == SATED_LIONMETAL_HELM.get().getDefaultInstance() && player.getEquipmentAndArmor().iterator().next() == SATED_LIONMETAL_FEET.get().getDefaultInstance() &&
        player.getEquipmentAndArmor().iterator().next() == SATED_LIONMETAL_CHEST.get().getDefaultInstance() && player.getEquipmentAndArmor().iterator().next() == SATED_LIONMETAL_LEGS.get().getDefaultInstance()){
            return true;
        }
        else{ return false;}
    }



}
