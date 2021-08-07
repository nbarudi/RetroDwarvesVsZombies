package me.nbarudi.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.nbarudi.main.RDvZ;
import me.nbarudi.util.PlayerManager;

public class PlayerJoin implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		PlayerManager.createPlayerData(player);
		
		player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100.0D); 
		
		if(!player.hasPlayedBefore()) {
			event.setJoinMessage("§dWelcome " + player.getName() + " to the server!");
			
			if(RDvZ.gameStarted && !RDvZ.monstersReleased) {
				player.getInventory().addItem(RDvZ.is.magmacream);
				player.sendMessage("§6You have joined late but you can still play as a dwarf!");
			}
			
			double x = RDvZ.instance.getConfig().getDouble("Warps.spawn.x");
			double y = RDvZ.instance.getConfig().getDouble("Warps.spawn.y");
			double z = RDvZ.instance.getConfig().getDouble("Warps.spawn.z");
			String world = RDvZ.instance.getConfig().getString("Warps.spawn.world");
			World w = Bukkit.getWorld(world);
			
			Location loc = new Location(w, x, y, z);
			player.teleport(loc);
			
		}else {
			event.setJoinMessage("§d" + player.getName() + " has rejoined the game!");
		}
		
		if(RDvZ.monstersReleased) {
			player.setHealth(0);
		}
		
		if(RDvZ.instance.getConfig().getConfigurationSection("Warps.spawn") != null) {
			
			if(RDvZ.gameStarted && !RDvZ.monstersReleased)
				return;
			
			double x = RDvZ.instance.getConfig().getDouble("Warps.spawn.x");
			double y = RDvZ.instance.getConfig().getDouble("Warps.spawn.y");
			double z = RDvZ.instance.getConfig().getDouble("Warps.spawn.z");
			String world = RDvZ.instance.getConfig().getString("Warps.spawn.world");
			World w = Bukkit.getWorld(world);
			
			Location loc = new Location(w, x, y, z);
			player.teleport(loc);
			player.damage(200);
			
		}else {
			player.sendMessage("§aThere seems to be no spawn point so you have been sent to the world spawn!");
		}
		
		
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(!RDvZ.gameStarted)
			PlayerManager.deletePlayerData(player);
	}

}
