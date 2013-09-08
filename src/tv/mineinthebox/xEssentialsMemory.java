package tv.mineinthebox;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class xEssentialsMemory {
	
	/*
	 * 
	 *  Ban System
	 *  
	 * 
	 */
	
	public boolean isBanSystemEnabled = true;
	public boolean isAntiPwnAgeEnabled = false;
	public boolean isAntiFloodEnabled = false;
	public boolean isHumanSpamEnabled = false;
	public boolean showAlternateAccounts = false;
	public String pwnageMessage = null;
	public String antiFloodSpamMessage = null;
	public String humanSpamMessage = null;
	
	public void setBanSystem() {
		isBanSystemEnabled = fileManager.getBooleanValue("ban.yml", "ban.system.enable", fileManager.getDir());
		isAntiPwnAgeEnabled = fileManager.getBooleanValue("ban.yml", "ban.system.enablePwnAgeProtection", fileManager.getDir());
		isAntiFloodEnabled = fileManager.getBooleanValue("ban.yml", "ban.system.enableAntiFloodSpam", fileManager.getDir());
		isHumanSpamEnabled = fileManager.getBooleanValue("ban.yml", "ban.system.enableHumanSpamProtection", fileManager.getDir());
		showAlternateAccounts = fileManager.getBooleanValue("ban.yml", "ban.system.showAlternateAccounts", fileManager.getDir());
		pwnageMessage = fileManager.getStringValue("ban.yml", "ban.system.PwnAgeProtection.banMessage", fileManager.getDir());
		antiFloodSpamMessage = fileManager.getStringValue("ban.yml", "ban.system.AntiFloodSpam.banMessage", fileManager.getDir());
		humanSpamMessage = fileManager.getStringValue("ban.yml", "ban.system.HumanSpamProtection.banMessage", fileManager.getDir());
	}
	
	/*
	 * 
	 * Entity system
	 * 
	 * 
	 */
	
	public  boolean firespread = false;
	public  boolean explosion = false;
	public  boolean firework = false;
	public  boolean withergrief = false;
	public  boolean endermangrief = false;
	public  boolean enderdragongrief = false;
	public  boolean disable_spawneggs = false;
	public  boolean logSpawnEggs = false;
	public  HashMap<String, Boolean> mobValues = new HashMap<String, Boolean>();
	
	public  void setEntitySystem() {
		mobValues.clear();
		firespread = fileManager.getBooleanValue("entity.yml", "disable-firespread", fileManager.getDir());
		explosion = fileManager.getBooleanValue("entity.yml", "disable-explosion", fileManager.getDir());
		firework = fileManager.getBooleanValue("entity.yml", "disable-firework", fileManager.getDir());
		withergrief = fileManager.getBooleanValue("entity.yml", "disable-wither-grief", fileManager.getDir());
		endermangrief = fileManager.getBooleanValue("entity.yml", "disable-enderman-grief", fileManager.getDir());
		enderdragongrief = fileManager.getBooleanValue("entity.yml", "disable-enderdragon-grief", fileManager.getDir());
		disable_spawneggs = fileManager.getBooleanValue("entity.yml", "disable-spawneggs", fileManager.getDir());
		logSpawnEggs = fileManager.getBooleanValue("entity.yml", "log.spawnEggs", fileManager.getDir());
		File f = fileManager.returnFile("entity.yml", fileManager.getDir());
		FileConfiguration con = YamlConfiguration.loadConfiguration(f);
		for(String mobname : con.getConfigurationSection("mobs.allowToSpawn").getKeys(false)) {
			mobValues.put(mobname, con.getBoolean("mobs.allowToSpawn." + mobname));
		}
	}
	
	/*
	 * 
	 * broadcast system
	 * 
	 * 
	 */
	
	public  boolean broadcastEnabled = false;
	public  String broadcast_prefix;
	public  String broadcast_suffix;
	public  ArrayList<String> broadcast_list = new ArrayList<String>();
	
	public  void setBroadcastSystem() {
		broadcast_list.clear();
		broadcastEnabled = fileManager.getBooleanValue("broadcast.yml", "broadcast.system.normal.enable", fileManager.getDir());
		broadcast_prefix = fileManager.getStringValue("broadcast.yml", "broadcast.prefix", fileManager.getDir());
		broadcast_suffix = fileManager.getStringValue("broadcast.yml", "broadcast.suffix", fileManager.getDir());
		broadcast_list.addAll(fileManager.getStringListValue("broadcast.yml", "broadcast.messages", fileManager.getDir()));
	}
	
	/*
	 * 
	 * chat system
	 * 
	 * 
	 */
	
	public  boolean hightlights = false;
	public  boolean smilleys = false;
	public  boolean antiAddvertise = false;
	
	public  void setChatSystem() {
		hightlights = fileManager.getBooleanValue("chat.yml", "chat.enable.playerHighlights", fileManager.getDir());
		smilleys = fileManager.getBooleanValue("chat.yml", "chat.enable.smilleys", fileManager.getDir());
		antiAddvertise = fileManager.getBooleanValue("chat.yml", "chat.enable.antiAddvertise", fileManager.getDir());
	}
	
	/*
	 * 
	 * motd system
	 * 
	 * 
	 * 
	 */
	public  boolean motdEnabled = false;
	public  boolean motdRandom = false;
	public  ArrayList<String> motdMessages = new ArrayList<String>();
	public  String motdMessage = null;
	
	public  void setMotdSystem() {
		motdEnabled = fileManager.getBooleanValue("motd.yml", "motd.normal.enable", fileManager.getDir());
		motdRandom = fileManager.getBooleanValue("motd.yml", "motd.random.enable", fileManager.getDir());
		motdMessages.addAll(fileManager.getStringListValue("motd.yml", "motd.messages", fileManager.getDir()));
		motdMessage = fileManager.getStringValue("motd.yml", "motd.message", fileManager.getDir());
	}
	
	/*
	 * 
	 * player system
	 * 
	 * 
	 */
	
	public  boolean playerUseSeperatedInventorys = false;
	public  boolean playerSaveInventory = false;
	public  boolean playerGodmodeAfk = false;
	public  boolean playerSteveHurtSound = false;
	
	public  void setPlayerSystem() {
		playerUseSeperatedInventorys = fileManager.getBooleanValue("player.yml", "useSeperatedInventorys", fileManager.getDir());
		playerSaveInventory = fileManager.getBooleanValue("player.yml", "save-playerInventory", fileManager.getDir());
		playerGodmodeAfk = fileManager.getBooleanValue("player.yml", "godmode-inAfk", fileManager.getDir());
		playerSteveHurtSound = fileManager.getBooleanValue("player.yml", "steve-hurt-sound.enable", fileManager.getDir());
	}
	
	/*
	 * 
	 * pvp system
	 * 
	 * 
	 */
	public  boolean disablePvp = false;
	public  boolean createClientsideGraveyard = false;
	public  boolean killBountyEnabled = false;
	public  Double killBountyPrice = null;
	public  boolean npcReplaceLoggers = false;
	
	public  void setPvpSystem() {
		disablePvp = fileManager.getBooleanValue("pvp.yml", "disable-pvp", fileManager.getDir());
		createClientsideGraveyard = fileManager.getBooleanValue("pvp.yml", "createClientSideGraveyard", fileManager.getDir());
		killBountyEnabled = fileManager.getBooleanValue("pvp.yml", "killBounty.enable", fileManager.getDir());
		killBountyPrice = fileManager.getDoubleValue("pvp.yml", "killBounty.earn", fileManager.getDir());
		npcReplaceLoggers = fileManager.getBooleanValue("pvp.yml", "npcReplaceLoggers", fileManager.getDir());
	}
	
	/*
	 * 
	 * rules system
	 * 
	 * 
	 */
	
	public  String rules_prefix;
	public  String rules_suffix;
	public  ArrayList<String> rules = new ArrayList<String>();
	
	public  void setRulesSystem() {
		rules_prefix = fileManager.getStringValue("rules.yml", "rules.prefix", fileManager.getDir());
		rules_suffix = fileManager.getStringValue("rules.yml", "rules.suffix", fileManager.getDir());
		rules.addAll(fileManager.getStringListValue("rules.yml", "rules.messages", fileManager.getDir()));
	}
	
	/*
	 * 
	 * spawn system
	 * 
	 * 
	 */
	
	public  Double spawn_x;
	public  Double spawn_y;
	public  Double spawn_z;
	public  int spawn_yaw;
	public  World spawn_world;
	
	public  void setSpawnSystem() {
		if(fileManager.file_exists("spawn.yml", fileManager.getDir())) {
			spawn_x = fileManager.getDoubleValue("spawn.yml", "x", fileManager.getDir());
			spawn_y = fileManager.getDoubleValue("spawn.yml", "y", fileManager.getDir());
			spawn_z = fileManager.getDoubleValue("spawn.yml", "z", fileManager.getDir());
			spawn_yaw = fileManager.getIntegerValue("spawn.yml", "yaw", fileManager.getDir());
			spawn_world = Bukkit.getWorld(fileManager.getStringValue("spawn.yml", "world", fileManager.getDir()));
		}
	}
	
	/*
	 * 
	 * online player system
	 * 
	 * 
	 */
	
	protected HashMap<String, HashMap<String, Object>> onlinePlayers = new HashMap<String, HashMap<String, Object>>();
	
	public HashMap<String, Object> returnPlayer(Player p) {
		if(!onlinePlayers.containsKey(p.getName())) {
			onlinePlayers.put(p.getName(), new HashMap<String, Object>());
			return onlinePlayers.get(p.getName());
		} else {
			return onlinePlayers.get(p.getName());
		}
	}
	
	public void setPlayer(Player p) {
		if(!onlinePlayers.containsKey(p.getName())) {
			if(fileManager.file_exists(p.getName() + ".yml", fileManager.getDir() + File.separator + "players")) {
				if(fileManager.isSet(p.getName() + ".yml", "torch", fileManager.getDir() + File.separator + "players")) {
					returnPlayer(p).put("torch", fileManager.getBooleanValue(p.getName() + ".yml", "torch", fileManager.getDir() + File.separator + "players"));
				}
				if(fileManager.isSet(p.getName() + ".yml", "location", fileManager.getDir() + File.separator + "players")) {
					returnPlayer(p).put("location.x", fileManager.getDoubleValue(p.getName() + ".yml", "location.x", fileManager.getDir() + File.separator + "players"));
					returnPlayer(p).put("location.y", fileManager.getDoubleValue(p.getName() + ".yml", "location.y", fileManager.getDir() + File.separator + "players"));
					returnPlayer(p).put("location.z", fileManager.getDoubleValue(p.getName() + ".yml", "location.z", fileManager.getDir() + File.separator + "players"));
					returnPlayer(p).put("location.yaw", fileManager.getIntegerValue(p.getName() + ".yml", "location.yaw", fileManager.getDir() + File.separator + "players"));
					returnPlayer(p).put("location.world", fileManager.getStringValue(p.getName() + ".yml", "location.world", fileManager.getDir() + File.separator + "players"));
				}
				if(fileManager.isSet(p.getName() + ".yml", "fly", fileManager.getDir() + File.separator + "players")) {
					returnPlayer(p).put("fly", fileManager.getBooleanValue(p.getName() + ".yml", "fly", fileManager.getDir() + File.separator + "players"));
				}
				if(fileManager.isSet(p.getName() + ".yml", "firefly", fileManager.getDir() + File.separator + "players")) {
					returnPlayer(p).put("firefly", fileManager.getBooleanValue(p.getName() + ".yml", "firefly", fileManager.getDir() + File.separator + "players"));
				}
				if(fileManager.isSet(p.getName() + ".yml", "Vanished", fileManager.getDir() + File.separator + "players")) {
					returnPlayer(p).put("Vanished", fileManager.getBooleanValue(p.getName() + ".yml", "Vanished", fileManager.getDir() + File.separator + "players"));
				}
			}
		}
	}
	
	public void removePlayer(Player p) {
		if(onlinePlayers.containsKey(p.getName())) {
			returnPlayer(p).clear();
			onlinePlayers.remove(p.getName());
		}
	}
	
	public void updatePlayerConfig(Player p) {
		Map<String, Object> map = returnPlayer(p);
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, Object> its = (Entry<String, Object>) it.next();
			fileManager.writeFile(p.getName() + ".yml", its.getKey(), its.getValue(), fileManager.getDir() + File.separator + "players");
		}
	}
	
	/*
	 * 
	 * memory injector
	 * 
	 * 
	 */
	
	public void startMemoryInput() {
		setBanSystem();
		setEntitySystem();
		setBroadcastSystem();
		setChatSystem();
		setMotdSystem();
		setPlayerSystem();
		setPvpSystem();
		setRulesSystem();
		setSpawnSystem();
		for(Player player : Bukkit.getOnlinePlayers()) {
			setPlayer(player);
		}
	}
	
	public void closeMemoryInput() {
		broadcast_list.clear();
		motdMessages.clear();
		rules.clear();
		mobValues.clear();
		onlinePlayers.clear();
	}
}
