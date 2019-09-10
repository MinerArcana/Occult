package com.minerarcana.occult.util.network;

import com.minerarcana.occult.Occult;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class OccultNetwork {
	public static final ResourceLocation CHANNEL_NAME = new ResourceLocation(Occult.MOD_ID, "network");

	public static final String NETWORK_VERSION = new ResourceLocation(Occult.MOD_ID, "1").toString();

	public static SimpleChannel getNetworkChannel() {
		final SimpleChannel channel = NetworkRegistry.ChannelBuilder.named(CHANNEL_NAME)
				.clientAcceptedVersions(version -> true)
				.serverAcceptedVersions(version -> true)
				.networkProtocolVersion(() -> NETWORK_VERSION)
				.simpleChannel();

		channel.messageBuilder(UpdateChunkEnergyValueMessage.class, 4)
				.decoder(UpdateChunkEnergyValueMessage::decode)
				.encoder(UpdateChunkEnergyValueMessage::encode)
				.consumer(UpdateChunkEnergyValueMessage::handle)
				.add();

		return channel;
	}
}