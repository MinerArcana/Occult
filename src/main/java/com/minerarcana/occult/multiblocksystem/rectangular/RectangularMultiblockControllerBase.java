package com.minerarcana.occult.multiblocksystem.rectangular;


import com.minerarcana.occult.multiblocksystem.MultiblockControllerBase;
import com.minerarcana.occult.multiblocksystem.validation.IMultiblockValidator;
import com.minerarcana.occult.multiblocksystem.validation.ValidationError;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class RectangularMultiblockControllerBase extends MultiblockControllerBase {

	protected RectangularMultiblockControllerBase(World world) {
		super(world);
	}

	/**
	 * @return True if the machine is "whole" and should be assembled. False
	 *         otherwise.
	 */
	@Override
	protected boolean isMachineWhole(IMultiblockValidator validatorCallback) {

		if (connectedParts.size() < getMinimumNumberOfBlocksForAssembledMachine()) {
			validatorCallback.setLastError(ValidationError.VALIDATION_ERROR_TOO_FEW_PARTS);
			return false;
		}

		BlockPos maximumCoord = getMaximumCoord();
		BlockPos minimumCoord = getMinimumCoord();

		int minX = minimumCoord.getX();
		int minY = minimumCoord.getY();
		int minZ = minimumCoord.getZ();
		int maxX = maximumCoord.getX();
		int maxY = maximumCoord.getY();
		int maxZ = maximumCoord.getZ();

		// Quickly check for exceeded dimensions
		int deltaX = maxX - minX + 1;
		int deltaY = maxY - minY + 1;
		int deltaZ = maxZ - minZ + 1;

		int maxXSize = getMaximumXSize();
		int maxYSize = getMaximumYSize();
		int maxZSize = getMaximumZSize();
		int minXSize = getMinimumXSize();
		int minYSize = getMinimumYSize();
		int minZSize = getMinimumZSize();

		if (maxXSize > 0 && deltaX > maxXSize) {
			validatorCallback.setLastError("base.api.multiblock.validation.machine_too_large", maxXSize, "X");
			return false;
		}
		if (maxYSize > 0 && deltaY > maxYSize) {
			validatorCallback.setLastError("base.api.multiblock.validation.machine_too_large", maxYSize, "Y");
			return false;
		}
		if (maxZSize > 0 && deltaZ > maxZSize) {
			validatorCallback.setLastError("base.api.multiblock.validation.machine_too_large", maxZSize, "Z");
			return false;
		}
		if (deltaX < minXSize) {
			validatorCallback.setLastError("base.api.multiblock.validation.machine_too_small", minXSize, "X");
			return false;
		}
		if (deltaY < minYSize) {
			validatorCallback.setLastError("base.api.multiblock.validation.machine_too_small", minYSize, "Y");
			return false;
		}
		if (deltaZ < minZSize) {
			validatorCallback.setLastError("base.api.multiblock.validation.machine_too_small", minZSize, "Z");
			return false;
		}

		// Now we run a simple check on each block within that volume.
		// Any block deviating = NO DEAL SIR
		TileEntity te;
		RectangularMultiblockTileEntityBase<?> part;
		Class<? extends RectangularMultiblockControllerBase> myClass = this.getClass();
		int extremes;
		boolean isPartValid;

		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {
					// Okay, figure out what sort of block this should be.

					te = WORLD.getTileEntity(new BlockPos(x, y, z));
					if (te instanceof RectangularMultiblockTileEntityBase) {
						part = (RectangularMultiblockTileEntityBase<?>) te;

						// Ensure this part should actually be allowed within a cube of this
						// controller's type
						if (!myClass.equals(part.getMultiblockControllerType())) {

							validatorCallback.setLastError("base.api.multiblock.validation.invalid_part", x, y, z);
							return false;
						}
					} else {
						// This is permitted so that we can incorporate certain non-multiblock parts
						// inside interiors
						part = null;
					}

					// Validate block type against both part-level and material-level validators.
					extremes = 0;

					if (x == minX) {
						extremes++;
					}
					if (y == minY) {
						extremes++;
					}
					if (z == minZ) {
						extremes++;
					}

					if (x == maxX) {
						extremes++;
					}
					if (y == maxY) {
						extremes++;
					}
					if (z == maxZ) {
						extremes++;
					}

					if (extremes >= 2) {

						isPartValid = part != null ? part.isGoodForFrame(validatorCallback)
								: isBlockGoodForFrame(WORLD, x, y, z, validatorCallback);

						if (!isPartValid) {

							if (null == validatorCallback.getLastError()) {
								validatorCallback.setLastError("base.api.multiblock.validation.invalid_part_for_frame",
										x, y, z);
							}

							return false;
						}
					} else if (extremes == 1) {
						if (y == maxY) {

							isPartValid = part != null ? part.isGoodForTop(validatorCallback)
									: isBlockGoodForTop(WORLD, x, y, z, validatorCallback);

							if (!isPartValid) {

								if (null == validatorCallback.getLastError()) {
									validatorCallback.setLastError(
											"base.api.multiblock.validation.invalid_part_for_top", x, y, z);
								}

								return false;
							}
						} else if (y == minY) {

							isPartValid = part != null ? part.isGoodForBottom(validatorCallback)
									: isBlockGoodForBottom(WORLD, x, y, z, validatorCallback);

							if (!isPartValid) {

								if (null == validatorCallback.getLastError()) {
									validatorCallback.setLastError(
											"base.api.multiblock.validation.invalid_part_for_bottom", x, y, z);
								}

								return false;
							}
						} else {
							// Side
							isPartValid = part != null ? part.isGoodForSides(validatorCallback)
									: isBlockGoodForSides(WORLD, x, y, z, validatorCallback);

							if (!isPartValid) {

								if (null == validatorCallback.getLastError()) {
									validatorCallback.setLastError(
											"base.api.multiblock.validation.invalid_part_for_sides", x, y, z);
								}

								return false;
							}
						}
					} else {

						isPartValid = part != null ? part.isGoodForInterior(validatorCallback)
								: isBlockGoodForInterior(WORLD, x, y, z, validatorCallback);

						if (!isPartValid) {

							if (null == validatorCallback.getLastError()) {
								validatorCallback.setLastError(
										"base.api.multiblock.validation.invalid_part_for_interior", x, y, z);
							}

							return false;
						}
					}
				}
			}
		}

		return true;
	}

}
