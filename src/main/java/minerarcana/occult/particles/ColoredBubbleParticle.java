package minerarcana.occult.particles;

import net.minecraft.client.particle.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class ColoredBubbleParticle extends SpriteTexturedParticle {

    protected ColoredBubbleParticle(ColoredBubbleData data,World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ) {
        super(world, posX, posY, posZ, speedX, speedY, speedZ);
        motionX = speedX;
        motionY = speedY;
        motionZ = speedZ;
        particleScale = (rand.nextFloat() * 0.3f) + (rand.nextFloat() * 0.3f);
        this.particleRed = data.getRed();
        this.particleBlue = data.getBlue();
        this.particleGreen = data.getGreen();
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<ColoredBubbleData>{

        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle makeParticle(ColoredBubbleData data, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ColoredBubbleParticle particle = new ColoredBubbleParticle(data,worldIn,x,y,z,xSpeed,zSpeed,ySpeed);
            particle.selectSpriteRandomly(this.spriteSet);
            return particle;
        }
    }


}
