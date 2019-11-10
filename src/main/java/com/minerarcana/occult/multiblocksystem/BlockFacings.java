package com.minerarcana.occult.multiblocksystem;

import java.util.HashMap;

import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public final class BlockFacings {

	public static final BlockFacings NONE;
	public static final BlockFacings ALL;
	public static final BlockFacings DOWN;
	public static final BlockFacings UP;
	public static final BlockFacings NORTH;
	public static final BlockFacings SOUTH;
	public static final BlockFacings WEST;
	public static final BlockFacings EAST;

	public static final BooleanProperty FACING_DOWN = BooleanProperty.create("downFacing");
	public static final BooleanProperty FACING_UP = BooleanProperty.create("upFacing");
	public static final BooleanProperty FACING_WEST = BooleanProperty.create("westFacing");
	public static final BooleanProperty FACING_EAST = BooleanProperty.create("eastFacing");
	public static final BooleanProperty FACING_NORTH = BooleanProperty.create("northFacing");
	public static final BooleanProperty FACING_SOUTH = BooleanProperty.create("southFacing");

	/**
	 * Check if a specific face is "set"
	 *
	 * @param facing
	 *            the face to check
	 * @return true if the face is "set", false otherwise
	 */
	public boolean isSet(Direction facing) {

		return 0 != (_value & (1 << facing.getIndex()));
	}

	public boolean none() {
		return 0 == _value;
	}

	public boolean all() {
		return 0x3f == _value;
	}

	public boolean down() {
		return isSet(Direction.DOWN);
	}

	public boolean up() {
		return isSet(Direction.UP);
	}

	public boolean north() {
		return isSet(Direction.NORTH);
	}

	public boolean south() {
		return isSet(Direction.SOUTH);
	}

	public boolean west() {
		return isSet(Direction.WEST);
	}

	public boolean east() {
		return isSet(Direction.EAST);
	}

	public BlockState toBlockState(BlockState state) {

		return state.with(FACING_DOWN, isSet(Direction.DOWN)).with(FACING_UP, isSet(Direction.UP))
				.with(FACING_WEST, isSet(Direction.WEST)).with(FACING_EAST, isSet(Direction.EAST))
				.with(FACING_NORTH, isSet(Direction.NORTH))
				.with(FACING_SOUTH, isSet(Direction.SOUTH));
	}

	/**
	 * Return a BlockFacing object that describe the current facing with the given
	 * face set or unset
	 *
	 * @param facing
	 *            the face to modify
	 * @param value
	 *            the new value for the state of the face
	 * @return a BlockFacing object
	 */
	public BlockFacings set(Direction facing, boolean value) {

		byte newHash = _value;

		if (value) {
			newHash |= (1 << facing.getIndex());
		} else {
			newHash &= ~(1 << facing.getIndex());
		}

		return BlockFacings.from(Byte.valueOf(newHash));
	}

	/**
	 * Count the number of faces that are in the required state
	 *
	 * @param areSet
	 *            specify if you are looking for "set" faces (true) or not (false)
	 * @return the number of faces found in the required state
	 */
	public int countFacesIf(boolean areSet) {

		int checkFor = areSet ? 1 : 0;
		int mask = _value;
		int faces = 0;

		for (int i = 0; i < 6; ++i, mask = mask >>> 1) {

			if ((mask & 1) == checkFor) {
				++faces;
			}
		}

		return faces;
	}

	/**
	 * Return a PropertyBlockFacings for the current facing
	 *
	 * @return a PropertyBlockFacings value
	 */
	public PropertyBlockFacings toProperty() {

		PropertyBlockFacings[] values = PropertyBlockFacings.values();

		for (PropertyBlockFacings value : values) {
			if (value._hash == _value) {
				return value;
			}
		}

		return PropertyBlockFacings.None;
	}

	/**
	 * Offset the given BlockPos in all direction set in this object
	 *
	 * @param originalPosition
	 *            the original position
	 * @return the new position
	 */
	public BlockPos offsetBlockPos(BlockPos originalPosition) {

		int x = 0, y = 0, z = 0;

		for (Direction facing : Direction.values()) {
			if (isSet(facing)) {

				x += facing.getXOffset();
				y += facing.getYOffset();
				z += facing.getZOffset();
			}
		}

		return originalPosition.add(x, y, z);
	}

	/**
	 * Return the first face that is in the required state
	 *
	 * @param isSet
	 *            specify if you are looking for "set" faces (true) or not (false)
	 * @return the first face that match the required state or null if no face is
	 *         found
	 */
	public Direction firstIf(boolean isSet) {

		for (Direction facing : Direction.values()) {
			if (isSet == isSet(facing)) {
				return facing;
			}
		}

		return null;
	}

	/**
	 * Return a BlockFacing object that describe the passed in state
	 *
	 * @param down
	 *            the state of the "down" face
	 * @param up
	 *            the state of the "up" face
	 * @param north
	 *            the state of the "north" face
	 * @param south
	 *            the state of the "south" face
	 * @param west
	 *            the state of the "west" face
	 * @param east
	 *            the state of the "east" face
	 * @return a BlockFacing object
	 */
	public static BlockFacings from(boolean down, boolean up, boolean north, boolean south, boolean west,
			boolean east) {

		return BlockFacings.from(BlockFacings.computeHash(down, up, north, south, west, east));
	}

	/**
	 * Return a BlockFacing object that describe the passed in state
	 *
	 * @param facings
	 *            an array describing the state. the elements of the array must be
	 *            filled in following the order in Direction.VALUES
	 * @return a BlockFacing object
	 */
	public static BlockFacings from(boolean[] facings) {

		return BlockFacings.from(BlockFacings.computeHash(facings));
	}

	@Override
	public String toString() {

		return String.format("Facings: %s%s%s%s%s%s", isSet(Direction.DOWN) ? "DOWN " : "",
				isSet(Direction.UP) ? "UP " : "", isSet(Direction.NORTH) ? "NORTH " : "",
				isSet(Direction.SOUTH) ? "SOUTH " : "", isSet(Direction.WEST) ? "WEST " : "",
				isSet(Direction.EAST) ? "EAST " : "");
	}

	static BlockFacings from(Byte hash) {

		BlockFacings facings = BlockFacings.s_cache.get(hash);

		if (null == facings) {

			facings = new BlockFacings(hash.byteValue());
			BlockFacings.s_cache.put(hash, facings);
		}

		return facings;
	}

	private BlockFacings(byte value) {

		_value = value;
	}

	static Byte computeHash(boolean down, boolean up, boolean north, boolean south, boolean west, boolean east) {

		byte hash = 0;

		if (down) {
			hash |= (1 << Direction.DOWN.getIndex());
		}

		if (up) {
			hash |= (1 << Direction.UP.getIndex());
		}

		if (north) {
			hash |= (1 << Direction.NORTH.getIndex());
		}

		if (south) {
			hash |= (1 << Direction.SOUTH.getIndex());
		}

		if (west) {
			hash |= (1 << Direction.WEST.getIndex());
		}

		if (east) {
			hash |= (1 << Direction.EAST.getIndex());
		}

		return Byte.valueOf(hash);
	}

	static Byte computeHash(boolean[] facings) {

		byte hash = 0;
		int len = null == facings ? -1 : facings.length;

		if (len < 0 || len > Direction.values().length) {
			throw new IllegalArgumentException("Invalid length of facings array");
		}

		for (int i = 0; i < len; ++i) {

			if (facings[i]) {
				hash |= (1 << Direction.values()[i].getIndex());
			}
		}

		return Byte.valueOf(hash);
	}

	private byte _value;

	private static HashMap<Byte, BlockFacings> s_cache;

	static {

		Byte hash;

		s_cache = new HashMap<Byte, BlockFacings>(8);

		hash = BlockFacings.computeHash(false, false, false, false, false, false);
		s_cache.put(hash, NONE = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(true, true, true, true, true, true);
		s_cache.put(hash, ALL = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(true, false, false, false, false, false);
		s_cache.put(hash, DOWN = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(false, true, false, false, false, false);
		s_cache.put(hash, UP = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(false, false, true, false, false, false);
		s_cache.put(hash, NORTH = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(false, false, false, true, false, false);
		s_cache.put(hash, SOUTH = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(false, false, false, false, true, false);
		s_cache.put(hash, WEST = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(false, false, false, false, false, true);
		s_cache.put(hash, EAST = new BlockFacings(hash.byteValue()));
	}
}