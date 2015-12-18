package com.kiro.projchaos.nms;

import java.lang.reflect.Field;

import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntitySkeleton;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.PathfinderGoal;
import net.minecraft.server.v1_8_R3.World;

import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.util.Vector;

import com.kiro.projchaos.methods.ISkeleton;
import com.kiro.projchaos.methods.Modifier;
import com.kiro.projchaos.methods.mods.Equiptment;
import com.kiro.projchaos.methods.mods.ModAttack;
import com.kiro.projchaos.methods.mods.ModAttributes;
import com.kiro.projchaos.methods.mods.ModBaseEntityTick;
import com.kiro.projchaos.methods.mods.ModCollide;
import com.kiro.projchaos.methods.mods.ModDamage;
import com.kiro.projchaos.methods.mods.ModDeath;
import com.kiro.projchaos.methods.mods.ModLoot;
import com.kiro.projchaos.methods.mods.ModMobTick;
import com.kiro.projchaos.methods.mods.ModProjectileLaunch;
import com.kiro.projchaos.methods.mods.ModSound;


public class _Skeleton extends EntitySkeleton implements ISkeleton, IArmorHolder
{

	private final Equiptment equiptment;
	private boolean newAI;
	private ModBaseEntityTick tickMod;
	private ModSound soundMod;
	private ModLoot lootMod;
	private ModDamage dmgMod;
	private ModAttributes attrMod;
	private ModMobTick mobTickMod;
	private ModAttack attackMod;
	private ModCollide collideMod;
	private ModProjectileLaunch launchMod;

	public _Skeleton(World world)
	{
		super(world);
		equiptment = new Equiptment(this);
	}

	private ModDeath deathMod;

	@Override
	public void die()
	{
		if (deathMod != null)
		{
			deathMod.onDeath();
		}
		else
		{
			defaultDie();
		}
	}

	@Override
	public void defaultDie()
	{
		super.die();
	}

	@Override
	public void collide(Entity entity)
	{
		if (collideMod != null)
		{
			collideMod.onCollide(entity);
		}
		else
		{
			defaultCollide(entity);
		}
	}

	@Override
	public void defaultCollide(Entity entity)
	{
		super.collide(entity);
	}

	// Entity Tick
	@Override
	public void t_()
	{
		if (tickMod != null)
		{
			tickMod.onTick();
		}
		defaultEntityTick();
	}

	// call to default defaultEntityTick
	@Override
	public void defaultEntityTick()
	{
		super.t_();
	}

	@Override
	public void E()
	{
		if (mobTickMod != null)
		{
			mobTickMod.mobTick();
		}
		else
		{
			defaultMobTick();
		}
	}

	@Override
	public void defaultMobTick()
	{
		super.E();
	}


	@Override
	public boolean r(Entity entity)
	{
		if (attackMod != null)
		{
			return attackMod.attackEntity(entity);
		}
		return attackEntity(entity);
	}

	public boolean attackEntity(Entity entity)
	{
		return super.r(entity);
	}

	// Sounds
	@Override
	protected String z() // say
	{
		return soundMod == null ? saySound() : soundMod.saySound();
	}

	@Override
	public String saySound()
	{
		return super.z();
	}

	@Override
	protected String bo() // hurt
	{
		return soundMod == null ? hurtSound() : soundMod.hurtSound();
	}

	@Override
	public String hurtSound()
	{
		return super.bo();
	}

	@Override
	protected String bp() // death
	{
		return soundMod == null ? deathSound() : soundMod.deathSound();
	}

	@Override
	public String deathSound()
	{
		return super.bp();
	}


	@Override
	protected void initAttributes()
	{
		super.initAttributes();

		newAI = super.bM();

		if (attrMod != null)
		{
			attrMod.initAttributes();
		}
	}

	// Damage Handler
	@Override
	public boolean damageEntity(DamageSource damagesource, float f)
	{
		if (dmgMod != null)
		{
			return dmgMod.damageEntity(damagesource, f);
		}
		return _damageEntity(damagesource, f);
	}

	@Override
	public boolean _damageEntity(DamageSource damagesource, float f)
	{
		return super.damageEntity(damagesource, f);
	}

	@Override
	public void a(EntityLiving entityliving, float f)
	{
		if (launchMod != null)
		{
			launchMod.onLaunch(entityliving, f);
		}
		else
		{
			launchProjectile(entityliving, f);
		}
	}

	public void launchProjectile(EntityLiving entityLiving, float f)
	{
		super.a(entityLiving, f);
	}

	@Override
	public void registerMods(Modifier modifier)
	{

		NMSHandler.registerMods(this, modifier);
		modifier.setEntity(this);
	}

	@Override
	public Item getLoot()
	{
		if (lootMod != null)
		{
			return lootMod.getLoot();
		}
		return super.getLoot();
	}

	@Override
	public void moveTo(double px, double py, double pz)
	{
		move(px, py, pz);
	}

	@Override
	public void teleportTo(double px, double py, double pz)
	{
		setPosition(px, py, pz);
	}

	@Override
	public Vector getLocation()
	{
		return new Vector(locX, locY, locZ);
	}

	// AI
	@Override
	public void clearAI()
	{
		try
		{
			Class<?> clz = targetSelector.getClass();
			Field field = clz.getDeclaredField("b");
			field.setAccessible(true);
			field.set(targetSelector, new UnsafeList<>());
			field.set(goalSelector, new UnsafeList<>());

			field = clz.getDeclaredField("c");
			field.setAccessible(true);
			field.set(targetSelector, new UnsafeList<>());
			field.set(goalSelector, new UnsafeList<>());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void addGoal(PathfinderGoal goal, int p)
	{
		goalSelector.a(p, goal);
	}

	@Override
	public void addTarget(PathfinderGoal goal, int p)
	{
		targetSelector.a(p, goal);
	}

	@Override
	public boolean inWater()
	{
		return inWater;
	}

	@Override
	public boolean bM()
	{
		return newAI;
	}

	@Override
	public boolean setNewAI(boolean newAI)
	{
		return newAI;
	}

	@Override
	public Equiptment getEquiptment()
	{
		return equiptment;
	}
}