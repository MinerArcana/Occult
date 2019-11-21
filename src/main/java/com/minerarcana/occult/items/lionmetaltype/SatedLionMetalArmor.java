package com.minerarcana.occult.items.lionmetaltype;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import static com.minerarcana.occult.Occult.MOD_ID;
import static com.minerarcana.occult.content.OccultItems.SATED_LIONMETAL_INGOT;

public class SatedLionMetalArmor implements IArmorMaterial {

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        int durability = 0;
        if (slotIn.equals(EquipmentSlotType.HEAD)) {
            durability = 200;
        } else if (slotIn.equals(EquipmentSlotType.CHEST)) {
            durability = 200;
        } else if (slotIn.equals(EquipmentSlotType.LEGS)) {
            durability = 200;
        } else if (slotIn.equals(EquipmentSlotType.FEET)) {
            durability = 200;
        }
        return durability;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        int damageReduction = 0;
        if (slotIn.equals(EquipmentSlotType.HEAD)) {
            damageReduction = 2;
        } else if (slotIn.equals(EquipmentSlotType.CHEST)) {
            damageReduction = 2;
        } else if (slotIn.equals(EquipmentSlotType.LEGS)) {
            damageReduction = 2;
        } else if (slotIn.equals(EquipmentSlotType.FEET)) {
            damageReduction = 2;
        }
        return damageReduction;
    }

    @Override
    public int getEnchantability() {
        return 50;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(SATED_LIONMETAL_INGOT.get());
    }

    @Override
    public String getName() {
        return MOD_ID + ":sated_lionmetal_material";
    }

    @Override
    public float getToughness() {
        return 1;
    }
}
