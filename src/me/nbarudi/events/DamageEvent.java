package me.nbarudi.events;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.util.Vector;

import me.nbarudi.main.RDvZ;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class DamageEvent implements Listener {
	
	public static double dragonHealth = 100;
	public static String lastDamager = "";
	public static boolean trackDamage = true;
	
	public static ArrayList<String> godmode = new ArrayList<String>();
	
	public static BossBar bb = Bukkit.createBossBar("§4Vlaurunga the Dragon", BarColor.RED, BarStyle.SOLID, BarFlag.DARKEN_SKY);
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		
		System.out.print(event.getEntity());
		if(RDvZ.dragon == null)
			return;
		
		if(!(event.getEntity() instanceof Player))
			return;
		
		Player dragon = (Player)event.getEntity();
		
		if(event.getDamager() instanceof Projectile) {
			if(!dragon.getName().equals(RDvZ.dragon.getName()))
				return;
			
			if(((Projectile)event.getDamager()) instanceof Fireball) {
				event.setCancelled(true);
				return;
			}
				
			
			if(trackDamage) {
				dragonHealth = dragonHealth - 1;
				bb.setProgress(dragonHealth/100);
			}
			
			Random rnd = new Random();
			int chance = rnd.nextInt(100) + 1;
			if(chance > 90) {
				dragon.setFlying(false);
				dragon.setAllowFlight(false);
				dragon.getWorld().playSound(dragon.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 3, 0.5f);
				Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
					public void run() {
						dragon.setVelocity(new Vector(0, 10, 0));
						dragon.setAllowFlight(true);
						dragon.setFlying(true);
					}
				}, 200);
			}
			
			event.setDamage(1);
			dragon.setHealth(20);
			dragon.getWorld().playSound(dragon.getLocation(), Sound.ENTITY_ENDERDRAGON_HURT, 2, 1);
			return;
		}
		
		if(!(event.getDamager() instanceof Player))
			return;
		
		Player damager = (Player)event.getDamager();

		if(!dragon.getName().equals(RDvZ.dragon.getName()))
			return;
		
		if(trackDamage) {
			dragonHealth = dragonHealth - 1;
			bb.setProgress(dragonHealth/100);
		}
		
		event.setDamage(1);
		dragon.setHealth(20);
		dragon.getWorld().playSound(dragon.getLocation(), Sound.ENTITY_ENDERDRAGON_HURT, 2, 1);
		lastDamager = damager.getName();
		
	}
	
	@EventHandler
	public void onGDamage(EntityDamageEvent event) {
		if(!(event.getEntity() instanceof Player))
			return;
		
		if(godmode.contains(event.getEntity().getName()))
			event.setCancelled(true);
		
		Player dragon = (Player)event.getEntity();
		
		if(RDvZ.dragon == null)
			return;
		
		if(!(dragon.getName().equals(RDvZ.dragon.getName())))
			return;
		
		
		if(event.getCause().equals(DamageCause.FIRE) || event.getCause().equals(DamageCause.FIRE_TICK)) {
			event.setDamage(0);
			event.setCancelled(true);
		}
		
		if(event.getCause().equals(DamageCause.BLOCK_EXPLOSION) || event.getCause().equals(DamageCause.ENTITY_EXPLOSION)) {
			event.setDamage(0);
			event.setCancelled(true);
		}
		
		if(event.getCause().equals(DamageCause.LAVA) || event.getCause().equals(DamageCause.SUFFOCATION)) {
			event.setDamage(0);
			event.setCancelled(true);
		}
		
		
		if(!event.getCause().equals(DamageCause.FALL))
			return;
		
		
		
		dragon.getWorld().createExplosion(dragon.getLocation(), 7, true);
		
		event.setCancelled(true);
		
	}
	
	@EventHandler
	public void onDamage2(EntityDamageEvent event) {
		if(!(event.getEntity() instanceof Player))
			return;
		
		Player player = (Player)event.getEntity();
		PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(!pd.isDwarf) 
			if(event.getCause().equals(DamageCause.FALL))
				event.setCancelled(true);
	}
	
	@EventHandler
	public void onDamage3(EntityDamageByEntityEvent event) {
		if(!(event.getEntity() instanceof Player))
			return;
		if(!(event.getDamager() instanceof Player))
			return;
		
		Player damager = (Player)event.getDamager();
		
		if(damager.isOp())
			event.setDamage(99999);
		
		if(damager.getInventory().getItemInMainHand().getType().toString().toLowerCase().contains("axe")) {
			event.setDamage(0);
			damager.sendMessage("§cSorry! Axes are 'a little' too OP for combat! Good Luck!");
			damager.sendMessage("§6You've dealt 0 damage with your axe!");
		}
	}

}
