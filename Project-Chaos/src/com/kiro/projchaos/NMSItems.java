package com.kiro.projchaos;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class NMSItems {


	public static ItemStack sword() {
		ItemStack i = new ItemStack(Material.DIAMOND_AXE);
		i.addEnchantment(Enchantment.DAMAGE_ALL, 4);
		i.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
		return i;
		
	}
	public static ItemStack helm() {
		ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		i.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		i.addUnsafeEnchantment(Enchantment.OXYGEN, 1);
		SkullMeta skm = (SkullMeta) i.getItemMeta();
		skm.setOwner("Cheapshot");
		i.setItemMeta(skm);
		return i;
		
	}
	public static ItemStack chest() {
		ItemStack i = new ItemStack(Material.DIAMOND_HELMET);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		i.addEnchantment(Enchantment.PROTECTION_FIRE, 3);
		return i;
		
	}
	public static ItemStack legs() {
		ItemStack i = new ItemStack(Material.DIAMOND_LEGGINGS);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		return i;
		
	}
	public static ItemStack boots() {
		ItemStack i = new ItemStack(Material.DIAMOND_BOOTS);
		i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		return i;
	}
}
