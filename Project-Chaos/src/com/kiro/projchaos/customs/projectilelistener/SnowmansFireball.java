package com.kiro.projchaos.customs.projectilelistener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class SnowmansFireball implements Listener	{
	
	public static String SNOWMAN_NAME = (ChatColor.RED + "Fireman");
	public static String SNOWBALL_NAME = "snowball543";
	@EventHandler
	public void onSnowballLaunch(ProjectileLaunchEvent e) {
		if (!(e.getEntity().getShooter() instanceof Snowman)){	
			return;
		}
		
		Snowman sm = (Snowman) e.getEntity().getShooter();
		
		if (sm.getCustomName() == null) return;
		if (sm.getCustomName().equals(SNOWMAN_NAME)) {
			e.getEntity().setFireTicks(30);
			e.getEntity().setCustomName(SNOWBALL_NAME);
			return;
		}
	}
	
	@EventHandler
	public void onSnowballHit(EntityDamageByEntityEvent e) {
		
		if (!(e.getDamager() instanceof Snowball)) {
			return;
		}
		
		if (e.getDamager().getCustomName() == null) return;
		if (e.getDamager().getCustomName().equals(SNOWBALL_NAME)) {
			e.getEntity().setFireTicks(100);
			e.setDamage(4.0D);
			return;
		}
	}

}
