package me.nbarudi.modules.Extra;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.RayCast.RayTrace;


public class EasyFixSlabs extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		//PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.easyFixSlabs.getItemMeta().getDisplayName()))
			return;
		if(player.isOp())
			player.sendMessage("§cOP Bypass Enabled");
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			
			ItemStack cost = RDvZ.is.easyFixSlabs;
			cost.setAmount(1);
			
			player.getInventory().removeItem(cost);
			
			String dir = getCardinalDirection(player);
			Block block = RayTrace.getTargetBlock(player, 3);
			if(block.getType().equals(Material.AIR)) {
				Location bloc = block.getLocation();
				
				//Simple Directions
				if(dir.equalsIgnoreCase("N")) {
					// Build on the Z
					Location loc = new Location(bloc.getWorld(), bloc.getX() + 1, bloc.getY(), bloc.getZ());
					//Pos X
					for(int x = -4; x < 5; x++) {
						for(int y = -1; y < 3; y++) {
							Location nloc = new Location(loc.getWorld(), loc.getX(), loc.getY() + y, loc.getZ() + x);
							if(nloc.getBlock().getType().equals(Material.AIR)) {
								nloc.getBlock().setType(Material.DOUBLE_STEP);
								nloc.getWorld().spawnEntity(nloc, EntityType.SPLASH_POTION);
							}
						}
					}
					
				}
				else if(dir.equalsIgnoreCase("E")) {
					//Build on the Z
					Location loc = new Location(bloc.getWorld(), bloc.getX() - 1, bloc.getY(), bloc.getZ());
					//Pos X
					for(int x = -4; x < 5; x++) {
						for(int y = -1; y < 3; y++) {
							Location nloc = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ());
							if(nloc.getBlock().getType().equals(Material.AIR)) {
								nloc.getBlock().setType(Material.DOUBLE_STEP);
								nloc.getWorld().spawnEntity(nloc, EntityType.SPLASH_POTION);
							}
						}
					}
				}
				else if(dir.equalsIgnoreCase("S")) {
					// Build on the X
					Location loc = new Location(bloc.getWorld(), bloc.getX(), bloc.getY(), bloc.getZ() + 1);
					//Pos X
					for(int x = -4; x < 5; x++) {
						for(int y = -1; y < 3; y++) {
							Location nloc = new Location(loc.getWorld(), loc.getX(), loc.getY() + y, loc.getZ() + x);
							if(nloc.getBlock().getType().equals(Material.AIR)) {
								nloc.getBlock().setType(Material.DOUBLE_STEP);
								nloc.getWorld().spawnEntity(nloc, EntityType.SPLASH_POTION);
							}
						}
					}
				}
				else if(dir.equalsIgnoreCase("W")) {
					//Build on the X
					Location loc = new Location(bloc.getWorld(), bloc.getX(), bloc.getY(), bloc.getZ() - 1);
					
					//Pos X
					for(int x = -4; x < 5; x++) {
						for(int y = -1; y < 3; y++) {
							Location nloc = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ());
							if(nloc.getBlock().getType().equals(Material.AIR)) {
								nloc.getBlock().setType(Material.DOUBLE_STEP);
								nloc.getWorld().spawnEntity(nloc, EntityType.SPLASH_POTION);
							}
						}
					}
				}
				
				
			}
		}
		runAbility(player);
	}
	
	@Override
	public void runAbility(Player player) {}

	
	public static String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
         if (0 <= rotation && rotation < 22.5) {
            return "N";
        } else if (22.5 <= rotation && rotation < 67.5) {
            return "NE";
        } else if (67.5 <= rotation && rotation < 112.5) {
            return "E";
        } else if (112.5 <= rotation && rotation < 157.5) {
            return "SE";
        } else if (157.5 <= rotation && rotation < 202.5) {
            return "S";
        } else if (202.5 <= rotation && rotation < 247.5) {
            return "SW";
        } else if (247.5 <= rotation && rotation < 292.5) {
            return "W";
        } else if (292.5 <= rotation && rotation < 337.5) {
            return "NW";
        } else if (337.5 <= rotation && rotation < 360.0) {
            return "N";
        } else {
            return null;
        }
	}
	
}
