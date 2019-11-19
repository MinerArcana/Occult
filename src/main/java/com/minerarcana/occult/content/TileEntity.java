package com.minerarcana.occult.content;

import com.minerarcana.occult.blocks.tileentity.CrucibleTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.occult.Occult.MOD_ID;
import static com.minerarcana.occult.content.Blocks.CRUCIBLE;

public class TileEntity {

    private static final DeferredRegister<TileEntityType<?>> TILE_TYPE =
            new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MOD_ID);

    public static final RegistryObject<TileEntityType<?>> SOCKET_TYPE = TILE_TYPE.register("crucible",
            () -> TileEntityType.Builder.create(CrucibleTile::new, CRUCIBLE.get()).build(null));


    public static void register(IEventBus bus) {
        TILE_TYPE.register(bus);
    }

}
