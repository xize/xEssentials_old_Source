package tv.mineinthebox.configuration;

import java.util.ArrayList;

import tv.mineinthebox.fileManager;

public class broadcast_config {

	public void create_broadcast() {
		if(!fileManager.file_exists("broadcast.yml", fileManager.getDir())) {
			ArrayList<String> list = new ArrayList<String>();
			list.add("&ebroadcast 1");
			list.add("&ebroadcast 2");
			list.add("&ebroadcast 3");
			fileManager.createHeader("broadcast.yml", "the default configuration for broadcasts", fileManager.getDir());
			fileManager.writeFile("broadcast.yml", "broadcast.system.normal.enable", true, fileManager.getDir());
			fileManager.writeFile("broadcast.yml", "broadcast.prefix", "&e[broadcast]", fileManager.getDir());
			fileManager.writeFile("broadcast.yml", "broadcast.suffix", "&2", fileManager.getDir());
			fileManager.writeFile("broadcast.yml", "broadcast.messages", list, fileManager.getDir());
			list.clear();
		}
	}

}
