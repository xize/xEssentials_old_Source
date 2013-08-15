package tv.mineinthebox.configuration;

import tv.mineinthebox.fileManager;

public class ban_config {
	
	public void create_ban() {
		if(!fileManager.file_exists("ban.yml", fileManager.getDir())) {
			fileManager.createHeader("ban.yml", "this is the default ban.yml, here you can edit all ban protection", fileManager.getDir());
			fileManager.writeFile("ban.yml", "ban.system.enable", true, fileManager.getDir());
			fileManager.writeFile("ban.yml", "ban.system.enablePwnAgeProtection", true, fileManager.getDir());
			fileManager.writeFile("ban.yml", "ban.system.enableAntiFloodSpam", true, fileManager.getDir());
			fileManager.writeFile("ban.yml", "ban.system.enableHumanSpamProtection", true, fileManager.getDir());
			fileManager.writeFile("ban.yml", "ban.system.PwnAgeProtection.banMessage", "[PwnAge] spam hacks", fileManager.getDir());
			fileManager.writeFile("ban.yml", "ban.system.AntiFloodSpam.banMessage", "[FloodSpam] spam hacks", fileManager.getDir());
			fileManager.writeFile("ban.yml", "ban.system.HumanSpamProtection.banMessage", "[normal spam] dont spam!", fileManager.getDir());
		}
	}

}
