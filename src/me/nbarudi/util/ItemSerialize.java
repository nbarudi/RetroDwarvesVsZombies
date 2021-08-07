package me.nbarudi.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
@SuppressWarnings("deprecation")
public class ItemSerialize {
	
	//Systems
	public ArrayList<ItemStack> itemLists = new ArrayList<ItemStack>();
	
	//Game Items
	public ItemStack magmacream = new ItemStack(Material.MAGMA_CREAM);
	public ItemStack classbook = new ItemStack(Material.BOOK);
	public ItemStack goldnug = new ItemStack(Material.GOLD_NUGGET);
	
	//Dwarf Class Items
	public ItemStack becomeBuilder = new ItemStack(Material.GOLD_RECORD);
	public ItemStack becomeSmith = new ItemStack(Material.RECORD_4);
	public ItemStack becomeTailor = new ItemStack(Material.GREEN_RECORD);
	public ItemStack becomeAlchemist = new ItemStack(Material.RECORD_12);
	public ItemStack becomeBaker = new ItemStack(Material.RECORD_11);
	public ItemStack becomeEnchanter = new ItemStack(Material.RECORD_7);
	public ItemStack becomeHero = new ItemStack(Material.NETHER_STAR);
	
	//Monster Class Items
	public ItemStack becomeZombie = new ItemStack(Material.MONSTER_EGG, 1, EntityType.ZOMBIE.getTypeId());
	public ItemStack becomeSkeleton = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SKELETON.getTypeId());
	public ItemStack becomeCreeper = new ItemStack(Material.MONSTER_EGG, 1, EntityType.CREEPER.getTypeId());
	public ItemStack becomeSpider = new ItemStack(Material.MONSTER_EGG, 1, EntityType.CAVE_SPIDER.getTypeId());
	public ItemStack becomeWolf = new ItemStack(Material.MONSTER_EGG, 1, EntityType.WOLF.getTypeId());
	public ItemStack becomeIrongolem = new ItemStack(Material.MONSTER_EGG, 1, EntityType.IRON_GOLEM.getTypeId());
	public ItemStack becomeBroodmother = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SILVERFISH.getTypeId());
	public ItemStack becomeEnderman = new ItemStack(Material.MONSTER_EGG, 1, EntityType.ENDERMAN.getTypeId());
	
	//Monster Castables
	public ItemStack creeperExplode = new ItemStack(Material.SULPHUR);
	public ItemStack spiderPoison = new ItemStack(Material.SPLASH_POTION);
	public ItemStack wolfJump = new ItemStack(Material.SUGAR);
	public ItemStack endermanBlink = new ItemStack(Material.EYE_OF_ENDER);
	public ItemStack suicide = new ItemStack(Material.GHAST_TEAR);
	public ItemStack map = new ItemStack(Material.MAP);
	public ItemStack broodBreak = new ItemStack(Material.SLIME_BALL);
	
	//Dwarf Castables
	public ItemStack rocket = new ItemStack(Material.FIREWORK);
	
	//Potions
	public ItemStack healPotion = new ItemStack(Material.POTION, 1, (short) 8197);
	public ItemStack speedPotion = new ItemStack(Material.POTION, 1, (short) 8194);
	public ItemStack fresistPotion = new ItemStack(Material.POTION, 1, (short) 8195);
	public ItemStack strengthPotion = new ItemStack(Material.POTION, 1, (short) 8201);
	
	//Dragon Items
	public ItemStack roarGrapple = new ItemStack(Material.SHEARS);
	public ItemStack firebreathBall = new ItemStack(Material.INK_SACK, 1, (short) 1);
	public ItemStack caveDeath = new ItemStack(Material.REDSTONE);
	
	//Extras
	public ItemStack cakeSpawner = new ItemStack(Material.EMERALD);
	public ItemStack deathStare = new ItemStack(Material.REDSTONE_TORCH_ON);
	public ItemStack easyFixSlabs = new ItemStack(Material.CLAY_BRICK);
	public ItemStack trackingCompas = new ItemStack(Material.COMPASS);
	
	//Hero Items
	public ItemStack dwarriorfireball = new ItemStack(Material.FIREBALL);
	public ItemStack icequeenstaff = new ItemStack(Material.DIAMOND_HOE);
	public ItemStack lightningkingstaff = new ItemStack(Material.GOLD_HOE);
	public ItemStack barbarianslam = new ItemStack(Material.STONE_SPADE);
	
	public ItemSerialize() {
		//Game Items
		magmacream();
		book();
		goldnug();
		
		//Dwarf Classes
		builder();
		smith();
		tailor();
		alchemist();
		baker();
		enchanter();
		hero();
		
		//Monster Classes
		zombie();
		skeleton();
		creeper();
		spider();
		wolf();
		irongolem();
		broodmother();
		enderman();
		
		//Monster Abilities
		creeperExplode();
		spiderPoison();
		wolfJump();
		endermanBlink();
		suicide();
		map();
		broodBreak();
		
		//Potions
		healthpotion();
		speedpotion();
		fresistpotion();
		strengthpotion();
		
		//Extras
		cakesummoner();
		deathstare();
		easyfixSlabs();
		trackingCompass();
		
		//Hero
		dwarrior();
		icequeenstaff();
		lightningkingstaff();
		barbarianslam();
		
		//Dragon
		roarGrapple();
		firebreathBall();
		caveDeath();
		
		//Dwarf Castables
		rocket();
		
		setupList();
	}
	
	void setupList() {
		//Game Items
		itemLists.add(magmacream);
		itemLists.add(classbook);
		itemLists.add(goldnug);
		
		//Dwarf Classes
		itemLists.add(becomeBuilder);
		itemLists.add(becomeSmith);
		itemLists.add(becomeTailor);
		itemLists.add(becomeAlchemist);
		itemLists.add(becomeBaker);
		itemLists.add(becomeEnchanter);
		itemLists.add(becomeHero);
		
		//Monster Classes
		itemLists.add(becomeZombie);
		itemLists.add(becomeSkeleton);
		itemLists.add(becomeCreeper);
		itemLists.add(becomeSpider);
		itemLists.add(becomeWolf);
		itemLists.add(becomeIrongolem);
		itemLists.add(becomeBroodmother);
		itemLists.add(becomeEnderman);
		
		//Monster Abilities
		itemLists.add(creeperExplode);
		itemLists.add(spiderPoison);
		itemLists.add(wolfJump);
		itemLists.add(endermanBlink);
		itemLists.add(suicide);
		itemLists.add(map);
		itemLists.add(broodBreak);
		
		//Dwarf Castables
		itemLists.add(rocket);
		
		//Potions
		itemLists.add(healPotion);
		itemLists.add(speedPotion);
		itemLists.add(fresistPotion);
		itemLists.add(strengthPotion);
		
		//Extras
		itemLists.add(cakeSpawner);
		itemLists.add(deathStare);
		itemLists.add(easyFixSlabs);
		itemLists.add(trackingCompas);
		
		//Hero
		itemLists.add(dwarriorfireball);
		itemLists.add(icequeenstaff);
		itemLists.add(lightningkingstaff);
		itemLists.add(barbarianslam);
		
		//Dragon
		itemLists.add(roarGrapple);
		itemLists.add(firebreathBall);
		itemLists.add(caveDeath);
	}
	
	void trackingCompass() {
		ItemMeta im = trackingCompas.getItemMeta();
		im.setDisplayName("§aTracking Compass");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§b- Right click to update/change targets");
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		trackingCompas.setItemMeta(im);
		trackingCompas.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, -1);
	}
	
	void easyfixSlabs() {
		ItemMeta im = easyFixSlabs.getItemMeta();
		im.setDisplayName("§3Easy Fix Slabs");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§b- Left click to create a wall of slabs in front of you!");
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		easyFixSlabs.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, -1);
		easyFixSlabs.setItemMeta(im);
	}
	
	void rocket() {
		ItemMeta im = rocket.getItemMeta();
		im.setDisplayName("§aRocket Jump");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§b- Left click to leap into the air!");
		lore.add("§b- Right click to shoot a rocket! (Single Use, Destroys the item!)");
		im.setLore(lore);
		rocket.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, -1);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		rocket.setItemMeta(im);
		
	}
	
	void barbarianslam() {
		ItemMeta im = barbarianslam.getItemMeta();
		im.setDisplayName("§aBarbarian Slam");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§b- Right click to leap into the air and slam the ground!");
		lore.add("§b- Hit a player to throw them into the distance!");
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.setUnbreakable(true);
		im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		barbarianslam.setItemMeta(im);
		barbarianslam.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 0);
	}
	
	void deathstare() {
		ItemMeta im = deathStare.getItemMeta();
		im.setDisplayName("§4Death Stare");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§b- Left click to Death Stare someone!");
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		deathStare.setItemMeta(im);
		deathStare.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 0);
	}
	
	void lightningkingstaff() {
		ItemMeta im = lightningkingstaff.getItemMeta();
		im.setDisplayName("§1Lightning Staff");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§b- Right click to smite all monsters (and players) around you.");
		lore.add("§b- Left click to an arrow that smites who it hits.");
		im.setLore(lore);
		im.setUnbreakable(true);
		lightningkingstaff.setItemMeta(im);
	}
	
	void icequeenstaff() {
		ItemMeta im = icequeenstaff.getItemMeta();
		im.setDisplayName("§bIce Staff");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§b- Right click to slow all monsters (and players) around you.");
		lore.add("§b- Left click to shoot a snowball that damages who it hits.");
		im.setLore(lore);
		im.setUnbreakable(true);
		icequeenstaff.setItemMeta(im);
	}
	
	void creeperExplode() {
		ItemMeta im = creeperExplode.getItemMeta();
		im.setDisplayName("§4Explode");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§b- Left click to explode!");
		im.setLore(lore);
		creeperExplode.setItemMeta(im);
	}
	
	void spiderPoison() {
		PotionMeta im = (PotionMeta)spiderPoison.getItemMeta();
		im.setDisplayName("§2Spider Venom");
		List<String> lore = new ArrayList<String>();
		lore.add("§b- Right click a player to poison them for a short time");
		im.setLore(lore);
		im.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 100, 0), true);
		im.setColor(Color.GREEN);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		spiderPoison.setItemMeta(im);
		spiderPoison.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 0);
	}
	
	void wolfJump() {
		ItemMeta im = wolfJump.getItemMeta();
		im.setDisplayName("§rWolfs Jump");
		List<String> lore = new ArrayList<String>();
		lore.add("§b- Left click to launch yourself forward");
		im.setLore(lore);
		wolfJump.setItemMeta(im);
	}
	
	void endermanBlink() {
		ItemMeta im = endermanBlink.getItemMeta();
		im.setDisplayName("§0Blink");
		List<String> lore = new ArrayList<String>();
		lore.add("§b- Left click to blink");
		im.setLore(lore);
		endermanBlink.setItemMeta(im);
	}
	
	void suicide() {
		ItemMeta im = suicide.getItemMeta();
		im.setDisplayName("§4Suicide");
		List<String> lore = new ArrayList<String>();
		lore.add("§b- Left Click to commit Seppuku");
		im.setLore(lore);
		suicide.setItemMeta(im);
	}
	
	void map() {
		ItemMeta im = map.getItemMeta();
		im.setDisplayName("§0Warp to Ender Portal");
		List<String> lore = new ArrayList<String>();
		lore.add("§b- Left click to warp to the endermans portal");
		im.setLore(lore);
		map.setItemMeta(im);
	}
	
	void broodBreak() {
		ItemMeta im = broodBreak.getItemMeta();
		im.setDisplayName("§7Burrow");
		List<String> lore = new ArrayList<String>();
		lore.add("§b- Left click to weaken the dwarven walls");
		im.setLore(lore);
		broodBreak.setItemMeta(im);
	}
	
	void dwarrior() {
		ItemMeta meta = dwarriorfireball.getItemMeta();
		meta.setDisplayName("§6Dragons Charge");
		List<String> lore = new ArrayList<String>();
		lore.add("§b- Left click to shoot a fireball");
		meta.setLore(lore);
		dwarriorfireball.setItemMeta(meta);
	}
	
	void roarGrapple() {
		ItemMeta meta = roarGrapple.getItemMeta();
		meta.setDisplayName("§bClaws");
		List<String> lore = new ArrayList<String>();
		lore.add("§b- Right click to grapple someone");
		lore.add("§b- Left click to roar");
		meta.setLore(lore);
		roarGrapple.setItemMeta(meta);
	}
	
	void firebreathBall() {
		ItemMeta meta = firebreathBall.getItemMeta();
		meta.setDisplayName("§cRain Fire");
		List<String> lore = new ArrayList<String>();
		lore.add("§b- Right click to create doom");
		lore.add("§b- Left click to breath fire");
		meta.setLore(lore);
		firebreathBall.setItemMeta(meta);
	}
	
	void caveDeath() {
		ItemMeta meta = caveDeath.getItemMeta();
		meta.setDisplayName("§0Final Moments");
		List<String> lore = new ArrayList<String>();
		lore.add("§b- Right click to die");
		lore.add("§b- Left click to create your cave");
		meta.setLore(lore);
		caveDeath.setItemMeta(meta);
	}
	
	void magmacream() {
		ItemMeta im = magmacream.getItemMeta();
		im.setDisplayName("§6Claim Classes");
		List<String> lore = new ArrayList<String>();
		lore.add("§bLeft-Click me to claim your classes");
		im.setLore(lore);
		magmacream.setItemMeta(im);
	}
	
	void book() {
		ItemMeta im = classbook.getItemMeta();
		im.setDisplayName("§5Run Ability");
		List<String> lore = new ArrayList<String>();
		lore.add("§b- Left-Click to run your class ability!");
		lore.add("§b- Right-Click to open your storage vault!");
		im.setLore(lore);
		classbook.setItemMeta(im);
	}
	
	void cakesummoner() {
		ItemMeta im = cakeSpawner.getItemMeta();
		im.setDisplayName("§eCake Summoner");
		List<String> lore = new ArrayList<String>();
		lore.add("§bRight-Click to spawn a cake!");
		im.setLore(lore);
		cakeSpawner.setItemMeta(im);
	}
	
	void goldnug() {
		ItemMeta im = goldnug.getItemMeta();
		im.setDisplayName("§4Monster Classes");
		List<String> lore = new ArrayList<String>();
		lore.add("§bLeft-Click me to claim your monster classes");
		im.setLore(lore);
		goldnug.setItemMeta(im);
	}
	
	void builder() {
		ItemMeta im = becomeBuilder.getItemMeta();
		im.setDisplayName("§6Become Builder");
		List<String> lore = new ArrayList<String>();
		lore.add("§bLeft-Click me to become a Builder");
		im.setLore(lore);
		becomeBuilder.setItemMeta(im);
	}
	
	void smith() {
		ItemMeta im = becomeSmith.getItemMeta();
		im.setDisplayName("§4Become Blacksmith");
		List<String> lore = new ArrayList<String>();
		lore.add("§bLeft-Click me to become a Blacksmith");
		im.setLore(lore);
		becomeSmith.setItemMeta(im);
	}
	
	void tailor() {
		ItemMeta im = becomeTailor.getItemMeta();
		im.setDisplayName("§aBecome Tailor");
		List<String> lore = new ArrayList<String>();
		lore.add("§bLeft-Click me to become a Tailor");
		im.setLore(lore);
		becomeTailor.setItemMeta(im);
	}
	
	void alchemist() {
		ItemMeta im = becomeAlchemist.getItemMeta();
		im.setDisplayName("§3Become Alchemist");
		List<String> lore = new ArrayList<String>();
		lore.add("§bLeft-Click me to become an Alchemist");
		im.setLore(lore);
		becomeAlchemist.setItemMeta(im);
	}
	
	void baker() {
		ItemMeta im = becomeBaker.getItemMeta();
		im.setDisplayName("§0Become Baker");
		List<String> lore = new ArrayList<String>();
		lore.add("§bLeft-Click me to become a Baker");
		im.setLore(lore);
		becomeBaker.setItemMeta(im);
	}
	
	void enchanter() {
		ItemMeta im = becomeEnchanter.getItemMeta();
		im.setDisplayName("§5Become Enchanter");
		List<String> lore = new ArrayList<String>();
		lore.add("§bLeft-Click me to become an Enchanter");
		im.setLore(lore);
		becomeEnchanter.setItemMeta(im);
	}
	
	void hero() {
		ItemMeta im = becomeHero.getItemMeta();
		im.setDisplayName("§5§kBecome Hero");
		List<String> lore = new ArrayList<String>();
		lore.add("§b§kLeft-Click me to become an Enchanter");
		lore.add("§b§kLeft-Click me to become an Enchanter");
		im.setLore(lore);
		becomeHero.setItemMeta(im);
	}
	
	void zombie() {
		ItemMeta im = becomeZombie.getItemMeta();
		im.setDisplayName("§2Become Zombie");
		becomeZombie.setItemMeta(im);
	}
	
	void skeleton() {
		ItemMeta im = becomeSkeleton.getItemMeta();
		im.setDisplayName("§7Become Skeleton");
		becomeSkeleton.setItemMeta(im);
	}
	
	void creeper() {
		ItemMeta im = becomeCreeper.getItemMeta();
		im.setDisplayName("§aBecome Creeper");
		becomeCreeper.setItemMeta(im);
	}
	
	void spider() {
		ItemMeta im = becomeSpider.getItemMeta();
		im.setDisplayName("§9Become Spider");
		becomeSpider.setItemMeta(im);
	}
	
	void wolf() {
		ItemMeta im = becomeWolf.getItemMeta();
		im.setDisplayName("§rBecome Wolf");
		becomeWolf.setItemMeta(im);
	}
	
	void irongolem() {
		ItemMeta im = becomeIrongolem.getItemMeta();
		im.setDisplayName("§rBecome IronGolem");
		becomeIrongolem.setItemMeta(im);
	}
	
	void broodmother() {
		ItemMeta im = becomeBroodmother.getItemMeta();
		im.setDisplayName("§5Become Broodmother");
		becomeBroodmother.setItemMeta(im);
	}
	
	void enderman() {
		ItemMeta im = becomeEnderman.getItemMeta();
		im.setDisplayName("§0Become Enderman");
		becomeEnderman.setItemMeta(im);
	}
	
	
	void healthpotion() {
		PotionMeta im = (PotionMeta) healPotion.getItemMeta();
		im.setDisplayName("§dHealing Potion");
		List<String> lore = new ArrayList<String>();
		lore.add("§bLeft-Click to cast a healing spell!");
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.setColor(Color.RED);
		healPotion.setItemMeta(im);
		healPotion.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 0);
	}
	
	void speedpotion() {
		PotionMeta im = (PotionMeta) speedPotion.getItemMeta();
		im.setDisplayName("§bSpeed Potion");
		List<String> lore = new ArrayList<String>();
		lore.add("§bLeft-Click to cast a healing spell!");
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.setColor(Color.AQUA);
		speedPotion.setItemMeta(im);
		speedPotion.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 0);
	}
	
	void fresistpotion() {
		PotionMeta im = (PotionMeta) fresistPotion.getItemMeta();
		im.setDisplayName("§6Fire Resistance Potion");
		List<String> lore = new ArrayList<String>();
		lore.add("§bLeft-Click to cast a healing spell!");
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.setColor(Color.ORANGE);
		fresistPotion.setItemMeta(im);
		fresistPotion.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 0);
	}
	
	void strengthpotion() {
		PotionMeta im = (PotionMeta) strengthPotion.getItemMeta();
		im.setDisplayName("§5Strength Potion");
		List<String> lore = new ArrayList<String>();
		lore.add("§bLeft-Click to cast a healing spell!");
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.setColor(Color.PURPLE);
		strengthPotion.setItemMeta(im);
		strengthPotion.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 0);
	}
	
	
}
