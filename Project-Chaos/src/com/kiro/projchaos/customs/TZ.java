package com.kiro.projchaos.customs;

import java.util.List;

import net.minecraft.server.v1_8_R3.EntityOcelot;
import net.minecraft.server.v1_8_R3.EntitySnowman;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.v1_8_R3.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;

import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import com.kiro.projchaos.methods.NMSUtils;

public class TZ extends EntityZombie
{

	@SuppressWarnings("unused")
	private final boolean isCustom;

	public TZ(World world)
	{
		super(world);
		isCustom = false;
	}

	@SuppressWarnings({})
	public TZ(CraftWorld world)
	{
		super(world.getHandle());
		isCustom = false;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public TZ(CraftWorld world, boolean isCustom)
	{
		super(world.getHandle());

		this.isCustom = isCustom;

		if (isCustom)
		{
			List goalB = (List) NMSUtils.getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
			goalB.clear();

			List goalC = (List) NMSUtils.getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
			goalC.clear();

			List targetB = (List) NMSUtils.getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
			targetB.clear();

			List targetC = (List) NMSUtils.getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
			targetC.clear();

			goalSelector.a(0, new PathfinderGoalFloat(this));
			goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntitySnowman.class, 1.0D, true));
			goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityOcelot.class, 1.0D, true));
			goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
			goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
			goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
			goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntitySnowman.class, 8.0F));
			goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityOcelot.class, 8.0F));
			goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
			targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
			targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntitySnowman.class, true));
			targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityOcelot.class, false));
		}
	}

}
