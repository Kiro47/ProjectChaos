package com.kiro.projchaos.methods.mods;

import net.minecraft.server.v1_8_R3.EntityLiving;

import com.kiro.projchaos.methods.Modifier;

public interface ModProjectileLaunch extends Modifier
{

	void onLaunch(EntityLiving entityLiving, float f);

}