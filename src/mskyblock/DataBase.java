package mskyblock;

import java.util.LinkedHashMap;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import mskyblock.skyblock.Skyblock;

public class DataBase {
	public Main plugin;
	public Config messages, config;
	public LinkedHashMap<String, Object> skyblockDB;
	public static final int m_version = 1;
	
	public DataBase(Main plugin) {
		this.plugin = plugin;
		
		plugin.getDataFolder().mkdirs();
		initMessage();
		initDB();
		
		registerCommands();
	}
	public void initMessage() {
		plugin.saveResource("messages.yml");
		messages = new Config(this.plugin.getDataFolder() + "/messages.yml", Config.YAML);
		updateMessage();
	}
	public void updateMessage() {
		if (messages.get("m_version", 1) < m_version) {
			this.plugin.saveResource("messages.yml", true);
			messages = new Config(this.plugin.getDataFolder() + "/messages.yml", Config.YAML);
		}
	}
	public void initDB() {
		skyblockDB = (LinkedHashMap<String, Object>) (new Config(plugin.getDataFolder() + "/skyblockDB.json", Config.JSON)).getAll();
	}
	public void save() {
		Config skyblockDB = new Config(plugin.getDataFolder() + "/skyblockDB.json", Config.JSON);
		skyblockDB.setAll(this.skyblockDB);
		skyblockDB.save();
	}
	public void registerCommands() {
		registerCommand(get("commands-skyblock"), get("commands-skyblock-description"), get("commands-skyblock-usage"), "mskyblock.commands.island");
	}
	public void registerCommand(String name, String description, String usage, String permission) {
		SimpleCommandMap commandMap = this.plugin.getServer().getCommandMap();
		PluginCommand<Main> command = new PluginCommand<Main>(name, plugin);
		command.setDescription(description);
		command.setUsage(usage);
		command.setPermission(permission);
		commandMap.register(name, command);
	}
	public String get(String key) {
		return this.messages.get(this.messages.get("default-language", "kor") + "-" + key, "default-value");
	}
	public void alert(CommandSender player, String message) {
		player.sendMessage(TextFormat.RED + get("default-prefix") + " " + message);
	}
	public void message(CommandSender player, String message) {
		player.sendMessage(TextFormat.DARK_AQUA + get("default-prefix") + " " + message);
	}
}
