package mskyblock;

import java.io.File;
import java.util.Arrays;

import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.plugin.PluginBase;
import mskyblock.generator.SkyblockGenerator;

public class Main extends PluginBase {
	private EventListener listener;
	private DataBase db;
	
	@Override
	public void onLoad() {
		Generator.addGenerator(SkyblockGenerator.class, "skyblock", SkyblockGenerator.TYPE_SKYBLOCK);
	}
	
	@Override
	public void onEnable() {
		initSkyblock();
		
		listener = new EventListener(this);
		db = new DataBase(this);
		
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
	@Override
	public void onDisable() {
		db.save();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return listener.onCommand(sender, command, label, args);
	}
	
	public DataBase getDB() {
		return db;
	}
	public EventListener getListener() {
		return listener;
	}
	
	private void initSkyblock() {
		File worldsDirectory = new File(Server.getInstance().getDataPath() + File.separator + "worlds" + File.separator);
		Arrays.stream(worldsDirectory.list()).forEach(worldName -> {
			if (worldName.startsWith("skyblock")) {
				Server.getInstance().loadLevel(worldName);
			}
		});
	}
}
