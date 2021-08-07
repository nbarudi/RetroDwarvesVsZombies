package me.nbarudi.modules.Extra;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;

public class CakeSpawner extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		//PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.cakeSpawner.getItemMeta().getDisplayName()))
			return;
		if(player.isOp())
			player.sendMessage("§cOP Bypass Enabled");
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		
		if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
			
			ItemStack cost = RDvZ.is.cakeSpawner;
			cost.setAmount(1);
			
			player.getInventory().removeItem(cost);
			
			Block block = event.getClickedBlock();
			if(block == null || block.getType().equals(Material.AIR)) {
				return;
			}
			
			Location loc = block.getLocation();
			loc.setY(loc.getY() + 1);
			loc.getBlock().setType(Material.CAKE_BLOCK);
			
			runAbility(player);
		}
	}
	
	@Override
	public void runAbility(Player player) {}

}
