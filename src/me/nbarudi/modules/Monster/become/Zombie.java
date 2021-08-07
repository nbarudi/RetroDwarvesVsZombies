package me.nbarudi.modules.Monster.become;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerData.DwarfClass;
import me.nbarudi.util.PlayerData.MonsterClass;
import me.nbarudi.util.PlayerManager;

public class Zombie extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted) {
			player.sendMessage("§cGame has not started!");
			return;
		}
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.becomeZombie.getItemMeta().getDisplayName()))
			return;
		PlayerData pd = PlayerManager.getPlayerData(player);
		if(!pd.hasClaimedClasses) {
			player.sendMessage("§cYou have not claimed your classes!");
			return;
		}
		
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			runAbility(player);
		}
		
	}
	
	@Override
	public void runAbility(Player player) {
		player.getInventory().clear();
		PlayerData pd = PlayerManager.getPlayerData(player);
		pd.role = DwarfClass.NONE;
		pd.mrole = MonsterClass.ZOMBIE;
		pd.setDwarf(false);
		
		
		//Give Items:
		
		//Class Spesific
		
		//Disugise
		MobDisguise md = new MobDisguise(DisguiseType.ZOMBIE);
		md.setEntity(player);
		md.startDisguise();
		
		//Creating Items
		ItemStack ihelm = new ItemStack(Material.IRON_HELMET);
		ItemStack ichest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack ilegg = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack iboot = new ItemStack(Material.IRON_BOOTS);
		ItemStack isword = new ItemStack(Material.IRON_SWORD);
		ItemStack hpot = new ItemStack(Material.SPLASH_POTION, 2);
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 16);
		
		ihelm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		ichest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		ilegg.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		iboot.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		isword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		
		PotionMeta pm = (PotionMeta)hpot.getItemMeta();
		pm.setDisplayName("§dHealth Potion");
		pm.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1), true);
		hpot.setItemMeta(pm);
		
		//Adding to Inventory
		final PlayerInventory inv = player.getInventory();
		
		inv.setHelmet(ihelm);
		inv.setChestplate(ichest);
		inv.setLeggings(ilegg);
		inv.setBoots(iboot);
		
		inv.addItem(isword);
		
		inv.addItem(hpot);
		
		inv.addItem(steak);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
			public void run() {
				inv.addItem(RDvZ.is.suicide);
			}
		}, 100);
		
		double x = RDvZ.instance.getConfig().getDouble("Warps.monster.x");
		double y = RDvZ.instance.getConfig().getDouble("Warps.monster.y");
		double z = RDvZ.instance.getConfig().getDouble("Warps.monster.z");
		String world = RDvZ.instance.getConfig().getString("Warps.monster.world");
		World w = Bukkit.getWorld(world);
		
		Location loc = new Location(w, x, y, z);
		player.sendMessage("§6Warping to Monster Spawn");
		player.teleport(loc);
		
		player.sendMessage("§aYou have become a Zombie!");
		if(RDvZ.shrinedestroyed) {
			player.getInventory().addItem(RDvZ.is.trackingCompas);
		}
	}

}
