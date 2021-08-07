package me.nbarudi.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.heros.HeroModule;

public class PlayerManager {
	
	public static PlayerData createPlayerData(Player player) {
		if(playerExists(player)) {
			return getPlayerData(player);
		}
		PlayerData pd = new PlayerData(player.getName());
		RDvZ.data.add(pd);
		return pd;
	}
	
	public static boolean playerExists(Player player) {
		boolean toReturn = false;
		for(PlayerData pd : RDvZ.data) {
			if(pd.realName.equalsIgnoreCase(player.getName()))
				toReturn = true;
		}
		return toReturn;
	}
	
	public static PlayerData getPlayerData(Player player) {
		for(PlayerData data : RDvZ.data) {
			if(data.realName.contains(player.getName()))
				return data;
		}
		return null;
	}
	
	public static PlayerData getPlayerData(String name) {
		for(PlayerData data : RDvZ.data) {
			if(data.realName.contains(name))
				return data;
		}
		return null;
	}
	
	public static void deletePlayerData(Player player) {
		PlayerData pd = getPlayerData(player);
		RDvZ.data.remove(pd);
	}
	
	public static Player getPlayer(PlayerData pd) {
		return Bukkit.getPlayer(pd.realName);
	}
	
	public static HeroModule getHeroByName(String name) {
		for(HeroModule hero : RDvZ.heros) {
			if(name.equals(hero.name)) {
				return hero;
			}
		}
		return null;
	}

}
