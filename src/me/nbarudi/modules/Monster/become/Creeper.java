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

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.nbarudi.main.RDvZ;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerData.DwarfClass;
import me.nbarudi.util.PlayerData.MonsterClass;
import me.nbarudi.util.PlayerManager;

public class Creeper extends ItemModule {
	
	@Override
	public void onInteracted(Action action, ItemStack item, Player player, PlayerInteractEvent event) {
		if(!RDvZ.gameStarted) {
			player.sendMessage("§cGame has not started!");
			return;
		}
		if(!item.getItemMeta().getDisplayName().equals(RDvZ.is.becomeCreeper.getItemMeta().getDisplayName()))
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
		pd.mrole = MonsterClass.CREEPER;
		pd.setDwarf(false);
		
		
		//Give Items:
		
		//Class Spesific
		
		//Disugise
		MobDisguise md = new MobDisguise(DisguiseType.CREEPER);
		md.setEntity(player);
		md.startDisguise();
		
		ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack legg = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack boot = new ItemStack(Material.LEATHER_BOOTS);
		
		
		ItemStack sword = new ItemStack(Material.WOOD_SWORD);
		
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 16);
		
		
		sword.getItemMeta().setUnbreakable(true);
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
		
		//Adding to Inventory
		final PlayerInventory inv = player.getInventory();
		
		inv.setHelmet(helm);
		inv.setChestplate(chest);
		inv.setLeggings(legg);
		inv.setBoots(boot);

		inv.addItem(sword);
		
		inv.addItem(RDvZ.is.creeperExplode);
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
		
		player.sendMessage("§aYou have become a Creeper!");
		if(RDvZ.shrinedestroyed) {
			player.getInventory().addItem(RDvZ.is.trackingCompas);
		}
	}

}
