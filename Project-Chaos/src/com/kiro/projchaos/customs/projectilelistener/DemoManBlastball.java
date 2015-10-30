package com.kiro.projchaos.customs.projectilelistener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.List;
import java.util.logging.Logger;

public class DemoManBlastball implements Listener
{

	public static final String DEMO_MAN_NAME = ChatColor.DARK_GREEN + "Demo_Man";
	public static final String BLASTBALL_NAME = "blastball225";
	Logger log = Bukkit.getServer().getLogger();

	@EventHandler
	public void onSnowballLaunch(ProjectileLaunchEvent e)
	{
		if (!(e.getEntity().getShooter() instanceof Snowman))
		{
			return;
		}

		Snowman sm = (Snowman) e.getEntity().getShooter();
		if (sm.getCustomName() == null)
		{
			return;
		}
		if (sm.getCustomName().equals(DEMO_MAN_NAME))
		{
			e.getEntity().setCustomName(BLASTBALL_NAME);
		}
	}

	@EventHandler
	public void onSnowballHit(ProjectileHitEvent e)
	{
		if (!(e.getEntity() instanceof Snowball))
		{
			return;
		}
		if (!(e.getEntity().getShooter() instanceof Snowman))
		{
			return;
		}
		if (e.getEntity().getCustomName() == null)
		{
			return;
		}
		if (e.getEntity().getCustomName().equals(BLASTBALL_NAME))
		{
			World world = e.getEntity().getLocation().getWorld();
			world.createExplosion(e.getEntity().getLocation(), 3F, false);
		}
	}

	@EventHandler
	public void onExplosionHold(EntityExplodeEvent e)
	{
		List<Block> blocks = e.blockList();
		if (!(e.getEntity() instanceof Snowball))
		{
			return;
		}

		e.blockList().removeAll(blocks);
	}
}
