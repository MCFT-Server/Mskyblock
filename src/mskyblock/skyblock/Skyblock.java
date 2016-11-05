package mskyblock.skyblock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import mskyblock.Main;
import mskyblock.generator.SkyblockGenerator;
import mskyblock.skyblock.exception.DifferentLevelException;

public class Skyblock extends Area {
	private String owner;
	private LinkedTreeMap<String, Object> shares, invites;
	private int num;
	private Position spawn;
	private boolean inviteAll, pvp;

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
					put("num", skyblock.num);
					put("shares", skyblock.shares);
					put("invites", skyblock.invites);
					put("spawn", posToString(skyblock.spawn));
					put("inviteAll", skyblock.inviteAll);
					put("pvp", skyblock.pvp);
				}
			});
		}
		return map;
	}

	private static String posToString(Position pos) {
		return getInt(pos.getX()) + ":" + getInt(pos.getY()) + ":" + getInt(pos.getZ()) + ":" + pos.getLevel().getFolderName();
	}

	public static int getInt(double d) {
		Double f = new Double(d);
		return f.intValue();
	}

	public static void makeSkyblock(Player player) {
		try {
			plugin.getDB().count.put("count", (int) plugin.getDB().count.get("count") + 1);
		} catch (ClassCastException e) {
			plugin.getDB().count.put("count", getInt((double) plugin.getDB().count.get("count")) + 1);
		}
		Skyblock.skyblocklist.put(player.getName().toLowerCase(), new Skyblock(player.getName().toLowerCase(), 
				new LinkedTreeMap<String, Object>(), (int) plugin.getDB().count.get("count")));
	}

	public static Skyblock getSkyblock(String name) {
		return skyblocklist.get(name.toLowerCase());
	}

	public static void remove(String ownername) {
		Skyblock.skyblocklist.remove(ownername.toLowerCase());
	}

	public static Skyblock getSkyblockByPos(Position pos) {
		for (Skyblock skyblock : Skyblock.skyblocklist.values()) {
			if (skyblock.isInside(pos)) {
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

	public static List<Skyblock> getShareSkyblockList(Player player) {
		return getShareSkyblockList(player.getName());
	}

	public static List<Skyblock> getShareSkyblockList(String name) {
		String lowername = name.toLowerCase();
		ArrayList<Skyblock> list = new ArrayList<>();
		for (Skyblock skyblock : Skyblock.skyblocklist.values()) {
			if (skyblock.isShare(lowername)) {
				list.add(skyblock);
			}
		}
		return list;
	}

	public Skyblock(String player, LinkedTreeMap<String, Object> shares, int num) {
		this(player, shares, new LinkedTreeMap<String, Object>(), num, getOriginalSpawn(num), true, false);
	}

	public Skyblock(String player, LinkedTreeMap<String, Object> shares, int num, boolean isInviteAll) {
		this(player, shares, new LinkedTreeMap<String, Object>(), num, getOriginalSpawn(num), isInviteAll, false);
	}

	public Skyblock(String player, LinkedTreeMap<String, Object> shares, LinkedTreeMap<String, Object> invites, int num, Position spawn, boolean isInviteAll, boolean pvp) {
		super(getOriginalSpawn(num).add(-75, 0, -75), getOriginalSpawn(num).add(+75, 0, +75));
		this.owner = player;
		this.shares = shares;
		this.num = num;
		this.spawn = spawn;
		this.invites = invites;
		this.inviteAll = isInviteAll;
		this.pvp = pvp;
	}
	
	public void setPvp(boolean pvp) {
		this.pvp = pvp;
	}
	
	public boolean getPvp() {
		return pvp;
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
		int skyBlockLevelNum = num / 1000000;
		int x = (((int) ((num % 1000000) / 1000)) * 20 - 20) * 16 + 8;
		int z = (((int) ((num % 1000000) % 1000)) * 20 - 20) * 16 + 8;
		Level level = Server.getInstance().getLevelByName("skyblock" + skyBlockLevelNum);
		if (level == null) {
			if (!Server.getInstance().loadLevel("skyblock" + skyBlockLevelNum)) {
				Server.getInstance().generateLevel("skyblock" + skyBlockLevelNum, 0, SkyblockGenerator.class);
			}
			level = Server.getInstance().getLevelByName("skyblock" + skyBlockLevelNum);
		}

		return new Position(x, 13, z, level);
	}

	private static Position getOriginalSpawn(int num) {
		int skyBlockLevelNum = num / 1000000;
		int x = (((int) ((num % 1000000) / 1000) + 1) * 20 - 20) * 16 + 8;
		int z = (((int) ((num % 1000000) % 1000)) * 20 - 20) * 16 + 8;
		Level level = Server.getInstance().getLevelByName("skyblock" + skyBlockLevelNum);
		if (level == null) {
			if (!Server.getInstance().loadLevel("skyblock" + skyBlockLevelNum)) {
				Server.getInstance().generateLevel("skyblock" + skyBlockLevelNum, 0, SkyblockGenerator.class);
			}
			level = Server.getInstance().getLevelByName("skyblock" + skyBlockLevelNum);
		}

		return new Position(x, 13, z, level);
	}

	public Position getSpawn() {
		return spawn;
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

	public boolean isInviteAll() {
		return inviteAll;
	}

	public void setInviteAll(boolean bool) {
		inviteAll = bool;
	}
}
