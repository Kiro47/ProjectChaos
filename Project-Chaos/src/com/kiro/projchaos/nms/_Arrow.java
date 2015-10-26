package com.kiro.projchaos.nms;

import java.util.List;

import net.minecraft.server.v1_8_R3.*;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.event.CraftEventFactory;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.util.Vector;

import com.kiro.projchaos.methods.IArrow;
import com.kiro.projchaos.methods.Modifier;
import com.kiro.projchaos.methods.mods.ModArrowStuckInGround;
import com.kiro.projchaos.methods.mods.ModBaseEntityTick;
import com.kiro.projchaos.methods.mods.ModDeath;
import com.kiro.projchaos.methods.mods.ModMotion;
import com.kiro.projchaos.methods.mods.ModOnPickup;
import com.kiro.projchaos.methods.mods.ModProjectileHit;


public class _Arrow extends EntityArrow implements IArrow
{
	private int tileX = -1;
	private int tileY = -1;
	private int tileZ = -1;
	private Block block;
	private int blockID;
	public boolean inGround = false;
	public int fromPlayer;
	public int shake;
	public Entity shooter;
	public int ticksInGround;
	public int ticksInAir;
	private double damage = 2.0D;
	public int knockbackStrength;

	private ModBaseEntityTick tickMod;
	private ModOnPickup pickupMod;
	private ModMotion travelMod;
	private ModArrowStuckInGround stuckInGroundMod;
	private ModProjectileHit hitMod;

	public _Arrow(World world)
	{
		super(world);
	}

	public _Arrow(World world, double d0, double d1, double d2)
	{
		super(world, d0, d1, d2);
	}

	public _Arrow(World world, EntityLiving entityliving, EntityLiving entityliving1, float f, float f1)
	{
		super(world, entityliving, entityliving1, f, f1);
	}

	public _Arrow(World world, EntityLiving entityliving, float f)
	{
		super(world, entityliving, f);
	}

	private ModDeath deathMod;

	public void die()
	{
		if (deathMod != null)
		{
			deathMod.onDeath();
		}
		else
		{
			defaultDie();
		}
	}

	public void defaultDie()
	{
		super.die();
	}

	@Override
	public void t_()
	{
		if (tickMod != null)
		{
			tickMod.onTick();
		}
		else
		{
			K();

			if (lastPitch == 0.0F && lastYaw == 0.0F)
			{
				float blockposition = MathHelper.sqrt(motX * motX + motZ * motZ);
				lastYaw = yaw = (float) (MathHelper.b(motX, motZ) * 180.0D / 3.1415927410125732D);
				lastPitch = pitch = (float) (MathHelper.b(motY, blockposition) * 180.0D / 3.1415927410125732D);
			}

			BlockPosition var20 = new BlockPosition(tileX, tileY, tileZ);
			IBlockData iblockdata = world.getType(var20);
			Block block = iblockdata.getBlock();
			if (block.getMaterial() != Material.AIR)
			{
				block.updateShape(world, var20);
				AxisAlignedBB vec3d = block.a(world, var20, iblockdata);
				if (vec3d != null && vec3d.a(new Vec3D(locX, locY, locZ)))
				{
					inGround = true;
				}
			}

			if (shake > 0)
			{
				--shake;
			}

			if (inGround)
			{
				int block_id = block.toLegacyData(iblockdata);
				if (block == this.block && block_id == blockID)
				{
					ticksInGround++;
					if (stuckInGroundMod != null)
					{
						stuckInGroundMod.tickInGround();
					}
					else
					{
						stuckInGround();
					}
				}
				else
				{
					inGround = false;
					motX *= random.nextFloat() * 0.2F;
					motY *= random.nextFloat() * 0.2F;
					motZ *= random.nextFloat() * 0.2F;
					ticksInGround = 0;
					ticksInAir = 0;
				}
			}
			else
			{
				++ticksInAir;
				Vec3D point1 = new Vec3D(locX, locY, locZ);
				Vec3D point2 = new Vec3D(locX + motX, locY + motY, locZ + motZ);
				MovingObjectPosition mop = world.rayTrace(point1, point2, false, true, false);
				point1 = new Vec3D(locX, locY, locZ);
				point2 = new Vec3D(locX + motX, locY + motY, locZ + motZ);
				if (mop != null)
				{
					point2 = new Vec3D(mop.pos.a, mop.pos.b, mop.pos.c);
				}

				Entity entity = null;
				List<?> list = world.getEntities(this, getBoundingBox().a(motX, motY, motZ).grow(1.0D, 1.0D, 1.0D));
				double maxDist = 0.0D;

				for (int index = 0; index < list.size(); ++index)
				{
					Entity curEntity = (Entity) list.get(index);
					if (curEntity.ad() && (curEntity != shooter || ticksInAir >= 5))
					{
						AxisAlignedBB boundingBox = curEntity.getBoundingBox().grow(0.3F, 0.3F, 0.3F);
						MovingObjectPosition mop2 = boundingBox.a(point1, point2);
						if (mop2 != null)
						{
							double dist = point1.distanceSquared(mop2.pos);
							if (dist < maxDist || maxDist == 0.0D)
							{
								entity = curEntity;
								maxDist = dist;
							}
						}
					}
				}

				if (entity != null)
				{
					mop = new MovingObjectPosition(entity);
				}

				if (mop != null && mop.entity != null && mop.entity instanceof EntityHuman)
				{
					EntityHuman var23 = (EntityHuman) mop.entity;
					if (var23.abilities.isInvulnerable || shooter instanceof EntityHuman && !((EntityHuman) shooter).a(var23))
					{
						mop = null;
					}
				}

				if (mop != null) // Projectile Hit
				{
					if (hitMod != null)
					{
						hitMod.onHit(mop);
					}
					else
					{
						defaultHit(mop);
					}

				}

				if (isCritical())
				{
					for (j = 0; j < 4; ++j)
					{
						world.addParticle(EnumParticle.CRIT, locX + motX * j / 4.0D, locY + motY * j / 4.0D, locZ + motZ * j / 4.0D, -motX, -motY + 0.2D, -motZ, new int[0]);
					}
				}

				if (travelMod != null)
				{
					travelMod.motion();
				}
				else
				{
					defaultMotion();
				}

				setPosition(locX, locY, locZ);
				checkBlockCollisions();

			}
		}
	}

