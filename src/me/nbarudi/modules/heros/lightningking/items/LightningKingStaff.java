package me.nbarudi.modules.heros.lightningking.items;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class LightningKingStaff extends ItemModule {
	
	ArrayList<String> cooldown = new ArrayList<String>();
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted)
			return;
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.lightningkingstaff.getItemMeta().getDisplayName()))
			return;
		
		PlayerData pd = PlayerManager.getPlayerData(player);
		
		

		if(!pd.isHero) {
			player.sendMessage("§cYou must be a hero to use this ability!");
			return;
		}
		

		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			if(pd.mana < 75) {
				player.sendMessage("§cYou do not have enough mana! (Cost: 75 Mana)");
				return;
			}
			Arrow sb = (Arrow) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.ARROW);
			sb.setVelocity(player.getLocation().getDirection().multiply(1.5));
			sb.setShooter(player);
			sb.setMetadata("lightningking", new FixedMetadataValue(RDvZ.instance, "yes"));
			pd.mana = pd.mana - 75;
		}
		else if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if(pd.mana < 750) {
				player.sendMessage("§cYou do not have enough mana! (Cost: 750 Mana)");
				return;
			}
			if(cooldown.contains(player.getName())) {
				player.sendMessage("§3This spell is on cooldown!");
				return;
			}
			
			cooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					cooldown.remove(player.getName());
					player.sendMessage("§3You may now use your lightning staff!");
				}
			}, 300);
			
			for(Player plr : Bukkit.getOnlinePlayers()) {
				Location tloc = plr.getLocation();
				Location ploc = player.getLocation();
				
				if(plr.getName().equals(player.getName()))
					continue;
				
				double dist = tloc.distance(ploc);
				if(dist <= 10) {
					plr.getWorld().strikeLightning(plr.getLocation());
					plr.sendMessage("§bYou've been smitten!");
				}
			}
			
			player.sendMessage("§aYou've smitten the people around you!");
			pd.mana = pd.mana - 750;
			
		}
		
	}
}
