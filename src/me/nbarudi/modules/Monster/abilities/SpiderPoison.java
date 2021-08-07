package me.nbarudi.modules.Monster.abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;

public class SpiderPoison extends ItemModule {
	
	public SpiderPoison() {
		this.isEntityInteract = true;
	}
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		//PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.creeperExplode.getItemMeta().getDisplayName()))
			return;
		if(player.isOp())
			player.sendMessage("§cOP Bypass Enabled");
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			
			player.getWorld().createExplosion(player.getLocation(), 1);
			player.sendMessage("§4You have exploded");
			player.setHealth(0);
			
			runAbility(player);
		}
	}
	
	public void onEntityInteract(PlayerInteractEntityEvent event, ItemStack item, Player player) {
		
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.spiderPoison.getItemMeta().getDisplayName()))
			return;
		if(player.isOp())
			player.sendMessage("§cOP Bypass Enabled");
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		
		if(!(event.getRightClicked() instanceof Player))
			return;
		
		Player target = (Player)event.getRightClicked();
		target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
		target.sendMessage("§4You have been poisoned!");
		player.sendMessage("§aYou have poisoned " + target.getName());
		
	}
	
	@Override
	public void runAbility(Player player) {}

}
