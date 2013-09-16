package tv.mineinthebox;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

	public static boolean isBanSystemEnabled = false;
	public static boolean isAntiPwnAgeEnabled = false;
	public static boolean isAntiFloodEnabled = false;
	public static boolean isHumanSpamEnabled = false;
	public static boolean showAlternateAccounts = false;
	public static String pwnageMessage = null;
	public static String antiFloodSpamMessage = null;
	public static String humanSpamMessage = null;

	public static void setBanSystem() {
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

	public static boolean weather = false;
	public static boolean firespread = false;
	public static boolean explosion = false;
	public static boolean firework = false;
	public static boolean withergrief = false;
	public static boolean endermangrief = false;
	public static boolean enderdragongrief = false;
	public static boolean disable_spawneggs = false;
	public static boolean logSpawnEggs = false;
	public static boolean zombieAggroEnabled = false;
	public static Double zombieRange = 0.0;
	public static HashMap<String, Boolean> mobValues = new HashMap<String, Boolean>();

	public static void setEntitySystem() {
		mobValues.clear();
		weather = fileManager.getBooleanValue("entity.yml", "disable-weather", fileManager.getDir());
		firespread = fileManager.getBooleanValue("entity.yml", "disable-firespread", fileManager.getDir());
		explosion = fileManager.getBooleanValue("entity.yml", "disable-explosion", fileManager.getDir());
		firework = fileManager.getBooleanValue("entity.yml", "disable-firework", fileManager.getDir());
		withergrief = fileManager.getBooleanValue("entity.yml", "disable-wither-grief", fileManager.getDir());
		endermangrief = fileManager.getBooleanValue("entity.yml", "disable-enderman-grief", fileManager.getDir());
		enderdragongrief = fileManager.getBooleanValue("entity.yml", "disable-enderdragon-grief", fileManager.getDir());
		disable_spawneggs = fileManager.getBooleanValue("entity.yml", "disable-spawneggs", fileManager.getDir());
		logSpawnEggs = fileManager.getBooleanValue("entity.yml", "log.spawnEggs", fileManager.getDir());
		zombieAggroEnabled = fileManager.getBooleanValue("entity.yml", "zombie-custom-aggro.enable", fileManager.getDir());
		zombieRange = fileManager.getDoubleValue("entity.yml", "zombie-custom-aggro.range", fileManager.getDir());
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

	public static  boolean broadcastEnabled = false;
	public static  String broadcast_prefix;
	public static  String broadcast_suffix;
	public static  ArrayList<String> broadcast_list = new ArrayList<String>();

	public static  void setBroadcastSystem() {
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

	public static  boolean hightlights = false;
	public static  boolean smilleys = false;
	public static String hashtag = null;
	public static  boolean antiAddvertise = false;
	public static String setSmilleys(String msg, String RecallSuffix) {
		if(smilleys) {
			if(msg.contains(":D") || msg.contains(":@") || msg.contains(":d") || msg.contains("<3")) {
				return msg.toString().replace(":D", ChatColor.translateAlternateColorCodes('&', "  &6☻ " + RecallSuffix)).toString().replace(":d", ChatColor.translateAlternateColorCodes('&', "  &6☻ " + RecallSuffix)).toString().replace("<3", ChatColor.translateAlternateColorCodes('&', "  &c♥ " + RecallSuffix)).toString().replace(":@", ChatColor.translateAlternateColorCodes('&', " &c(╯°□°）╯︵ ┻━┻  " + RecallSuffix));
			} else {
				return msg;
			}
		} else {
			return msg;
		}
	}

	public static void setChatSystem() {
		hightlights = fileManager.getBooleanValue("chat.yml", "chat.enable.playerHighlights", fileManager.getDir());
		smilleys = fileManager.getBooleanValue("chat.yml", "chat.enable.smilleys", fileManager.getDir());
		hashtag = fileManager.getStringValue("chat.yml", "chat.enable.hashtag", fileManager.getDir());
		antiAddvertise = fileManager.getBooleanValue("chat.yml", "chat.enable.antiAddvertise", fileManager.getDir());
	}

	/*
	 * 
	 * motd system
	 * 
	 * 
	 * 
	 */
	public static  boolean motdEnabled = false;
	public static  boolean motdRandom = false;
	public static  ArrayList<String> motdMessages = new ArrayList<String>();
	public static  String motdMessage = null;

	public static  void setMotdSystem() {
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

	public static  boolean playerUseSeperatedInventorys = false;
	public static  boolean playerSaveInventory = false;
	public static  boolean playerGodmodeAfk = false;
	public static  boolean playerSteveHurtSound = false;

	public static  void setPlayerSystem() {
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
	public static  boolean disablePvp = false;
	public static  boolean createClientsideGraveyard = false;
	public static  boolean killBountyEnabled = false;
	public static  Double killBountyPrice = null;
	public static  boolean npcReplaceLoggers = false;

	public static  void setPvpSystem() {
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

	public static  String rules_prefix;
	public static  String rules_suffix;
	public static  ArrayList<String> rules = new ArrayList<String>();

	public static  void setRulesSystem() {
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

	public static  Double spawn_x;
	public static  Double spawn_y;
	public static  Double spawn_z;
	public static  int spawn_yaw;
	public static  World spawn_world;

	public static  void setSpawnSystem() {
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

	protected static HashMap<String, HashMap<String, Object>> onlinePlayers = new HashMap<String, HashMap<String, Object>>();

	public static HashMap<String, Object> returnPlayer(Player p) {
		if(!onlinePlayers.containsKey(p.getName())) {
			onlinePlayers.put(p.getName(), new HashMap<String, Object>());
			return onlinePlayers.get(p.getName());
		} else {
			return onlinePlayers.get(p.getName());
		}
	}

	public static void setPlayer(Player p) {
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
				if(fileManager.isSet(p.getName() + ".yml", "muted", fileManager.getDir() + File.separator + "players")) {
					System.out.println("detected mute for player " + p.getName());
					returnPlayer(p).put("muted", fileManager.getLongValue(p.getName() + ".yml", "muted", fileManager.getDir() + File.separator + "players"));
				}
			}
		}
	}

	public static void removePlayer(Player p) {
		if(onlinePlayers.containsKey(p.getName())) {
			returnPlayer(p).clear();
			onlinePlayers.remove(p.getName());
		}
	}

	public static void updatePlayerConfig(Player p) {
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

	public static void startMemoryInput() {
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

	public static void closeMemoryInput() {
		broadcast_list.clear();
		motdMessages.clear();
		rules.clear();
		mobValues.clear();
		for(Player p : Bukkit.getOnlinePlayers()) {
			returnPlayer(p).clear();
		}
		onlinePlayers.clear();
	}
}
