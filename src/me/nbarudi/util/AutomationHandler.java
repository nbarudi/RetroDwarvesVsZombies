package me.nbarudi.util;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nbarudi.main.RDvZ;

public class AutomationHandler implements Listener {
	
	public boolean useAutomation = false;
	public boolean adminEvent = false;
	public int numPlayers = 0;
	private int deadPlayers = 0;
	private int toDie;
	private int task = 0;
	public int updateList = 0;
	
	private ArrayList<EventTypes> events = new ArrayList<EventTypes>();
	private EventTypes plannedEvent;
	
	public AutomationHandler() {
		
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(RDvZ.instance, new Runnable() {
			public void run() {
				
				if(!useAutomation)
					return;

				if(numPlayers >= 21) {
					Bukkit.broadcastMessage("§6Starting the game in 1 minute!");
					Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
						public void run() {
							Bukkit.broadcastMessage("§aStarting the game!");
							startGame();
							return;
						}
					}, 1200);
					cancelTask();
					return;
				}
				
				numPlayers = Bukkit.getOnlinePlayers().size();

				if(updateList == 15) {
					Bukkit.broadcastMessage("§6We require atleast 20 players to start! (Currently: " + numPlayers + " players!)");
					updateList = 0;
					return;
				}
				updateList++;
				
				
			}
		}, 20, 20);

		
		
//		Bukkit.getScheduler().scheduleAsyncRepeatingTask(RDvZ.instance, new BukkitRunnable() {
//			public void run() {
//				
//				if(!useAutomation)
//					return;
//				
//				if(numPlayers >= 20) {
//					Bukkit.broadcastMessage("§aThe game will be starting in 1 minute!");
//					Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
//						public void run() {
//							startGame();
//						}
//					}, 3600);
//					cancel();
//				}else {
//					Bukkit.broadcastMessage("§6We require atleast 20 players to start! (Currently: " + numPlayers + " player(s)!)");
//				}
//				int tempPlayers = 0;
//				for(Player p : Bukkit.getOnlinePlayers()) {
//					if(!p.isOp())
//						tempPlayers++;
//				}
//				numPlayers = tempPlayers;
//				
//			}
//		}, 20, 900);
		
	}
	
	private void cancelTask() {
		Bukkit.getScheduler().cancelTask(task);
	}
	
	
	public void startGame() {
		toDie = (int)Math.floor((RDvZ.data.size() * 0.2) + 0.5);
		//sender.sendMessage("§aGame Started!");
		Bukkit.broadcastMessage("§6It's time to play... §bDwarves §7Vs §2Zombies");
		for(PlayerData data : RDvZ.data) {
			Player player = PlayerManager.getPlayer(data);
			player.setHealth(20);
			player.setFoodLevel(20);
			player.setSaturation(20);
			if(player.isOp()) {
				player.sendMessage("§cYou are opped so you do not get to participate!");
				continue;
			}
			player.getInventory().addItem(RDvZ.is.magmacream);
			data.setDwarf(true);
		}
		
		RDvZ.gameStarted = true;
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.setScoreboard(RDvZ.sb);
		}
		Bukkit.getScheduler().scheduleSyncRepeatingTask(RDvZ.instance, new Runnable() {
			public void run() {
				NickManager.updateNames();
			}
		}, 20, 20);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "time set day");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doDaylightCycle true");
		
		deadPlayers = 0;
		initEvents();
		
		Random rnd = new Random();
		plannedEvent = events.get(rnd.nextInt(events.size()));
		
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
			public void run() {
				nightPhase();
			}
		}, 25000);
		
		
		return;
	}
	
	private void initEvents() {
		events.add(new PlagueEvent("Plague", false));
	}
	
	private void nightPhase() {
		Bukkit.broadcastMessage(ChatColor.RED + "The second night has come!");
		
		if(adminEvent)
			return;
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(RDvZ.instance, new Runnable() {
			public void run() {
				plannedEvent.runEvent();
			}
		}, 100);
	}
	
	private void releaseMonsters() {
		
		Bukkit.broadcastMessage("§7[§dGods§7] §aMonsters have been released!!!!");
		Bukkit.broadcastMessage("§7[§dGods§7] §aMonsters have been released!!!!");
		Bukkit.broadcastMessage("§7[§dGods§7] §aMonsters have been released!!!!");
		Bukkit.broadcastMessage("§7[§dGods§7] §aMonsters have been released!!!!");
		Bukkit.broadcastMessage("§7[§dGods§7] §aMonsters have been released!!!!");
		
//		Bukkit.dispatchCommand(sender, "summon Lightning_Bolt ~ ~50 ~");
//		Bukkit.dispatchCommand(sender, "summon Lightning_Bolt ~ ~50 ~");
//		Bukkit.dispatchCommand(sender, "summon Lightning_Bolt ~ ~50 ~");
//		Bukkit.dispatchCommand(sender, "summon Lightning_Bolt ~ ~50 ~");
//		Bukkit.dispatchCommand(sender, "summon Lightning_Bolt ~ ~50 ~");
//		Bukkit.dispatchCommand(sender, "summon Lightning_Bolt ~ ~50 ~");
		
		RDvZ.monstersReleased = true;
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doMobSpawning false");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "time set night");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doDaylightCycle false");
		
	}
	
	class EventTypes{
		public String name = "";
		public boolean requireAdmin = false;
		
		public void runEvent() {
			
		}
	}
	
	class PlagueEvent extends EventTypes {
		public PlagueEvent(String name, boolean requireAdmin) {
			this.name = name;
			this.requireAdmin = requireAdmin;
		}
		
		@Override
		public void runEvent() {
			int numPlayers = RDvZ.data.size();
			
			double n1 = numPlayers * 0.2;
			int toDie = (int)Math.floor(n1);
			
			Random rnd = new Random();
			
			ArrayList<Integer> chosenNumbers = new ArrayList<Integer>();
			for(int i = 0; i < toDie; i++) {
				int player = rnd.nextInt(numPlayers);
				while(chosenNumbers.contains(player))
					player = rnd.nextInt(numPlayers);
				PlayerData data = RDvZ.data.get(player);
				Player plr = PlayerManager.getPlayer(data);
				plr.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100000, 2));
				plr.sendMessage("§0You have been affected by the plague!");
				chosenNumbers.add(player);
			}
			
			Bukkit.broadcastMessage("§0A Plague has consumed the keep!");
		}
	}
	
	@EventHandler
	public void onDead(PlayerDeathEvent event) {
		Player player = event.getEntity();
		PlayerData pd = PlayerManager.getPlayerData(player);
		
		if(!pd.isDwarf)
			return;
		
		if(!useAutomation)
			return;
		
		deadPlayers++;
		if(deadPlayers == toDie) {
			releaseMonsters();
		}
		
	}

}
