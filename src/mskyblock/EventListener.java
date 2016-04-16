package mskyblock;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.level.ChunkLoadEvent;
import mskyblock.skyblock.Skyblock;

public class EventListener implements Listener {
	private Main plugin;
	
	public EventListener(Main plugin) {
		this.plugin = plugin;
	}
	
	public DataBase getDB() {
		return plugin.getDB();
	}
	
	@EventHandler
	public void onChunkLoad(ChunkLoadEvent event) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				event.getChunk().setBiomeColor(x, z, 133, 188, 86);
			}
		}
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().toLowerCase().equals(getDB().messages.get("commands-skyblock"))) {
			if (args.length < 1) {
				if (!(sender instanceof Player)) {
					getDB().alert(sender, getDB().get("commands-skyblock-usage"));
					return true;
				}
				if (Skyblock.hasSkyblock((Player) sender)) {
					Skyblock.makeSkyblock((Player) sender);
					getDB().message(sender, getDB().get("make-skyblock-success-1"));
					getDB().message(sender, getDB().get("make-skyblock-success-2"));
					return true;
				}
				getDB().alert(sender, getDB().get("commands-skyblock-usage"));
				return true;
			}
			if (args[0].toLowerCase().equals(getDB().get("command-move"))) {
				
			}
		}
		return true;
	}
}

