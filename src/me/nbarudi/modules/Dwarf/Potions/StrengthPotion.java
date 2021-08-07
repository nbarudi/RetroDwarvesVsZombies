package me.nbarudi.modules.Dwarf.Potions;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class StrengthPotion extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		//PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.strengthPotion.getItemMeta().getDisplayName()))
			return;
		if(player.isOp())
			player.sendMessage("§cOP Bypass Enabled");
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			runAbility(player);
		}
	}
	
	@Override
	public void runAbility(Player player) {
		
		Location loc = player.getLocation();
		
		PlayerData pd = PlayerManager.getPlayerData(player);
		if(pd.mana < 300){
			player.sendMessage("§3You do not have enough mana!");
			return;
		}
		
		loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY() - 1, loc.getZ(), 100, 1, 1, 1);
		loc.getWorld().playSound(loc, Sound.ENTITY_SPLASH_POTION_BREAK, 1, 1);
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 0));
		
		pd.mana = pd.mana - 300;
		
	}

}
