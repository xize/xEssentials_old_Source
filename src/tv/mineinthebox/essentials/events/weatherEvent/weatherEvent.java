package tv.mineinthebox.essentials.events.weatherEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class weatherEvent implements Listener {

	@EventHandler
	public void weatherCheck(WeatherChangeEvent e) {
		if(e.toWeatherState() == true) {
			e.setCancelled(true);
		} else {
			//exit method
			return;
		}
	}

}
