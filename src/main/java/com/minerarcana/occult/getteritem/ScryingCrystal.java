package com.minerarcana.occult.getteritem;

import com.minerarcana.occult.api.PressureType;
import com.minerarcana.occult.api.pressure.PressureCap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ScryingCrystal extends Item {

    public ScryingCrystal(Properties properties) {
        super(properties);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (!world.isRemote) {
            final Chunk chunk = world.getChunkAt(new BlockPos(player));
            final ChunkPos chunkPos = chunk.getPos();

            PressureCap.getChunkPressure(chunk).map(chunkPressure -> {
                if(chunkPressure.)
                        if (!chunkPressure.getAllPressure().isEmpty()) {
                            for (PressureType type : chunkPressure.getAllPressure().keySet()) {

                                int pressure = chunkPressure.getPressureFromType(type);
                                player.sendMessage(new TranslationTextComponent("message.occult.pressure.get", chunkPos, pressure, type));
                                return true;
                            }
                        }
                        return false;
                    }
            ).orElseGet(() -> {
                player.sendMessage(new TranslationTextComponent("message.occult.pressure.none", chunkPos));
                return false;});
        }
        return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }
}
