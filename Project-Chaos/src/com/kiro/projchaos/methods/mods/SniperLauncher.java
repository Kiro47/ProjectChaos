package com.kiro.projchaos.methods.mods;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;

import com.kiro.projchaos.nms._Arrow;

public class SniperLauncher implements ModProjectileLaunch, ProjectileLauncher
{

	private EntityLiving entity;
	private float damage = 20.0f;
	private float speed = 5.0f;

	@Override
	public void onLaunch(EntityLiving target, float f)
	{
		_Arrow arrow = new _Arrow(entity.world);
		StraightArrow motMod = new StraightArrow();
		arrow.registerMods(motMod);

		motMod.setTarget(entity, target);
		motMod.setSpeed(speed);

		arrow.setDamage(damage);
		arrow.setCritical(true);

		arrow.setKnockbackStrength(2);

		entity.world.addEntity(arrow);

		entity.makeSound("random.bow", 1.0F, 1.0F / (entity.bc().nextFloat() * 0.4F + 0.8F));

	}

	@Override
	public void setEntity(Entity entity)
	{
		this.entity = (EntityLiving) entity;
	}

	@Override
	public void setDamage(double damage)
	{
		this.damage = (float) damage;
	}

	@Override
	public void setSpeed(double speed)
	{
		this.speed = (float) speed;
	}
}