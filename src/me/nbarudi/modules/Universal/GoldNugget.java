package me.nbarudi.modules.Universal;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class GoldNugget extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		PlayerData pd = PlayerManager.getPlayerData(player);
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.goldnug.getItemMeta().getDisplayName()))
			return;
		if(player.isOp())
			player.sendMessage("§cOP Bypass Enabled");
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		else if(pd.hasClaimedClasses) {
			player.sendMessage("§3You have already claimed your classes!");
			return;
		}
		else if(!RDvZ.monstersReleased) {
			player.sendMessage("§3You cannot cast this spell!");
			return;
		}

		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			player.getInventory().clear();
			player.sendMessage("§aYou have claimed your classes!");
			pd.hasClaimedClasses = true;
			runAbility(player);
		}
	}
	
	@Override
	public void runAbility(Player player) {
		Random rnd = new Random();
		player.getInventory().addItem(RDvZ.is.becomeZombie);
		
		//Chances
		int skelChance = rnd.nextInt(100);
		int creChance = rnd.nextInt(100);
		int spiChance = rnd.nextInt(100);
		int wolfChance = rnd.nextInt(100);
		int golemChance = rnd.nextInt(100);
		int bmothChance = rnd.nextInt(100);
		int enderChance = rnd.nextInt(100);
		
		if(skelChance > 24) {
			player.getInventory().addItem(RDvZ.is.becomeSkeleton);
		}
		if(creChance > 44) {
			player.getInventory().addItem(RDvZ.is.becomeCreeper);
		}
		if(spiChance > 64) {
			player.getInventory().addItem(RDvZ.is.becomeSpider);
		}
		if(wolfChance > 75) {
			player.getInventory().addItem(RDvZ.is.becomeWolf);
		}
		if(golemChance > 85) {
			player.getInventory().addItem(RDvZ.is.becomeIrongolem);
		}
		if(bmothChance > 85) {
			player.getInventory().addItem(RDvZ.is.becomeBroodmother);
		}
		if(enderChance > 95) {
			player.getInventory().addItem(RDvZ.is.becomeEnderman);
		}
		
	}

}
