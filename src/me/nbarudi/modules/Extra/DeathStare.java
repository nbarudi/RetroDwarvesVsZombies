package me.nbarudi.modules.Extra;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;
import me.nbarudi.util.RayCast.Raycast;
import me.nbarudi.util.RayCast.Raycast.RaycastType;

public class DeathStare extends ItemModule {
	
	ArrayList<String> cooldown = new ArrayList<String>();
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		PlayerData pd = PlayerManager.getPlayerData(player);

		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.deathStare.getItemMeta().getDisplayName()))
			return;
		if(player.isOp())
			player.sendMessage("§cOP Bypass Enabled");
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		
		if(cooldown.contains(player.getName())) {
			player.sendMessage("§3Spell is on cooldown!");
			return;
		}
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			
			cooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					cooldown.remove(player.getName());
				}
			}, 10);
			
			Raycast ray = new Raycast(player.getEyeLocation(), 15);
			ray.setOwner(player);
			ray.addPassthroughMaterial(Material.LONG_GRASS);
			ray.addPassthroughMaterial(Material.LEAVES);
			ray.setShowRayCast(true);
			
			if(ray.compute(RaycastType.ENTITY)) {
				Entity ent = ray.getHurtEntity();
				if(!(ent instanceof Player))
					return;
				Player t = (Player) ent;
				t.setHealth(0);
				Bukkit.broadcastMessage(pd.name + "§r: §cDeath Stare!");
			}
			
		}
	}
	
	@Override
	public void runAbility(Player player) {}

}
