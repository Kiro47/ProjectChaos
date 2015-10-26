package com.kiro.projchaos.methods.mods;

import net.minecraft.server.v1_8_R3.DamageSource;

import com.kiro.projchaos.methods.Modifier;

public interface ModDamage extends Modifier
{

	boolean damageEntity(DamageSource src, float amnt);

}