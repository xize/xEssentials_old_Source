package tv.mineinthebox.configuration;

import java.util.ArrayList;

import tv.mineinthebox.fileManager;

public class motd_config {
	
	public void create_motd() {
		if(!fileManager.file_exists("motd.yml", fileManager.getDir())) {
			ArrayList<String> list = new ArrayList<String>();
			list.add("message 1");
			list.add("message 2");
			fileManager.createHeader("motd.yml", "", fileManager.getDir());
			fileManager.writeFile("motd.yml", "motd.normal.enable", true, fileManager.getDir());
			fileManager.writeFile("motd.yml", "motd.random.enable", false, fileManager.getDir());
			fileManager.writeFile("motd.yml", "motd.messages", list, fileManager.getDir());
			fileManager.writeFile("motd.yml", "motd.message", "default motd for xEssentials", fileManager.getDir());
			list.clear();
		}
	}

}
