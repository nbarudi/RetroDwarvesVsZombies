package me.nbarudi.modules.Monster.abilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.events.ShrineManager;
import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;

public class BroodBreak extends ItemModule {
	
	ArrayList<String> cooldown = new ArrayList<String>();
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		//PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.broodBreak.getItemMeta().getDisplayName()))
			return;
		if(player.isOp())
			player.sendMessage("§cOP Bypass Enabled");
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		
		if(cooldown.contains(player.getName())) {
			player.sendMessage("§3This spell is currently on cooldown!");
			return;
		}
		
		if(action.equals(Action.LEFT_CLICK_BLOCK)) {
			cooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					cooldown.remove(player.getName());
					player.sendMessage("§3Spell is no longer on cooldown!");
				}
			}, 300);
			
			Block block = event.getClickedBlock();
			if(block == null || block.getType() == Material.AIR)
				return;
			
			if(block.getType().equals(Material.ENCHANTMENT_TABLE))
				ShrineManager.breakShrine();
			
			if(block.getType().equals(Material.BEDROCK)) {
				player.sendMessage("§4Metas are fun... but I like to change things up!");
				player.sendMessage("§eBreaking Bedrock has been patched!");
				return;
			}
			
			block.setType(Material.WOOL);
			block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_CLOTH_PLACE, 2, 1);
			block.getLocation().getWorld().spawnParticle(Particle.BLOCK_DUST, block.getLocation().getX(), block.getLocation().getY(), block.getLocation().getZ(), 50);
			
			
			runAbility(player);
		}
	}
	
	@Override
	public void runAbility(Player player) {}

}
