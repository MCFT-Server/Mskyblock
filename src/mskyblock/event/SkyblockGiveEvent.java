package mskyblock.event;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import mskyblock.skyblock.Skyblock;

public class SkyblockGiveEvent extends SkyblockEvent implements Cancellable {
	private Player player;
	
	public SkyblockGiveEvent(Skyblock skyblock, Player player) {
		super(skyblock);
		
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
}
