package me.nbarudi.modules.Universal;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

import me.nbarudi.main.RDvZ;
import me.nbarudi.misc.DwarvenVault;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerData.DwarfClass;
import me.nbarudi.util.PlayerManager;

public class ClassBook extends ItemModule {
	
	ArrayList<String> cooldown = new ArrayList<String>();
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		//PlayerData pd = PlayerManager.getPlayerData(player);
		if(!item.equals(RDvZ.is.classbook))
			return;
		if(player.isOp())
			player.sendMessage("§cOP Bypass Enabled");
		else if(RDvZ.gameStarted == false) {
			player.sendMessage("§3Game has not started yet!");
			return;
		}
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			runAbility(player);
		}
		if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			PlayerData data = PlayerManager.getPlayerData(player);
			if(data.mana >= 200) {
				player.sendMessage("§aOpening vault!");
				player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
				player.openInventory(DwarvenVault.getInventory(player));
				data.mana -= 200;
			}else{
				player.sendMessage("§cNot enough mana!");
			}
		}
	}
	
	@Override
	public void runAbility(Player player) {
		Random rnd = new Random();
		PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(cooldown.contains(player.getName())) {
			player.sendMessage("§3This ability is on a cooldown!");
			return;
		}
		
		if(pd.role == DwarfClass.BUILDER) {
			int ammount = rnd.nextInt(4);
			int toGive = 64;
			int times = 1;
			switch(ammount) {
			case 1:
				times = 1;
				break;
			case 2:
				times = 2;
				break;
			case 3:
				times = 3;
				break;
			}
			
			int type = rnd.nextInt(4);
			ItemStack item = new ItemStack(Material.GLOWSTONE, 3);
			switch(type) {
			case 0:
				item = new ItemStack(Material.SMOOTH_BRICK, toGive);
				break;
			case 1:
				item = new ItemStack(Material.SMOOTH_BRICK, toGive, (short) 1);
				break;
			case 2:
				item = new ItemStack(Material.SMOOTH_BRICK, toGive, (short) 2);
				break;
			case 3:
				item = new ItemStack(Material.SMOOTH_BRICK, toGive, (short) 3);
				break;
			}
			
			Location loc = player.getLocation();
			loc.setY(loc.getY() + 1);
			Vector direction = player.getLocation().getDirection();
			loc.add(direction);
			for(int i = 0; i < times; i++) {
				player.getWorld().dropItemNaturally(loc, item);
			}
			player.getWorld().dropItemNaturally(loc, new ItemStack(Material.TORCH, 4));
			cooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					cooldown.remove(player.getName());
					player.sendMessage("§eCooldown over!");
				}
			}, 300);
		}
		else if(pd.role == DwarfClass.SMITH) {
			
			ItemStack cost = new ItemStack(Material.WATCH);
			if(player.getInventory().containsAtLeast(cost, 4)) {
				ItemStack tcost = new ItemStack(Material.WATCH, 4);
				player.getInventory().removeItem(tcost);
			}else {
				player.sendMessage("§3You are missing required items! (4 Golden Clocks)");
				return;
			}
			
			int swordChance = rnd.nextInt(101);
			int pickChance = rnd.nextInt(101);
			int shovelChance = rnd.nextInt(101);
			int bowChance = rnd.nextInt(101);
			int randomArrows = rnd.nextInt(65) + 1;
			int randomGold = rnd.nextInt(5);
			int randomRedstone = rnd.nextInt(5) + 1;
			int randomCoal = rnd.nextInt(5) + 1;
			
			Location loc = player.getLocation();
			loc.setY(loc.getY() + 1);
			Vector direction = player.getLocation().getDirection();
			loc.add(direction);
			
			if(swordChance > 50) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(Material.DIAMOND_SWORD));
			}
			
			if(bowChance > 50) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(Material.BOW));
			}
			
			if(pickChance > 50) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(Material.DIAMOND_PICKAXE));
			}
			
			if(shovelChance > 50) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(Material.DIAMOND_SPADE));
			}
			
			player.getWorld().dropItemNaturally(loc, new ItemStack(Material.ARROW, randomArrows));
			player.getWorld().dropItemNaturally(loc, new ItemStack(Material.GOLD_ORE, randomGold));
			player.getWorld().dropItemNaturally(loc, new ItemStack(Material.REDSTONE_ORE, randomRedstone));
			player.getWorld().dropItemNaturally(loc, new ItemStack(Material.COAL, randomCoal));
			cooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					cooldown.remove(player.getName());
					player.sendMessage("§eCooldown over!");
				}
			}, 300);
		}
		else if(pd.role == DwarfClass.TAILOR) {
			
			ItemStack cost = new ItemStack(Material.INK_SACK);
			cost.setDurability((short)14);
			
			if(player.getInventory().containsAtLeast(cost, 20)) {
				ItemStack tcost = new ItemStack(Material.INK_SACK, 20);
				tcost.setDurability((short)14);
				player.getInventory().removeItem(tcost);
			}else {
				player.sendMessage("§3You are missing required items! (20 Orange Dye)");
				return;
			}
			
			int helmChance = rnd.nextInt(101);
			int chestChance = rnd.nextInt(101);
			int legChance = rnd.nextInt(101);
			int bootsChance = rnd.nextInt(101);

			
			Location loc = player.getLocation();
			loc.setY(loc.getY() + 1);
			Vector direction = player.getLocation().getDirection();
			loc.add(direction);
			
			if(helmChance > 50) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(Material.DIAMOND_HELMET));
			}
			
			if(chestChance > 50) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(Material.DIAMOND_CHESTPLATE));
			}
			
			if(legChance > 50) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(Material.DIAMOND_LEGGINGS));
			}
			
			if(bootsChance > 50) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(Material.DIAMOND_BOOTS));
			}
			
			player.getWorld().dropItemNaturally(loc, new ItemStack(Material.GOLD_ORE, 10));
			player.getWorld().dropItemNaturally(loc, new ItemStack(Material.BONE, rnd.nextInt(5)));
			cooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					cooldown.remove(player.getName());
					player.sendMessage("§eCooldown over!");
				}
			}, 300);
		}
		else if(pd.role == DwarfClass.ALCHEMIST) {
			
			//Cost Check
			Inventory inv = player.getInventory();
			ItemStack cost = new ItemStack(Material.POTION);
			PotionMeta pm = (PotionMeta) cost.getItemMeta();
			pm.setBasePotionData(new PotionData(PotionType.MUNDANE));
			cost.setItemMeta(pm);
			
			if(inv.containsAtLeast(cost, 3)) {
				ItemStack tcost = new ItemStack(Material.POTION, 3);
				PotionMeta tpm = (PotionMeta) cost.getItemMeta();
				pm.setBasePotionData(new PotionData(PotionType.MUNDANE));
				tcost.setItemMeta(tpm);
				inv.removeItem(tcost);
			}else {
				player.sendMessage("§3You are missing required items! (3 Mundane Potions [Created with 1 redstone in a brewing stand])");
				return;
			}
			
			int healpotChance = rnd.nextInt(101);
			int speedpotChance = rnd.nextInt(101);
			int fresistChance = rnd.nextInt(101);
			int strengthChance = rnd.nextInt(101);

			
			Location loc = player.getLocation();
			loc.setY(loc.getY() + 1);
			Vector direction = player.getLocation().getDirection();
			loc.add(direction);
			
			if(healpotChance > 25) {
				player.getWorld().dropItemNaturally(loc, RDvZ.is.healPotion);
			}
			
			if(speedpotChance > 50) {
				player.getWorld().dropItemNaturally(loc, RDvZ.is.speedPotion);
			}
			
			if(fresistChance > 75) {
				player.getWorld().dropItemNaturally(loc, RDvZ.is.fresistPotion);
			}
			
			if(strengthChance > 88) {
				player.getWorld().dropItemNaturally(loc, RDvZ.is.strengthPotion);
			}
			
			player.getWorld().dropItemNaturally(loc, new ItemStack(Material.BONE, 10));
			player.getWorld().dropItemNaturally(loc, new ItemStack(Material.BLAZE_POWDER, rnd.nextInt(2)));
			player.getWorld().dropItemNaturally(loc, new ItemStack(Material.GLASS, rnd.nextInt(17)));
			cooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					cooldown.remove(player.getName());
					player.sendMessage("§eCooldown over!");
				}
			}, 300);
		}
		else if(pd.role == DwarfClass.BAKER) {
			
			ItemStack cost = new ItemStack(Material.CLAY_BRICK);
			if(player.getInventory().containsAtLeast(cost, 4)) {
				ItemStack tcost = new ItemStack(Material.CLAY_BRICK, 4);
				player.getInventory().removeItem(tcost);
			}else {
				player.sendMessage("§3You are missing required items! (4 Clay Bricks)");
				return;
			}
			
			int cakeChance = rnd.nextInt(101);
			int breadChance = rnd.nextInt(101);
			
			Location loc = player.getLocation();
			loc.setY(loc.getY() + 1);
			Vector direction = player.getLocation().getDirection();
			loc.add(direction);
			
			if(cakeChance > 25) {
				ItemStack newItem = RDvZ.is.cakeSpawner;
				newItem.setAmount(rnd.nextInt(2) + 1);
				player.getWorld().dropItemNaturally(loc, newItem);
			}
			
			if(breadChance > 50) {
				player.getWorld().dropItemNaturally(loc, new ItemStack(Material.BREAD, rnd.nextInt(5)));
			}
			
			
			player.getWorld().dropItemNaturally(loc, new ItemStack(Material.CLAY_BALL, rnd.nextInt(16) + 4));
			player.getWorld().dropItemNaturally(loc, new ItemStack(Material.COAL, rnd.nextInt(4)));
			cooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					cooldown.remove(player.getName());
					player.sendMessage("§eCooldown over!");
				}
			}, 300);
		}
		else if(pd.role == DwarfClass.ENCHANTER) {
			ItemStack cost = new ItemStack(Material.COAL);
			if(player.getInventory().containsAtLeast(cost, 5) && pd.mana >= 100) {
				
			}else {
				player.sendMessage("§3You are missing required resources! (5 Coal, 100 Mana)");
				return;
			}

			PlayerInventory inv = player.getInventory();
			ItemStack item = inv.getItemInOffHand();
			
			if(item == null || item.getType().equals(Material.AIR)) {
				player.sendMessage("§3You must have an enchantable item in your off hand to use this ability!");
				return;
			}
			
			
			ArrayList<Enchantment> aenchants = new ArrayList<Enchantment>();
			ArrayList<Enchantment> senchants = new ArrayList<Enchantment>();
			ArrayList<Enchantment> benchants = new ArrayList<Enchantment>();
			
			//Armor Enchantments
			aenchants.add(Enchantment.PROTECTION_FALL);
			aenchants.add(Enchantment.PROTECTION_ENVIRONMENTAL);
			aenchants.add(Enchantment.PROTECTION_EXPLOSIONS);
			aenchants.add(Enchantment.PROTECTION_FIRE);
			aenchants.add(Enchantment.PROTECTION_PROJECTILE);
			aenchants.add(Enchantment.THORNS);
			
			//Sword Enchantments
			senchants.add(Enchantment.DAMAGE_ALL);
			senchants.add(Enchantment.FIRE_ASPECT);
			senchants.add(Enchantment.KNOCKBACK);
			
			//Bow Enchantments
			benchants.add(Enchantment.ARROW_INFINITE);
			benchants.add(Enchantment.ARROW_FIRE);
			benchants.add(Enchantment.ARROW_DAMAGE);
			benchants.add(Enchantment.ARROW_KNOCKBACK);
			
			switch(item.getType()) {
			case DIAMOND_HELMET:
				if(item.getEnchantments().size() > 0) {
					player.sendMessage("§3You cannot enchant an already enchanted item..");
					return;
				}
				
				for(Enchantment enc : aenchants) {
					int chance = rnd.nextInt(100) + 1;
					if(chance >= 75)
						item.addUnsafeEnchantment(enc, rnd.nextInt(1) + 1);
				}
				listEnchants(item, player);
				break;
			case DIAMOND_CHESTPLATE:
				if(item.getEnchantments().size() > 0) {
					player.sendMessage("§3You cannot enchant an already enchanted item..");
					return;
				}
				for(Enchantment enc : aenchants) {
					int chance = rnd.nextInt(100) + 1;
					if(chance >= 75)
						item.addUnsafeEnchantment(enc, rnd.nextInt(1) + 1);
				}
				listEnchants(item, player);
				break;
			case DIAMOND_LEGGINGS:
				if(item.getEnchantments().size() > 0) {
					player.sendMessage("§3You cannot enchant an already enchanted item..");
					return;
				}
				for(Enchantment enc : aenchants) {
					int chance = rnd.nextInt(100) + 1;
					if(chance >= 75)
						item.addUnsafeEnchantment(enc, rnd.nextInt(1) + 1);
				}
				listEnchants(item, player);
				break;
			case DIAMOND_BOOTS:
				if(item.getEnchantments().size() > 0) {
					player.sendMessage("§3You cannot enchant an already enchanted item..");
					return;
				}
				for(Enchantment enc : aenchants) {
					int chance = rnd.nextInt(100) + 1;
					if(chance >= 75)
						item.addUnsafeEnchantment(enc, rnd.nextInt(1) + 1);
				}
				listEnchants(item, player);
				break;
			case DIAMOND_SWORD:
				if(item.getEnchantments().size() > 0) {
					player.sendMessage("§3You cannot enchant an already enchanted item..");
					return;
				}
				for(Enchantment enc : senchants) {
					int chance = rnd.nextInt(100) + 1;
					if(chance >= 75)
						item.addUnsafeEnchantment(enc, rnd.nextInt(1) + 1);
				}
				listEnchants(item, player);
				break;
			case BOW:
				if(item.getEnchantments().size() > 0) {
					player.sendMessage("§3You cannot enchant an already enchanted item..");
					return;
				}
				for(Enchantment enc : benchants) {
					int chance = rnd.nextInt(100) + 1;
					if(chance >= 75)
						item.addUnsafeEnchantment(enc, rnd.nextInt(1) + 1);
				}
				listEnchants(item, player);
				break;
			default:
				break;
			}
			
			cooldown.add(player.getName());
			Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
				public void run() {
					cooldown.remove(player.getName());
					player.sendMessage("§eCooldown over!");
				}
			}, 40);
			
			ItemStack tcost = new ItemStack(Material.COAL, 5);
			player.getInventory().removeItem(tcost);
			pd.mana = pd.mana - 100;
		}
		
		player.giveExp(rnd.nextInt(10));
		
	}
	
	private void listEnchants(ItemStack item, Player plr) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("§aAdded the following enchantments to the item: \n");
		
		for(Enchantment enc : item.getEnchantments().keySet()) {
			if(enc.equals(Enchantment.ARROW_DAMAGE))
				sb.append("§a- Power - " + item.getEnchantmentLevel(enc));
			else if(enc.equals(Enchantment.ARROW_FIRE))
				sb.append("§a- Flame - " + item.getEnchantmentLevel(enc));
			else if(enc.equals(Enchantment.ARROW_INFINITE))
				sb.append("§a- Infinity - " + item.getEnchantmentLevel(enc));
			else if(enc.equals(Enchantment.ARROW_KNOCKBACK))
				sb.append("§a- Punch - " + item.getEnchantmentLevel(enc));
			else if(enc.equals(Enchantment.DAMAGE_ALL))
				sb.append("§a- Sharpness - " + item.getEnchantmentLevel(enc));
			else if(enc.equals(Enchantment.PROTECTION_ENVIRONMENTAL))
				sb.append("§a- Protection - " + item.getEnchantmentLevel(enc));
			else if(enc.equals(Enchantment.PROTECTION_EXPLOSIONS))
				sb.append("§a- Blast Protection - " + item.getEnchantmentLevel(enc));
			else if(enc.equals(Enchantment.PROTECTION_FALL))
				sb.append("§a- Feather Falling - " + item.getEnchantmentLevel(enc));
			else if(enc.equals(Enchantment.PROTECTION_FIRE))
				sb.append("§a- Fire Protection - " + item.getEnchantmentLevel(enc));
			else if(enc.equals(Enchantment.PROTECTION_PROJECTILE))
				sb.append("§a- Projectile Protection - " + item.getEnchantmentLevel(enc));
			else if(enc.equals(Enchantment.FIRE_ASPECT))
				sb.append("§a- Fire Aspect - " + item.getEnchantmentLevel(enc));
			else if(enc.equals(Enchantment.KNOCKBACK))
				sb.append("§a- Knockback - " + item.getEnchantmentLevel(enc));
			else if(enc.equals(Enchantment.THORNS))
				sb.append("§a- Thorns - " + item.getEnchantmentLevel(enc));
			sb.append("\n");
		}
		
		plr.sendMessage(sb.toString());
		
	}

}
