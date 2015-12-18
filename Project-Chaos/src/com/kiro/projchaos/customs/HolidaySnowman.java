package com.kiro.projchaos.customs;

import java.util.List;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntitySnowman;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.PathfinderGoalArrowAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;

import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import com.kiro.projchaos.customs.projectilelistener.DemoManBlastball;
import com.kiro.projchaos.methods.NMSUtils;


public class HolidaySnowman extends EntitySnowman{
	
	@SuppressWarnings("unused")
	private final boolean isCustom;

	public HolidaySnowman(World world)
	{
		super(world);
		isCustom = false;
	}

	public HolidaySnowman(CraftWorld world)
	{
		super(world.getHandle());
		isCustom = false;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public HolidaySnowman(CraftWorld world, boolean isCustom)
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

			goalSelector.a(1, new PathfinderGoalArrowAttack(this, 1.25D, 20, 10.0F));
			goalSelector.a(2, new PathfinderGoalRandomStroll(this, 1.0D));
			goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, EntityPlayer.class, 6.0F));
			goalSelector.a(4, new PathfinderGoalRandomLookaround(this));
			targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, true));
			getAttributeInstance(GenericAttributes.maxHealth).setValue(40.0D);
			setHealth(this.getMaxHealth());
			setCustomName(DemoManBlastball.DEMO_MAN_NAME);
			setCustomNameVisible(true);
			getAttributeInstance(GenericAttributes.c).setValue(0.5D);
		}
	}
	
}
