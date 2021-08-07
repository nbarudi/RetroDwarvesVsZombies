package me.nbarudi.modules.heros;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.nbarudi.main.RDvZ;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class HeroModule {
	
	public int maxHeros = 1;
	public int maxMana = 1500;
	public String suffix = "";
	public String player = "";
	public String name = "";
	public ChatColor color = ChatColor.GOLD;
	
	
	public HeroModule(String name, String suffix) {
		this.suffix = suffix;
		this.name = name;
	}
	
	
	public void setPlayer(Player hero, boolean isForced) {
		this.player = hero.getName();
		PlayerData pd = PlayerManager.getPlayerData(hero);
		pd.isHero = true;
		pd.hero = this;
		
		RDvZ.dwarf.removeEntry(hero.getName());
		RDvZ.hero.addEntry(hero.getName());
		
		pd.name = hero.getName() + "§6 " + suffix;
		if(!isForced)
			hero.getInventory().clear();
		
	}
	
}
