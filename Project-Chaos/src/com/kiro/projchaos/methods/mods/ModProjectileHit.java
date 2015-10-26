package com.kiro.projchaos.methods.mods;

import net.minecraft.server.v1_8_R3.MovingObjectPosition;

import com.kiro.projchaos.methods.Modifier;

public interface ModProjectileHit extends Modifier
{

	void onHit(MovingObjectPosition entity);

}