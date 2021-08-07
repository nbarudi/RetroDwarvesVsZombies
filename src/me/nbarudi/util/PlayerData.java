package me.nbarudi.util;

import me.nbarudi.modules.heros.HeroModule;

public class PlayerData {
	
	public String name = "";
	public String realName = "";
	public boolean isDwarf = true;
	
	public boolean isHero = false;
	public HeroModule hero;
	
	public boolean hasClaimedClasses = false;
	public DwarfClass role = DwarfClass.NONE;
	public MonsterClass mrole = MonsterClass.NONE;
	public int mana = 1000;
	
	public PlayerData(String realName) {
		this.realName = realName;
		this.name = realName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRealName() {
		return realName;
	}
	
	public boolean getHero() {
		return isHero;
	}
	
	public boolean getDwarf() {
		return isDwarf;
	}
	
	public void setDwarf(boolean value) {
		isDwarf = value;
	}
	
	public void setHero(boolean value) {
		isHero = value;
	}
	
	public static enum DwarfClass  {
		NONE, BUILDER, SMITH, TAILOR, ALCHEMIST, BAKER, ENCHANTER
	}
	
	public static enum MonsterClass  {
		NONE, ZOMBIE, SKELETON, CREEPER, SPIDER, WOLF, IRONGOLEM, BROODMOTHER, ENDERMAN
	}
	
}
