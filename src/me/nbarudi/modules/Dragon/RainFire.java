package me.nbarudi.modules.Dragon;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;

public class RainFire extends ItemModule {
	
	ArrayList<String> bcooldown = new ArrayList<String>();
	ArrayList<String> fcooldown = new ArrayList<String>();
	
	@SuppressWarnings("deprecation")
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted)
			return;
		if(RDvZ.dragon == null)
			return;
		if(!player.getName().equals(RDvZ.dragon.getName()))
			return;
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.firebreathBall.getItemMeta().getDisplayName()))
			return;
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			
			if(bcooldown.contains(player.getName())) {
				player.sendMessage("§3This spell is on cooldown!");
				return;
			}
			
			bcooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					bcooldown.remove(player.getName());
				}
			}, 70);
			
			RDvZ.fireTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(RDvZ.instance, new BukkitRunnable() {
				public void run() {
					if(RDvZ.looped == 25) {
						RDvZ.endTask(RDvZ.fireTask);
						RDvZ.looped = 0;
						return;
					}else {
						Location loc = player.getLocation();
						
						Vector add = loc.getDirection();
						add.setX(add.getX() * 2);
						add.setZ(add.getZ() * 2);
						
						Location fbloc = loc.add(add);
						
						
						FallingBlock fb = player.getWorld().spawnFallingBlock(fbloc, Material.FIRE, (byte) 0);
						fb.setDropItem(false);
						fb.setHurtEntities(false);
						
						
						Sound sound = Sound.BLOCK_PISTON_CONTRACT;
						loc.getWorld().playSound(loc, sound, 1, (float) 0.5);
						
						Vector vec = player.getLocation().getDirection();
						fb.setVelocity(vec);
						RDvZ.looped++;
					}
				}
			}, 0, 2);
			
			return;
		}
		if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			
			//FAA MOO LAA TEE ROO NOO DOOOOOOOMMMM
			
			if(fcooldown.contains(player.getName())) {
				player.sendMessage("§3This spell is on cooldown!");
				return;
			}
			
			fcooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					fcooldown.remove(player.getName());
				}
			}, 70);
			
			
			runAbility(player);
			
			return;
		}
		
	}
	
	@Override
	public void runAbility(Player player) {
		
		RDvZ.ballTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(RDvZ.instance, new Runnable() {
			public void run() {
				
				if(RDvZ.looped == 21) {
					RDvZ.endTask(RDvZ.ballTask);
					RDvZ.looped = 0;
					return;
				}else {
					Location loc = player.getLocation();
					
					Vector dir = loc.getDirection();
					dir.setX(dir.getX() * 3);
					dir.setZ(dir.getZ() * 3);

					loc.add(dir);
					
					loc.setY(loc.getY() + 2);
					
					
					Vector vec = player.getLocation().getDirection();

					
					
					Fireball ent = (Fireball) player.getWorld().spawnEntity(loc, EntityType.FIREBALL);
					ent.setVelocity(vec.multiply(1.5));
					ent.setBounce(false);
					ent.setYield(5);
					
					loc.getWorld().spawnParticle(Particle.FLAME, ent.getLocation().getX(), ent.getLocation().getY(), ent.getLocation().getZ(), 100);
					loc.getWorld().spawnParticle(Particle.SMOKE_NORMAL, ent.getLocation().getX(), ent.getLocation().getY(), ent.getLocation().getZ(), 100);
					loc.getWorld().playSound(loc, Sound.ITEM_FIRECHARGE_USE, 1, 1);
					
					RDvZ.looped++;
				}
				
			}
		}, 0, 5);
		
		
	}
	

}
