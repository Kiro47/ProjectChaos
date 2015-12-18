package com.kiro.projchaos.nms;

import java.util.Random;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.kiro.projchaos.methods.VecUtils;


public class ParticleUtils
{

	@SuppressWarnings("unused")
	private static final Random rand = new Random();

    public static void playEffect(ParticleType effect, Location location, float data, int count)
    {
        playEffect(effect, location, 0f, 0f, 0f, data, count);
    }

    public static void playEffect(ParticleType effect, Location location, float offX, float offY, float offZ, float data, int count)
    {

        Packet<?> packet = new PacketPlayOutWorldParticles(effect.enumParticle, false, (float) location.getX(), (float) location.getY(), (float) location.getZ(), offX, offY, offZ, data, count);
        World world = location.getWorld();
        for (Player player : world.getPlayers())
        {
            Location loc = player.getLocation();
            if (loc.distanceSquared(location) < 4096)
            {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    public static void playEffect(ParticleType effect, World world, double px, double py, double pz, float offX, float offY, float offZ, float data, int count)
    {
        Packet<?> packet = new PacketPlayOutWorldParticles(effect.enumParticle, false, (float) px, (float) py, (float) pz, offX, offY, offZ, data, count);

        for (Player player : world.getPlayers())
        {
            Location loc = player.getLocation();
            if (VecUtils.getDistanceSquared(loc.getX(), loc.getZ(), px, pz) < 4096)
            {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    public static void playEffect(ParticleType effect, net.minecraft.server.v1_8_R3.World world, double px, double py, double pz, float offX, float offY, float offZ, float data, int count)
    {
        Packet<?> packet = new PacketPlayOutWorldParticles(effect.enumParticle, false, (float) px, (float) py, (float) pz, offX, offY, offZ, data, count);

        for (EntityHuman player : world.players)
        {
            if (player instanceof EntityPlayer && VecUtils.getDistanceSquared(player.locX, player.locZ, px, pz) < 4096)
            {
                ((EntityPlayer) player).playerConnection.sendPacket(packet);
            }
        }
    }


    public enum ParticleType
    {
        HUGE_EXPLOSION("hugeexplosion", EnumParticle.EXPLOSION_HUGE),
        LARGE_EXPLODE("largeexplode", EnumParticle.EXPLOSION_LARGE),
        FIREWORKS_SPARK("fireworksSpark", EnumParticle.FIREWORKS_SPARK),
        BUBBLE("bubble", EnumParticle.WATER_BUBBLE),
        SUSPEND("suspend", EnumParticle.SUSPENDED),
        DEPTH_SUSPEND("depthSuspend", EnumParticle.SUSPENDED_DEPTH),
        TOWN_AURA("townaura", EnumParticle.TOWN_AURA),
        CRIT("crit", EnumParticle.CRIT),
        MAGIC_CRIT("magicCrit", EnumParticle.CRIT_MAGIC),
        MOB_SPELL("mobSpell", EnumParticle.SPELL_MOB),
        MOB_SPELL_AMBIENT("mobSpellAmbient", EnumParticle.SPELL_MOB_AMBIENT),
        SPELL("spell", EnumParticle.SPELL),
        INSTANT_SPELL("instantSpell", EnumParticle.SPELL_INSTANT),
        WITCH_MAGIC("witchMagic", EnumParticle.SPELL_WITCH),
        NOTE("note", EnumParticle.NOTE),
        PORTAL("portal", EnumParticle.PORTAL),
        ENCHANTMENT_TABLE("enchantmenttable", EnumParticle.ENCHANTMENT_TABLE),
        EXPLODE("explode", EnumParticle.EXPLOSION_NORMAL),
        FLAME("flame", EnumParticle.FLAME),
        LAVA("lava", EnumParticle.LAVA),
        FOOTSTEP("footstep", EnumParticle.FOOTSTEP),
        SPLASH("splash", EnumParticle.WATER_SPLASH),
        LARGE_SMOKE("largesmoke", EnumParticle.SMOKE_LARGE),
        CLOUD("cloud", EnumParticle.CLOUD),
        RED_DUST("reddust", EnumParticle.REDSTONE),
        SNOWBALL_POOF("snowballpoof", EnumParticle.SNOWBALL),
        DRIP_WATER("dripWater", EnumParticle.DRIP_WATER),
        DRIP_LAVA("dripLava", EnumParticle.DRIP_LAVA),
        SNOW_SHOVEL("snowshovel", EnumParticle.SNOW_SHOVEL),
        SLIME("slime", EnumParticle.SLIME),
        HEART("heart", EnumParticle.HEART),
        ANGRY_VILLAGER("angryVillager", EnumParticle.VILLAGER_ANGRY),
        HAPPY_VILLAGER("happerVillager", EnumParticle.VILLAGER_HAPPY),
        MOB_APPEARANCE("mobappearance", EnumParticle.MOB_APPEARANCE);

        public final String particleName;
        public final EnumParticle enumParticle;

        ParticleType(String particleName, EnumParticle particle)
        {
            this.particleName = particleName;
            this.enumParticle = particle;
        }
    }

}