package com.kiro.projchaos;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import com.kiro.projchaos.customs.CustomSnowman;
import com.kiro.projchaos.customs.DemoMan;
import com.kiro.projchaos.customs.HelperBot;
import com.kiro.projchaos.customs.HydraSnowman;
import com.kiro.projchaos.customs.WitherCat;
import com.kiro.projchaos.customs.WolfPuck;

public class CommandManager implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{

		if (!(sender instanceof Player))
		{
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("projchaos.spawnmob"))
		{
			p.sendMessage("no perm"); return true;
		}

		if (args.length == 0)
		{
			p.sendMessage(ChatColor.DARK_PURPLE + "Mobs: " + ChatColor.DARK_AQUA + "withercat, hydrasnowman ,fireman, demoman, boss, wolfpuck");
			return true;
		}

		switch (args[0])
		{
			case "withercat":
				EntityTypes.spawnEntity(new WitherCat((CraftWorld) p.getLocation().getWorld(), true), p.getLocation());
				p.sendMessage("withercat");
				break;
			case "fireman":
				EntityTypes.spawnEntity(new CustomSnowman((CraftWorld) p.getLocation().getWorld(), true), p.getLocation());
				p.sendMessage("fireman spawned");
				break;
			case "demoman":
				EntityTypes.spawnEntity(new DemoMan((CraftWorld) p.getLocation().getWorld(), true), p.getLocation());
				p.sendMessage("demoman spawned");
				break;
			case "boss":
				EntityTypes.spawnEntity(new HelperBot((CraftWorld) p.getLocation().getWorld(), true), p.getLocation());
				p.sendMessage("boss spawned");
				break;
			case "wolfpuck":
				EntityTypes.spawnEntity(new WolfPuck((CraftWorld)p.getLocation().getWorld(), true), p.getLocation());
				p.sendMessage("wolfpuck spawned");
				break;
			case "hydrasnowman":
				EntityTypes.spawnEntity(new HydraSnowman((CraftWorld)p.getLocation().getWorld(), true), p.getLocation());
				p.sendMessage("hydarasnowman spawned");
				break;
			default:
				p.sendMessage("Invalid Error 27: Inproper Name, Exception IO-NULL");

		}


		return true;
	}

}
