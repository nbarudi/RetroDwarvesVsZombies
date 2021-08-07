package me.nbarudi.modules.Extra;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class TrackingCompass extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		//PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.trackingCompas.getItemMeta().getDisplayName()))
			return;
		if(player.isOp())
			player.sendMessage("§cOP Bypass Enabled");
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		
		if(action.equals(Action.RIGHT_CLICK_AIR)) {
			
			double maxDist = 200;
			Player t = null;
			
			for(PlayerData data : RDvZ.data) {
				if(!data.isDwarf)
					continue;
				Player target = Bukkit.getPlayer(data.getRealName());
				if(target == null)
					continue;
				if(target.getLocation().distance(player.getLocation()) < maxDist) {
					maxDist = target.getLocation().distance(player.getLocation());
					t = target;
				}
			}
			
			if(t == null) {
				ItemStack hand = player.getInventory().getItemInMainHand();
				ItemMeta im = hand.getItemMeta();
				im.setDisplayName("§aTracking: No one!");
				hand.setItemMeta(im);
				return;
			}else {
				player.setCompassTarget(t.getLocation());
				ItemStack hand = player.getInventory().getItemInMainHand();
				ItemMeta im = hand.getItemMeta();
				im.setDisplayName("§aTracking: " + PlayerManager.getPlayerData(t).getName() + "!");
				hand.setItemMeta(im);
				return;
			}
			
		}
	}
	
	@Override
	public void runAbility(Player player) {}

}
