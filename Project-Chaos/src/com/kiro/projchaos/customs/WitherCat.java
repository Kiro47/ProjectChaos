package com.kiro.projchaos.customs;

import com.kiro.projchaos.customs.meleeattacks.WitherCatHit;
import com.kiro.projchaos.methods.NMSUtils;

import net.minecraft.server.v1_8_R3.*;

import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import java.util.List;

public class WitherCat extends EntityOcelot
{

	@SuppressWarnings("unused")
	private final boolean isCustom;

	public WitherCat(World world)
	{
		super(world);
		isCustom = false;
	}

	@SuppressWarnings({})
	public WitherCat(CraftWorld world)
	{
		super(world.getHandle());
		isCustom = false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public WitherCat(CraftWorld world, boolean isCustom)
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
			goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, true));
			goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityWolf.class, 1.0D, true));
			goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
			goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
			goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
			goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
			targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
			targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
			targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityWolf.class, false));

			getAttributeInstance(GenericAttributes.maxHealth).setValue(30.0D);
			setCustomName(WitherCatHit.WitherCat_Name);
			setCustomNameVisible(true);
			setCatType(1);
			getAttributeInstance(GenericAttributes.c).setValue(0.5D);
		}
	}

}
