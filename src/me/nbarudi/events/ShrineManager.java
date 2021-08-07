package me.nbarudi.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nbarudi.main.RDvZ;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class ShrineManager implements Listener {
	
	private static int remShrine = 9;
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		Player player = event.getPlayer();
		PlayerData pd = PlayerManager.getPlayerData(player);
		
		Block block = event.getBlock();
		
		if(!block.getType().equals(Material.ENCHANTMENT_TABLE))
			return;
		
		if(pd.isDwarf) {
			event.setCancelled(true);
			player.getWorld().strikeLightning(player.getLocation());
			player.sendMessage("§7[§dGods§7] §aHow dare you attempt to destroy our shrine!");
			return;
		}
		
		
		breakShrine();
		
		
	}
	
	public static void breakShrine() {
		remShrine--;
		
		if(remShrine == 0) {
			Bukkit.broadcastMessage("§7[§dGods§7] §aAll of the shrines have been destroyed! The dwarves have been blessed with a final stand!");
			RDvZ.shrinedestroyed = true;
			for(PlayerData pd : RDvZ.data) {
				Player plr = PlayerManager.getPlayer(pd);
				if(pd.isDwarf) {
					plr.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6000, 1));
					plr.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 1));
					plr.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100000000, 5));
					plr.sendMessage("§6You feel your strength and speed increase!");
				}
			}
			Bukkit.broadcastMessage("§4All Monsters have been given a tracking compass!");
			return;
		}
		
		Bukkit.broadcastMessage("§7[§dGods§7] §aA Piece of the shrine has been broken! (" + remShrine + " shrines remain)");
	}

}
