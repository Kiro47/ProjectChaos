package com.kiro.projchaos;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import com.kiro.projchaos.customs.CustomSnowman;
import com.kiro.projchaos.customs.CustomZombie;
import com.kiro.projchaos.customs.WitherCat;

public class CommandManager implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	
		if (!(sender instanceof Player)) return true;
		Player p = (Player) sender;
		if (!(p.hasPermission("projchaos.spawnmob"))){ p.sendMessage("no perm"); return true;}
		
		if (args.length == 0) {
			p.sendMessage(ChatColor.DARK_PURPLE + "Mobs: " + ChatColor.DARK_AQUA + "zombie, withercat, fireman, demoman, boss" );
			return true;
		}
		
		switch(args[0]) {
			
		case "zombie":
			EntityTypes.spawnEntity(new CustomZombie((CraftWorld) p.getLocation().getWorld()), p.getLocation());
			p.sendMessage("zombie spawned");
			break;
		case "withercat":
			EntityTypes.spawnEntity(new WitherCat((CraftWorld) p.getLocation().getWorld()), p.getLocation());
			p.sendMessage("withercat");
			break;
		case "fireman":
			EntityTypes.spawnEntity(new CustomSnowman((CraftWorld) p.getLocation().getWorld()), p.getLocation());
			p.sendMessage("fireman spawned");
			break;
		default:
			p.sendMessage("Invalid Error 27");
		
		}
		
		
 		return true;
	}

}