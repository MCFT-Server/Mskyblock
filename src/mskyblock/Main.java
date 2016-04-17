package mskyblock;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.plugin.PluginBase;
import mskyblock.generator.SkyblockGenerator;
import mskyblock.task.AuthorTask;

public class Main extends PluginBase {
	private EventListener listener;
	private DataBase db;
	
	@Override
	public void onLoad() {
		Generator.addGenerator(SkyblockGenerator.class, "skyblock", SkyblockGenerator.TYPE_SKYBLOCK);
	}
	
	@Override
	public void onEnable() {
		listener = new EventListener(this);
		db = new DataBase(this);
		
		initSkyblock();
		
		getServer().getPluginManager().registerEvents(listener, this);
		getServer().getScheduler().scheduleRepeatingTask(new AuthorTask(this), 20*60*3);
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
	public void initSkyblock() {
		getServer().generateLevel("skyblock", 0, SkyblockGenerator.class);
	}
}
