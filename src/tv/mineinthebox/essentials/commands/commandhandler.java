package tv.mineinthebox.essentials.commands;

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
		} else if(cmd.getName().equalsIgnoreCase("torch")) {
			cmdtorch.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("warps")) {
			cmdwarps.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("delwarp")) {
			cmddelwarp.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("ban")) {
			cmdban.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("unban")) {
			cmdunban.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("tempban")) {
			cmdtempban.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("claim")) {
			cmdclaim.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("check")) {
			cmdcheck.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("done")) {
			cmddone.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("teleport")) {
			cmdteleport.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("hometp")) {
			cmdhometp.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("tphere")) {
			cmdtphere.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("tpa")) {
			cmdtpa.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("tpahere")) {
			cmdtpahere.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("fly")) {
			cmdfly.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("mute")) {
			cmdmute.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("unmute")) {
			cmdunmute.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("xessentials")) {
			cmdxessentials.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("gc")) {
			cmdgc.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("homeinvite")) {
			cmdhomeinvite.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("back")) {
			cmdback.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("broadcast")) {
			cmdbroadcast.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("afk")) {
			cmdafk.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("task")) {
			cmdtask.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("checkalt")) {
			cmdcheckalt.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("boom")) {
			cmdboom.execute(sender, args, cmd);
		} else if(cmd.getName().equalsIgnoreCase("hat")) {
			cmdhat.execute(sender, args, cmd);
		} else if(cmd.getName().equalsIgnoreCase("ghost")) {
			cmdghost.execute(sender, cmd, args);
		} else if(cmd.getName().equalsIgnoreCase("motd")) {
			cmdmotd.execute(sender, cmd, args);
		}
		return false;
	}

}
