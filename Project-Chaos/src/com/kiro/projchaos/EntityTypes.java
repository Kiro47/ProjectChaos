package com.kiro.projchaos;

import java.util.Map;

import net.minecraft.server.v1_8_R3.Entity;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.kiro.projchaos.customs.CustomSnowman;
import com.kiro.projchaos.customs.DemoMan;
import com.kiro.projchaos.customs.WitherCat;
import com.kiro.projchaos.customs.HelperBot;
import com.kiro.projchaos.methods.NMSUtils;

public enum EntityTypes {

	// NAME("ENTITY name, Entity ID, customclass.class
	WITHER_CAT("Ocelot", 98, WitherCat.class),
	Fireman("Snowman",97 , CustomSnowman.class ),
	Demoman("Snowman", 97 , DemoMan.class),
	HelperBot("Skeleton", 51, HelperBot.class);

	private EntityTypes(String name, int id, Class<? extends Entity> custom) {
		addToMaps(custom, name,id);
}

	public static void spawnEntity(Entity entity, Location loc) {
		entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		((CraftWorld)loc.getWorld()).getHandle().addEntity(entity, SpawnReason.CUSTOM);
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void addToMaps(Class clazz , String name , int id) {
		((Map)NMSUtils.getPrivateField("c", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(name, clazz);
		((Map)NMSUtils.getPrivateField("d", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(clazz, name);
		
		((Map)NMSUtils.getPrivateField("f", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(clazz, Integer.valueOf(id));
	}
	
		}


