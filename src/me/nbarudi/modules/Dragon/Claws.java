package me.nbarudi.modules.Dragon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.TeleportUtil;

public class Claws extends ItemModule {
	
	public Claws() {
		this.isEntityInteract = true;
		Bukkit.getScheduler().scheduleSyncRepeatingTask(RDvZ.instance, new Runnable() {
			public void run() {
				if(!RDvZ.gameStarted)
					return;
				if(RDvZ.dragon == null) 
					return;
				if(RDvZ.grappled == "") 
					return;
				
				Player grappled = Bukkit.getPlayer(RDvZ.grappled);
				Player dragon = RDvZ.dragon;
				
				Location loc = dragon.getLocation();
				Vector direc = dragon.getLocation().getDirection();
				loc.add(direc);
				
				TeleportUtil.teleport(grappled, loc);
				
			}
		}, 20, 2);
	}
	
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted)
			return;
		if(RDvZ.dragon == null)
			return;
		if(!player.getName().equals(RDvZ.dragon.getName()))
			return;
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.roarGrapple.getItemMeta().getDisplayName()))
			return;
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 5, 1);
			return;
		}
		/*
		if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			Raycast cast = new Raycast(player.getEyeLocation(), 3);
			cast.setOwner(player);
			cast.setShowRayCast(false);
			
			
			if(cast.compute(RaycastType.ENTITY)) {
				Entity ent = cast.getHurtEntity();
				if(!(ent instanceof Player)) {
					player.sendMessage("§3No target");
					return;
				}
				
				Player t = (Player) ent;
				if(!RDvZ.grappled.equalsIgnoreCase(""))
					return;
				
				RDvZ.grappled = t.getName();
				player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1, (float) 0.5);
				player.sendMessage("§3You have grappled " + RDvZ.grappled);
				Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
					public void run() {
						player.sendMessage("§3You have dropped " + RDvZ.grappled);
						RDvZ.grappled = "";
						player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1, (float) 0.3);
					}
				}, 100);
			}else {
				player.sendMessage("§3No target");
				return;
			}
			
		}
		*/
	}
	
	@Override
	public void onEntityInteract(PlayerInteractEntityEvent event, ItemStack item, Player player) {
		
		Entity ent = event.getRightClicked();
		if(!(ent instanceof Player))
			return;
		if(!RDvZ.gameStarted)
			return;
		if(RDvZ.dragon == null)
			return;
		if(!player.getName().equals(RDvZ.dragon.getName()))
			return;
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.roarGrapple.getItemMeta().getDisplayName()))
			return;
		
		Player target = (Player) ent;
		
		if(!RDvZ.grappled.equalsIgnoreCase(""))
			return;
		
		RDvZ.grappled = target.getName();
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1, (float) 0.5);
		player.sendMessage("§3You have grappled " + RDvZ.grappled);
		Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
			public void run() {
				player.sendMessage("§3You have dropped " + RDvZ.grappled);
				RDvZ.grappled = "";
				player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1, (float) 0.3);
			}
		}, 100);
		
	}
}
