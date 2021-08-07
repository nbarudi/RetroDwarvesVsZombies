package me.nbarudi.misc;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.nbarudi.main.RDvZ;

public class DwarvenVault {
	
	private static HashMap<String, Inventory> vaults = new HashMap<String, Inventory>();
	
	public DwarvenVault(Player player) {
		vaults.put(player.getName(), createInventory(player));
	}
	
	public DwarvenVault(Player player, Inventory cinv) {
		vaults.put(player.getName(), cinv);
	}
	
	private Inventory createInventory(Player player) {
		Inventory inv = Bukkit.createInventory(player, 54, "§a" + player.getName() + "'s Vault!");
		inv.addItem(new ItemStack(Material.SMOOTH_BRICK, 64));
		inv.addItem(new ItemStack(Material.SMOOTH_BRICK, 64));
		inv.addItem(new ItemStack(Material.TORCH, 16));
		inv.addItem(new ItemStack(Material.COOKED_BEEF, 16));
		return inv;
	}
	
	public static Inventory quickInventory(Player player) {
		Inventory inv = Bukkit.createInventory(player, 54, "§a" + player.getName() + "'s Vault!");
		
		inv.addItem(new ItemStack(Material.DIAMOND_HELMET));
		inv.addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
		inv.addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
		inv.addItem(new ItemStack(Material.DIAMOND_BOOTS));
		
		inv.addItem(new ItemStack(Material.DIAMOND_SWORD));
		inv.addItem(new ItemStack(Material.BOW));
		inv.addItem(new ItemStack(Material.ARROW, 64));
		inv.addItem(new ItemStack(Material.ARROW, 64));
		
		inv.addItem(RDvZ.is.healPotion);
		inv.addItem(RDvZ.is.speedPotion);
		inv.addItem(RDvZ.is.fresistPotion);
		inv.addItem(RDvZ.is.strengthPotion);
		
		inv.addItem(new ItemStack(Material.SMOOTH_BRICK, 64));
		inv.addItem(new ItemStack(Material.SMOOTH_BRICK, 64));
		inv.addItem(new ItemStack(Material.TORCH, 16));
		inv.addItem(new ItemStack(Material.COOKED_BEEF, 16));
		
		ItemStack easyFix = RDvZ.is.easyFixSlabs;
		easyFix.setAmount(16);
		inv.addItem(easyFix);
		
		return inv;
	}
	
	public static Inventory getInventory(Player player) {
		Inventory inv = null;
		inv = vaults.get(player.getName());
		return inv;
	}

}
