package me.nbarudi.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.nbarudi.main.RDvZ;

public class ReleaseMonsters implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Bukkit.broadcastMessage("§7[§dGods§7] §aMonsters have been released!!!!");
		Bukkit.broadcastMessage("§7[§dGods§7] §aMonsters have been released!!!!");
		Bukkit.broadcastMessage("§7[§dGods§7] §aMonsters have been released!!!!");
		Bukkit.broadcastMessage("§7[§dGods§7] §aMonsters have been released!!!!");
		Bukkit.broadcastMessage("§7[§dGods§7] §aMonsters have been released!!!!");
		
		Bukkit.dispatchCommand(sender, "summon Lightning_Bolt ~ ~50 ~");
		Bukkit.dispatchCommand(sender, "summon Lightning_Bolt ~ ~50 ~");
		Bukkit.dispatchCommand(sender, "summon Lightning_Bolt ~ ~50 ~");
		Bukkit.dispatchCommand(sender, "summon Lightning_Bolt ~ ~50 ~");
		Bukkit.dispatchCommand(sender, "summon Lightning_Bolt ~ ~50 ~");
		Bukkit.dispatchCommand(sender, "summon Lightning_Bolt ~ ~50 ~");
		
		RDvZ.monstersReleased = true;
		Bukkit.dispatchCommand(sender, "gamerule doMobSpawning false");
		Bukkit.dispatchCommand(sender, "time set night");
		Bukkit.dispatchCommand(sender, "gamerule doDaylightCycle false");
		
		return true;
	}

}
