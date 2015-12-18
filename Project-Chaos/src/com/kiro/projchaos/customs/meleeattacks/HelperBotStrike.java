package com.kiro.projchaos.customs.meleeattacks;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import com.kiro.projchaos.EntityTypes;
import com.kiro.projchaos.NMSItems;
import com.kiro.projchaos.customs.WitherCat;
import com.kiro.projchaos.methods.RngRangeGen;
import com.kiro.projchaos.nms.ParticleUtils;
import com.kiro.projchaos.nms.ParticleUtils.ParticleType;


public class HelperBotStrike implements Listener{

	public static String HELPERBOT_NAME = (ChatColor.LIGHT_PURPLE + "HelperBot-Beta 0.3");

	@EventHandler
	public void onHelperbotDamageRecieve(EntityDamageByEntityEvent e) {
		
		if (!(e.getEntity() instanceof Skeleton)) return;
		Skeleton s = (Skeleton) e.getEntity();
		
		if (!(e.getDamager() instanceof Projectile) ) return;
		if (!(s.getSkeletonType().equals(SkeletonType.WITHER))) return;
		if (s.getCustomName() == null) return;
		if (!(s.getCustomName().equals(HELPERBOT_NAME))) return;
		if (e.isCancelled()) return;
		else {
			Potion potion = new Potion(PotionType.WEAKNESS, 2);
			potion.setSplash(true);
			ItemStack is = new ItemStack(Material.POTION);
			potion.apply(is);
			ThrownPotion tp = s.launchProjectile(ThrownPotion.class);
			tp.setItem(is); 
			
			Potion potion2 = new Potion(PotionType.SLOWNESS, 2);
			potion2.setSplash(true);
			ItemStack is2 = new ItemStack(Material.POTION);
			potion2.apply(is2);
			ThrownPotion tp2 = s.launchProjectile(ThrownPotion.class);
			tp2.setItem(is2); 
			
			Potion potion3 = new Potion(PotionType.WATER);
			potion3.setSplash(true);
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
		if (sk.getCustomName() == null) return;
		if (!(sk.getCustomName().equals(HELPERBOT_NAME))) return;
		else {
			sk.getEquipment().setItemInHand(NMSItems.sword());
			sk.getEquipment().setHelmet(NMSItems.helm());
			sk.getEquipment().setChestplate(NMSItems.chest());
			sk.getEquipment().setLeggings(NMSItems.legs());
			sk.getEquipment().setBoots(NMSItems.boots());
		}
		
	}
	@EventHandler
	public void onHelperPain(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Potion) return;
		if (!(e.getEntity() instanceof Skeleton)) return;
		Skeleton sk = (Skeleton) e.getEntity();
		if (!(sk.getSkeletonType().equals(SkeletonType.WITHER))) return;
		if (sk.getCustomName() == null) return;
		if (!(sk.getCustomName().equals(HELPERBOT_NAME))) return;
		
		else {
				if (sk.getHealth() > 1) {
					if (sk.getHealth() > 750) return;
					if (sk.getHealth() <  755) {
								if (sk.getHealth() > 740) {
										ParticleUtils.playEffect(ParticleType.EXPLODE, e.getEntity().getLocation(), 5, 2);
										sk.getLocation().getWorld().createExplosion(sk.getLocation().getX(), sk.getLocation().getY(), sk.getLocation().getZ(), 4.0F, false, false);
									}
					if (sk.getHealth() < 510) {
								if (sk.getHealth() > 490) {
									ParticleUtils.playEffect(ParticleType.EXPLODE, e.getEntity().getLocation(), 5, 2);
									sk.getLocation().getWorld().createExplosion(sk.getLocation().getX(), sk.getLocation().getY(), sk.getLocation().getZ(), 6.0F, false, false);
								}
								}
					if (sk.getHealth() < 260) {
							if (sk.getHealth() > 245) {
								ParticleUtils.playEffect(ParticleType.EXPLODE, e.getEntity().getLocation(), 5, 2);
								sk.getLocation().getWorld().createExplosion(sk.getLocation().getX(), sk.getLocation().getY(), sk.getLocation().getZ(), 8.0F, false, false);
							}
					}
					if (sk.getHealth() < 20) {
						ParticleUtils.playEffect(ParticleType.EXPLODE, e.getEntity().getLocation(), 5, 2);
						sk.getLocation().getWorld().createExplosion(sk.getLocation().getX(), sk.getLocation().getY(), sk.getLocation().getZ(), 10.0F, false, false);
					}
					}
				}
			
			int in = RngRangeGen.randInt(0, 100);
			if (in > 25){
			EntityTypes.spawnEntity(new WitherCat(((CraftWorld) e.getDamager().getWorld()), true), e.getDamager().getLocation().add(3.0D, 1.0D, 3.0D));
			EntityTypes.spawnEntity(new WitherCat(((CraftWorld) e.getDamager().getWorld()), true), e.getDamager().getLocation().add(0.0D, 1.0D, 3.0D));
			EntityTypes.spawnEntity(new WitherCat(((CraftWorld) e.getDamager().getWorld()), true), e.getDamager().getLocation().add(3.0D, 1.0D, 0.0D));
//			EntityTypes.spawnEntity(new WitherCat(((CraftWorld) e.getDamager().getWorld()), true), e.getDamager().getLocation().add(5.0D, 1.0D, 3.0D));
//			EntityTypes.spawnEntity(new WitherCat(((CraftWorld) e.getDamager().getWorld()), true), e.getDamager().getLocation().add(3.0D, 1.0D, 5.0D));
			}
			
			if (e.getDamage() > 10.5D) {
			//	EntityTypes.spawnEntity(new DemoMan(((CraftWorld) e.getEntity().getWorld()), true), e.getEntity().getLocation().add(1.0D, 2.0D, 6.0D));
			//	EntityTypes.spawnEntity(new DemoMan(((CraftWorld) e.getEntity().getWorld()), true), e.getEntity().getLocation().add(6.0D, 2.0D, 1.0D));
				if (in > 45){
				e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation().add(1.0D, 1.0D, 1.0D), EntityType.CAVE_SPIDER);
				e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation().add(1.0D, 1.0D, 1.0D), EntityType.CAVE_SPIDER);
				}
				
				if (e.getDamage() > 12.0D) {
					if (in > 60) {
						flyingGuardian(e.getEntity());
					}
					// Spawn wither skeleton x2
				//	sk.getLocation().getWorld().spawnEntity(sk.getLocation(), EntityType.)
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	private static EntityType skel() {
		// wither skelton


		
			
		return null;
		
	}
	
	private void flyingGuardian(Entity entity) {
		World world = entity.getLocation().getWorld();
		Guardian guard = (Guardian) world.spawn(entity.getLocation().add(3.0D, 5.0D, 1.0D), Guardian.class);
		Bat bat = (Bat) world.spawn(entity.getLocation().add(3.0D, 4.0D, 1.0D), Bat.class);
		
		guard.setRemoveWhenFarAway(false);
		bat.setPassenger(guard);
		bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 3, false, false));
		bat.setRemoveWhenFarAway(false);
		
		Guardian guard1 = (Guardian) world.spawn(entity.getLocation().add(1.0D, 5.0D, 3.0D), Guardian.class);
		Bat bat1 = (Bat) world.spawn(entity.getLocation().add(1.0D, 4.0D, 3.0D), Bat.class);
		
		guard1.setRemoveWhenFarAway(false);
		bat1.setPassenger(guard);
		bat1.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 3, false, false));
		bat1.setRemoveWhenFarAway(false);
	}
}
