package tv.mineinthebox.essentials;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import tv.mineinthebox.essentials.commands.commandhandler;
import tv.mineinthebox.essentials.commands.commandlist;
import tv.mineinthebox.essentials.configuration.configHandler;
import tv.mineinthebox.essentials.events.handler;
import tv.mineinthebox.essentials.logtype.logType;


public class xEssentials extends JavaPlugin {
	
	private static String filePath;
	private static xEssentials pl;
	private Logger logger = Logger.getLogger("Minecraft");
	private commandlist cmdlist = new commandlist();
	private commandhandler command = new commandhandler();
	private configHandler handleConfig = new configHandler();
	private pluginDescription pluginhandle = new pluginDescription();
	public static handler handle = new handler();
	public static xEssentialsMemory mem = new xEssentialsMemory();
	
	public void log(String log, logType type) {
		if(type == logType.info) {
			logger.info(pluginhandle.pname() + pluginhandle.version() + " " + log);
		} else if(type == logType.servere) {
			logger.severe(pluginhandle.pname() + pluginhandle.version() + " " + log);
		}
	}
	
	@Override
	public void onEnable() {
		pl = this;
		log("has been enabled!", logType.info);
		filePath = this.getDataFolder().getAbsolutePath();
		handleConfig.setup_config();
		xEssentialsMemory.startMemoryInput();
		handle.getListener();
		for(String commandS : cmdlist.getCommandList) {
			getCommand(commandS).setExecutor(command);
		}
	}
	@Override
	public void onDisable() {
		log("has been disabled!", logType.info);
		xEssentialsMemory.closeMemoryInput();
	}
	
	public static xEssentials getPlugin() {
		return pl;
	}
	
	public static String getFileFolder() {
		return filePath;
	}

}
