package me.nbarudi.util;

import org.bukkit.entity.Player;

public class ChatSystems {
	
	public static void Warning(Player player, String message) {
		
		String filler = "§4§k00000";
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(filler + " §r§eWARNING WARNING WARNING " + filler + "\n");
		
		sb.append(message);
		
		sb.append("\n" + filler + " §r§eWARNING WARNING WARNING " + filler);
		
		player.sendMessage(sb.toString());
		
	}

}
