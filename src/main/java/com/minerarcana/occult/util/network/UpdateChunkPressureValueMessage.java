package com.minerarcana.occult.util.network;

import com.minerarcana.occult.api.PressureType;
import com.minerarcana.occult.api.pressure.IPressure;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class UpdateChunkPressureValueMessage {

	private final ChunkPos chunkPos;

	private final int pressure;

	private final PressureType type;


	public UpdateChunkPressureValueMessage(final IPressure chunkPressure) {
		chunkPos = chunkPressure.getChunkPos();
		pressure = chunkPressure.getPressure();
		type = chunkPressure.getType();
	}

	private UpdateChunkPressureValueMessage(final ChunkPos chunkPos, final int pressure, PressureType type) {
		this.chunkPos = chunkPos;
		this.pressure = pressure;
		this.type = type;
	}

	public static UpdateChunkPressureValueMessage decode(final PacketBuffer buffer) {
		return new UpdateChunkPressureValueMessage(
				new ChunkPos(buffer.readInt(), buffer.readInt()),
				buffer.readInt(),
				new PressureType(new Pressure()));
	}

	public static void encode(final UpdateChunkPressureValueMessage message, final PacketBuffer buffer) {
		buffer.writeInt(message.chunkPos.x);
		buffer.writeInt(message.chunkPos.z);
		buffer.writeInt(message.pressure);
	}

	public static void handle(final UpdateChunkPressureValueMessage message, final Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			final Optional<World> optionalWorld = LogicalSidedProvider.CLIENTWORLD.get(ctx.get().getDirection().getReceptionSide());

			optionalWorld.ifPresent(world ->
					ChunkPressureCapability.getChunkEnergy(world, message.chunkPos).ifPresent(chunkPressure -> {
						if (!(chunkPressure instanceof PressureChunkStorage)) return;

						((PressureChunkStorage) chunkPressure).setPressure(message.pressure, message.type);
					})
			);
		});

		ctx.get().setPacketHandled(true);
	}
}