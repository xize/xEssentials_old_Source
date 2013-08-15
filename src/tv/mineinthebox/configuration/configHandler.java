package tv.mineinthebox.configuration;

public class configHandler {
	
	public void setup_config() {
		entity_config entity = new entity_config();
		entity.entity_create();
		ban_config ban = new ban_config();
		ban.create_ban();
		broadcast_config broadcast = new broadcast_config();
		broadcast.create_broadcast();
		chat_config chat = new chat_config();
		chat.create_chat();
		motd_config motd = new motd_config();
		motd.create_motd();
		player_config player = new player_config();
		player.create_player();
		
	}
}
