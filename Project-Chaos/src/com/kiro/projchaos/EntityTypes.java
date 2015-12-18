package com.kiro.projchaos;

import com.kiro.projchaos.customs.*;
import com.kiro.projchaos.methods.NMSUtils;
import com.kiro.projchaos.nms._Skeleton;
import com.kiro.projchaos.nms._Zombie;
import net.minecraft.server.v1_8_R3.Entity;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import java.util.Map;

public enum EntityTypes
{

	// NAME("ENTITY name, Entity ID, customclass.class
	WITHER_CAT("Ocelot", 98, WitherCat.class),
	Fireman("Snowman", 97, CustomSnowman.class),
	Demoman("Snowman", 97, DemoMan.class),
	BasicSkeleton("Skeleton", 51, _Skeleton.class),
	HelperBot("Skeleton", 51, HelperBot.class),
	BasicZombie("Zombie", 54, _Zombie.class),
	WolfPuck("Wolf", 95 , WolfPuck.class),
	HolidaySnowman("Snowman", 97, HolidaySnowman.class),
	HydraSnowman("Snowman", 97, HydraSnowman.class);

	EntityTypes(String name, int id, Class<? extends Entity> custom)
	{
		addToMaps(custom, name, id);
	}

	public static void spawnEntity(Entity entity, Location loc)
	{
		entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		((CraftWorld) loc.getWorld()).getHandle().addEntity(entity, SpawnReason.CUSTOM);
	}


	@SuppressWarnings({"unchecked", "rawtypes"})
	private static void addToMaps(Class clazz, String name, int id)
	{
		((Map) NMSUtils.getPrivateField("c", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(name, clazz);
		((Map) NMSUtils.getPrivateField("d", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(clazz, name);

		((Map) NMSUtils.getPrivateField("f", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(clazz, Integer.valueOf(id));
	}

}


