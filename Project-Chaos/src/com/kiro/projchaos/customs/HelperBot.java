package com.kiro.projchaos.customs;

import com.kiro.projchaos.customs.goals.SummonMobGoal;
import com.kiro.projchaos.customs.meleeattacks.HelperBotStrike;
import com.kiro.projchaos.methods.NMSUtils;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import java.util.List;

public class HelperBot extends EntitySkeleton
{

	private final boolean isCustom;

	public HelperBot(World world)
	{
		super(world);
		isCustom = false;
		System.out.println("HelperBot Spawned Naturally!");
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public HelperBot(CraftWorld world, boolean isCustom)
	{
		super(world.getHandle());
		this.isCustom = isCustom;

		if (this.isCustom)
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
			goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityWolf.class, 1.0D, true));
			goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityOcelot.class, 1.0D, true));
			goalSelector.a(3, new SummonMobGoal(this));
			goalSelector.a(4, new PathfinderGoalRandomStroll(this, 3.0D));
			goalSelector.a(5, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
			goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
			goalSelector.a(7, new PathfinderGoalRandomStroll(this, 2.0D));

			targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
			targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
			targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityWolf.class, true));
			targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityOcelot.class, true));

			getAttributeInstance(GenericAttributes.maxHealth).setValue(1000.0D);
			setSkeletonType(1);
			setCustomName(HelperBotStrike.HELPERBOT_NAME);
			setCustomNameVisible(true);
			getAttributeInstance(GenericAttributes.c).setValue(0.3D);
			//		this.setEquipment(0, NMSItems.sword());
			//		this.setEquipment(4, NMSItems.helm());
			//		this.setEquipment(3, NMSItems.chest());
			//		this.setEquipment(2, NMSItems.legs());
			//		this.setEquipment(1, NMSItems.boots());
		}

	}

}