package com.kiro.projchaos.customs;

import java.util.List;

import net.minecraft.server.v1_8_R3.EntityOcelot;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntitySnowman;
import net.minecraft.server.v1_8_R3.EntityWolf;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;
import net.minecraft.server.v1_8_R3.PathfinderGoalArrowAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;

import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import com.kiro.projchaos.customs.listener.HydraSnowmanTactics;
import com.kiro.projchaos.methods.NMSUtils;

public class HydraSnowman extends EntitySnowman{


	private final boolean isCustom;

	public HydraSnowman(World world)
	{
		super(world);
		isCustom = false;
	}

	public HydraSnowman(CraftWorld world)
	{
		super(world.getHandle());
		isCustom = false;
	}


	@SuppressWarnings({"rawtypes", "unchecked"})
	public HydraSnowman(CraftWorld world, boolean isCustom)
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

			goalSelector.a(1, new PathfinderGoalArrowAttack(this, 1.5D, 25, 15.0F));
			goalSelector.a(2, new PathfinderGoalRandomStroll(this, 1.25D));
			goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, EntityPlayer.class, 11.0F));
			goalSelector.a(4, new PathfinderGoalRandomLookaround(this));
			
			targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityOcelot.class, true));
			targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, true));
			targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityWolf.class, true));
					
			getAttributeInstance(GenericAttributes.maxHealth).setValue(30.0D);
			setHealth(this.getMaxHealth());
			setCustomName(HydraSnowmanTactics.HS_SNOWMAN_NAME);
			setCustomNameVisible(true);
			getAttributeInstance(GenericAttributes.c).setValue(0.3D);
		}
	}


	@Override
	protected Item getLoot()
	{
		if (isCustom)
		{
			return Items.COOKIE;
		}
		return super.getLoot();
	}



}
