package me.nbarudi.modules.Dragon;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.libraryaddict.disguise.DisguiseAPI;
import me.nbarudi.events.DamageEvent;
import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerManager;

public class DragonCave extends ItemModule {
	
	boolean hasDied = false;
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted)
			return;
		if(RDvZ.dragon == null)
			return;
		if(!player.getName().equals(RDvZ.dragon.getName()))
			return;
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.caveDeath.getItemMeta().getDisplayName()))
			return;
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			
			DamageEvent.trackDamage = false;
			
			DamageEvent.dragonHealth = 10;
			DamageEvent.bb.setProgress(DamageEvent.dragonHealth/100);
			
			Bukkit.dispatchCommand(player, "/sphere air 15");
	        Bukkit.dispatchCommand(player, "/cyl netherrack 15");
	        Bukkit.dispatchCommand(player, "/hcyl 10%lava,90%air 15 3");
	        Bukkit.dispatchCommand(player, "minecraft:tp @p ~ ~2 ~");
	        Bukkit.dispatchCommand(player, "minecraft:setblock ~ ~-2 ~ minecraft:netherrack");
			
		}
		if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			
			if(hasDied)
				return;
			
			hasDied = true;
			
			DamageEvent.dragonHealth = 0;
			DamageEvent.bb.setProgress(DamageEvent.dragonHealth/100);
			
			Bukkit.dispatchCommand(player, "summon ender_dragon ~ ~ ~ {DragonPhase:9}");
			//Bukkit.dispatchCommand(player, "ud");
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999999, 0));
			player.setGameMode(GameMode.CREATIVE);
			
			Player dwarrior = null;
			double curDist = 9999999;
			for(Player d : Bukkit.getOnlinePlayers()) {
				
				if(d.getName().equals(player.getName()))
					continue;
				if(curDist > d.getLocation().distance(player.getLocation())) {
					if(d.isDead())
						continue;
					curDist = d.getLocation().distance(player.getLocation());
					dwarrior = d;
				}
			}
			final Player dw = dwarrior;
			//PlayerData pd = PlayerManager.getPlayerData(dwarrior);
			DisguiseAPI.undisguiseToAll(player);
			
			dwarrior.sendMessage("§6You have become the Dragon Warrior!");
			DamageEvent.godmode.add(dwarrior.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					DamageEvent.godmode.remove(dw.getName());
					dw.sendMessage("§cYour invincibility has run out!");
				}
			}, 1200);
			dwarrior.sendMessage("§aYou have been granted 60 seconds of invincibility!");
			PlayerManager.getHeroByName("DragonWarrior").setPlayer(dwarrior, false);
			RDvZ.dragon = null;
			
			DamageEvent.bb.removeAll();
		}
	}

}
