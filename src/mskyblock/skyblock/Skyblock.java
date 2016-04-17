package mskyblock.skyblock;

import java.util.HashMap;
import java.util.LinkedHashMap;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.Position;
import javafx.geometry.Pos;
import mskyblock.Main;

public class Skyblock {
	private String owner;
	private LinkedHashMap<String, Object> shares; 
	private int num;
	private Position spawn;
	
	public static HashMap<String, Skyblock> skyblocklist;
	public static Main plugin;
	
	public static boolean hasSkyblock(Player player) {
		return hasSkyblock(player.getName());
	}
	public static boolean hasSkyblock(String player) {
		return skyblocklist.containsKey(player.toLowerCase());
	}
	public static LinkedHashMap<String, Object> toHashMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		for (Skyblock skyblock : Skyblock.skyblocklist.values()) {
			map.put(skyblock.owner, new LinkedHashMap<String, Object>() {
				{
					put("owner", skyblock.owner);
					put("num", skyblock.num);
					put("shares", skyblock.shares);
					put("spawn", posToString(skyblock.spawn));
				}
			});
		}
		return map;
	}
	
	private static String posToString(Position pos) {
		return (int)pos.getX()+":"+(int)pos.getY()+":"+(int)pos.getZ()+":"+pos.getLevel().getFolderName();
	}
	
	public static void makeSkyblock(Player player) {
		plugin.getDB().count.put("count", ((int)plugin.getDB().count.get("count") + 1));
		Skyblock.skyblocklist.put(player.getName().toLowerCase(), new Skyblock(player.getName().toLowerCase(), new LinkedHashMap<String, Object>() {
			{
				put(player.getName().toLowerCase(), player.getName().toLowerCase());
			}
		}, (int)plugin.getDB().count.get("count") + 1));
	}
	
	public static Skyblock getSkyblock(String name) {
		return skyblocklist.get(name.toLowerCase());
	}
	
	public static void remove(String ownername) {
		Skyblock.skyblocklist.remove(ownername.toLowerCase());
	}
	
	public Skyblock(String player, LinkedHashMap<String, Object> shares, int num) {
		this(player, shares, num, new Position(8, 13, (num * 20 - 20) * 16 + 8, Server.getInstance().getLevelByName("skyblock")));
	}
	public Skyblock(String player, LinkedHashMap<String, Object> shares, int num, Position spawn) {
		this.owner = player;
		this.shares = shares;
		this.num =num;
		this.spawn = spawn;
	}
	
	public void remove() {
		Skyblock.remove(owner);
	}
	
	public String getOwner() {
		return owner;
	}
	
	public LinkedHashMap<String, Object> getShares() {
		return shares;
	}
	
	public int getNumber() {
		return num;
	}
	public Position getOriginalSpawn() {
		return new Position(8, 13, (num * 20 - 20) * 16 + 8, Server.getInstance().getLevelByName("skyblock"));
	}
	public Position getSpawn() {
		return spawn;
	}
	
	public void setSpawn(Position pos) {
		this.spawn = pos;
	}
	public void shareSkyblock(String name) {
		
	}
}
