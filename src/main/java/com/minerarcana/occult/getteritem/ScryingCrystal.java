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

import static com.minerarcana.occult.api.pressure.PressureCap.getAllPressureFromChunk;
import static com.minerarcana.occult.api.pressure.PressureCap.getChunkPressureForType;

public class ScryingCrystal extends Item {

    public ScryingCrystal(Properties properties) {
        super(properties);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (!world.isRemote) {
            final Chunk chunk = world.getChunkAt(new BlockPos(player));
            final ChunkPos chunkPos = chunk.getPos();

            for (PressureType type : getAllPressureFromChunk(chunk).values()) {
                int pressure = getChunkPressureForType(chunk, type);
                player.sendMessage(new TranslationTextComponent("message.occult.pressure.get", chunkPos, pressure, type));
            }
        }
        return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }
}