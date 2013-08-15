package tv.mineinthebox.configuration;

import java.util.ArrayList;

import tv.mineinthebox.fileManager;

public class rules_config {
	
	public void create_rules() {
		if(!fileManager.file_exists("rules.yml", fileManager.getDir())) {
			ArrayList<String> list = new ArrayList<String>();
			list.add("1. No griefing, also random non generated blocks or farms are seen as considered griefing.");
			list.add("2. No asking for operator or any other rank, it's on ours to decide whenever we want.");
			list.add("3. Do not cheat, hacked clients, dupes, spambots, xray mods or xray texture packs are forbidden.");
			list.add("4. addvertising is forbidden, it's a silly thing, also it means you are not allowed to addvertise our server on others");
			list.add("5. swearing is allowed, but limited you are not allowed to offend players or talk about racism/religions or the server it self");
			list.add("6. whining is not allowed, asking items, or complaining about warps, all are falling under the justification of whining use the forum instead ;)");
			
			fileManager.writeFile("rules.yml", "rules.prefix", "&e[Rules]", fileManager.getDir());
			fileManager.writeFile("rules.yml", "rules.suffix", "&c", fileManager.getDir());
			fileManager.writeFile("rules.yml", "rules.messages", list, fileManager.getDir());
			list.clear();
		}
	}

}
