package com.minerarcana.occult.items.liontools;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.minerarcana.occult.content.OccultItems.*;
import static com.minerarcana.occult.items.liontools.OccultArmorTypes.hungrylionarmortype;
import static com.minerarcana.occult.util.lib.OccultTagLib.Blocks.LIONMETALFOOD;

public class HungryLionArmorItem extends ArmorItem {

    public HungryLionArmorItem(EquipmentSlotType slot, Properties builder) {
        super(hungrylionarmortype, slot, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        int distance = 0;
        if(isArmorFull(player)){
            distance = 10;
        } else{distance = 3;}

        BlockPos pos = player.getPosition();
        for(int x = -distance; x < distance; ++x){
            for(int y = -distance; y < distance; ++y){
                for(int z = -distance; z < distance; ++z){
                    BlockPos targetPos = pos.add(x,y,z);
                    Block targetblock = world.getBlockState(targetPos).getBlock();
                    if(targetblock.isIn(LIONMETALFOOD)){
                        world.setBlockState(targetPos, Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("Hungry", 2));
    }

    private Boolean isArmorFull(PlayerEntity player){
        if(player.getEquipmentAndArmor().iterator().next() == HUNGRY_LIONMETAL_HELM.get().getDefaultInstance() && player.getEquipmentAndArmor().iterator().next() == HUNGRY_LIONMETAL_FEET.get().getDefaultInstance() &&
        player.getEquipmentAndArmor().iterator().next() == HUNGRY_LIONMETAL_CHEST.get().getDefaultInstance() && player.getEquipmentAndArmor().iterator().next() == HUNGRY_LIONMETAL_LEGS.get().getDefaultInstance()){
            return true;
        }
        else{ return false;}
}



}
