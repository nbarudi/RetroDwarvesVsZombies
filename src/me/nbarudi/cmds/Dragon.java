package me.nbarudi.cmds;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.nbarudi.events.DamageEvent;
import me.nbarudi.main.RDvZ;
import me.nbarudi.util.PlayerData;
import me.nbarudi.util.PlayerManager;

public class Dragon implements CommandExecutor {
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player))
			return true;
		
		Player player = (Player) sender;
		PlayerData pd = PlayerManager.getPlayerData(player);
		
		player.getInventory().clear();
		pd.name = "§4Vlaurunga the Dragon";
		//Disguise disguise = new MobDisguise(DisguiseType.ENDER_DRAGON);
		//disguise.setVisibility(Visibility.EVERYONE);
		//RDvZ.dapi.disguise(player, disguise);
		
		MobDisguise md = new MobDisguise(DisguiseType.ENDER_DRAGON);
		md.setEntity(player);
		md.startDisguise();
		
		
		RDvZ.dragon = player;
		
		player.getInventory().addItem(RDvZ.is.roarGrapple);
		player.getInventory().addItem(RDvZ.is.firebreathBall);
		player.getInventory().addItem(RDvZ.is.caveDeath);
		
		double x = RDvZ.instance.getConfig().getDouble("Warps.monster.x");
		double y = RDvZ.instance.getConfig().getDouble("Warps.monster.y");
		double z = RDvZ.instance.getConfig().getDouble("Warps.monster.z");
		String world = RDvZ.instance.getConfig().getString("Warps.monster.world");
		World w = Bukkit.getWorld(world);
		
		Location loc = new Location(w, x, y, z);
		player.teleport(loc);
		player.sendMessage("§6Warping to Monster Spawn");
		player.sendMessage("§aYou have become the dragon!");
		
		for(Player r : Bukkit.getOnlinePlayers()) {
			DamageEvent.bb.addPlayer(r);
		}
		
		player.setGameMode(GameMode.SURVIVAL);
		player.setAllowFlight(true);
		player.setFlying(true);
		
		return true;
	}

}
