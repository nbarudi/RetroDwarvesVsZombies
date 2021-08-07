package me.nbarudi.modules.Dwarf;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.ChatSystems;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;
import me.nbarudi.util.PlayerData.DwarfClass;

public class BecomeEnchanter extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted) {
			player.sendMessage("§cGame has not started!");
			return;
		}
		if(!item.equals(RDvZ.is.becomeEnchanter))
			return;
		PlayerData pd = PlayerManager.getPlayerData(player);
		if(!pd.hasClaimedClasses) {
			player.sendMessage("§cYou have not claimed your classes!");
			return;
		}
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			runAbility(player);
		}
		
	}
	
	@Override
	public void runAbility(Player player) {
		player.getInventory().clear();
		PlayerData pd = PlayerManager.getPlayerData(player);
		pd.role = DwarfClass.ENCHANTER;
		pd.setDwarf(true);
		RDvZ.dwarf.addEntry(player.getName());
		
		ChatSystems.Warning(player, "§eTHE ENCHANTER CLASS IS STILL A WORK IN PROGRESS! IT IS ONLY ADDED FOR EASTER EGG REASONS! EXPECT TO SEE MANY BUGS AND BALANCE ISSUES! IF YOU WOULD LIKE TO CHANGE CLASSES, SAY IN CHAT!");
		//Give Items:
		
		//Class Book
		player.getInventory().addItem(RDvZ.is.classbook);
		
		//Class Spesific
		player.getInventory().addItem(new ItemStack(Material.BOOKSHELF, 64));
		player.getInventory().addItem(new ItemStack(Material.CHEST, 2));
		player.getInventory().addItem(new ItemStack(Material.COAL, 20));
		player.getInventory().addItem(new ItemStack(Material.SIGN, 3));
		
		//Universal
		player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
		player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
		player.getInventory().addItem(new ItemStack(Material.IRON_SPADE));
		player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 5));
		
		double x = RDvZ.instance.getConfig().getDouble("Warps.dwarf.x");
		double y = RDvZ.instance.getConfig().getDouble("Warps.dwarf.y");
		double z = RDvZ.instance.getConfig().getDouble("Warps.dwarf.z");
		String world = RDvZ.instance.getConfig().getString("Warps.dwarf.world");
		World w = Bukkit.getWorld(world);
		
		Location loc = new Location(w, x, y, z);
		player.sendMessage("§6Warping to Dwarf Spawn");
		player.teleport(loc);
		
		player.sendMessage("§aYou have become an Enchanter!");
	}

}