package me.nbarudi.modules;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemModule implements Listener {
	
	public boolean isEntityInteract = false;
	
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) { runAbility(player); }

	public void runAbility(Player player) {}
	
	public void onEntityInteract(PlayerInteractEntityEvent event, ItemStack item, Player player) {}

}