	public void defaultHit(MovingObjectPosition mop)
	{
		CraftEventFactory.callProjectileHitEvent(this);

		if (mop.entity == null) // Projectile Did not hit an entity
		{
			BlockPosition blockPos = mop.a();
			tileX = blockPos.getX();
			tileY = blockPos.getY();
			tileZ = blockPos.getZ();
			IBlockData blockData = world.getType(blockPos);
			block = blockData.getBlock();
			blockID = block.toLegacyData(blockData);
			motX = (float) (mop.pos.a - locX);
			motY = (float) (mop.pos.b - locY);
			motZ = (float) (mop.pos.c - locZ);
			float vel = MathHelper.sqrt(motX * motX + motY * motY + motZ * motZ);
			locX -= motX / vel * 0.05000000074505806D;
			locY -= motY / vel * 0.05000000074505806D;
			locZ -= motZ / vel * 0.05000000074505806D;
			makeSound("random.bowhit", 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F));
			inGround = true;
			shake = 7;
			setCritical(false);
			if (block.getMaterial() != Material.AIR)
			{
				block.a(world, blockPos, blockData, this);
			}
		}
		else // Projectile Hit Entity
		{
			float vel = MathHelper.sqrt(motX * motX + motY * motY + motZ * motZ);
			int dmg = MathHelper.f(vel * damage);
			if (isCritical())
			{
				dmg += random.nextInt(dmg / 2 + 2);
			}

			DamageSource dmgSrc;
			if (shooter == null)
			{
				dmgSrc = DamageSource.arrow(this, this);
			}
			else
			{
				dmgSrc = DamageSource.arrow(this, shooter);
			}

			if (!mop.entity.damageEntity(dmgSrc, dmg))
			{
				motX *= -0.10000000149011612D;
				motY *= -0.10000000149011612D;
				motZ *= -0.10000000149011612D;
				yaw += 180.0F;
				lastYaw += 180.0F;
				ticksInAir = 0;
			}
			else
			{
				if (isBurning() && !(mop.entity instanceof EntityEnderman) && (!(mop.entity instanceof EntityPlayer) || !(shooter instanceof EntityPlayer) || world.pvpMode))
				{
					EntityCombustByEntityEvent entityliving = new EntityCombustByEntityEvent(getBukkitEntity(), mop.entity.getBukkitEntity(), 5);
					Bukkit.getPluginManager().callEvent(entityliving);
					if (!entityliving.isCancelled())
					{
						mop.entity.setOnFire(entityliving.getDuration());
					}
				}

				if (mop.entity instanceof EntityLiving)
				{
					EntityLiving var30 = (EntityLiving) mop.entity;
					if (!world.isClientSide)
					{
						var30.o(var30.bv() + 1);
					}

					if (knockbackStrength > 0)
					{
						float velXZ = MathHelper.sqrt(motX * motX + motZ * motZ);
						if (velXZ > 0.0F)
						{
							mop.entity.g(motX * knockbackStrength * 0.6000000238418579D / velXZ, 0.1D, motZ * knockbackStrength * 0.6000000238418579D / velXZ);
						}
					}

					if (shooter instanceof EntityLiving)
					{
						EnchantmentManager.a(var30, shooter);
						EnchantmentManager.b((EntityLiving) shooter, var30);
					}

					if (shooter != null && mop.entity != shooter && mop.entity instanceof EntityHuman && shooter instanceof EntityPlayer)
					{
						((EntityPlayer) shooter).playerConnection.sendPacket(new PacketPlayOutGameStateChange(6, 0.0F));
					}
				}

				makeSound("random.bowhit", 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F));
				if (!(mop.entity instanceof EntityEnderman))
				{
					die();
				}
			}
		}
	}

	public void fixRotation()
	{

		double motXZ = MathHelper.sqrt(motX * motX + motZ * motZ);
		yaw = (float) (MathHelper.b(motX, motZ) * 180.0D / 3.1415927410125732D);

		for (pitch = (float) (MathHelper.b(motY, motXZ) * 180.0D / 3.1415927410125732D); pitch - lastPitch < -180.0F; lastPitch -= 360.0F)
		{
			;
		}

		while (pitch - lastPitch >= 180.0F)
		{
			lastPitch += 360.0F;
		}

		while (yaw - lastYaw < -180.0F)
		{
			lastYaw -= 360.0F;
		}

		while (yaw - lastYaw >= 180.0F)
		{
			lastYaw += 360.0F;
		}

		pitch = lastPitch + (pitch - lastPitch) * 0.2F;
		yaw = lastYaw + (yaw - lastYaw) * 0.2F;
	}

	public void defaultMotion()
	{
		locX += motX;
		locY += motY;
		locZ += motZ;

		fixRotation();

		float motMult = 0.99F;
		float dY = 0.05F;

		if (V()) // If in water
		{
			for (int var31 = 0; var31 < 4; ++var31)
			{
				dY = 0.25F;
				world.addParticle(EnumParticle.WATER_BUBBLE, locX - motX * dY, locY - motY * dY, locZ - motZ * dY, motX, motY, motZ, new int[0]);
			}

			motMult = 0.6F;
		}

		if (U()) // if wet
		{
			extinguish();
		}

		motX *= motMult;
		motY *= motMult;
		motZ *= motMult;
		motY -= dY;
	}

	public void stuckInGround()
	{
		if (ticksInGround >= world.spigotConfig.arrowDespawnRate)
		{
			die();
		}
	}

	public void onTick()
	{
		super.t_();
	}

	@Override
	public double getDamage()
	{
		return damage;
	}

	@Override
	public void setDamage(double damage)
	{
		this.damage = damage;
	}

	@Override
	public Entity getShooter()
	{
		return shooter;
	}

	@Override
	public void b(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("xTile", (short) tileX);
		nbttagcompound.setShort("yTile", (short) tileY);
		nbttagcompound.setShort("zTile", (short) tileZ);
		nbttagcompound.setShort("life", (short) ticksInGround);
		MinecraftKey minecraftkey = (MinecraftKey) Block.REGISTRY.c(block);
		nbttagcompound.setString("inTile", minecraftkey == null ? "" : minecraftkey.toString());
		nbttagcompound.setByte("inData", (byte) blockID);
		nbttagcompound.setByte("shake", (byte) shake);
		nbttagcompound.setByte("inGround", (byte) (inGround ? 1 : 0));
		nbttagcompound.setByte("pickup", (byte) fromPlayer);
		nbttagcompound.setDouble("damage", damage);
	}

	@Override
	public void a(NBTTagCompound nbttagcompound)
	{
		tileX = nbttagcompound.getShort("xTile");
		tileY = nbttagcompound.getShort("yTile");
		tileZ = nbttagcompound.getShort("zTile");
		ticksInGround = nbttagcompound.getShort("life");
		if (nbttagcompound.hasKeyOfType("inTile", 8))
		{
			block = Block.getByName(nbttagcompound.getString("inTile"));
		}
		else
		{
			block = Block.getById(nbttagcompound.getByte("inTile") & 255);
		}

		blockID = nbttagcompound.getByte("inData") & 255;
		shake = nbttagcompound.getByte("shake") & 255;
		inGround = nbttagcompound.getByte("inGround") == 1;
		if (nbttagcompound.hasKeyOfType("damage", 99))
		{
			damage = nbttagcompound.getDouble("damage");
		}

		if (nbttagcompound.hasKeyOfType("pickup", 99))
		{
			fromPlayer = nbttagcompound.getByte("pickup");
		}
		else if (nbttagcompound.hasKeyOfType("player", 99))
		{
			fromPlayer = nbttagcompound.getBoolean("player") ? 1 : 0;
		}

	}

	@Override
	public void d(EntityHuman entityhuman)
	{
		if (pickupMod != null)
		{
			pickupMod.onPickup(entityhuman);
		}
		else
		{
			onPickup(entityhuman);
		}
	}

	public void onPickup(EntityHuman entityHuman)
	{
		super.d(entityHuman);
	}

	@Override
	public void registerMods(Modifier modifier)
	{

		NMSHandler.registerMods(this, modifier);
		modifier.setEntity(this);
	}

	@Override
	public Vector getLocation()
	{
		return new Vector(locX, locY, locZ);
	}

	@Override
	public Vector getVelocity()
	{
		return new Vector(motX, motY, motZ);
	}
}