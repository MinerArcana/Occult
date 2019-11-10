package com.minerarcana.occult.multiblocksystem;


import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author Surseance
 */
@OnlyIn(Dist.CLIENT)
public class ClientHelper {


    public static Minecraft mc() {
        return Minecraft.getInstance();
    }

    public static World world() {
        return mc().world;
    }


}
