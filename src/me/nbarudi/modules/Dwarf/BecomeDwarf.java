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
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;
import me.nbarudi.util.PlayerData.DwarfClass;

public class BecomeDwarf extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted) {
			player.sendMessage("§cGame has not started!");
			return;
		}
		if(!item.equals(RDvZ.is.becomeBuilder))
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
		pd.role = DwarfClass.BUILDER;
		pd.setDwarf(true);
		RDvZ.dwarf.addEntry(player.getName());
		
		//Give Items:
		
		//Class Book
		player.getInventory().addItem(RDvZ.is.classbook);
		
		//Class Spesific
		player.getInventory().addItem(new ItemStack(Material.LEATHER_HELMET));
		player.getInventory().addItem(new ItemStack(Material.LEATHER_CHESTPLATE));
		player.getInventory().addItem(new ItemStack(Material.LEATHER_LEGGINGS));
		player.getInventory().addItem(new ItemStack(Material.LEATHER_BOOTS));
		player.getInventory().addItem(new ItemStack(Material.SMOOTH_BRICK, 128));
		
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
		
		player.sendMessage("§aYou have become a Builder!");
	}

}
