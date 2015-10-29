package com.kiro.projchaos.customs;

import java.util.List;

import net.minecraft.server.v1_8_R3.EntityOcelot;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntitySkeleton;
import net.minecraft.server.v1_8_R3.EntityWolf;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.Items;
import net.minecraft.server.v1_8_R3.PathfinderGoalArrowAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;

import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import com.kiro.projchaos.customs.goals.SummonMobGoal;
import com.kiro.projchaos.customs.meleeattacks.HelperBotStrike;
import com.kiro.projchaos.customs.projectilelistener.SnowmansFireball;
import com.kiro.projchaos.methods.NMSUtils;

public class HelperBot extends EntitySkeleton{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HelperBot(org.bukkit.craftbukkit.v1_8_R3.CraftWorld world) {
		super(((CraftWorld)world).getHandle());
		
		List goalB = (List)NMSUtils.getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();

		List goalC = (List)NMSUtils.getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
		
		List targetB = (List)NMSUtils.getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
		
		List targetC = (List)NMSUtils.getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
		
		this.goalSelector.a(1, new PathfinderGoalFloat(this));
		this.goalSelector.a(2, new SummonMobGoal(this));
		this.goalSelector.a(3, new PathfinderGoalMeleeAttack(this, EntityPlayer.class, 1.0D, true));
		this.goalSelector.a(4, new PathfinderGoalRandomStroll(this, 3.0D));
		this.goalSelector.a(5, new PathfinderGoalLookAtPlayer(this, EntityPlayer.class, 8.0F));
		this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
		this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 2.0D));
		
		this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityWolf.class, true));
		this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityOcelot.class, true));
	}

	protected void initAttributes(){
		super.initAttributes();
		this.getAttributeInstance(GenericAttributes.maxHealth).setValue(1000.0D);
		this.setSkeletonType(1);
		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.0);
		this.setCustomName(HelperBotStrike.HELPERBOT_NAME);
		this.setCustomNameVisible(true);
		this.getAttributeInstance(GenericAttributes.c).setValue(0.3D);
	}
	
	protected Item getLoot() {
		return Items.FIRE_CHARGE;
	}
	
	
}