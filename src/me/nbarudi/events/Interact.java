package me.nbarudi.events;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;



public class Interact implements Listener {
	

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		if(event.getClickedBlock() == null) return;
		
		
//		if(event.getClickedBlock().getState().getData() instanceof Crops && ((Crops) event.getClickedBlock().getState().getData()).getState() == CropState.RIPE) {
//			if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
//			Block block = event.getClickedBlock();
//			block.breakNaturally();
//			block.setType(Material.WHEAT);
//		}
		
		Block block = event.getClickedBlock();
		
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(block.getType().equals(Material.CAKE_BLOCK)) {
				event.setCancelled(true);
				event.getPlayer().sendMessage("Nom...");
				event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() + 5);
			}
		}
		/*
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(block.getType().equals(Material.ENCHANTMENT_TABLE)) {
				if(!event.getPlayer().isOp()) {
					event.setCancelled(true);
					event.getPlayer().sendMessage("§cEnchanting has been disabled for balance reasons! (This may change!)");
				}
			}
		}
		*/
		ItemStack item = event.getItem();
		
		if(item == null) return;
		if(block.getType().equals(Material.GRASS)) {
		
		if((item.getData() instanceof Dye)) {
			
			Dye dye = (Dye) item.getData();
			
			if(dye.getColor().equals(DyeColor.WHITE)) {
				
			}else {
				return;
			}
			
		}else {
			return;
		}
		
		if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		
		event.setCancelled(true);
		double x = block.getX();
		double y = block.getY();
		double z = block.getZ();
		World world = block.getWorld();
		
		ArrayList<Block> blocks = new ArrayList<Block>();
		
		double sizex = 2;
		double sizez = 2;
		
		for (double x1 = -sizex; x1 < sizex; x1++) {
	           for (double z1 = -sizez; z1 < sizez; z1++) {
	                  //make a location with the x and the z from the for loop, make sure to use deduct 1 from y to get the blocks below the player
	                  //check for block type
	                  //and to remove the block you can just set it to air like so
	                  Location loc = new Location(world, x - x1, y, z - z1);
	                  Block b1 = world.getBlockAt(loc);
	                  if(b1.getType().equals(Material.GRASS))
	                	  blocks.add(b1);
	           }
		}
		for(Block b : blocks) {
			Location loc = b.getLocation();
			Block bl = world.getBlockAt((int)loc.getX(), (int)loc.getBlockY() + 1, (int)loc.getZ());
			if(bl.getType().equals(Material.AIR)) {
				Random rand = new Random();
				int chance = rand.nextInt(10);
				if(chance == 1) {
					if(bl.getLightLevel() < 4) {
						world.dropItemNaturally(bl.getLocation(), new ItemStack(Material.YELLOW_FLOWER));
					}else
						bl.setType(Material.YELLOW_FLOWER);
				}else if (chance == 2){
					if(bl.getLightLevel() < 4) {
						world.dropItemNaturally(bl.getLocation(), new ItemStack(Material.RED_ROSE));
					}else
						bl.setType(Material.RED_ROSE);
				}else {
					continue;
				}
			}
		}
		
		int ammount = item.getAmount();
		int nammount = ammount - 1;
		item.setAmount(nammount);
		}
	}
}
