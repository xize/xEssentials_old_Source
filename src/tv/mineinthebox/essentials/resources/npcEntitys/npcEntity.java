package tv.mineinthebox.essentials.resources.npcEntitys;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import tv.mineinthebox.essentials.xEssentials;
import de.kumpelblase2.remoteentities.CreateEntityContext;
import de.kumpelblase2.remoteentities.api.RemoteEntity;
import de.kumpelblase2.remoteentities.api.RemoteEntityType;

public class npcEntity {

	public static HashMap<String, Integer> npcNames = new HashMap<String, Integer>();
	
	public static RemoteEntity createNpc(String npcName, Location loc, final Player p) {
		if(xEssentials.manager == null) {
			return null;
		}
		CreateEntityContext c = xEssentials.manager.prepareEntity(RemoteEntityType.Human)
				.atLocation(loc)
				.withName(npcName)
				.asPushable(false)
				.asStationary(false);
			RemoteEntity entity = c.create();
			//npcNames.put(entity.getName(), entity.getID());
			return entity;
	}

	public static void despawnNpc(String name) {
		if(npcNames.containsKey(name)) {
			xEssentials.manager.removeEntity(npcNames.get(name), true);	
		}
	}

}
