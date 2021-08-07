package me.nbarudi.util.reflection;

import org.bukkit.Bukkit;

public class ReflectionUtil {

	public static String pkg = Bukkit.getServer().getClass().getPackage().getName();
	
	public Class<?> getNMSClass(String name) throws ClassNotFoundException {
	       return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
	}
	
	public Class<?> getCBClass(String name) throws ClassNotFoundException{
		return Class.forName(pkg + name);
	}
	
}
