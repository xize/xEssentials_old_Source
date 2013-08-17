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
		}
		return false;
	}

}
