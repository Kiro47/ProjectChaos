package com.kiro.projchaos.methods;

import java.lang.reflect.InvocationTargetException;
import com.kiro.projchaos.methods.Modifier;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.World;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;


public class SUtils
{

	private static Entity spawnEntity(World world, Class<? extends Entity> entityClass, Class<? extends Modifier> modClass) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException
	{
		Entity entity = entityClass.getConstructor(World.class).newInstance(world);

		if (entity instanceof IEntity)
		{
			((IEntity) entity).registerMods(modClass.newInstance());
		}

		return entity;
	}

	public static void spawnEntity(Location location, Class<? extends Entity> entityClass, Class<? extends Modifier> modClass, int count)
	{
		try
		{

			World world = ((CraftWorld) location.getWorld()).getHandle();
			for (int i = 0; i < count; i++)
			{

				Entity entity = spawnEntity(world, entityClass, modClass);

				entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());

				world.addEntity(entity);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void spawnEntitySafe(Location location, Class<? extends Entity> entityClass, Class<? extends Modifier> modClass, int count)
	{
		try
		{

			World world = ((CraftWorld) location.getWorld()).getHandle();
			for (int i = 0; i < count; i++)
			{

				Entity entity = spawnEntity(world, entityClass, modClass);

				boolean flag = testPosition(entity, location.getX(), location.getY(), location.getZ(), 0);

				if (!flag)
				{
					entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
				}

				world.addEntity(entity);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static boolean setPositionRandom(Entity entity, double cx, double cy, double cz, double range)
	{
		for (int i = 0; i < 64; i++)
		{
			double px = Utils.getRandom(cx, range);
			double pz = Utils.getRandom(cz, range);

			if (testPosition(entity, px, cy + 10, pz, 10))
			{
				return true;
			}
		}
		return false;
	}

	public static boolean testPosition(Entity entity, double px, double py, double pz, double dY)
	{
		double tempX = entity.locX;
		double tempY = entity.locY;
		double tempZ = entity.locZ;
		entity.locX = px;
		entity.locY = py;
		entity.locZ = pz;
		boolean success = false;
		BlockPosition blockposition = new BlockPosition(entity.locX, entity.locY, entity.locZ);
		if (entity.world.isLoaded(blockposition))
		{
			boolean isSolidBlock = false;

			while (!isSolidBlock && blockposition.getY() > 0)
			{
				BlockPosition i = blockposition.down();
				Block to = entity.world.getType(i).getBlock();
				if (to.getMaterial().isSolid())
				{
					isSolidBlock = true;
				}
				else
				{
					entity.locY--;
					blockposition = i;
				}
			}

			if (isSolidBlock)
			{
				entity.enderTeleportTo(entity.locX, entity.locY, entity.locZ);
				//				if (entity.world.getCubes(entity, entity.getBoundingBox()).isEmpty() && !entity.world.containsLiquid(entity.getBoundingBox()))
				//				{
				success = true;
				//				}
				//				else
				//				{
				//					System.out.println("failure 2");
				//				}
			}
		}

		if (!success)
		{
			entity.setPosition(tempX, tempY, tempZ);
			return false;
		}
		else
		{
			return true;
		}
	}

}