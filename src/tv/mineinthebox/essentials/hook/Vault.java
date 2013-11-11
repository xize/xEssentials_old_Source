package tv.mineinthebox.essentials.hook;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Vault {
	
	public static void desposit(Player p, Double amount) {
		RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		Economy econ = economyProvider.getProvider();
		econ.depositPlayer(p.getName(), amount);
	}

}
