package mskyblock.event;

import cn.nukkit.event.Event;
import mskyblock.skyblock.Skyblock;

public class SkyblockEvent extends Event {
	private Skyblock skyblock;
	
	public SkyblockEvent(Skyblock skyblock) {
		this.skyblock = skyblock;
	}
	
	public Skyblock getSkyblock() {
		return this.skyblock;
	}
}
