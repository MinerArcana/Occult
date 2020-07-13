package minerarcana.occult.items.equipment.lionmetal;

import minerarcana.occult.items.equipment.OccultArmorItem;
import minerarcana.occult.models.LionmetalFeetModel;
import minerarcana.occult.models.LionmetalLegsModel;
import minerarcana.occult.models.LionmetalHeadModel;
import minerarcana.occult.models.LionmetalTorsoModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public abstract class LionmetalArmor extends OccultArmorItem {

    public LionmetalArmor(IArmorMaterial materialIn, EquipmentSlotType slot) {
        super(materialIn, slot);
    }

    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        if (armorSlot == EquipmentSlotType.HEAD) {
            LionmetalHeadModel model = new LionmetalHeadModel();
            model.isChild = _default.isChild;
            model.isSneak = _default.isSneak;
            model.isSitting = _default.isSitting;
            model.rightArmPose = _default.rightArmPose;
            model.leftArmPose = _default.leftArmPose;

            return (A) model;
        } else if (armorSlot == EquipmentSlotType.FEET) {
            LionmetalFeetModel model = new LionmetalFeetModel();
            model.isChild = _default.isChild;
            model.isSneak = _default.isSneak;
            model.isSitting = _default.isSitting;
            model.rightArmPose = _default.rightArmPose;
            model.leftArmPose = _default.leftArmPose;

            return (A) model;
        } else if (armorSlot == EquipmentSlotType.LEGS) {
            LionmetalLegsModel model = new LionmetalLegsModel();
            model.isChild = _default.isChild;
            model.isSneak = _default.isSneak;
            model.isSitting = _default.isSitting;
            model.rightArmPose = _default.rightArmPose;
            model.leftArmPose = _default.leftArmPose;

            return (A) model;
        } else if (armorSlot == EquipmentSlotType.CHEST) {
            LionmetalTorsoModel model = new LionmetalTorsoModel();
            model.isChild = _default.isChild;
            model.isSneak = _default.isSneak;
            model.isSitting = _default.isSitting;
            model.rightArmPose = _default.rightArmPose;
            model.leftArmPose = _default.leftArmPose;

            return (A) model;
        }
        return null;
    }

}
