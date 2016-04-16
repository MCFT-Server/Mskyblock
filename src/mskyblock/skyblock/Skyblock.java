package mskyblock.skyblock;

import java.util.HashMap;

import cn.nukkit.Player;
import cn.nukkit.level.Location;

public class Skyblock {
	private Location pos;
	private String owner;
	
	public static HashMap<String, Skyblock> skyblocklist;
	
	
	public static boolean hasSkyblock(Player player) {
		return hasSkyblock(player.getName());
	}
	public static boolean hasSkyblock(String player) {
		player = player.toLowerCase();
		//TODO
		return true;
	}
	
	public static void makeSkyblock(Player player) {
		
	}
}
