package com.minerarcana.occult.controllermultiblock.rectangular;

import javax.annotation.Nullable;



import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

public enum PartPosition implements IStringSerializable {
	UNKNOWN(null, Type.UNKNOWN, Layer.UNKNOWN), INTERIOR(null, Type.UNKNOWN,
			Layer.UNKNOWN), FRAME_CORNER_NORTH_EAST_TOP(null, Type.FRAME, Layer.TOP), FRAME_CORNER_NORTH_WEST_TOP(null,
					Type.FRAME,
					Layer.TOP), FRAME_CORNER_SOUTH_EAST_TOP(null, Type.FRAME, Layer.TOP), FRAME_CORNER_SOUTH_WEST_TOP(
							null, Type.FRAME, Layer.TOP), FRAME_CORNER_NORTH_EAST_BOTTOM(null, Type.FRAME,
									Layer.BOTTOM), FRAME_CORNER_NORTH_WEST_BOTTOM(null, Type.FRAME,
											Layer.BOTTOM), FRAME_CORNER_SOUTH_EAST_BOTTOM(null, Type.FRAME,
													Layer.BOTTOM), FRAME_CORNER_SOUTH_WEST_BOTTOM(null, Type.FRAME,
															Layer.BOTTOM), FRAME_NORTH_TOP(Direction.NORTH, Type.FRAME,
																	Layer.TOP), FRAME_EAST_TOP(Direction.EAST,
																			Type.FRAME, Layer.TOP), FRAME_WEST_TOP(
																					Direction.WEST, Type.FRAME,
																					Layer.TOP), FRAME_SOUTH_TOP(
																							Direction.SOUTH,
																							Type.FRAME,
																							Layer.TOP), FRAME_NORTH_BOTTOM(
																									Direction.NORTH,
																									Type.FRAME,
																									Layer.BOTTOM), FRAME_EAST_BOTTOM(
																											Direction.EAST,
																											Type.FRAME,
																											Layer.BOTTOM), FRAME_WEST_BOTTOM(
																													Direction.WEST,
																													Type.FRAME,
																													Layer.BOTTOM), FRAME_SOUTH_BOTTOM(
																															Direction.SOUTH,
																															Type.FRAME,
																															Layer.BOTTOM), FRAME_VERTICAL_NORTH_EAST(
																																	null,
																																	Type.FRAME,
																																	Layer.MIDDLE), FRAME_VERTICAL_SOUTH_EAST(
																																			null,
																																			Type.FRAME,
																																			Layer.MIDDLE), FRAME_VERTICAL_NORTH_WEST(
																																					null,
																																					Type.FRAME,
																																					Layer.MIDDLE), FRAME_VERTICAL_SOUTH_WEST(
																																							null,
																																							Type.FRAME,
																																							Layer.MIDDLE), TOP_FACE(
																																									Direction.UP,
																																									Type.FACE,
																																									Layer.TOP), BOTTOM_FACE(
																																											Direction.DOWN,
																																											Type.FACE,
																																											Layer.BOTTOM), NORTH_FACE(
																																													Direction.NORTH,
																																													Type.FACE,
																																													Layer.MIDDLE), SOUTH_FACE(
																																															Direction.SOUTH,
																																															Type.FACE,
																																															Layer.MIDDLE), EAST_FACE(
																																																	Direction.EAST,
																																																	Type.FACE,
																																																	Layer.MIDDLE), WEST_FACE(
																																																			Direction.WEST,
																																																			Type.FACE,
																																																			Layer.MIDDLE);

	public static enum Type {
		UNKNOWN, INTERIOR, FRAME, FACE
	}

	public static enum Layer {
		UNKNOWN, TOP, BOTTOM, MIDDLE
	}

	public boolean isFace() {
		return _type == Type.FACE;
	}

	public boolean isFrame() {
		return _type == Type.FRAME;
	}

	@Nullable
	public Direction getFacing() {
		return _facing;
	}

	public Type getType() {
		return _type;
	}

	public Layer getLayer() {
		return _layer;
	}

	public static PropertyEnum<PartPosition> createProperty(String name) {
		return PropertyEnum.create(name, PartPosition.class);
	}

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	PartPosition(@Nullable Direction facing, Type type, Layer layer) {
		_facing = facing;
		_type = type;
		_layer = layer;
	}

	private Direction _facing;
	private Type _type;
	private Layer _layer;
}
