package com.minerarcana.occult.tileentity.tileinventory;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nonnull;

public class BaseTileEntity extends TileEntity {


	public BaseTileEntity(TileEntityType<?> type) {
		super(type);
	}

	@Nonnull
	@Override
	public CompoundNBT write(CompoundNBT nbt) {
		CompoundNBT ret = super.write(nbt);
		writePacketNBT(ret);
		return ret;
	}

	@Nonnull
	@Override
	public final CompoundNBT getUpdateTag() {
		return write(new CompoundNBT());
	}

	@Override
	public void read(CompoundNBT nbt) {
		super.read(nbt);
		readPacketNBT(nbt);
	}

	public void writePacketNBT(CompoundNBT cmp) {}

	public void readPacketNBT(CompoundNBT cmp) {}

	@Override
	public final SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT tag = new CompoundNBT();
		writePacketNBT(tag);
		return new SUpdateTileEntityPacket(pos, -999, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
		super.onDataPacket(net, packet);
		readPacketNBT(packet.getNbtCompound());
	}

}