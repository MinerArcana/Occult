package minerarcana.occult.items.equipment.lionmetal;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

import static minerarcana.occult.Occult.MOD_ID;
import static minerarcana.occult.items.equipment.OccultArmorMaterials.SATIATED_LIONMETAL;

public class SatiatedLionmetalArmor extends LionmetalArmor {

    public SatiatedLionmetalArmor(EquipmentSlotType slot) {
        super(SATIATED_LIONMETAL, slot);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return new ResourceLocation(MOD_ID, "textures/armor/satiated_lionmetal_" + slot.getName().toLowerCase() + ".png").toString();
    }

}
