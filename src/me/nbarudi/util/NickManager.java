package me.nbarudi.util;

import me.nbarudi.main.RDvZ;
import me.nbarudi.util.PlayerData.DwarfClass;

public class NickManager {
	
	public static void updateNames() {
		
		for(PlayerData pd : RDvZ.data) {
			StringBuilder sb = new StringBuilder();
			
			if(PlayerManager.getPlayer(pd) == null)
				continue;
			
			if(PlayerManager.getPlayer(pd).isOp())
				continue;
			
			if(pd.isHero == true)
				continue;
			
			if(pd.isDwarf) {
				if(pd.role.equals(DwarfClass.ENCHANTER)) {
					sb.append(pd.realName + " §3the Enchanter");
				}
				else if(pd.role.equals(DwarfClass.BUILDER)) {
					sb.append(pd.realName + " §3the Dwarf");
				}
				else if(pd.role.equals(DwarfClass.SMITH)) {
					sb.append(pd.realName + " §3the Blacksmith");
				}
				else if(pd.role.equals(DwarfClass.TAILOR)) {
					sb.append(pd.realName + " §3the Tailor");
				}
				else if(pd.role.equals(DwarfClass.ALCHEMIST)) {
					sb.append(pd.realName + " §3the Alchemist");
				}
				else if(pd.role.equals(DwarfClass.BAKER)) {
					sb.append(pd.realName + " §3the Baker");
				}else {
					sb.append(pd.realName + " §3the Unknown");
				}
			}else {
				switch(pd.mrole) {
				case ZOMBIE:
					sb.append(pd.realName + " §4The Zombie");
					break;
				case SKELETON:
					sb.append(pd.realName + " §4The Skeleton");
					break;
				case CREEPER:
					sb.append(pd.realName + " §4The Creeper");
					break;
				case SPIDER:
					sb.append(pd.realName + " §4The Spider");
					break;
				case WOLF:
					sb.append(pd.realName + " §4The Wolf");
					break;
				case IRONGOLEM:
					sb.append(pd.realName + " §4The IronGolem");
					break;
				case BROODMOTHER:
					sb.append(pd.realName + " §4The BroodMother");
					break;
				case ENDERMAN:
					sb.append(pd.realName + " §4The Enderman");
					break;
				case NONE:
					sb.append(pd.realName + " §4the Perished");
					break;
				}
			}
			
//			if(pd.isDwarf) {
//				switch(pd.role) {
//				case BUILDER:
//					sb.append(pd.realName + " §3the Dwarf");
//					break;
//				case SMITH:
//					sb.append(pd.realName + " §3the Blacksmith");
//					break;
//				case ALCHEMIST:
//					sb.append(pd.realName + " §3the Alchemist");
//					break;
//				case BAKER:
//					sb.append(pd.realName + " §3the Baker");
//					break;
//				case TAILOR:
//					sb.append(pd.realName + " §3the Tailor");
//					break;
//				case ENCHANTER:
//					sb.append(pd.realName + " §3the Enchanter");
//					break;
//				case NONE:
//					sb.append(pd.realName + " §3the Unknown");
//					break;
//				}
//			}
//			else {

//			}
			
			pd.name = sb.toString();
		}
		
	}

}
