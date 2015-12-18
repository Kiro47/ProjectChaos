package com.kiro.projchaos.methods;

import org.bukkit.util.Vector;

public interface IEntity
{
	void registerMods(Modifier modifier);

	Vector getLocation();

	void defaultDie();

}