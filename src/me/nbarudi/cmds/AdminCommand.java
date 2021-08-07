package me.nbarudi.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.events.DeathRespawnManager;
import me.nbarudi.main.RDvZ;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;
import net.md_5.bungee.api.ChatColor;

public class AdminCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(args.length == 0) {
			sender.sendMessage("§cInvalid Usage");
			return true;
		}
		
		if(args.length == 3) {
			if(args[0].equalsIgnoreCase("setmana")) {
				Player target = Bukkit.getPlayer(args[1]);
				if(target == null) {
					sender.sendMessage("§cInvalid Player");
					return true;
				}
				
				try {
					Integer mana = Integer.parseInt(args[2]);
					
					PlayerData td = PlayerManager.getPlayerData(target);
					td.mana = mana;
					
					sender.sendMessage("Set " + target.getName() + "'s mana to " + mana);
					
				}catch(NumberFormatException e) {
					sender.sendMessage("§cInvalid Number!");
				}
				
			}
		}
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("customevent")) {
				RDvZ.ah.adminEvent = !RDvZ.ah.adminEvent;
				sender.sendMessage("§aSet custom event to: "+ (RDvZ.ah.adminEvent ? ChatColor.DARK_GREEN + "TRUE" : ChatColor.DARK_RED + "FALSE"));
				return true;
			}
			else if(args[0].equalsIgnoreCase("auto")) {
				RDvZ.ah.useAutomation = !RDvZ.ah.useAutomation;
				sender.sendMessage((RDvZ.ah.useAutomation ? "§aEnabled" : "§4Disabled") + " Automation on this server!");
				return true;
			}
			else if(args[0].equalsIgnoreCase("forcestart")) {
				RDvZ.ah.numPlayers = 21;
				sender.sendMessage("§aForce starting the game!");
				Bukkit.broadcastMessage("§eA Host has force started the game! Please wait while we get things ready....");
			}
		}

		if(args.length == 2) {
			if(args[0].equalsIgnoreCase("addadmin")) {
				Player t = Bukkit.getPlayer(args[1]);
				if(t != null) {
					RDvZ.admins.add(t.getName());
					sender.sendMessage("§aAdded " + t.getName() + " to the admin list!");
					return true;
				}else {
					sender.sendMessage("§cInvalid Player!");
				}
			}
			else if(args[0].equalsIgnoreCase("removeadmin")) {
				Player t = Bukkit.getPlayer(args[1]);
				if(t != null) {
					RDvZ.admins.remove(t.getName());
					sender.sendMessage("§aRemoved " + t.getName() + " from admin list!");
					return true;
				}else {
					sender.sendMessage("§cInvalid Player!");
				}
			}
			else if(args[0].equalsIgnoreCase("rez")) {
				Player target = Bukkit.getPlayer(args[1]);
				if(target == null) {
					sender.sendMessage("§cCouldn't find player!");
					return true;
				}
				
				//To-Do: Respawn and give player their inventory back.
				
				if(!DeathRespawnManager.backup.containsKey(target.getName())) {
					sender.sendMessage("§aPlayer has not died yet!");
					return true;
				}
				
				DeathRespawnManager.backup.get(target.getName()).RezPlayer();
				sender.sendMessage("§aResurrected Player!");
				
			}
		}
		
		
		if(args.length == 2) {
			if(args[0].equalsIgnoreCase("magmacream")) {
				Player target = Bukkit.getPlayer(args[1]);
				if(target == null) {
					sender.sendMessage("§cInvalid player!");
					return true;
				}
				
				target.getInventory().addItem(RDvZ.is.magmacream);
				target.sendMessage("§aYou've been gifted with a second chance!");
				
				PlayerData td = PlayerManager.getPlayerData(target);
				td.hasClaimedClasses = false;
				td.isDwarf = false;
				
				sender.sendMessage("§aGiven player a class claim");
			}
		}
		
		if(args.length == 3) {
			if(args[0].equalsIgnoreCase("sethero")) {
			Player target = Bukkit.getPlayer(args[1]);
			if(target == null) {
				sender.sendMessage("§cInvalid Player!");
				return true;
			}
			
			switch(args[2].toLowerCase()) {
			case "dragonwarrior":
				PlayerManager.getHeroByName("DragonWarrior").setPlayer(target, true);
				sender.sendMessage("§aTurned " + target.getName() + " into the Dragon Warrior!");
				target.sendMessage("You have become the Dragon Warrior!");
				break;
			case "icequeen":
				PlayerManager.getHeroByName("IceQueen").setPlayer(target, true);
				sender.sendMessage("§aTurned " + target.getName() + " into the Ice Queen!");
				target.sendMessage("You have become the Ice Queen!");
				break;
			case "lightningking":
				PlayerManager.getHeroByName("LightningKing").setPlayer(target, true);
				sender.sendMessage("§aTurned " + target.getName() + " into the Lightning King!");
				target.sendMessage("You have become the Lightning King!");
				break;
			case "barbarian":
				PlayerManager.getHeroByName("Barbarian").setPlayer(target, true);
				sender.sendMessage("§aTurned " + target.getName() + " into the Barbarian!");
				target.sendMessage("You have become the Barbarian!");
				break;
			}
			}
		}
		
		if(args.length == 1) {
			if(!(sender instanceof Player))
				return true;
			Player player = (Player) sender;
			if(args[0].equalsIgnoreCase("classes")) {
				Inventory inv = player.getInventory();
				
				inv.addItem(RDvZ.is.becomeBuilder);
				inv.addItem(RDvZ.is.becomeSmith);
				inv.addItem(RDvZ.is.becomeTailor);
				inv.addItem(RDvZ.is.becomeAlchemist);
				inv.addItem(RDvZ.is.becomeBaker);
				
				inv.addItem(RDvZ.is.becomeZombie);
				inv.addItem(RDvZ.is.becomeSkeleton);
				inv.addItem(RDvZ.is.becomeCreeper);
				inv.addItem(RDvZ.is.becomeSpider);
				inv.addItem(RDvZ.is.becomeWolf);
				inv.addItem(RDvZ.is.becomeIrongolem);
				inv.addItem(RDvZ.is.becomeBroodmother);
				inv.addItem(RDvZ.is.becomeEnderman);
				sender.sendMessage("§aGiven items!");
			}
			
			if(args[0].equalsIgnoreCase("potions")) {
				Inventory inv = player.getInventory();
				
				inv.addItem(RDvZ.is.healPotion);
				inv.addItem(RDvZ.is.speedPotion);
				inv.addItem(RDvZ.is.fresistPotion);
				inv.addItem(RDvZ.is.strengthPotion);
				sender.sendMessage("§aGiven items!");
			}
			
			if(args[0].equalsIgnoreCase("list")) {
				sender.sendMessage("§6Item List:");
				for(ItemStack item : RDvZ.is.itemLists) {
					sender.sendMessage("§a- §7" + item.getItemMeta().getDisplayName());
				}
			}
			
		}
		if(args.length == 2) {
			Player target = Bukkit.getPlayer(args[1]);
			if(target == null) {
				sender.sendMessage("§cCould not find player!");
				return true;
			}
			if(args[0].equalsIgnoreCase("classes")) {
				Inventory inv = target.getInventory();
				
				inv.addItem(RDvZ.is.becomeBuilder);
				inv.addItem(RDvZ.is.becomeSmith);
				inv.addItem(RDvZ.is.becomeTailor);
				inv.addItem(RDvZ.is.becomeAlchemist);
				inv.addItem(RDvZ.is.becomeBaker);
				
				inv.addItem(RDvZ.is.becomeZombie);
				inv.addItem(RDvZ.is.becomeSkeleton);
				inv.addItem(RDvZ.is.becomeCreeper);
				inv.addItem(RDvZ.is.becomeSpider);
				inv.addItem(RDvZ.is.becomeWolf);
				inv.addItem(RDvZ.is.becomeIrongolem);
				inv.addItem(RDvZ.is.becomeBroodmother);
				inv.addItem(RDvZ.is.becomeEnderman);
				sender.sendMessage("§aGiven items to player!");
				
			}
			if(args[0].equalsIgnoreCase("potions")) {
				Inventory inv = target.getInventory();
				
				inv.addItem(RDvZ.is.healPotion);
				inv.addItem(RDvZ.is.speedPotion);
				inv.addItem(RDvZ.is.fresistPotion);
				inv.addItem(RDvZ.is.strengthPotion);
				sender.sendMessage("§aGiven items to player!");
			}
		}
		
		if(args.length == 3) {
			if(args[0].equalsIgnoreCase("give")) {
				Player target = Bukkit.getPlayer(args[1]);
				if(target == null) {
					sender.sendMessage("§cInvalid Player!");
					return true;
				}
				
				PlayerData td = PlayerManager.getPlayerData(target);
				
				String iname = args[2].toLowerCase();
				
				for(ItemStack item : RDvZ.is.itemLists) {
					if(item.getItemMeta().getDisplayName().toLowerCase().contains(iname)) {
						target.getInventory().addItem(item);
						target.sendMessage("§aYou were given " + item.getItemMeta().getDisplayName());
						sender.sendMessage("§6Gave " + target.getName() + ": " + item.getItemMeta().getDisplayName());
						td.isHero = true;
						return true;
					}
				}
				
				sender.sendMessage("§cCould not find item: §e" + iname);
				
			}
		}
		

		return true;
	}

}
