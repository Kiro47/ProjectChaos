package com.kiro.projchaos.methods.mods;

import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.MovingObjectPosition;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import com.kiro.projchaos.nms._Arrow;

public class StraightArrow implements ModMotion, ModProjectileHit
{

	protected _Arrow entity;
	protected float speed;
	protected Vector dir;
	protected int lifeSpan;

	@Override
	public void motion()
	{
		if (lifeSpan++ > 120)
		{
			entity.die();
		}
		else
		{
			// set velocity
			entity.motX = dir.getX() * speed;
			entity.motY = dir.getY() * speed;
			entity.motZ = dir.getZ() * speed;

			// set position
			entity.locX += entity.motX;
			entity.locY += entity.motY;
			entity.locZ += entity.motZ;
		}
	}

	@Override
	public void setEntity(Entity entity)
	{
		this.entity = (_Arrow) entity;
	}

	public void setLocation(Location loc)
	{
		entity.setPosition(loc.getX(), loc.getY(), loc.getZ());
	}

	public void setLocation(Vector loc)
	{
		entity.setPosition(loc.getX(), loc.getY(), loc.getZ());
	}

	public void setLocation(double px, double py, double pz)
	{
		entity.setPosition(px, py, pz);
	}

	public void setSpeed(float speed)
	{
		this.speed = speed;
	}

	public void setDirection(Vector dir)
	{
		this.dir = dir;
	}

	public void setTarget(EntityLiving shooter, EntityLiving target)
	{
		entity.shooter = shooter;

		double dirX = target.locX - shooter.locX;
		double dirY = target.locY + target.getHeadHeight() / 2 - (shooter.locY + shooter.getHeadHeight() / 2);
		double dirZ = target.locZ - shooter.locZ;

		Vector dir = new Vector(dirX, dirY, dirZ).normalize();

		entity.motX = dir.getX() * speed;
		entity.motY = dir.getY() * speed;
		entity.motZ = dir.getZ() * speed;


		setDirection(dir);
		setLocation(shooter.locX + dir.getX(), shooter.locY + shooter.getHeadHeight() + dir.getY(), shooter.locZ + dir.getZ());
		entity.fixRotation();
	}

	@Override
	public void onHit(MovingObjectPosition mop)
	{
		entity.die();
		if (mop.entity instanceof EntityLiving)
		{
			hitEntity(mop, (EntityLiving) mop.entity);
		}
	}

	public void hitEntity(MovingObjectPosition mop, EntityLiving entity)
	{
		entity.damageEntity(DamageSource.arrow(this.entity, this.entity.shooter), (float) this.entity.getDamage());
	}
}