package com.kiro.projchaos.customs;

import java.util.List;

import net.minecraft.server.v1_8_R3.EntityOcelot;
import net.minecraft.server.v1_8_R3.EntityVillager;
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

import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import com.kiro.projchaos.methods.NMSUtils;

public class CustomZombie extends EntityZombie{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CustomZombie(org.bukkit.craftbukkit.v1_8_R3.CraftWorld world) {
		super(((CraftWorld)world).getHandle());
		
		List goalB = (List)NMSUtils.getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();

		List goalC = (List)NMSUtils.getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
		
		List targetB = (List)NMSUtils.getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
		
		List targetC = (List)NMSUtils.getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
		
		this.goalSelector.a(0, new PathfinderGoalFloat(this));
		this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityOcelot.class, 1.0D, true));
		this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityVillager.class, 1.0D, true));
	    this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
	    this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
	    this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
	    this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityOcelot.class, 8.0F));
	    this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
	    this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
	    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityOcelot.class, true));
	    this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class,false));
	}
	
	
	
	
	
}