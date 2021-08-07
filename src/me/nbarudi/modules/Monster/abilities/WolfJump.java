package me.nbarudi.modules.Monster.abilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;

public class WolfJump extends ItemModule {
	
	ArrayList<String> cooldown = new ArrayList<String>();
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		//PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.wolfJump.getItemMeta().getDisplayName()))
			return;
		if(player.isOp())
			player.sendMessage("§cOP Bypass Enabled");
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		
		if(cooldown.contains(player.getName())) {
			player.sendMessage("§3This spell is on cooldown");
			return;
		}
			
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			
			cooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					cooldown.remove(player.getName());
					player.sendMessage("§3Spell is no longer on cooldown!");
				}
			}, 300);
			
			
			Vector dir = player.getLocation().getDirection();
			dir.setX(dir.getX() * 3);
			dir.setY(dir.getY() * 2);
			dir.setZ(dir.getZ() * 3);
			
			player.setVelocity(dir);
			
			player.playSound(player.getLocation(), Sound.ENTITY_WOLF_HOWL, 5, 1);
			
			runAbility(player);
		}
	}
	
	@Override
	public void runAbility(Player player) {}

}
