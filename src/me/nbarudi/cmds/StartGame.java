package me.nbarudi.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nbarudi.main.RDvZ;
import me.nbarudi.util.NickManager;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class StartGame implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		sender.sendMessage("§aGame Started!");
		Bukkit.broadcastMessage("§6It's time to play... §bDwarves §7Vs §2Zombies");
		for(PlayerData data : RDvZ.data) {
			Player player = PlayerManager.getPlayer(data);
			if(player.isOp()) {
				player.sendMessage("§cYou are opped so you do not get to participate!");
				continue;
			}
			player.getInventory().addItem(RDvZ.is.magmacream);
			data.setDwarf(true);
		}
		
		RDvZ.gameStarted = true;
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.setScoreboard(RDvZ.sb);
		}
		Bukkit.getScheduler().scheduleSyncRepeatingTask(RDvZ.instance, new Runnable() {
			public void run() {
				NickManager.updateNames();
			}
		}, 20, 20);
		return true;
	}

}
