package com.kiro.projchaos.methods;

import java.util.Random;

import com.kiro.projchaos.nms.ParticleUtils;

import net.minecraft.server.v1_8_R3.Entity;

public class EntityEffects
{

	private static final Random rand = new Random();

	public static void playParticleTrail(ParticleUtils.ParticleType type, Entity entity, float speed, float data, int count)
	{
		playParticleTrail(type, entity, speed, 0.2f, data, count);
	}

	public static void playParticleTrail(ParticleUtils.ParticleType type, Entity entity, float speed, float spread, float data, int count)
	{
		float px = (float) (entity.locX - entity.motX);
		float py = (float) (entity.locY - entity.motY);
		float pz = (float) (entity.locZ - entity.motZ);

		float motX = (float) (entity.motX / speed);
		float motY = (float) (entity.motY / speed);
		float motZ = (float) (entity.motZ / speed);

		for (int i = 0; i < (int) speed; i++)
		{
			float offX = (float) (rand.nextGaussian() * spread);
			float offY = (float) (rand.nextGaussian() * spread);
			float offZ = (float) (rand.nextGaussian() * spread);

			ParticleUtils.playEffect(type, entity.world, px, py, pz, offX, offY, offZ, data, count);

			px += motX;
			py += motY;
			pz += motZ;
		}
	}

	public static void playParticleSpawnBurst(ParticleUtils.ParticleType type, Entity entity, int density, float data, int count)
	{

		double px = entity.locX;
		double py = entity.locY;
		double pz = entity.locZ;
		for (int i = 0; i < density; i++)
		{
			float offX = (float) ((rand.nextDouble() - 0.5) * entity.width);
			float offY = (float) (rand.nextDouble() * entity.getHeadHeight());
			float offZ = (float) ((rand.nextDouble() - 0.5) * entity.width);


			ParticleUtils.playEffect(type, entity.world, px, py, pz, offX, offY, offZ, data, count);
		}
	}
}