package me.nbarudi.cmds;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nbarudi.main.RDvZ;

public class SetupCommand implements CommandExecutor {
	
	RDvZ plugin = RDvZ.instance;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player))
			return true;
		
		Player player = (Player) sender;
		
		if(args.length == 0) {
			player.sendMessage("§cInvalid Usage!");
			return true;
		}
		
		if(args[0].equalsIgnoreCase("dwarf")) {
			Location loc = player.getLocation();
			double x = loc.getX();
			double y = loc.getY();
			double z = loc.getZ();
			String world = loc.getWorld().getName();
			
			plugin.getConfig().set("Warps.dwarf.x", x);
			plugin.getConfig().set("Warps.dwarf.y", y);
			plugin.getConfig().set("Warps.dwarf.z", z);
			plugin.getConfig().set("Warps.dwarf.world", world);
			
			player.sendMessage("§aSet the dwarf spawn point!");			
		}
		
		if(args[0].equalsIgnoreCase("monster")) {
			Location loc = player.getLocation();
			double x = loc.getX();
			double y = loc.getY();
			double z = loc.getZ();
			String world = loc.getWorld().getName();
			
			plugin.getConfig().set("Warps.monster.x", x);
			plugin.getConfig().set("Warps.monster.y", y);
			plugin.getConfig().set("Warps.monster.z", z);
			plugin.getConfig().set("Warps.monster.world", world);
			
			player.sendMessage("§aSet the monster spawn point!");			
		}
		
		if(args[0].equalsIgnoreCase("spawn")) {
			Location loc = player.getLocation();
			double x = loc.getX();
			double y = loc.getY();
			double z = loc.getZ();
			String world = loc.getWorld().getName();
			
			plugin.getConfig().set("Warps.spawn.x", x);
			plugin.getConfig().set("Warps.spawn.y", y);
			plugin.getConfig().set("Warps.spawn.z", z);
			plugin.getConfig().set("Warps.spawn.world", world);
			
			player.sendMessage("§aSet the server spawn point!");			
		}
		
		plugin.saveConfig();
		
		return true;
	}

}
