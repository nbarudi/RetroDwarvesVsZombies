package me.nbarudi.events;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.libraryaddict.disguise.DisguiseAPI;
import me.nbarudi.main.RDvZ;
import me.nbarudi.misc.PlayerBackup;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerData.DwarfClass;
import me.nbarudi.util.PlayerData.MonsterClass;
import me.nbarudi.util.PlayerManager;

public class DeathRespawnManager implements Listener {
	
	public static HashMap<String, PlayerBackup> backup = new HashMap<String, PlayerBackup>();
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(player.getKiller() == null) {
			event.setDeathMessage(pd.name + "§3 has died!");
		}else {
			PlayerData kpd = PlayerManager.getPlayerData(player.getKiller());
			event.setDeathMessage(pd.name + " §3has been killed by §r" + kpd.name);
		}
		
		
		
		
		if(DisguiseAPI.isDisguised(player))
			DisguiseAPI.undisguiseToAll(player);
		
		if(pd.isDwarf) {
			backup.put(player.getName(), new PlayerBackup(player));
			pd.setDwarf(false);
			RDvZ.dwarf.removeEntry(player.getName());
			RDvZ.hero.removeEntry(player.getName());
			RDvZ.monster.addEntry(player.getName());
			pd.hasClaimedClasses = false;
			event.getDrops().clear();
			event.setDroppedExp(0);
			pd.role = DwarfClass.NONE;
			
			if(pd.isHero) {
				Bukkit.broadcastMessage(pd.getName() + " has perrished!");
				pd.isHero = false;
				pd.setHero(false);
			}
			
			return;
		}else {
			event.getDrops().clear();
			event.setDroppedExp(0);
			pd.mrole = MonsterClass.NONE;
			pd.hasClaimedClasses = false;
			return;
		}
		
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		//PlayerData pd = PlayerManager.getPlayerData(player);
		
		player.getInventory().addItem(RDvZ.is.goldnug);
		double x = RDvZ.instance.getConfig().getDouble("Warps.spawn.x");
		double y = RDvZ.instance.getConfig().getDouble("Warps.spawn.y");
		double z = RDvZ.instance.getConfig().getDouble("Warps.spawn.z");
		String world = RDvZ.instance.getConfig().getString("Warps.spawn.world");
		World w = Bukkit.getWorld(world);
		
		Location loc = new Location(w, x, y, z);
		event.setRespawnLocation(loc);
	}

}
