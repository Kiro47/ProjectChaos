package com.kiro.projchaos.methods;

import org.bukkit.util.Vector;
import com.kiro.projchaos.methods.Modifier;;

public interface IEntity
{
	void registerMods(Modifier modifier);

	Vector getLocation();

	void defaultDie();

}