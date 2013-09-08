package tv.mineinthebox.configuration;

import tv.mineinthebox.fileManager;

public class chat_config {
	
	public void create_chat() {
		if(!fileManager.file_exists("chat.yml", fileManager.getDir())) {
			fileManager.createHeader("chat.yml", "default configuration file for all related chat events", fileManager.getDir());
			fileManager.writeFile("chat.yml", "chat.enable.playerHighlights", true, fileManager.getDir());
			fileManager.writeFile("chat.yml", "chat.enable.smilleys", true, fileManager.getDir());
			fileManager.writeFile("chat.yml", "chat.enable.hashtag", "&e@", fileManager.getDir());
			fileManager.writeFile("chat.yml", "chat.enable.antiAddvertise", true, fileManager.getDir());
		}
	}

}
