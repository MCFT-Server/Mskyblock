package mskyblock.skyblock;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.google.gson.internal.LinkedTreeMap;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.Position;
import mskyblock.Main;

public class Skyblock {
	private String owner;
	private LinkedTreeMap<String, Object> shares, invites;
	private int num;
	private Position spawn;
	private Area area;

	public static HashMap<String, Skyblock> skyblocklist;
	public static Main plugin;

	public static boolean hasSkyblock(Player player) {
		return hasSkyblock(player.getName());
	}

	public static boolean hasSkyblock(String player) {
		return skyblocklist.containsKey(player.toLowerCase());
	}

	@SuppressWarnings("serial")
	public static LinkedHashMap<String, Object> toHashMap() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		for (Skyblock skyblock : Skyblock.skyblocklist.values()) {
			map.put(skyblock.owner, new LinkedHashMap<String, Object>() {
				{
					put("owner", skyblock.owner);
					put("num", skyblock.num);
					put("shares", skyblock.shares);
					put("invites", skyblock.invites);
					put("spawn", posToString(skyblock.spawn));
				}
			});
		}
		return map;
	}

	private static String posToString(Position pos) {
		return getInt(pos.getX())+ ":" + getInt(pos.getY()) + ":" + getInt(pos.getZ()) + ":" + pos.getLevel().getFolderName();
	}
	public static int getInt(double d) {
		Double f = new Double(d);
		return f.intValue();
	}
	public static void makeSkyblock(Player player) {
		try {
			plugin.getDB().count.put("count", (int)plugin.getDB().count.get("count") + 1);
		} catch (ClassCastException e) {
			plugin.getDB().count.put("count", getInt((double)plugin.getDB().count.get("count")) + 1);
		}
		Skyblock.skyblocklist.put(player.getName().toLowerCase(), new Skyblock(player.getName().toLowerCase(),
				new LinkedTreeMap<String, Object>(), (int) plugin.getDB().count.get("count") + 1));
	}

	public static Skyblock getSkyblock(String name) {
		return skyblocklist.get(name.toLowerCase());
	}

	public static void remove(String ownername) {
		Skyblock.skyblocklist.remove(ownername.toLowerCase());
	}

	public static Skyblock getSkyblockByPos(Position pos) {
		for (Skyblock skyblock : Skyblock.skyblocklist.values()) {
			if (skyblock.area.isInside(pos)) {
				return skyblock;
			}
		}
		return null;
	}
	public static boolean hasSharedSkyblock(Player player) {
		return hasSharedSkyblock(player.getName());
	}
	public static boolean hasSharedSkyblock(String name) {
		String lowername = name.toLowerCase();
		for (Skyblock skyblock : skyblocklist.values()) {
			if (skyblock.isShare(lowername)) {
				return true;
			}
		}
		return false;
	}
	
	public static Skyblock getShareSkyblock(Player player) {
		return getShareSkyblock(player.getName());
	}
	public static Skyblock getShareSkyblock(String name) {
		String lowername = name.toLowerCase();
		for (Skyblock skyblock : Skyblock.skyblocklist.values()) {
			if (skyblock.isShare(lowername)) {
				return skyblock;
			}
		}
		return null;
	}

	public Skyblock(String player, LinkedTreeMap<String, Object> shares, int num) {
		this(player, shares, new LinkedTreeMap<String, Object>(), num,
				new Position(8, 13, (num * 20 - 20) * 16 + 8, Server.getInstance().getLevelByName("skyblock")));
	}

	public Skyblock(String player, LinkedTreeMap<String, Object> shares, LinkedTreeMap<String, Object> invites, int num,
			Position spawn) {
		this.owner = player;
		this.shares = shares;
		this.num = num;
		this.spawn = spawn;
		this.invites = invites;

		try {
			area = new Area(
					new Position(getOriginalSpawn().getX() - 150, 0, getOriginalSpawn().getZ() - 150,
							Server.getInstance().getLevelByName("skyblock")),
					new Position(getOriginalSpawn().getZ() + 150, 0, getOriginalSpawn().getZ() + 150,
							Server.getInstance().getLevelByName("skyblock")));
		} catch (DifferentLevelException e) {
			e.printStackTrace();
		}
	}

	public void remove() {
		Skyblock.remove(owner);
	}

	public String getOwner() {
		return owner;
	}

	public LinkedTreeMap<String, Object> getShares() {
		return shares;
	}
	
	public LinkedTreeMap<String, Object> getInvites() {
		return invites;
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

	public Area getArea() {
		return area;
	}

	public void setSpawn(Position pos) {
		this.spawn = pos;
	}

	public void shareSkyblock(String name) {
		shares.put(name.toLowerCase(), name.toLowerCase());
	}

	public void invitePlayer(String name) {
		invites.put(name.toLowerCase(), name.toLowerCase());
	}

	public boolean isOwner(Player player) {
		return isOwner(player.getName());
	}

	public boolean isOwner(String name) {
		if (owner.equals(name.toLowerCase())) {
			return true;
		}
		return false;
	}

	public boolean isShare(Player player) {
		return isShare(player.getName());
	}

	public boolean isShare(String name) {
		return shares.containsKey(name.toLowerCase());
	}

	public boolean isInvited(Player player) {
		return isInvited(player.getName());
	}

	public boolean isInvited(String name) {
		return invites.containsKey(name.toLowerCase());
	}

	public void expulsion(Player player) {
		expulsion(player.getName());
	}

	public void expulsion(String name) {
		shares.remove(name.toLowerCase());
		invites.remove(name.toLowerCase());
	}
}
