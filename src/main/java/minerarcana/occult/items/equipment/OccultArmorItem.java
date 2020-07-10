package minerarcana.occult.items.equipment;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static minerarcana.occult.Occult.OG;
import static minerarcana.occult.items.equipment.OccultArmorMaterials.AMALGAM_DIPPED;

public class OccultArmorItem extends ArmorItem {

    public OccultArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot) {
        super(materialIn, slot, new Item.Properties().group(OG));
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        if(stack.hasTag() && stack.getTag() != null){
            if(stack.getTag().contains("amalgam_dipped")) {
                return AMALGAM_DIPPED.getEnchantability();
            }
        }
        return material.getEnchantability();
    }

    @Override
    public int getItemEnchantability() {
        ItemStack stack = this.getDefaultInstance();
        if(stack.hasTag() && stack.getTag() != null){
            if(stack.getTag().contains("amalgam_dipped")) {
                return AMALGAM_DIPPED.getEnchantability();
            }
        }
        return material.getEnchantability();
    }
}
