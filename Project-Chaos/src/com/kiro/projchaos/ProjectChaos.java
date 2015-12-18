package com.kiro.projchaos;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.kiro.projchaos.customs.listener.HydraSnowmanTactics;
import com.kiro.projchaos.customs.meleeattacks.HelperBotStrike;
import com.kiro.projchaos.customs.meleeattacks.WitherCatHit;
import com.kiro.projchaos.customs.meleeattacks.WolfPuckMelee;
import com.kiro.projchaos.customs.projectilelistener.DemoManBlastball;
import com.kiro.projchaos.customs.projectilelistener.SnowmansFireball;
import com.kiro.projchaos.nms.NMSHandler;

public class ProjectChaos extends JavaPlugin
{

	public void onEnable()
	{
		getCommand("chaos").setExecutor(new CommandManager());

		EntityTypes.values();
		NMSHandler.registerMobs();

		Bukkit.getPluginManager().registerEvents(new SnowmansFireball(), this);
		Bukkit.getPluginManager().registerEvents(new WitherCatHit(), this);
		Bukkit.getPluginManager().registerEvents(new DemoManBlastball(), this);
		Bukkit.getPluginManager().registerEvents(new HelperBotStrike(), this);
		Bukkit.getPluginManager().registerEvents(new WolfPuckMelee(), this);
		Bukkit.getPluginManager().registerEvents(new HydraSnowmanTactics(), this);
	}
}
