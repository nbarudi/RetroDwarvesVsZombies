package me.nbarudi.modules.heros.barbarian.items;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.nbarudi.events.DamageEvent;
import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class BarbarianSlam extends ItemModule {
	
	
	ArrayList<String> cooldown = new ArrayList<String>();
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted)
			return;
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.barbarianslam.getItemMeta().getDisplayName()))
			return;
		
		PlayerData pd = PlayerManager.getPlayerData(player);
		
		

		if(!pd.isHero) {
			player.sendMessage("§cYou must be a hero to use this ability!");
			return;
		}
		

		
//		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
//			if(pd.mana < 50) {
//				player.sendMessage("§cYou do not have enough mana! (Cost: 50 Mana)");
//				return;
//			}
//			Snowball sb = (Snowball) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.SNOWBALL);
//			sb.setVelocity(player.getLocation().getDirection().multiply(1.5));
//			sb.setShooter(player);
//			sb.setMetadata("icequeen", new FixedMetadataValue(RDvZ.instance, "yes"));
//			pd.mana = pd.mana - 50;
//		}
		if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if(pd.mana < 250) {
				player.sendMessage("§cYou do not have enough mana! (Cost: 250 Mana)");
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
					player.sendMessage("§3You may now use your barbarian slam!");
				}
			}, 200);
			
			DamageEvent.godmode.add(player.getName());
			player.setVelocity(new Vector(0,10,0));
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					player.setVelocity(new Vector(0,-20,0));
					Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
						public void run() {
							DamageEvent.godmode.remove(player.getName());
							for(Player plr : Bukkit.getOnlinePlayers()) {
								
								if(plr.getName().equalsIgnoreCase(player.getName()))
									continue;
								
								Location loc1 = plr.getLocation();
								Location loc2 = player.getLocation();
								
								if(loc1.distance(loc2) < 10) {
									plr.setVelocity(new Vector(0, 15, 0));
									if(player.getHealth() <= 10) {
										player.setHealth(0);
									}else {
										plr.setHealth(plr.getHealth() - 10);
									}
									plr.getWorld().spawnParticle(Particle.BLOCK_DUST, loc1.getX(), loc1.getY(), loc1.getZ(), 50);
									plr.sendMessage("§3You've been launched!");
								}
								
							}
						}
					}, 20);
				}
			}, 20);
			
			pd.mana = pd.mana - 250;
			
		}
		
	}

}
