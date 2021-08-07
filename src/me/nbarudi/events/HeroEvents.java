package me.nbarudi.events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.nbarudi.main.RDvZ;

public class HeroEvents implements Listener {
	
	@EventHandler
	public void onSnowballDamage(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Snowball))
			return;
		Snowball sb = (Snowball)event.getDamager();
		if(sb.hasMetadata("icequeen")) {
			event.setDamage(4);
		}
	}
	
	@EventHandler
	public void onArrowDamage(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Arrow))
			return;
		Arrow arrow = (Arrow)event.getDamager();
		if(arrow.hasMetadata("lightningking")) {
			event.getEntity().getWorld().strikeLightning(event.getEntity().getLocation());
			event.getEntity().sendMessage("§3You've been smitten");
		}
	}
	
	@EventHandler
	public void onBarbSlam(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Player))
			return;
		
		Player player = (Player)event.getDamager();
		
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if(item == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null)
			return;
		
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.barbarianslam.getItemMeta().getDisplayName()))
			return;
		
		if(!(event.getEntity() instanceof Player))
			return;
		
		Player ent = (Player)event.getEntity();
		
		Vector vec = player.getLocation().getDirection();
		vec.setX(vec.getX() * 10);
		vec.setY((vec.getY() + 5) * 5);
		vec.setZ(vec.getZ() * 10);
		
		ent.setVelocity(vec);
		
		if(ent.getHealth() <= 5) {
			ent.setHealth(0);
		}else {
			ent.setHealth(ent.getHealth() - 5);
		}
		
		ent.sendMessage("§3You've been flung!");
		
	}

}
