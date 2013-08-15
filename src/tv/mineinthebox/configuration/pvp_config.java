package tv.mineinthebox.configuration;

import tv.mineinthebox.fileManager;

public class pvp_config {
	
	public void create_pvp() {
		if(!fileManager.file_exists("pvp.yml", fileManager.getDir())) {
			fileManager.writeFile("pvp.yml", "disable-pvp", true, fileManager.getDir());
			fileManager.writeFile("pvp.yml", "createClientSideGraveyard", false, fileManager.getDir());
			fileManager.writeFile("pvp.yml", "killBounty.enable", true, fileManager.getDir());
			fileManager.writeFile("pvp.yml", "killBounty.earn", 5.0, fileManager.getDir());
			fileManager.writeFile("pvp.yml", "npcReplaceLoggers", false, fileManager.getDir());
		}
	}

}
