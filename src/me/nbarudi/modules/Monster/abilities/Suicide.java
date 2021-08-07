package me.nbarudi.modules.Monster.abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;

public class Suicide extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		//PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.suicide.getItemMeta().getDisplayName()))
			return;
		if(player.isOp())
			player.sendMessage("§cOP Bypass Enabled");
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			
			player.setHealth(0);
			player.sendMessage("§4You have committed Seppuku");
			
			runAbility(player);
		}
	}
	
	@Override
	public void runAbility(Player player) {}

}
