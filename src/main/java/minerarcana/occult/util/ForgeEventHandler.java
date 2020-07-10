package minerarcana.occult.util;

import minerarcana.occult.api.pressure.chunkpressure.ChunkPressureStorageProvider;
import minerarcana.occult.api.pressure.worldpressure.WorldPressureStorageProvider;
import minerarcana.occult.items.equipment.lionmetal.SatiatedLionmetalArmor;
import minerarcana.occult.items.equipment.lionmetal.hungry.HungryLionmetalShield;
import minerarcana.occult.items.equipment.lionmetal.hungry.IHungryMetal;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static minerarcana.occult.Occult.MOD_ID;
import static minerarcana.occult.util.StaticHandler.hungryLionmetalEaterHelper;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {

    @SubscribeEvent
    public static void attachCapToChunk(AttachCapabilitiesEvent<Chunk> event) {
        event.addCapability(new ResourceLocation(MOD_ID, "pressure_chunk"), new ChunkPressureStorageProvider(event.getObject()));
    }

    @SubscribeEvent
    public static void attachCapToWorld(AttachCapabilitiesEvent<World> event) {
        event.addCapability(new ResourceLocation(MOD_ID, "pressure_world"), new WorldPressureStorageProvider());
    }

    @SubscribeEvent
    public static void attachCapToTileEntity(AttachCapabilitiesEvent<TileEntity> event) {
        //event.addCapability(new ResourceLocation(MOD_ID, "pressure"), new ChunkPressureStorageProvider());
    }

    @SubscribeEvent
    public static void onDamageEntityEvent(LivingDamageEvent event) {
        LivingEntity target = event.getEntityLiving();
        LivingEntity attacker = (LivingEntity) event.getSource().getTrueSource();

        if (attacker != null) {
            target.getArmorInventoryList().forEach(itemStack -> {
                if (itemStack.getItem() instanceof SatiatedLionmetalArmor) {
                    attacker.attackEntityFrom(new DamageSource("satiated_lionmetal_flame").setFireDamage().setMagicDamage(), 1);
                }
            });
        }

    }

    @SubscribeEvent
    public static void playerTickEvent(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        World world = player.world;
        if (player.getHeldItemMainhand().getItem() instanceof IHungryMetal && world.getGameTime() % 40 == 0) {
            int distance = 2;
            if(player.getHeldItemOffhand().getItem() instanceof HungryLionmetalShield){
                distance = 3;
                hungryLionmetalEaterHelper(world, player, distance);
            }

            hungryLionmetalEaterHelper(world, player, distance);

        } else if (player.getHeldItemOffhand().getItem() instanceof HungryLionmetalShield && world.getGameTime() % 40 == 0) {
            hungryLionmetalEaterHelper(world, player, 2);
        }

    }

}
