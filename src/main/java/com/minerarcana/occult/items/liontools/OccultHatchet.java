package com.minerarcana.occult.items.liontools;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;

import javax.annotation.Nullable;
import java.util.List;

import static com.minerarcana.occult.content.OccultItems.HUNGRY_LIONMETAL_HATCHET;
import static com.minerarcana.occult.content.OccultItems.SATED_LIONMETAL_HATCHET;
import static com.minerarcana.occult.util.lib.OccultTagLib.Blocks.LIONMETALFOOD;
import static com.minerarcana.occult.util.lib.OccultTagLib.Items.HUNGRYTOOLS;

public class OccultHatchet extends AxeItem {

    public OccultHatchet(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack p_179218_1_, World p_179218_2_, BlockState state, BlockPos p_179218_4_, LivingEntity p_179218_5_) {
        state.getDrops();
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if(entity instanceof LivingEntity){
            if(((LivingEntity) entity).getHeldItemMainhand().getItem().isIn(HUNGRYTOOLS)){
                BlockPos pos = entity.getPosition();
                for(int x = -3; x < 3; ++x){
                    for(int y = -3; y < 3; ++y){
                        for(int z = -3; z < 3; ++z){
                            BlockPos targetPos = pos.add(x,y,z);
                            Block targetblock = world.getBlockState(targetPos).getBlock();
                            if(targetblock.isIn(LIONMETALFOOD)){
                                world.setBlockState(targetPos, Blocks.AIR.getDefaultState());
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(this.equals(HUNGRY_LIONMETAL_HATCHET.get())) {
            tooltip.add(new TranslationTextComponent("Hungry", 2));
        } else if (this.equals(SATED_LIONMETAL_HATCHET.get())) {
            tooltip.add(new TranslationTextComponent("Satiated", 2));
        }
    }
}
