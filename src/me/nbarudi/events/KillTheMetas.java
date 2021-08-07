package me.nbarudi.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nbarudi.main.RDvZ;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;
import me.nbarudi.util.PlayerData.DwarfClass;

public class KillTheMetas implements Listener {
	
	static double MAXDISTANCE = 100;
	
	ArrayList<String> messageCooldown = new ArrayList<String>();
	
	@EventHandler
	public void antiMiningAct(PlayerMoveEvent event) {
		
		Player plr = event.getPlayer();
		PlayerData pd = PlayerManager.getPlayerData(plr);
		
		if(!pd.isDwarf || pd.role.equals(DwarfClass.NONE))
			return;
		
		if(!RDvZ.gameStarted)
			return;
		
		if(plr.isOp())
			return;
		
		//Shrine Location
		double x = RDvZ.instance.getConfig().getDouble("Warps.dwarf.x");
		double y = RDvZ.instance.getConfig().getDouble("Warps.dwarf.y");
		double z = RDvZ.instance.getConfig().getDouble("Warps.dwarf.z");
		String world = RDvZ.instance.getConfig().getString("Warps.dwarf.world");
		World w = Bukkit.getWorld(world);
		
		Location loc = new Location(w, x, y, z);
		
		Location ploc = event.getTo();
		
		double dist = loc.distance(ploc);
		dist = Math.abs(dist);
		if(dist >= MAXDISTANCE) {
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 99999, 2));
			if(messageCooldown.contains(event.getPlayer().getName()))
				return;
			event.getPlayer().sendMessage("§eThe shrines protection is too week this far way.. The monster aura begins to corrupt you");
			messageCooldown.add(event.getPlayer().getName());
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					messageCooldown.remove(event.getPlayer().getName());
				}
			}, 40);
		}else {
			event.getPlayer().removePotionEffect(PotionEffectType.WITHER);
		}
	}
	
	@EventHandler
	public void AntiPlaceBoio(BlockPlaceEvent event) {
	}
	
	@EventHandler
	public void screwLava(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(player.isOp())
			return;
		
		ItemStack item = player.getInventory().getItemInMainHand();
		if(item.getType().equals(Material.LAVA_BUCKET)) {
			event.setCancelled(true);
			player.sendMessage("§aIf I keep Lava being a Meta... This game will go to *hell*! Get it? Hell... Lava... HaHa I'm funny!");
		}
	}

}
