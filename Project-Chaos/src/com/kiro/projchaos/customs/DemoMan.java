package com.kiro.projchaos.customs;

import com.kiro.projchaos.customs.projectilelistener.DemoManBlastball;
import com.kiro.projchaos.methods.NMSUtils;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import java.util.List;

public class DemoMan extends EntitySnowman
{
	private final boolean isCustom;

	public DemoMan(CraftWorld world)
	{
		super(world.getHandle());
		isCustom = false;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public DemoMan(CraftWorld world, boolean isCustom)
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
		}
	}

	@Override
	protected void initAttributes()
	{
		super.initAttributes();
		if (isCustom)
		{
			getAttributeInstance(GenericAttributes.maxHealth).setValue(500.0D);
			setCustomName(DemoManBlastball.DEMO_MAN_NAME);
			setCustomNameVisible(true);
			getAttributeInstance(GenericAttributes.c).setValue(0.5D);
		}
	}

	@Override
	protected Item getLoot()
	{
		if (isCustom)
		{
			return Items.GUNPOWDER;
		}
		return super.getLoot();
	}


}