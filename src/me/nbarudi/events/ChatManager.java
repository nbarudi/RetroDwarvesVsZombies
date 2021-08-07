package me.nbarudi.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.nbarudi.main.RDvZ;
import me.nbarudi.util.NickManager;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class ChatManager implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		
		
		Player player = event.getPlayer();
		PlayerData pd = PlayerManager.getPlayerData(player.getName());
		
		NickManager.updateNames();
		
		event.setCancelled(true);
		
		if(event.getMessage().startsWith("-")) {
			if(!player.isOp()) {
				player.sendMessage("§4You do not have permission to run announcements!");
				return;
			}
			
			Bukkit.broadcastMessage("§7[§aAnnouncement§7] §r" + event.getMessage().substring(1));
			return;
		}
		
		if(!RDvZ.gameStarted) {
			if(player.isOp()) {
				Bukkit.broadcastMessage("§c" + player.getName() + "§r: " + event.getMessage());
				return;
			}else {
				Bukkit.broadcastMessage("§r" + player.getName() + ": " + event.getMessage());
				return;
			}
		}
		
		if(!RDvZ.monstersReleased) {
			if(player.isOp()) {
				Bukkit.broadcastMessage("§c" + pd.name + "§r: " + event.getMessage());
				return;
			}else {
				Bukkit.broadcastMessage(pd.name + "§r: " + event.getMessage());
				return;
			}
		}
		
		if(player.isOp()) {
			Bukkit.broadcastMessage("§c" + pd.name + "§r: " + event.getMessage());
			return;
		}
		
		if(!event.getMessage().startsWith("!")) {
			if(pd.isDwarf) {
				for(Player plr : Bukkit.getOnlinePlayers()) {
					if(plr.isOp()) {
						plr.sendMessage(pd.name + "§r: " + event.getMessage());
						continue;
					}
					PlayerData data = PlayerManager.getPlayerData(plr);
					if(data.isDwarf) {
						plr.sendMessage(pd.name + "§r: " + event.getMessage());
					}
				}
			}else {
				for(Player plr : Bukkit.getOnlinePlayers()) {
					if(plr.isOp()) {
						plr.sendMessage(pd.name + "§r: " + event.getMessage());
						continue;
					}
					PlayerData data = PlayerManager.getPlayerData(plr);
					if(!data.isDwarf) {
						plr.sendMessage(pd.name + "§r: " + event.getMessage());
					}
				}
			}
		}else {
			Bukkit.broadcastMessage("[§2Global§r] " + pd.name + "§r: " + event.getMessage().substring(1));
		}
		
	}

}
