package tv.mineinthebox.essentials.configuration;

import tv.mineinthebox.essentials.fileManager;

public class player_config {
	
	public void create_player() {
		if(!fileManager.file_exists("player.yml", fileManager.getDir())) {
			fileManager.createHeader("player.yml", "default configuration for players", fileManager.getDir());
			fileManager.writeFile("player.yml", "useSeperatedInventorys", true, fileManager.getDir());
			fileManager.writeFile("player.yml", "save-playerInventory", true, fileManager.getDir());
			fileManager.writeFile("player.yml", "godmode-inAfk", true, fileManager.getDir());
			fileManager.writeFile("player.yml", "steve-hurt-sound.enable", false, fileManager.getDir());
			fileManager.writeFile("player.yml", "entitysCanUseHeadOnPlayerDeath", false, fileManager.getDir());
			fileManager.writeFile("player.yml", "enable-realistic-glass", false, fileManager.getDir());
		}
	}

}
