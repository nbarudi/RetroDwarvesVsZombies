package me.nbarudi.misc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.main.RDvZ;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerData.DwarfClass;
import me.nbarudi.util.PlayerManager;

public class PlayerBackup {
	
	public String name;
	public ItemStack[] items;
	public DwarfClass role;
	public int mana;
	public int hunger;
	public Boolean isHero;
	public Location loc;
	
	public PlayerBackup(Player player) {
		PlayerData data = PlayerManager.getPlayerData(player);
		this.items = player.getInventory().getContents();
		this.name = player.getName();
		this.role = data.role;
		this.mana = data.mana;
		this.hunger = player.getFoodLevel();
		this.loc = player.getLocation();
		this.isHero = data.isHero;
	}
	
	public void RezPlayer() {
		
		Player player = Bukkit.getPlayer(name);
		PlayerData data = PlayerManager.getPlayerData(player);
		
		data.mana = mana;
		data.role = role;
		player.setFoodLevel(hunger);
		player.getInventory().setContents(items);
		data.isDwarf = true;
		data.isHero = isHero;
		RDvZ.dwarf.addEntry(player.getName());
		RDvZ.monster.removeEntry(player.getName());
		
		player.teleport(loc);
		player.sendMessage("You have been resurrected!");
		loc.getWorld().strikeLightning(new Location(loc.getWorld(), loc.getX(), loc.getY()+40, loc.getZ()));
		
	}

}
