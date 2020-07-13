package minerarcana.occult.items.equipment.lionmetal.hungry;

import minerarcana.occult.items.equipment.OccultArmorItem;
import minerarcana.occult.items.equipment.lionmetal.LionmetalArmor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import java.util.List;
import java.util.stream.Collectors;

import static minerarcana.occult.Occult.MOD_ID;
import static minerarcana.occult.items.equipment.OccultArmorMaterials.HUNGRY_LIONMETAL;
import static minerarcana.occult.util.StaticHandler.hungryLionmetalEaterHelper;

public class HungryLionMetalArmor extends LionmetalArmor {

    public HungryLionMetalArmor(EquipmentSlotType slot) {
        super(HUNGRY_LIONMETAL, slot);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        int distance = 3;
        if (isArmorFull(player)) {
            distance = 10;
        }
        if (!isArmorFull(player) && world.rand.nextInt(24) == 0 || isArmorFull(player) && slot == EquipmentSlotType.CHEST && world.getGameTime() % 20 == 0) {
            hungryLionmetalEaterHelper(world,player,distance);
        }

    }

    private Boolean isArmorFull(PlayerEntity player) {
        List<ItemStack> armor = player.inventory.armorInventory.stream().filter(itemStack -> itemStack.getItem() instanceof HungryLionMetalArmor).collect(Collectors.toList());
        return armor.size() == 4;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return new ResourceLocation(MOD_ID, "textures/armor/hungry_lionmetal_" + slot.getName().toLowerCase() + ".png").toString();
    }
}
