package me.nbarudi.modules.Universal;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.main.RDvZ;
import me.nbarudi.misc.DwarvenVault;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class MagmaCream extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		PlayerData pd = PlayerManager.getPlayerData(player);
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.magmacream.getItemMeta().getDisplayName()))
			return;
		if(player.isOp()) {}
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		else if(pd.hasClaimedClasses) {
			player.sendMessage("§3You have already claimed your classes!");
			return;
		}

		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			player.getInventory().clear();
			player.sendMessage("§aYou have claimed your classes!");
			pd.hasClaimedClasses = true;
			pd.setDwarf(true);
			super.onInteracted(action, item, player, event);
		}
	}
	
	@Override
	public void runAbility(Player player) {
		
		if(!RDvZ.admins.contains(player.getName())) {
			new DwarvenVault(player);
		}else {
			new DwarvenVault(player, DwarvenVault.quickInventory(player));
		}
		
		Random rnd = new Random();
		player.getInventory().addItem(RDvZ.is.becomeBuilder);
		
		int smithChance = rnd.nextInt(100);
		if(smithChance > 74)
			player.getInventory().addItem(RDvZ.is.becomeSmith);
		
		int tailorChance = rnd.nextInt(100);
		if(tailorChance > 74)
			player.getInventory().addItem(RDvZ.is.becomeTailor);
		
		int alchChance = rnd.nextInt(100);
		if(alchChance > 74)
			player.getInventory().addItem(RDvZ.is.becomeAlchemist);
		
		int bakerchance = rnd.nextInt(100);
		if(bakerchance > 74)
			player.getInventory().addItem(RDvZ.is.becomeBaker);
		
		//if((rnd.nextInt(100) + 1) >= 95)
		//	player.getInventory().addItem(RDvZ.is.becomeEnchanter);
		//if((rnd.nextInt(100) + 1) > 99)
		//	player.getInventory().setItem(8,RDvZ.is.becomeHero);
	}

}
