package com.kiro.projchaos.methods.mods;

import net.minecraft.server.v1_8_R3.EntityInsentient;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class Equiptment
{

	private final EntityInsentient entity;

	public Equiptment(EntityInsentient entity)
	{
		this.entity = entity;
	}

	public ItemStack getHeldItem()
	{
		return CraftItemStack.asBukkitCopy(entity.getEquipment(0));
	}

	public void setHeldItem(ItemStack stack)
	{
		entity.setEquipment(0, CraftItemStack.asNMSCopy(stack));
	}

	public ItemStack getHelm()
	{
		return CraftItemStack.asBukkitCopy(entity.getEquipment(4));
	}

	public void setHelm(ItemStack stack)
	{
		entity.setEquipment(4, CraftItemStack.asNMSCopy(stack));
	}

	public ItemStack getBody()
	{
		return CraftItemStack.asBukkitCopy(entity.getEquipment(3));
	}

	public void setBody(ItemStack stack)
	{
		entity.setEquipment(3, CraftItemStack.asNMSCopy(stack));
	}

	public ItemStack getLeggings()
	{
		return CraftItemStack.asBukkitCopy(entity.getEquipment(2));
	}

	public void setLeggings(ItemStack stack)
	{
		entity.setEquipment(2, CraftItemStack.asNMSCopy(stack));
	}

	public ItemStack getBoots()
	{
		return CraftItemStack.asBukkitCopy(entity.getEquipment(1));
	}

	public void setBoots(ItemStack stack)
	{
		entity.setEquipment(1, CraftItemStack.asNMSCopy(stack));
	}

}