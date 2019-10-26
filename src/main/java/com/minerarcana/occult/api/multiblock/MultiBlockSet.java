package com.minerarcana.occult.api.multiblock;

import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;

import java.util.Collections;
import java.util.Map;

public class MultiBlockSet {

	private final Map<Direction, Multiblock> multiblockstructures;

	public MultiBlockSet(Multiblock multiblock) {
		multiblockstructures = Collections.unmodifiableMap(multiblock.createRotations());
	}

	public Multiblock getForEntity(Entity entity) {
		return getForFacing(entity.getHorizontalFacing());
	}

	public Multiblock getForFacing(Direction facing) {
		return multiblockstructures.get(facing);
	}

}