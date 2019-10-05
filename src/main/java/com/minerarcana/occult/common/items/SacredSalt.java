package com.minerarcana.occult.common.items;

import com.minerarcana.occult.Occult;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SacredSalt extends Item {
    public SacredSalt(Properties properties) {
        super(properties);
    }




    @Override
    public ActionResult<ItemStack> onItemRightClick(World p_77659_1_, PlayerEntity player, Hand p_77659_3_) {

        player.setInvulnerable(true);

        player.setInvulnerable(false);
        return super.onItemRightClick(p_77659_1_, player, p_77659_3_);
    }
}
