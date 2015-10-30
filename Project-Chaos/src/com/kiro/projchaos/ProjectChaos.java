package com.kiro.projchaos;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.kiro.projchaos.customs.meleeattacks.HelperBotStrike;
import com.kiro.projchaos.customs.meleeattacks.WitherCatHit;
import com.kiro.projchaos.customs.projectilelistener.DemoManBlastball;
import com.kiro.projchaos.customs.projectilelistener.SnowmansFireball;

public class ProjectChaos extends JavaPlugin{

	public void onEnable() {
		getCommand("chaos").setExecutor(new CommandManager());
		
		Bukkit.getPluginManager().registerEvents(new SnowmansFireball(), this);
		Bukkit.getPluginManager().registerEvents(new WitherCatHit(), this);
		Bukkit.getPluginManager().registerEvents(new DemoManBlastball() , this);
		Bukkit.getPluginManager().registerEvents(new HelperBotStrike(), this);
	}
}
