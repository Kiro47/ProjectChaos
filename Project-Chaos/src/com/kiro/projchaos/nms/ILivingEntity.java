package com.kiro.projchaos.nms;

import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.PathfinderGoal;

import com.kiro.projchaos.methods.IEntity;

public interface ILivingEntity extends IEntity
{


	void moveTo(double px, double py, double pz);

	void teleportTo(double px, double py, double pz);

	void clearAI();

	void addGoal(PathfinderGoal goal, int p);

	void addTarget(PathfinderGoal goal, int p);

	boolean inWater();

	boolean setNewAI(boolean newAI);

	void defaultCollide(Entity entity);

	void defaultEntityTick();

	void defaultMobTick();

	String saySound();

	String hurtSound();

	String deathSound();

	boolean _damageEntity(DamageSource src, float amnt);


}