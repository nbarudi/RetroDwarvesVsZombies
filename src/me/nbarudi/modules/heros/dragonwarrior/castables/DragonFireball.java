package me.nbarudi.modules.heros.dragonwarrior.castables;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class DragonFireball extends ItemModule {
	
	ArrayList<String> cooldown = new ArrayList<String>();
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted)
			return;
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.dwarriorfireball.getItemMeta().getDisplayName()))
			return;
		
		PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(cooldown.contains(player.getName())) {
			player.sendMessage("§3This spell is on cooldown!");
			return;
		}

		if(!pd.isHero) {
			player.sendMessage("§cYou must be the dragon warrior to use this ability!");
			return;
		}
		
		if(pd.mana < 500) {
			player.sendMessage("§cYou do not have enough mana! (Cost: 500 Mana)");
			return;
		}
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			cooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					cooldown.remove(player.getName());
					player.sendMessage("§3You may now use your firecharge!");
				}
			}, 300);
			Location loc = player.getLocation();
			
			Vector vec = loc.getDirection();
			vec.setX(vec.getX() * 2);
			vec.setZ(vec.getZ() * 2);
			
			loc.setY(loc.getY() + 1);
			
			loc.add(vec);
			
			Fireball fb = (Fireball) player.getLocation().getWorld().spawnEntity(loc, EntityType.FIREBALL);
			
			fb.setYield(6);
			fb.setIsIncendiary(false);
			fb.setBounce(false);
			
			fb.setVelocity(loc.getDirection());
			player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1);
			player.sendMessage("§6You have shot a fireball!");
			pd.mana = pd.mana - 500;
			
		}
		
	}

}
