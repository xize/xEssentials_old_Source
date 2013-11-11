package tv.mineinthebox.essentials.configuration;

import java.io.IOException;

import org.bukkit.entity.EntityType;

import tv.mineinthebox.essentials.fileManager;


public class entity_config {
	public entity_config() {
		super();
	}

	public void entity_create() {
		try {
			if(fileManager.file_exists("entity.yml", fileManager.getDir())) {
				update();
			} else {
				fileManager.writeFile("entity.yml", "disable-weather", false, fileManager.getDir());
				fileManager.writeFile("entity.yml", "disable-firespread", false, fileManager.getDir());
				fileManager.writeFile("entity.yml", "disable-explosion", false, fileManager.getDir());
				fileManager.writeFile("entity.yml", "disable-firework", false, fileManager.getDir());
				fileManager.writeFile("entity.yml", "disable-wither-grief", false, fileManager.getDir());
				fileManager.writeFile("entity.yml", "disable-enderman-grief", false, fileManager.getDir());
				fileManager.writeFile("entity.yml", "disable-enderdragon-grief", false, fileManager.getDir());
				fileManager.writeFile("entity.yml", "zombie-custom-aggro.enable", false, fileManager.getDir());
				fileManager.writeFile("entity.yml", "zombie-custom-aggro.range", 8.0, fileManager.getDir());
				fileManager.writeFile("entity.yml", "disable-spawneggs", false, fileManager.getDir());
				fileManager.writeFile("entity.yml", "log.spawnEggs", false, fileManager.getDir());
				fileManager.writeFile("entity.yml", "use-criticals", false, fileManager.getDir());
				update("entity.yml");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		for(EntityType entity : EntityType.values()) {
			if(entity.isAlive()) {
				if(entity != EntityType.PLAYER) {
					fileManager.writeFile("entity.yml", "mobs.allowToSpawn." + serialize_name(entity.name()), true, fileManager.getDir());
				}
			}
		}
	}

	public void update(String fileName) throws IOException {
		for(EntityType entity : EntityType.values()) {
			if(entity.isAlive()) {
				if(!fileManager.isSet(fileName, "mobs.allowToSpawn." + serialize_name(entity.name()), fileManager.getDir()) && !entity.name().equalsIgnoreCase(EntityType.PLAYER.name())) {
					fileManager.writeFile(fileName, "mobs.allowToSpawn." + serialize_name(entity.name()), true, fileManager.getDir());
				}
			}
		}
	}

	public String serialize_name(String mob) {
		return mob.toString().toLowerCase();
	}

	public String deSerialize_name(String mob) {
		return mob.toString().toUpperCase();
	}

}
