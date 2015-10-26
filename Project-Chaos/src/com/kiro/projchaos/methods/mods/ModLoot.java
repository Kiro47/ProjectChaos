package com.kiro.projchaos.methods.mods;

import net.minecraft.server.v1_8_R3.Item;

import com.kiro.projchaos.methods.Modifier;

public interface ModLoot extends Modifier
{

	Item getLoot();

}