package com.kiro.projchaos.customs.meleeattacks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WitherCatHit implements Listener{

		public static String WitherCat_Name = (ChatColor.BLACK + "WitherCat");
		@EventHandler
		public void onWitherCatHit(EntityDamageByEntityEvent e) {
			if (!(e.getDamager() instanceof Ocelot)) return;
			
			if ((!(e.getEntity() instanceof Monster)) && (!(e.getEntity() instanceof Player)) && (!(e.getEntity() instanceof Animals))) return;
	
	
			if (e.getDamager().getCustomName() == null)  return;
			
			if (e.getDamager().getCustomName().equals(WitherCat_Name)) {
				if (e.getEntity() instanceof Player) {
					Player p = (Player) e.getEntity();
					p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 260 ,0 ));
					return;
				}
				if (e.getEntity() instanceof Monster) {
					Monster ent = (Monster) e.getEntity();
					ent.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 260, 0));
					return;
				}
				if (e.getEntity() instanceof Animals){
					Animals an = (Animals) e.getEntity();
					an.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 260, 0));
					return;
					
				}
			}
		}
	
}
