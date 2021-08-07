package me.nbarudi.modules.heros;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerData.DwarfClass;
import me.nbarudi.util.PlayerManager;

public class BecomeHero extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted) {
			player.sendMessage("§cGame has not started!");
			return;
		}
		if(!item.equals(RDvZ.is.becomeHero))
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
		
		pd.setHero(true);
		
		Random rnd = new Random();
		int chance = rnd.nextInt(100) + 1;
		if(chance <= 40) {
			PlayerManager.getHeroByName("Barbarian").setPlayer(player, false);
		}
		else if(chance <=80) {
			PlayerManager.getHeroByName("IceQueen").setPlayer(player, false);
		}
		else {
			PlayerManager.getHeroByName("LightningKing").setPlayer(player, false);
		}
		
		double x = RDvZ.instance.getConfig().getDouble("Warps.dwarf.x");
		double y = RDvZ.instance.getConfig().getDouble("Warps.dwarf.y");
		double z = RDvZ.instance.getConfig().getDouble("Warps.dwarf.z");
		String world = RDvZ.instance.getConfig().getString("Warps.dwarf.world");
		World w = Bukkit.getWorld(world);
		
		Location loc = new Location(w, x, y, z);
		player.sendMessage("§6Warping to Dwarf Spawn");
		player.teleport(loc);
		
		player.getInventory().addItem(RDvZ.is.classbook);
		
	}

}
