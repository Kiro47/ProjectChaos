package com.kiro.projchaos.methods.mods;

import net.minecraft.server.v1_8_R3.EntityHuman;

import com.kiro.projchaos.methods.Modifier;

public interface ModOnPickup extends Modifier
{

	void onPickup(EntityHuman entity);

}