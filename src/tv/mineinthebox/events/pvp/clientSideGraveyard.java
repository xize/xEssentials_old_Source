package tv.mineinthebox.events.pvp;

import net.minecraft.server.v1_6_R3.EntityPlayer;
import net.minecraft.server.v1_6_R3.Packet130UpdateSign;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class clientSideGraveyard implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void getGrave(PlayerDeathEvent e) {
		System.out.println("event found");
		for(Player p : Bukkit.getOnlinePlayers()) {
			Location loc = e.getEntity().getLocation();
			Block block = loc.getBlock();
			
			Block signBlock = block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);
			p.sendBlockChange(signBlock.getLocation(), Material.SIGN_POST, (byte) 0xD);
			
			String[] lines = {
				ChatColor.translateAlternateColorCodes('&', "&F&lR.I.P"),
				e.getEntity().getName(),
				"we'all miss-",
				"you."
			};
			//https://forums.bukkit.org/threads/change-the-direction-of-a-sign.31582/ relevant
			EntityPlayer player = ((CraftPlayer) p).getHandle();
			player.playerConnection.sendPacket(new Packet130UpdateSign(signBlock.getX(), signBlock.getY(), signBlock.getZ(), lines));
			p.sendBlockChange(block.getLocation(), Material.MOSSY_COBBLESTONE, block.getData());
			Block blockSkeleton = block.getRelative(BlockFace.UP);
			p.sendBlockChange(blockSkeleton.getLocation(), Material.SKULL, blockSkeleton.getData());
			Block redstone1 = block.getRelative(BlockFace.EAST);
			p.sendBlockChange(redstone1.getLocation(), Material.REDSTONE_WIRE, redstone1.getData());
			Block redstone2 = redstone1.getRelative(BlockFace.EAST);
			p.sendBlockChange(redstone2.getLocation(), Material.REDSTONE_WIRE, redstone2.getData());
			Block redstone3 = redstone2.getRelative(BlockFace.SOUTH);
			p.sendBlockChange(redstone3.getLocation(), Material.REDSTONE_WIRE, redstone3.getData());
			Block fire = redstone3.getRelative(BlockFace.WEST);
			p.sendBlockChange(fire.getLocation(), Material.FIRE, fire.getData());
		}
	}

}
