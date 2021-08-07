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

public class IronGolem extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted) {
			player.sendMessage("§cGame has not started!");
			return;
		}
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.becomeIrongolem.getItemMeta().getDisplayName()))
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
		pd.mrole = MonsterClass.IRONGOLEM;
		pd.setDwarf(false);
		
		
		//Give Items:
		
		//Class Spesific
		MobDisguise md = new MobDisguise(DisguiseType.IRON_GOLEM);
		md.setEntity(player);
		md.startDisguise();
		
		ItemStack helm = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack legg = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack boot = new ItemStack(Material.DIAMOND_BOOTS);
		
		
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 16);
		
		
		sword.getItemMeta().setUnbreakable(true);
		sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 0));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100000, 1));
		
		//Adding to Inventory
		final PlayerInventory inv = player.getInventory();
		
		inv.setHelmet(helm);
		inv.setChestplate(chest);
		inv.setLeggings(legg);
		inv.setBoots(boot);

		inv.addItem(sword);
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
		
		player.sendMessage("§aYou have become an Iron Golem!");
		if(RDvZ.shrinedestroyed) {
			player.getInventory().addItem(RDvZ.is.trackingCompas);
		}
	}

}
