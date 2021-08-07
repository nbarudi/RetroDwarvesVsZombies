package me.nbarudi.modules.heros.icequeen.items;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class IceQueenStaff extends ItemModule {
	
	ArrayList<String> cooldown = new ArrayList<String>();
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted)
			return;
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.icequeenstaff.getItemMeta().getDisplayName()))
			return;
		
		PlayerData pd = PlayerManager.getPlayerData(player);
		
		

		if(!pd.isHero) {
			player.sendMessage("§cYou must be a hero to use this ability!");
			return;
		}
		

		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			if(pd.mana < 50) {
				player.sendMessage("§cYou do not have enough mana! (Cost: 50 Mana)");
				return;
			}
			Snowball sb = (Snowball) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.SNOWBALL);
			sb.setVelocity(player.getLocation().getDirection().multiply(1.5));
			sb.setShooter(player);
			sb.setMetadata("icequeen", new FixedMetadataValue(RDvZ.instance, "yes"));
			pd.mana = pd.mana - 50;
		}
		else if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if(pd.mana < 500) {
				player.sendMessage("§cYou do not have enough mana! (Cost: 500 Mana)");
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
					player.sendMessage("§3You may now use your ice staff!");
				}
			}, 100);
			
			for(Player plr : Bukkit.getOnlinePlayers()) {
				Location tloc = plr.getLocation();
				Location ploc = player.getLocation();
				
				if(plr.getName().equals(player.getName()))
					continue;
				
				double dist = tloc.distance(ploc);
				if(dist <= 25) {
					plr.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 3));
					plr.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1200, 3));
					plr.spawnParticle(Particle.SNOWBALL, tloc.getX(), tloc.getY(), tloc.getZ(), 50, 0, 2, 0);
					plr.sendMessage("§bYou've been slowed!");
				}
			}
			
			player.sendMessage("§aYou've slowed the people around you!");
			pd.mana = pd.mana - 500;
			
		}
		
	}
}
