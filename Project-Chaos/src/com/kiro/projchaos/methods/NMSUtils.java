package com.kiro.projchaos.methods;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;

public class NMSUtils {

	
	
	public static Object getPrivateField(String fieldName, @SuppressWarnings("rawtypes") Class clazz, Object object) {
		Field field;
		Object o = null;
		
		try {
			field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			o = field.get(object);
		}
		catch(NoSuchFieldException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public static String getNMSPackage() {
		String ver = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		return "net.minecraft.server." + ver;
	}
	
	
		 public static Class<?> getNMSClass(String name) {
		    String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		    try {
		        return Class.forName("net.minecraft.server." + version + "." + name);
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		        return null;
		    }
		}
}