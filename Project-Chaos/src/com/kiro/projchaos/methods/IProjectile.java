package com.kiro.projchaos.methods;

import net.minecraft.server.v1_8_R3.Entity;

import org.bukkit.util.Vector;

public interface IProjectile extends IEntity
{

	void shoot(double px, double py, double pz, float yaw, float pitch);

	Entity getShooter();

	Vector getVelocity();
}