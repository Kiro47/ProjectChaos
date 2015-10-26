package com.kiro.projchaos.nms;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityArrow;

import com.kiro.projchaos.EntityTypes;
import com.kiro.projchaos.nms._Arrow;
import com.kiro.projchaos.methods.Modifier;


public class NMSHandler
{


	public static void registerMobs()
	{
		try
		{
			//			a(EntityItem.class, "Item", 1);
			//			a(EntityExperienceOrb.class, "XPOrb", 2);
			//			a(EntityEgg.class, "ThrownEgg", 7);
			//			a(EntityLeash.class, "LeashKnot", 8);
			//			a(EntityPainting.class, "Painting", 9);
			a(_Arrow.class, "Arrow", 10);
			//			a(EntitySnowball.class, "Snowball", 11);
	//		a(_LargeFireball.class, "Fireball", 12);
//			a(_SmallFireball.class, "SmallFireball", 13);
			//			a(EntityEnderPearl.class, "ThrownEnderpearl", 14);
			//			a(EntityEnderSignal.class, "EyeOfEnderSignal", 15);
			//			a(EntityPotion.class, "ThrownPotion", 16);
			//			a(EntityThrownExpBottle.class, "ThrownExpBottle", 17);
			//			a(EntityItemFrame.class, "ItemFrame", 18);
//			a(_WitherSkull.class, "WitherSkull", 19);
			//			a(EntityTNTPrimed.class, "PrimedTnt", 20);
			//a(_FallingBlock.class, "FallingSand", 21);
			//			a(EntityFireworks.class, "FireworksRocketEntity", 22);
			//			a(EntityArmorStand.class, "ArmorStand", 30);
			//			a(EntityBoat.class, "Boat", 41);
			//			a(EntityMinecartRideable.class, EntityMinecartAbstract.EnumMinecartType.RIDEABLE.b(), 42);
			//			a(EntityMinecartChest.class, EntityMinecartAbstract.EnumMinecartType.CHEST.b(), 43);
			//			a(EntityMinecartFurnace.class, EntityMinecartAbstract.EnumMinecartType.FURNACE.b(), 44);
			//			a(EntityMinecartTNT.class, EntityMinecartAbstract.EnumMinecartType.TNT.b(), 45);
			//			a(EntityMinecartHopper.class, EntityMinecartAbstract.EnumMinecartType.HOPPER.b(), 46);
			//			a(EntityMinecartMobSpawner.class, EntityMinecartAbstract.EnumMinecartType.SPAWNER.b(), 47);
			//			a(EntityMinecartCommandBlock.class, EntityMinecartAbstract.EnumMinecartType.COMMAND_BLOCK.b(), 40);
			//			a(EntityInsentient.class, "Mob", 48);
			//			a(EntityMonster.class, "Monster", 49);
//			a(_Creeper.class, "Creeper", 50);
//			a(_Skeleton.class, "Skeleton", 51);
//			a(_Spider.class, "Spider", 52);
//			a(_Giant.class, "Giant", 53);
//			a(_Zombie.class, "Zombie", 54);
// a (_Slime.class, "Slime", 55);
//			a(_Ghast.class, "Ghast", 56);
	//		a(_PigZombie.class, "PigZombie", 57);
//			a(_Enderman.class, "Enderman", 58);
//			a(_CaveSpider.class, "CaveSpider", 59);
	//		a(_Silverfish.class, "Silverfish", 60);
//			a(_Blaze.class, "Blaze", 61);
	//		a(_MagmaCube.class, "LavaSlime", 62);
//			a(_EnderDragon.class, "EnderDragon", 63);
//			a(_Wither.class, "WitherBoss", 64);
//			a(_Bat.class, "Bat", 65);
//			a(_Witch.class, "Witch", 66);
/*			a(_Endermite.class, "Endermite", 67);
			a(_Guardian.class, "Guardian", 68);
			a(_Pig.class, "Pig", 90);
			a(_Sheep.class, "Sheep", 91);
			a(_Cow.class, "Cow", 92);
			a(_Chicken.class, "Chicken", 93);
			a(_Squid.class, "Squid", 94);
			a(_Wolf.class, "Wolf", 95);
			a(_Mooshroom.class, "MushroomCow", 96);
			a(_SnowGolem.class, "SnowMan", 97);
			a(_Ocelot.class, "Ozelot", 98);
			a(_IronGolem.class, "VillagerGolem", 99);
			a(_Horse.class, "EntityHorse", 100);
			a(_Rabbit.class, "Rabbit", 101);
			a(_Villager.class, "Villager", 120);
			a(_EnderCrystal.class, "EnderCrystal", 200);
			*/
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void unregisterMobs()
	{
		try
		{
			//			a(EntityItem.class, "Item", 1);
			//			a(EntityExperienceOrb.class, "XPOrb", 2);
			//			a(EntityEgg.class, "ThrownEgg", 7);
			//			a(EntityLeash.class, "LeashKnot", 8);
			//			a(EntityPainting.class, "Painting", 9);
			a(EntityArrow.class, "Arrow", 10);
			//			a(EntitySnowball.class, "Snowball", 11);
	//		a(EntityLargeFireball.class, "Fireball", 12);
	///		a(EntitySmallFireball.class, "SmallFireball", 13);
			//			a(EntityEnderPearl.class, "ThrownEnderpearl", 14);
			//			a(EntityEnderSignal.class, "EyeOfEnderSignal", 15);
			//			a(EntityPotion.class, "ThrownPotion", 16);
			//			a(EntityThrownExpBottle.class, "ThrownExpBottle", 17);
			//			a(EntityItemFrame.class, "ItemFrame", 18);
	//		a(EntityWitherSkull.class, "WitherSkull", 19);
			//			a(EntityTNTPrimed.class, "PrimedTnt", 20);
			//a(EntityFallingBlock.class, "FallingSand", 21);
			//			a(EntityFireworks.class, "FireworksRocketEntity", 22);
			//			a(EntityArmorStand.class, "ArmorStand", 30);
			//			a(EntityBoat.class, "Boat", 41);
			//			a(EntityMinecartRideable.class, EntityMinecartAbstract.EnumMinecartType.RIDEABLE.b(), 42);
			//			a(EntityMinecartChest.class, EntityMinecartAbstract.EnumMinecartType.CHEST.b(), 43);
			//			a(EntityMinecartFurnace.class, EntityMinecartAbstract.EnumMinecartType.FURNACE.b(), 44);
			//			a(EntityMinecartTNT.class, EntityMinecartAbstract.EnumMinecartType.TNT.b(), 45);
			//			a(EntityMinecartHopper.class, EntityMinecartAbstract.EnumMinecartType.HOPPER.b(), 46);
			//			a(EntityMinecartMobSpawner.class, EntityMinecartAbstract.EnumMinecartType.SPAWNER.b(), 47);
			//			a(EntityMinecartCommandBlock.class, EntityMinecartAbstract.EnumMinecartType.COMMAND_BLOCK.b(), 40);
			//			a(EntityInsentient.class, "Mob", 48);
			//			a(EntityMonster.class, "Monster", 49);
	/*		a(EntityCreeper.class, "Creeper", 50);
			a(EntitySkeleton.class, "Skeleton", 51);
			a(EntitySpider.class, "Spider", 52);
			a(EntityGiantZombie.class, "Giant", 53);
			a(EntityZombie.class, "Zombie", 54);
			a(EntitySlime.class, "Slime", 55);
			a(EntityGhast.class, "Ghast", 56);
			a(EntityPigZombie.class, "PigZombie", 57);
			a(EntityEnderman.class, "Enderman", 58);
			a(EntityCaveSpider.class, "CaveSpider", 59);
			a(EntitySilverfish.class, "Silverfish", 60);
			a(EntityBlaze.class, "Blaze", 61);
			a(EntityMagmaCube.class, "LavaSlime", 62);
			a(EntityEnderDragon.class, "EnderDragon", 63);
			a(EntityWither.class, "WitherBoss", 64);
			a(EntityBat.class, "Bat", 65);
			a(EntityWitch.class, "Witch", 66);
			a(EntityEndermite.class, "Endermite", 67);
			a(EntityGuardian.class, "Guardian", 68);
			a(EntityPig.class, "Pig", 90);
			a(EntitySheep.class, "Sheep", 91);
			a(EntityCow.class, "Cow", 92);
			a(EntityChicken.class, "Chicken", 93);
			a(EntitySquid.class, "Squid", 94);
			a(EntityWolf.class, "Wolf", 95);
			a(EntityMushroomCow.class, "MushroomCow", 96);
			a(EntitySnowman.class, "SnowMan", 97);
			a(EntityOcelot.class, "Ozelot", 98);
			a(EntityIronGolem.class, "VillagerGolem", 99);
			a(EntityHorse.class, "EntityHorse", 100);
			a(EntityRabbit.class, "Rabbit", 101);
			a(EntityVillager.class, "Villager", 120);
			a(EntityEnderCrystal.class, "EnderCrystal", 200);
*/

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static Object getPrivateStatic(Class<?> clazz, String f) throws Exception
	{
		Field field = clazz.getDeclaredField(f);
		field.setAccessible(true);
		return field.get(null);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void a(Class<?> paramClass, String paramString, int paramInt)
	{
		try
		{
			((Map) getPrivateStatic(EntityTypes.class, "c")).put(paramString, paramClass);
			((Map) getPrivateStatic(EntityTypes.class, "d")).put(paramClass, paramString);
			((Map) getPrivateStatic(EntityTypes.class, "e")).put(Integer.valueOf(paramInt), paramClass);
			((Map) getPrivateStatic(EntityTypes.class, "f")).put(paramClass, Integer.valueOf(paramInt));
			((Map) getPrivateStatic(EntityTypes.class, "g")).put(paramString, Integer.valueOf(paramInt));
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	public static void registerMods(Entity entity, Modifier modifier)
	{
		modifier.setEntity(entity);
		try
		{
			Field[] fields = entity.getClass().getDeclaredFields();
			for (Field field : fields)
			{
				if (field.getType().isAssignableFrom(modifier.getClass()))
				{
					field.setAccessible(true);
					field.set(entity, modifier);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void unregisterMods(Entity entity)
	{
		try
		{
			Field[] fields = entity.getClass().getDeclaredFields();
			for (Field field : fields)
			{
				if (field.getType().isAssignableFrom(Modifier.class))
				{
					field.set(entity, null);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}