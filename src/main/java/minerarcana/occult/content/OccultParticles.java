package minerarcana.occult.content;

import minerarcana.occult.particles.ColoredBubbleData;
import net.minecraft.particles.ParticleType;

public class OccultParticles {

    public static ParticleType<ColoredBubbleData> COLOR_BUBBLE_PARTICLE = new ParticleType<>(false, ColoredBubbleData.DESERIALIZER);

}
