package com.kiro.projchaos.customs;

import java.util.List;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntitySnowman;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;
import net.minecraft.server.v1_8_R3.PathfinderGoalArrowAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;

import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import com.kiro.projchaos.customs.projectilelistener.DemoManBlastball;
import com.kiro.projchaos.methods.NMSUtils;

public class DemoMan extends EntitySnowman{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DemoMan(org.bukkit.craftbukkit.v1_8_R3.CraftWorld world) {
		super(((CraftWorld)world).getHandle());
		
		List goalB = (List)NMSUtils.getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();

		List goalC = (List)NMSUtils.getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
		
		List targetB = (List)NMSUtils.getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
		
		List targetC = (List)NMSUtils.getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
		
		this.goalSelector.a(1, new PathfinderGoalArrowAttack(this, 1.25D, 20, 10.0F));
		this.goalSelector.a(2, new PathfinderGoalRandomStroll(this, 1.0D));
		this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, EntityPlayer.class, 6.0F));
		this.goalSelector.a(4, new PathfinderGoalRandomLookaround(this));
		this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, true) );
	}

	protected void initAttributes(){
		super.initAttributes();
		this.getAttributeInstance(GenericAttributes.maxHealth).setValue(500.0D);
		this.setCustomName(DemoManBlastball.DEMO_MAN_NAME);
		this.setCustomNameVisible(true);
		this.getAttributeInstance(GenericAttributes.c).setValue(0.5D);
	}
	
	protected Item getLoot() {
		return Items.GUNPOWDER;
	}
	
	
}