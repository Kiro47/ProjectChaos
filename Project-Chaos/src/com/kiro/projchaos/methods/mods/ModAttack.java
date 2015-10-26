package com.kiro.projchaos.methods.mods;

import net.minecraft.server.v1_8_R3.Entity;

import com.kiro.projchaos.methods.Modifier;

public interface ModAttack extends Modifier
{
	boolean attackEntity(Entity entity);

}