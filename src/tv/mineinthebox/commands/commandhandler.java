package tv.mineinthebox.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class commandhandler implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sethome")) {
			cmdsethome.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("home")) {
			cmdhome.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("setspawn")) {
			cmdsetspawn.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("spawn")) {
			cmdspawn.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("vanish")) {
			cmdvanish.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("firefly")) {
			cmdfirefly.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("spawner")) {
			cmdspawner.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("gamemode")) {
			cmdgamemode.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("modreq")) {
			cmdmodreq.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("spawnmob")) {
			cmdspawnmob.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("invsee")) {
			cmdinvsee.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("warp")) {
			cmdwarp.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("setwarp")) {
			cmdsetwarp.execute(sender, cmd, args);
		}
		return false;
	}

}
