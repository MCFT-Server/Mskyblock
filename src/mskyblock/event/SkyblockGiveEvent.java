package mskyblock.event;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.HandlerList;
import mskyblock.skyblock.Skyblock;

public class SkyblockGiveEvent extends SkyblockEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	
	public static HandlerList getHandlers() {
	    return handlers;
	}
	private Player player;
	
	public SkyblockGiveEvent(Skyblock skyblock, Player player) {
		super(skyblock);
		
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
}
