package me.nbarudi.modules.Monster.abilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.TeleportUtil;
import me.nbarudi.util.RayCast.RayTrace;

public class EndermanBlink extends ItemModule {
	
	ArrayList<String> cooldown = new ArrayList<String>();
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		//PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.endermanBlink.getItemMeta().getDisplayName()))
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
			Block block = RayTrace.getTargetBlock(player, 20);
			if(block == null) {
				player.sendMessage("§3You cannot blink there!");
				return;
			}
			
			Location loc = block.getLocation();
			loc.setY(loc.getY() + 2);
			
			TeleportUtil.teleport(player, loc);
			player.playSound(loc, Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);
			player.spawnParticle(Particle.CRIT_MAGIC, loc.getX(), loc.getY(), loc.getZ(), 50);
			player.sendMessage("§3Blink!");
			
			runAbility(player);
		}
	}
	
	@Override
	public void runAbility(Player player) {}

}
