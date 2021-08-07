package me.nbarudi.modules.heros.barbarian;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.heros.HeroModule;

public class Barbarian extends HeroModule {

	public Barbarian(String name, String suffix) {
		super(name, suffix);
		this.maxMana = 1250;
		this.color = ChatColor.GREEN;
	}
	
	@Override
	public void setPlayer(Player hero, boolean isForced) {
		super.setPlayer(hero, isForced);
		
		PlayerInventory inv = hero.getInventory();
		
		Bukkit.broadcastMessage("§7"  + hero.getName() + " §dhas become a §aBarbarian§d!");
		
		ItemStack helm = new ItemStack(Material.IRON_HELMET);
		ItemMeta hm = helm.getItemMeta();
		hm.setDisplayName("§aBarbarian Helmet");
		hm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		hm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		hm.setUnbreakable(true);
		helm.setItemMeta(hm);
		helm.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		
		ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta cm = chest.getItemMeta();
		cm.setDisplayName("§aBarbarian Chestplate");
		cm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		cm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		cm.setUnbreakable(true);
		chest.setItemMeta(cm);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		
		ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta lm = leg.getItemMeta();
		lm.setDisplayName("§aBarbarian Leggings");
		lm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		lm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		lm.setUnbreakable(true);
		leg.setItemMeta(lm);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		
		ItemStack boot = new ItemStack(Material.IRON_BOOTS);
		ItemMeta bm = boot.getItemMeta();
		bm.setDisplayName("§aBarbarian Boots");
		bm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		bm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		bm.setUnbreakable(true);
		boot.setItemMeta(bm);
		boot.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		boot.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 2);
		
		inv.setHelmet(helm);
		inv.setChestplate(chest);
		inv.setLeggings(leg);
		inv.setBoots(boot);
		
		inv.addItem(RDvZ.is.barbarianslam);
		inv.addItem(RDvZ.is.healPotion);
		inv.addItem(RDvZ.is.speedPotion);
		inv.addItem(RDvZ.is.fresistPotion);
		inv.addItem(RDvZ.is.strengthPotion);
	}

}
