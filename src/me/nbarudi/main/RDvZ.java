package me.nbarudi.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.nbarudi.cmds.AdminCommand;
import me.nbarudi.cmds.Dragon;
import me.nbarudi.cmds.ReleaseMonsters;
import me.nbarudi.cmds.SetupCommand;
import me.nbarudi.cmds.StartGame;
import me.nbarudi.events.ChatManager;
import me.nbarudi.events.DamageEvent;
import me.nbarudi.events.DeathRespawnManager;
import me.nbarudi.events.EnchantingEventStuff;
import me.nbarudi.events.HeroEvents;
import me.nbarudi.events.Interact;
import me.nbarudi.events.KillTheMetas;
import me.nbarudi.events.PlayerJoin;
import me.nbarudi.events.ShrineManager;
import me.nbarudi.modules.ItemModule;
import me.nbarudi.modules.Dragon.Claws;
import me.nbarudi.modules.Dragon.DragonCave;
import me.nbarudi.modules.Dragon.RainFire;
import me.nbarudi.modules.Dwarf.BecomeAlchemist;
import me.nbarudi.modules.Dwarf.BecomeBaker;
import me.nbarudi.modules.Dwarf.BecomeDwarf;
import me.nbarudi.modules.Dwarf.BecomeEnchanter;
import me.nbarudi.modules.Dwarf.BecomeSmith;
import me.nbarudi.modules.Dwarf.BecomeTailor;
import me.nbarudi.modules.Dwarf.Potions.FResistPotion;
import me.nbarudi.modules.Dwarf.Potions.HealthPotion;
import me.nbarudi.modules.Dwarf.Potions.SpeedPotion;
import me.nbarudi.modules.Dwarf.Potions.StrengthPotion;
import me.nbarudi.modules.Extra.CakeSpawner;
import me.nbarudi.modules.Extra.DeathStare;
import me.nbarudi.modules.Extra.EasyFixSlabs;
import me.nbarudi.modules.Extra.TrackingCompass;
import me.nbarudi.modules.Monster.abilities.BroodBreak;
import me.nbarudi.modules.Monster.abilities.CreeperExplode;
import me.nbarudi.modules.Monster.abilities.EndermanBlink;
import me.nbarudi.modules.Monster.abilities.SpiderPoison;
import me.nbarudi.modules.Monster.abilities.Suicide;
import me.nbarudi.modules.Monster.abilities.WolfJump;
import me.nbarudi.modules.Monster.become.BroodMother;
import me.nbarudi.modules.Monster.become.Creeper;
import me.nbarudi.modules.Monster.become.Enderman;
import me.nbarudi.modules.Monster.become.IronGolem;
import me.nbarudi.modules.Monster.become.Skeleton;
import me.nbarudi.modules.Monster.become.Spider;
import me.nbarudi.modules.Monster.become.Wolf;
import me.nbarudi.modules.Monster.become.Zombie;
import me.nbarudi.modules.Universal.ClassBook;
import me.nbarudi.modules.Universal.GoldNugget;
import me.nbarudi.modules.Universal.MagmaCream;
import me.nbarudi.modules.heros.BecomeHero;
import me.nbarudi.modules.heros.HeroModule;
import me.nbarudi.modules.heros.barbarian.Barbarian;
import me.nbarudi.modules.heros.barbarian.items.BarbarianSlam;
import me.nbarudi.modules.heros.dragonwarrior.DragonWarrior;
import me.nbarudi.modules.heros.dragonwarrior.castables.DragonFireball;
import me.nbarudi.modules.heros.icequeen.IceQueen;
import me.nbarudi.modules.heros.icequeen.items.IceQueenStaff;
import me.nbarudi.modules.heros.lightningking.LightningKing;
import me.nbarudi.modules.heros.lightningking.items.LightningKingStaff;
import me.nbarudi.util.AutomationHandler;
import me.nbarudi.util.FileManager;
import me.nbarudi.util.ItemSerialize;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class RDvZ extends JavaPlugin implements Listener{
	
	public static RDvZ instance;
	
	public static AutomationHandler ah;
	public static ItemSerialize is;
	public static FileManager fm;
	//public static DisguiseAPI dapi;
	
	//Scoreboard
	public static Scoreboard sb;
	public static Team dwarf;
	public static Team monster;
	public static Team hero;
	
	public static ArrayList<PlayerData> data = new ArrayList<PlayerData>();
	public static ArrayList<HeroModule> heros = new ArrayList<HeroModule>();
	public static ArrayList<ItemModule> castables = new ArrayList<ItemModule>();
	public static boolean gameStarted = false;
	public static boolean monstersReleased = false;
	public static boolean shrinedestroyed = false;
	
	
	//Enchanting Stuff
	public ArrayList<EnchantingInventory> inventories;
	
	//Dragon Data
	public static Player dragon = null;
	public static String grappled = "";
	public static int looped = 0;
	public static int fireTask = 0;
	public static int ballTask = 0;
	

	
	
	public static ArrayList<String> admins = new ArrayList<String>() {/**
		 * 
		 */
		private static final long serialVersionUID = 6510182735238920663L;

	{
		add("ItzBungo");
		add("nbarudi");
	}};
	
	@Override
	public void onEnable() {
		//dapi = getServer().getServicesManager().getRegistration(DisguiseAPI.class).getProvider();
		instance = this;
		inventories = new ArrayList<EnchantingInventory>();
		
		registerConfig();
		registerEvents();
		registerCommands();
		registerCastables();
		is = new ItemSerialize();
		ah = new AutomationHandler();
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			PlayerManager.createPlayerData(player);
		}
		
		
		startManaRegen();
		registerTeams();
		registerHeros();
		
		attackCooldown();
		lightLoop();
		
	}

	@Override
	public void onDisable() {
	    
		dwarf.unregister();
		monster.unregister();
		hero.unregister();
		
		for (EnchantingInventory ei : inventories) {
			ei.setItem(1, null);
		}
		inventories = null;
	}
	
	private void registerTeams() {
		
		sb = Bukkit.getScoreboardManager().getMainScoreboard();
		
		dwarf = sb.registerNewTeam("Dwarf");
		dwarf.setColor(ChatColor.AQUA);
		dwarf.setSuffix("§3 the Dwarf");
		
		monster = sb.registerNewTeam("Monster");
		monster.setColor(ChatColor.RED);
		monster.setSuffix("§4 the Monster");
		
		hero = sb.registerNewTeam("Hero");
		hero.setColor(ChatColor.GOLD);
		hero.setSuffix("§6 the Hero");
	}
	
	private void startManaRegen() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(PlayerData pd : data) {
					Player player = PlayerManager.getPlayer(pd);
					if(player == null)
						continue;
					if(!pd.isDwarf)
						continue;
					if(shrinedestroyed) {
						//player.setLevel(pd.mana);
						player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§bMana: §a§l" + pd.mana));
						continue;
					}
					if(pd.isHero) {
						if(pd.hero == null) {
							if((pd.mana + 3) >= 1000) {
								pd.mana = 1000;
								//player.setLevel(pd.mana);
								player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§bMana: §a§l" + pd.mana));
							}else {
								pd.mana = pd.mana + 3;
								//player.setLevel(pd.mana);
								player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§bMana: §a§l" + pd.mana));
							}
							continue;
						}
						if((pd.mana+5) >= pd.hero.maxMana) {
							pd.mana = pd.hero.maxMana;
							//player.setLevel(pd.mana);
							player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§bMana: §a§l" + pd.mana));
						}else {
							pd.mana = pd.mana + 5;
							//player.setLevel(pd.mana);
							player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§bMana: §a§l" + pd.mana));
						}
						continue;
					}
					
					if((pd.mana + 3) >= 1000) {
						pd.mana = 1000;
						//player.setLevel(pd.mana);
						player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§bMana: §a§l" + pd.mana));
					}else {
						pd.mana = pd.mana + 3;
						//player.setLevel(pd.mana);
						player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§bMana: §a§l" + pd.mana));
					}
				}
			}
		}, 20, 10);
	}
	
	private void registerCastables() {
		//Globals
		castables.add(new MagmaCream());
		castables.add(new GoldNugget());
		castables.add(new ClassBook());
		
		//Dwarf Classes
		castables.add(new BecomeDwarf());
		castables.add(new BecomeSmith());
		castables.add(new BecomeTailor());
		castables.add(new BecomeAlchemist());
		castables.add(new BecomeBaker());
		castables.add(new BecomeEnchanter());
		castables.add(new BecomeHero());
		
		//Monster Abilities
		castables.add(new BroodBreak());
		castables.add(new CreeperExplode());
		castables.add(new EndermanBlink());
		castables.add(new SpiderPoison());
		castables.add(new Suicide());
		castables.add(new WolfJump());
	
		//Monster Classes
		castables.add(new Zombie());
		castables.add(new Skeleton());
		castables.add(new Creeper());
		castables.add(new Spider());
		castables.add(new Wolf());
		castables.add(new IronGolem());
		castables.add(new BroodMother());
		castables.add(new Enderman());
		
		//Dragon Abilities
		castables.add(new Claws());
		castables.add(new RainFire());
		castables.add(new DragonCave());
		
		//Extras
		castables.add(new CakeSpawner());
		castables.add(new DeathStare());
		castables.add(new EasyFixSlabs());
		castables.add(new TrackingCompass());
		//castables.add(new Rocket());
		
		//Hero
		castables.add(new DragonFireball());
		castables.add(new IceQueenStaff());
		castables.add(new LightningKingStaff());
		castables.add(new BarbarianSlam());
		
		//Potions
		castables.add(new HealthPotion());
		castables.add(new FResistPotion());
		castables.add(new SpeedPotion());
		castables.add(new StrengthPotion());
	}
	
	public void registerHeros() {
		heros.add(new DragonWarrior("DragonWarrior", "§6the Dragon Warrior"));
		heros.add(new IceQueen("IceQueen", "§bthe Ice Queen"));
		heros.add(new LightningKing("LightningKing", "§9the Lightning King"));
		heros.add(new Barbarian("Barbarian", "§athe Barbarian"));
	}
	
	private void registerConfig() {
		fm = new FileManager(this);
		saveDefaultConfig();
	}
	
	private void attackCooldown() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (all.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getBaseValue() != 100.0D)
				all.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100.0D); 
			
		} 
	}
	
	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new ChatManager(), this);
		pm.registerEvents(new DeathRespawnManager(), this);
		pm.registerEvents(new Interact(), this);
		pm.registerEvents(new DamageEvent(), this);
		pm.registerEvents(new ShrineManager(), this);
		pm.registerEvents(new HeroEvents(), this);
		pm.registerEvents(new KillTheMetas(), this);
		pm.registerEvents(new EnchantingEventStuff(this), this);
	}
	
	private void registerCommands() {
		
		this.getCommand("releasemonsters").setExecutor(new ReleaseMonsters());
		this.getCommand("startgame").setExecutor(new StartGame());
		this.getCommand("setup").setExecutor(new SetupCommand());
		this.getCommand("admin").setExecutor(new AdminCommand());
		this.getCommand("becomedragon").setExecutor(new Dragon());
	}
	
	private void lightLoop() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				if(!gameStarted)
					return;
				for(Player plr : Bukkit.getOnlinePlayers()) {
					PlayerData pd = PlayerManager.getPlayerData(plr);
					PlayerInventory inv = plr.getInventory();
					
					if(!pd.isDwarf)
						continue;
					
					if(plr.isOp())
						continue;
					
					if(inv.getItemInMainHand().getType().equals(Material.TORCH)) {
						plr.removePotionEffect(PotionEffectType.BLINDNESS);
						continue;
					}
					if(inv.getItemInOffHand().getType().equals(Material.TORCH)) {
						plr.removePotionEffect(PotionEffectType.BLINDNESS);
						continue;
					}
					
					Location loc = plr.getLocation();
					if(loc.getBlock().getLightLevel() <= 4) {
						plr.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000000, 0));
					}else {
						plr.removePotionEffect(PotionEffectType.BLINDNESS);
					}
					
				}
			}
		}, 1, 1);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getItem() == null)
			return;
		if(event.getItem().getItemMeta().getDisplayName() == null)
			return;
		for(ItemStack item : is.itemLists) {
			if(item.getItemMeta().getDisplayName().equals(event.getItem().getItemMeta().getDisplayName())) {
				if(!gameStarted) {
					event.getPlayer().sendMessage("§3You cannot cast this spell!");
					return;
				}
				event.setCancelled(true);
				for(ItemModule mod : castables) {
					mod.onInteracted(event.getAction(), event.getItem(), event.getPlayer(), event);
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand() == null)
			return;
		if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null)
			return;
		for(ItemStack item : is.itemLists) {
			if(item.getItemMeta().getDisplayName().equals(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName())) {
				if(!gameStarted) {
					event.getPlayer().sendMessage("§3You cannot cast this spell!");
					return;
				}
				event.setCancelled(true);
				
				for(ItemModule mod : castables) {
					if(!mod.isEntityInteract)
						continue;
					mod.onEntityInteract(event, event.getPlayer().getInventory().getItemInMainHand(), event.getPlayer());
				}
				
			}
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		Item item = event.getItemDrop();
		Player player = event.getPlayer();
		
		if(!gameStarted)
			return;
		
		for(ItemStack it : is.itemLists) {
			if(it.getItemMeta().getDisplayName().equals(item.getItemStack().getItemMeta().getDisplayName())) {
				event.setCancelled(true);
				player.sendMessage("§cYou cannot drop class based items!");
			}
		}
		
	}
	
	@EventHandler
	public void onPickup(EntityPickupItemEvent event) {
		if(!(event.getEntity() instanceof Player))
			return;
		
		Player player = (Player)event.getEntity();
		PlayerData pd = PlayerManager.getPlayerData(player);
		if(!pd.isDwarf)
			event.setCancelled(true);
		
	}
	
	public static void endTask(int task) {
		Bukkit.getScheduler().cancelTask(task);
	}

}
