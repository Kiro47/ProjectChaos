package com.kiro.projchaos.customs.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.kiro.projchaos.EntityTypes;
import com.kiro.projchaos.customs.HydraSnowman;
import com.kiro.projchaos.nms.ParticleUtils;
import com.kiro.projchaos.nms.ParticleUtils.ParticleType;

public class HydraSnowmanTactics implements Listener{

	public static String HS_SNOWMAN_SNOWBALL = "kajdawjeop";
	public static String HS_SNOWMAN_NAME = (ChatColor.AQUA + "SnowHydra");
	
	@EventHandler
	public void onHSDeath(EntityDeathEvent e) {
		if (!(e.getEntity() instanceof Snowman)) return;
		Snowman sn = (Snowman) e.getEntity();
		if (!(sn.getCustomName().equals(HS_SNOWMAN_NAME))) return;
		else {
			
			Location loc = e.getEntity().getLastDamageCause().getEntity().getLocation();
				if (sn.getLastDamageCause().getCause().equals(DamageCause.FIRE)) {
					return;
				}
				if	 (sn.getLastDamageCause().getCause().equals(DamageCause.MAGIC)){
					return;
				}
				if	 (sn.getLastDamageCause().getCause().equals(DamageCause.FIRE_TICK)) return;
				snowballPuff(loc);
				EntityTypes.spawnEntity(new HydraSnowman((CraftWorld)loc.getWorld(), true), new Location(loc.getWorld(), loc.getX() + 1, 46.0, loc.getZ() - 1));
				EntityTypes.spawnEntity(new HydraSnowman((CraftWorld)loc.getWorld(), true), new Location(loc.getWorld(), loc.getX() - 1, 46.0, loc.getZ() + 1));
				return;
		
		}
	}
	@EventHandler
	public void onHydAttack(ProjectileLaunchEvent e) {
		if (!(e.getEntity().getShooter() instanceof Snowman)){	
			return;
		}
		
		Snowman sm = (Snowman) e.getEntity().getShooter();
		
		if (sm.getCustomName() == null) return;
		if (sm.getCustomName().equals(HS_SNOWMAN_NAME)) {
			e.getEntity().setCustomName(HS_SNOWMAN_SNOWBALL);
			return;
		}
	
	}
	
	@EventHandler
	public void onHydSBHit(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Snowball)) return;
		Snowball sb = (Snowball) e.getDamager();	
		if (sb.getCustomName() == null) return;
		if (!(sb.getCustomName().equals(HS_SNOWMAN_SNOWBALL))) return;
		else {
			e.setDamage(7.0D);
				if (e.getEntity() instanceof Player) {
					Player pl = (Player) e.getEntity();
					pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 8*20, 1, true, true));
				}
			return;
		}
	}
	
	private void snowballPuff(Location loc){
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc, 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(1.0, 0.0, -1.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(-3, 2.0, 0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(2.0, 1.0, -1.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(2.0, 3.0, 2.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(0.0, 3.0, 0.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(-1.0, 3.0, -2.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(-2.0, 2.0, 1.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(1.0, 1.0, 2.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(3.0, 2.0, -3.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(1.0, 2.0, 1.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(1.0, 1.0, 0.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(0.0, 2.0, 3.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(1.0, 2.0, -1.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(2.0, 1.0, -1.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(-1.0, 2.0, 3.0), 1, 5);
		ParticleUtils.playEffect(ParticleType.MAGIC_CRIT, loc.add(-1.0, 2.0, -3.0), 1, 5);
		return;
	}
}