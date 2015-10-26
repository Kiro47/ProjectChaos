package com.kiro.projchaos.customs.goals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.World;

import org.bukkit.event.entity.EntityTargetEvent;

import com.kiro.projchaos.methods.IEntity;
import com.kiro.projchaos.methods.SUtils;
import com.kiro.projchaos.nms.AbstractGoal;
import com.kiro.projchaos.nms._Skeleton;
import com.kiro.projchaos.nms._Zombie;

public class SummonMobGoal extends AbstractGoal
{
	private static final Random rand = new Random();

	private final List<EntityLiving> summonedMobs;
	private int ticksSinceSummon;

	public SummonMobGoal(EntityInsentient entity)
	{
		super(entity);
		summonedMobs = new ArrayList<>(6);
	}

	@Override
	public boolean canOperate()
	{
		// if being attacked
		return entity.getLastDamager() != null;
	}

	@Override
	public void goalSetup()
	{
		System.out.println("Attacked - Summoning Mobs");
		// set the target to who attacked
		entity.setGoalTarget(entity.getLastDamager(), EntityTargetEvent.TargetReason.CUSTOM, true);
		EntityLiving target = entity.getLastDamager();

		int count = rand.nextInt(4) + 2;

		entity.setAbsorptionHearts(count * 4);
		entity.setHealth(entity.getMaxHealth());
		System.out.println("SPAWN - HC: " + this.entity.getAbsorptionHearts());

		for (int i = 0; i < count; i++)
		{

			EntityLiving spawn = (EntityLiving) createRandomSpawn(entity.world);
			SUtils.setPositionRandom(spawn, target.locX, target.locY, target.locZ, 10.0D);

			SummonedMinionAI ai = new SummonedMinionAI(this, entity, target);
			((IEntity) spawn).registerMods(ai);

			entity.world.addEntity(spawn);
			spawn.makeSound("mob.endermen.portal", 1.0F, 1.0F);

			summonedMobs.add(spawn);

		}

	}

	@Override
	public void clearGoal()
	{
		// kill summoned entities
		for (EntityLiving entity : summonedMobs)
		{
			entity.die();
		}
		ticksSinceSummon = 0;
		summonedMobs.clear();
	}

	@Override
	public boolean continueOperating()
	{
		if (entity.ticksLived - entity.hurtTimestamp > 600)
		{
			// clear last Damager
			entity.b((EntityLiving) null);
			return false;
		}

		return !(summonedMobs.isEmpty() && ticksSinceSummon++ > 60);

	}

	@Override
	public void goalTick()
	{
		// remove dead entities
		Iterator<EntityLiving> iterator = summonedMobs.iterator();
		while (iterator.hasNext())
		{
			EntityLiving entity = iterator.next();
			if (!entity.isAlive())
			{
				iterator.remove();
			}
		}
	}

	private static Entity createRandomSpawn(World world)
	{

		switch ((int) (Math.random() * 2))
		{
			case 0:
				return new _Zombie(world);
			case 1:
				return new _Skeleton(world);
		}
		return new _Zombie(world);
	}

	public void childDeath(EntityLiving entity)
	{
		this.entity.setAbsorptionHearts(Math.max(0, this.entity.getAbsorptionHearts() - 4));
		System.out.println("DEATH - HC: " + this.entity.getAbsorptionHearts());
	}

}	