package com.kiro.projchaos.customs.meleeattacks;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import com.kiro.projchaos.NMSItems;


public class HelperBotStrike implements Listener{

	public static String HELPERBOT_NAME = (ChatColor.LIGHT_PURPLE + "HelperBot-Beta 0.2");

	@EventHandler
	public void onHelperbotDamageRecieve(EntityDamageByEntityEvent e) {
		
		if (!(e.getEntity() instanceof Skeleton)) return;
		Skeleton s = (Skeleton) e.getEntity();
		
		if (!(e.getDamager() instanceof Projectile) ) return;
		if (!(s.getSkeletonType().equals(SkeletonType.WITHER))) return;
		if (!(s.getCustomName().equals(HELPERBOT_NAME))) return;
		if (e.isCancelled()) return;
		else {
			Potion potion = new Potion(PotionType.WEAKNESS, 2);
			potion.setSplash(true);
			ItemStack is = new ItemStack(Material.POTION);
			potion.apply(is);
			ThrownPotion tp = s.launchProjectile(ThrownPotion.class);
			tp.setItem(is); 
			
			Potion potion2 = new Potion(PotionType.SLOWNESS, 3);
			potion2.setSplash(true);
			ItemStack is2 = new ItemStack(Material.POTION);
			potion2.apply(is2);
			ThrownPotion tp2 = s.launchProjectile(ThrownPotion.class);
			tp2.setItem(is2); 
			
			Potion potion3 = new Potion(PotionType.WATER);
			potion3.setSplash(true);
			potion3.setHasExtendedDuration(true);
			ItemStack is3 = new ItemStack(Material.POTION);
			potion3.apply(is3);
			ThrownPotion tp3 = s.launchProjectile(ThrownPotion.class);
			tp3.setItem(is3); 
			
			// Add custom effect to players and wolves
		
		}
	}

	@EventHandler
	public void onHelperBotSpawn(EntitySpawnEvent e) {
		if (!(e.getEntity() instanceof Skeleton)) return;
		Skeleton sk = (Skeleton) e.getEntity();
		if (!(sk.getSkeletonType().equals(SkeletonType.WITHER))) return;
		if (!(sk.getCustomName().equals(HELPERBOT_NAME))) return;
		else {
			sk.getEquipment().setItemInHand(NMSItems.sword());
	//		sk.getEquipment().setHelmet(NMSItems.helm());
			sk.getEquipment().setChestplate(NMSItems.chest());
			sk.getEquipment().setLeggings(NMSItems.legs());
			sk.getEquipment().setBoots(NMSItems.boots());
		}
		
	}
}
