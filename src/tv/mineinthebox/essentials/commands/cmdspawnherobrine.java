package tv.mineinthebox.essentials.commands;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import de.kumpelblase2.remoteentities.api.DespawnReason;
import de.kumpelblase2.remoteentities.api.RemoteEntity;
import tv.mineinthebox.essentials.xEssentials;
import tv.mineinthebox.essentials.resources.npcEntitys.npcEntity;

public class cmdspawnherobrine {

	@SuppressWarnings("deprecation")
	public static boolean execute(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("spawnherobrine")) {
			if(sender.hasPermission("xEssentials.command.spawnherobrine")) {
				if(Bukkit.getPluginManager().isPluginEnabled("RemoteEntities")) {
					if(args.length == 0) {
						final Player p = (Player) sender;
						final RemoteEntity entity = npcEntity.createNpc("Herobrine", p.getLocation(), p);
						Player entityPlayer = (Player) entity.getBukkitEntity();
						entityPlayer.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
						Location loc = entityPlayer.getLocation();
						entityPlayer.getWorld().createExplosion(entityPlayer.getLocation(), 0.0F);
						loc.setY(loc.getY() + 40);
						playRespectedSound(Sound.AMBIENCE_CAVE, entityPlayer.getLocation());
						playRespectedSound(Sound.AMBIENCE_RAIN, entityPlayer.getLocation());
						playRespectedSound(Sound.ANVIL_BREAK, entityPlayer.getLocation());
						playRespectedSound(Sound.AMBIENCE_THUNDER, entityPlayer.getLocation());
						playRespectedSound(Sound.WOLF_DEATH, entityPlayer.getLocation());
						loc.setY(-1);
						entityPlayer.getWorld().strikeLightning(loc);
						Block block = entityPlayer.getLocation().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH);
						block.setTypeIdAndData(Material.SIGN_POST.getId(), (byte) 0, true);
						Sign sign = (Sign) block.getState();
						signChangeTicks(sign, entityPlayer, entity);	
					} else if(args.length == 1) {
						Player player = Bukkit.getPlayer(args[0]);
						if(player instanceof Player) {
							final RemoteEntity entity = npcEntity.createNpc("Herobrine", player.getLocation(), player);
							Player entityPlayer = (Player) entity.getBukkitEntity();
							entityPlayer.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
							Location loc = entityPlayer.getLocation();
							entityPlayer.getWorld().createExplosion(entityPlayer.getLocation(), 0.0F);
							loc.setY(loc.getY() + 40);
							playRespectedSound(Sound.AMBIENCE_CAVE, entityPlayer.getLocation());
							playRespectedSound(Sound.AMBIENCE_RAIN, entityPlayer.getLocation());
							playRespectedSound(Sound.ANVIL_BREAK, entityPlayer.getLocation());
							playRespectedSound(Sound.AMBIENCE_THUNDER, entityPlayer.getLocation());
							playRespectedSound(Sound.WOLF_DEATH, entityPlayer.getLocation());
							loc.setY(-1);
							entityPlayer.getWorld().strikeLightning(loc);
							Block block = entityPlayer.getLocation().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH);
							block.setTypeIdAndData(Material.SIGN_POST.getId(), (byte) 0, true);
							Sign sign = (Sign) block.getState();
							signChangeTicks(sign, entityPlayer, entity);
						}
					}
				} else {
					sender.sendMessage(ChatColor.RED + "RemoteEntities is not enabled!");
				}

			}
		}
		return false;
	}

	public static void signChangeTicks(final Sign sign, final Player p, final RemoteEntity e) {

		Bukkit.getScheduler().scheduleSyncDelayedTask(xEssentials.getPlugin(), new Runnable() {

			@Override
			public void run() {
				sign.setLine(0, "I saw you");
				sign.update();
			}

		}, 30);

		Bukkit.getScheduler().scheduleSyncDelayedTask(xEssentials.getPlugin(), new Runnable() {

			@Override
			public void run() {
				sign.setLine(0, "steve thought");
				sign.setLine(1, "I whas death");
				sign.update();
			}

		}, 50);

		Bukkit.getScheduler().scheduleSyncDelayedTask(xEssentials.getPlugin(), new Runnable() {

			@Override
			public void run() {
				sign.setLine(0, "look under you.");
				sign.setLine(1, "");
				sign.update();
			}

		}, 70);

		Bukkit.getScheduler().scheduleSyncDelayedTask(xEssentials.getPlugin(), new Runnable() {

			@Override
			public void run() {
				sign.setLine(0, "die! >:)");
				sign.setLine(1, "");
				sign.update();
				createHole(getReadFaceBlock(sign));
				p.chat("Byee your xray is bad...");
				e.despawn(DespawnReason.CHUNK_UNLOAD);
			}

		}, 90);
	}

	public static HashMap<Location, MaterialData> blocks = new HashMap<Location, MaterialData>();

	public static void createHole(Block block) {
		Block b0 = block.getRelative(BlockFace.DOWN);
		Block b1 = b0.getRelative(BlockFace.DOWN);
		Block b2 = b1.getRelative(BlockFace.DOWN);
		Block b3 = b2.getRelative(BlockFace.DOWN);
		Block b4 = b3.getRelative(BlockFace.DOWN);
		Block b5 = b4.getRelative(BlockFace.DOWN);
		Block b6 = b5.getRelative(BlockFace.DOWN);
		Block b7 = b6.getRelative(BlockFace.DOWN);
		Block b8 = b7.getRelative(BlockFace.DOWN);
		Block b9 = b8.getRelative(BlockFace.DOWN);

		blocks.put(b0.getLocation(), b0.getState().getData());
		blocks.put(b1.getLocation(), b1.getState().getData());
		blocks.put(b2.getLocation(), b2.getState().getData());
		blocks.put(b3.getLocation(), b3.getState().getData());
		blocks.put(b4.getLocation(), b4.getState().getData());
		blocks.put(b5.getLocation(), b5.getState().getData());
		blocks.put(b6.getLocation(), b6.getState().getData());
		blocks.put(b7.getLocation(), b7.getState().getData());
		blocks.put(b8.getLocation(), b8.getState().getData());
		blocks.put(b9.getLocation(), b9.getState().getData());


	}



	public static Block getReadFaceBlock(Sign sign) {

		org.bukkit.material.Sign signData = (org.bukkit.material.Sign) sign.getData();

		switch(signData.getFacing()) {
		case DOWN:
			Block blockDown = sign.getBlock().getRelative(BlockFace.DOWN);
			return blockDown;
		case EAST:
			Block blockEast = sign.getBlock().getRelative(BlockFace.EAST);
			return blockEast;
		case EAST_NORTH_EAST:
			Block blockNorthEast = sign.getBlock().getRelative(BlockFace.NORTH_EAST);
			return blockNorthEast;
		case EAST_SOUTH_EAST:
			Block blockEastSouthEast = sign.getBlock().getRelative(BlockFace.EAST_SOUTH_EAST);
			return blockEastSouthEast;
		case NORTH:
			Block blockNorth = sign.getBlock().getRelative(BlockFace.NORTH);
			return blockNorth;
		case NORTH_NORTH_EAST:
			Block blockNorthNorthEast = sign.getBlock().getRelative(BlockFace.NORTH_NORTH_EAST);
			return blockNorthNorthEast;
		case NORTH_NORTH_WEST:
			Block blockNorthNorthWest = sign.getBlock().getRelative(BlockFace.NORTH_NORTH_WEST);
			return blockNorthNorthWest;
		case NORTH_WEST:
			Block blockNorthWest = sign.getBlock().getRelative(BlockFace.NORTH_WEST);
			return blockNorthWest;
		case SELF:
			Block blockSelf = sign.getBlock().getRelative(BlockFace.SELF);
			return blockSelf;
		case SOUTH:
			Block blockSouth = sign.getBlock().getRelative(BlockFace.SOUTH);
			return blockSouth;
		case SOUTH_EAST:
			Block blockSouthEast = sign.getBlock().getRelative(BlockFace.SOUTH_EAST);
			return blockSouthEast;
		case SOUTH_SOUTH_EAST:
			Block blockSouthSouthEast = sign.getBlock().getRelative(BlockFace.SOUTH_SOUTH_EAST);
			return blockSouthSouthEast;
		case SOUTH_SOUTH_WEST:
			Block blockSouthSouthWest = sign.getBlock().getRelative(BlockFace.SOUTH_SOUTH_WEST);
			return blockSouthSouthWest;
		case SOUTH_WEST:
			Block blockSouthWest = sign.getBlock().getRelative(BlockFace.SOUTH_WEST);
			return blockSouthWest;
		case UP:
			Block blockUp = sign.getBlock().getRelative(BlockFace.UP);
			return blockUp;
		case WEST:
			Block blockWest = sign.getBlock().getRelative(BlockFace.WEST);
			return blockWest;
		case WEST_NORTH_WEST:
			Block blockWestNorthWest = sign.getBlock().getRelative(BlockFace.WEST_NORTH_WEST);
			return blockWestNorthWest;
		case WEST_SOUTH_WEST:
			Block blockWestSouthWest = sign.getBlock().getRelative(BlockFace.WEST_SOUTH_WEST);
			return blockWestSouthWest;
		default:
			Block blockDefault = sign.getBlock().getRelative(BlockFace.SELF);
			return blockDefault;
		}
	}

	public static void startHerobrineTask() {
		Bukkit.getScheduler().runTaskTimer(xEssentials.getPlugin(), new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				if(!blocks.isEmpty()) {
					Iterator<Entry<Location, MaterialData>> it = blocks.entrySet().iterator();
					it.hasNext();
					Map.Entry<Location, MaterialData> its = (Map.Entry<Location, MaterialData>) it.next();
					Location loca = (Location) its.getKey();
					MaterialData blockType = (MaterialData) its.getValue();
					loca.getBlock().setTypeId(blockType.getItemTypeId());
					loca.getBlock().setData(blockType.getData());
					loca.getBlock().getWorld().playEffect(loca.getBlock().getLocation(), Effect.STEP_SOUND, loca.getBlock().getType());
					loca.getBlock().setType(Material.AIR);
					it.remove();
					blocks.remove(loca);
				}
			}

		}, 0, 5);
	}

	public static void playRespectedSound(final Sound sound, final Location loc) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(xEssentials.getPlugin(), new Runnable() {

			@Override
			public void run() {
				loc.getWorld().playSound(loc, sound, 3, 3);
			}

		}, 15);
	}
}