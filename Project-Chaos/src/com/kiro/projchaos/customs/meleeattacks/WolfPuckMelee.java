package com.kiro.projchaos.customs.meleeattacks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class WolfPuckMelee implements Listener{
	
	public static String WOLF_NAME = (ChatColor.GOLD + "Wolf Puck!");
	
	@EventHandler
	public void onPuckDamage(EntityDamageByEntityEvent e) {
		
		if (!(e.getEntity() instanceof Wolf)) return;
		Wolf w = (Wolf) e.getEntity();
		if (w.getCustomName().equalsIgnoreCase(WOLF_NAME)) {
			e.setDamage(0.0D);
			return;
		}
		
	}

}
