package tv.mineinthebox;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class xEssentialsMemory {
	
	/*
	 * 
	 *  Ban System
	 *  
	 * 
	 */
	
	protected static boolean isBanSystemEnabled = false;
	protected static boolean isAntiPwnAgeEnabled = false;
	protected static boolean isAntiFloodEnabled = false;
	protected static boolean isHumanSpamEnabled = false;
	protected static boolean showAlternateAccounts = false;
	protected static String pwnageMessage = null;
	protected static String antiFloodSpamMessage = null;
	protected static String humanSpamMessage = null;
	
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
	
	protected static boolean firespread = false;
	protected static boolean explosion = false;
	protected static boolean firework = false;
	protected static boolean withergrief = false;
	protected static boolean endermangrief = false;
	protected static boolean enderdragongrief = false;
	protected static boolean disable_spawneggs = false;
	protected static boolean logSpawnEggs = false;
	protected static HashMap<String, Boolean> mobValues = new HashMap<String, Boolean>();
	
	public static void setEntitySystem() {
		mobValues.clear();
		firespread = fileManager.getBooleanValue("entity.yml", "disable-firespread", fileManager.getDir());
		explosion = fileManager.getBooleanValue("entity.yml", "disable-explosion", fileManager.getDir());
		firework = fileManager.getBooleanValue("entity.yml", "disable-firework", fileManager.getDir());
		withergrief = fileManager.getBooleanValue("entity.yml", "disable-wither-grief", fileManager.getDir());
		endermangrief = fileManager.getBooleanValue("entity.yml", "disable-enderman-grief", fileManager.getDir());
		enderdragongrief = fileManager.getBooleanValue("entity.yml", "disable-enderdragon-grief", fileManager.getDir());
		disable_spawneggs = fileManager.getBooleanValue("entity.yml", "disable-spawneggs", fileManager.getDir());
		logSpawnEggs = fileManager.getBooleanValue("entity.yml", "log.spawnEggs", fileManager.getDir());
		Iterator<?> mobs = fileManager.getListValue("entity.yml", "mobs.allowToSpawn", fileManager.getDir()).iterator();
		while(mobs.hasNext()) {
			String mob = (String) mobs.next();
			mobValues.put(mob, fileManager.getBooleanValue("entity.yml", "mobs.allowToSpawn." + mob, fileManager.getDir()));
		}
	}
	
	/*
	 * 
	 * broadcast system
	 * 
	 * 
	 */
	
	protected static boolean broadcastEnabled = false;
	protected static String broadcast_prefix;
	protected static String broadcast_suffix;
	protected static ArrayList<String> broadcast_list = new ArrayList<String>();
	
	public static void setBroadcastSystem() {
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
	
	protected static boolean hightlights = false;
	protected static boolean smilleys = false;
	protected static boolean antiAddvertise = false;
	
	public static void setChatSystem() {
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
	protected static boolean motdEnabled = false;
	protected static boolean motdRandom = false;
	protected static ArrayList<String> motdMessages = new ArrayList<String>();
	protected static String motdMessage = null;
	
	public static void setMotdSystem() {
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
	
	protected static boolean playerUseSeperatedInventorys = false;
	protected static boolean playerSaveInventory = false;
	protected static boolean playerGodmodeAfk = false;
	protected static boolean playerSteveHurtSound = false;
	
	public static void setPlayerSystem() {
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
	protected static boolean disablePvp = false;
	protected static boolean createClientsideGraveyard = false;
	protected static boolean killBountyEnabled = false;
	protected static Double killBountyPrice = null;
	protected static boolean npcReplaceLoggers = false;
	
	public static void setPvpSystem() {
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
	
	protected static String rules_prefix;
	protected static String rules_suffix;
	protected static ArrayList<String> rules = new ArrayList<String>();
	
	public static void setRulesSystem() {
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
	
	protected static Double spawn_x;
	protected static Double spawn_y;
	protected static Double spawn_z;
	protected static int spawn_yaw;
	protected static World spawn_world;
	
	public static void setSpawnSystem() {
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
			}
		}
	}
	
	public static void removePlayer(Player p) {
		if(onlinePlayers.containsKey(p.getName())) {
			returnPlayer(p).clear();
			onlinePlayers.remove(p.getName());
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
}
