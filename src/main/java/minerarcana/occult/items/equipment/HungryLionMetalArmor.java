package minerarcana.occult.items.equipment;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static minerarcana.occult.content.OccultArmorMaterials.HUNGRY_LIONMETAL;
import static minerarcana.occult.content.OccultItems.*;
import static minerarcana.occult.util.TagHelper.LIONMETALFOOD;

public class HungryLionMetalArmor extends OccultArmorItem {

    public HungryLionMetalArmor(EquipmentSlotType slot) {
        super(HUNGRY_LIONMETAL, slot);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        int distance = 3;
        if(isArmorFull(player)){
            distance = 10;
        }

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

    private Boolean isArmorFull(PlayerEntity player) {
        if (player.getEquipmentAndArmor().iterator().next() == HUNGRY_LIONMETAL_HELM.get().getDefaultInstance() && player.getEquipmentAndArmor().iterator().next() == HUNGRY_LIONMETAL_BOOTS.get().getDefaultInstance() &&
                player.getEquipmentAndArmor().iterator().next() == HUNGRY_LIONMETAL_CHEST.get().getDefaultInstance() && player.getEquipmentAndArmor().iterator().next() == HUNGRY_LIONMETAL_LEGS.get().getDefaultInstance()) {
            return true;
        } else {
            return false;
        }
    }




}
