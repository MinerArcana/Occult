package com.minerarcana.occult.util.network;

import com.minerarcana.occult.api.pressure.IPressure;
import com.minerarcana.occult.capabilities.ChunkPressureCapability;
import com.minerarcana.occult.capabilities.PressureChunkStorage;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class UpdateChunkEnergyValueMessage {

	private final ChunkPos chunkPos;

	private final int pressure;


	public UpdateChunkEnergyValueMessage(final IPressure chunkPressure) {
		chunkPos = chunkPressure.getChunkPos();
		pressure = chunkPressure.getPressure();
	}

	private UpdateChunkEnergyValueMessage(final ChunkPos chunkPos, final int pressure) {
		this.chunkPos = chunkPos;
		this.pressure = pressure;
	}

	public static UpdateChunkEnergyValueMessage decode(final PacketBuffer buffer) {
		return new UpdateChunkEnergyValueMessage(
				new ChunkPos(buffer.readInt(), buffer.readInt()),
				buffer.readInt()
		);
	}

	public static void encode(final UpdateChunkEnergyValueMessage message, final PacketBuffer buffer) {
		buffer.writeInt(message.chunkPos.x);
		buffer.writeInt(message.chunkPos.z);
		buffer.writeInt(message.pressure);
	}

	public static void handle(final UpdateChunkEnergyValueMessage message, final Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			final Optional<World> optionalWorld = LogicalSidedProvider.CLIENTWORLD.get(ctx.get().getDirection().getReceptionSide());

			optionalWorld.ifPresent(world ->
					ChunkPressureCapability.getChunkEnergy(world, message.chunkPos).ifPresent(chunkPressure -> {
						if (!(chunkPressure instanceof PressureChunkStorage)) return;

						((PressureChunkStorage) chunkPressure).setPressure(message.pressure);
					})
			);
		});

		ctx.get().setPacketHandled(true);
	}
}