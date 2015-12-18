package com.kiro.projchaos.customs.goals;

import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityCreature;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.IRangedEntity;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.ItemStack;

import com.kiro.projchaos.methods.EntityEffects;
import com.kiro.projchaos.methods.IEntity;
import com.kiro.projchaos.methods.mods.Equiptment;
import com.kiro.projchaos.methods.mods.ModDamage;
import com.kiro.projchaos.methods.mods.ModDeath;
import com.kiro.projchaos.methods.mods.ModMobTick;
import com.kiro.projchaos.methods.mods.SniperLauncher;
import com.kiro.projchaos.nms.IArmorHolder;
import com.kiro.projchaos.nms.ILivingEntity;
import com.kiro.projchaos.nms.ParticleUtils;

public class SummonedMinionAI implements ModMobTick, ModDeath, ModDamage
{

	private EntityCreature entity;
	private final EntityLiving target;
	private final EntityLiving parent;
	private final SummonMobGoal parentAi;

	public SummonedMinionAI(SummonMobGoal parentAi, EntityLiving parent, EntityLiving target)
	{
		this.parentAi = parentAi;
		this.target = target;
		this.parent = parent;
	}

	@Override
	public void setEntity(Entity e)
	{
		entity = (EntityCreature) e;
		entity.ticksLived = 0;

		if (entity.getGoalTarget() == null)
		{
			entity.setGoalTarget(target, EntityTargetEvent.TargetReason.CUSTOM, true);
		}

		ILivingEntity ent = (ILivingEntity) entity;
		ent.clearAI();

		ent.addGoal(new PathfinderGoalFloat(entity), 1);

		Equiptment equiptment = ((IArmorHolder) ent).getEquiptment();
		equiptment.setHelm(new ItemStack(Material.GOLD_HELMET));

		if (ent instanceof IRangedEntity)
		{
			equiptment.setHeldItem(new ItemStack(Material.BOW));
			SniperLauncher mod = new SniperLauncher();
			mod.setSpeed(2.0);
			mod.setDamage(10.0);

			((IEntity) entity).registerMods(mod);

		}
		else
		{
			equiptment.setHeldItem(new ItemStack(Material.IRON_SWORD));
			ent.addGoal(new PathfinderGoalMeleeAttack(entity, target.getClass(), 1.0D, false), 2);
		}


		//EntityEffects.playParticleSpawnBurst(ParticleUtils.ParticleType.TOWN_AURA, entity, 10, 0.25f, 100);
		EntityEffects.playParticleSpawnBurst(ParticleUtils.ParticleType.FLAME, entity, 10, 0f, 8);

	}

	@Override
	public void mobTick()
	{
		((ILivingEntity) entity).defaultMobTick();
		if (!target.isAlive() || !parent.isAlive())
		{
			entity.die();
		}
	}

	@Override
	public void onDeath()
	{
		parentAi.childDeath(entity);
		((IEntity) entity).defaultDie();
	}

	@Override
	public boolean damageEntity(DamageSource src, float amnt)
	{
		boolean flag = ((ILivingEntity) entity)._damageEntity(src, amnt);
		if (entity.getHealth() <= 0)
		{
			EntityEffects.playParticleSpawnBurst(ParticleUtils.ParticleType.FIREWORKS_SPARK, entity, 10, 0.01f, 25);
		}

		return flag;
	}
}