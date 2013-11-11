/*
 * 
 * TimeUnit api wroten by Xeph0re(xize) © 2013,
 * allows you to convert Milliseconds into dates and reverse dates back to milliseconds
 * and get elapsed time between dates 
 * 
 * 
 */
package tv.mineinthebox.essentials.resources.timeunit;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import tv.mineinthebox.essentials.xEssentials;
public class timeunits {

	public static Date setLongToDate(Long m) {
		Date date = new Date(m);
		return date;
	}

	public static Long setDateToLong(Date date) {
		return date.getTime();
	}

	public static boolean isOverTime(Long time) {
		Long systemTime = System.currentTimeMillis();
		if(systemTime > time) {
			return true;
		}
		return false;
	}

	public static String getElapsedTime(Long time) {
		Date current_date = new Date(System.currentTimeMillis());
		Date newTime = new Date(time);
		StringBuilder build = new StringBuilder();
		long diff = newTime.getTime() - current_date.getTime();
		int seconds = (int) Math.round(diff / 1000 % 60);
		int minutes = (int) Math.round(diff / (60 * 1000) % 60);
		int hours = (int) Math.round(diff / (60 * 60 * 1000) % 24);
		int days = (int) Math.round(diff / (24 * 60 * 60 * 1000));
		//int weeks = (int) Math.round(diff / (7 * 24 * 60 * 60 * 1000));
		int months = (int) Math.round(diff / (4 * 7 * 24 * 60 * 60 * 1000));
		int years = (int) Math.round(diff / (12 * 4 * 7 * 24 * 60 * 60 * 1000));
		if(days == 0 || days < 0) {
			build.append(0 + "days, ").toString();
		} else {
			build.append(days + "days, ").toString();
		}
		if(months == 0 || months < 0) {
			build.append(0 + "months, ").toString();
		} else {
			build.append(months + "months, ").toString();
		}
		if(years == 0 || years < 0) {
			build.append(0 + "years, ").toString();
		} else {
			build.append(years + "years, ").toString();
		}
		if(hours == 0 || hours < 0) {
			build.append(0 + "hours, ").toString();
		} else {
			build.append(hours + "hours, ").toString();
		}
		if(minutes == 0 || minutes < 0) {
			build.append(0 + "minutes, ").toString();
		} else {
			build.append(minutes + "minutes, ").toString();
		}
		if(seconds == 0 || seconds < 0) {
			build.append(0 + "seconds, ").toString();
		} else {
			build.append(seconds + "seconds, ").toString();
		}
		return build.toString();
	}

	@SuppressWarnings("deprecation")
	public static String getElapsedTimeBetweenAnotherTime(Long time, Long otherTime) {
		Date current_date = new Date(otherTime);
		Date newTime = new Date(time);
		current_date = DateUtils.addDays(newTime, newTime.getDay());
		current_date = DateUtils.addMonths(newTime, newTime.getMonth());
		current_date = DateUtils.addYears(newTime, newTime.getYear());
		current_date = DateUtils.addMinutes(newTime, newTime.getMinutes());
		current_date = DateUtils.addSeconds(newTime, newTime.getSeconds());
		return current_date.toString();
	}

	@SuppressWarnings("deprecation")
	public static Long convertDateArguments(final String[] args, final CommandSender sender) {
		Date date = new Date(System.currentTimeMillis());
		try {
		String day = null;
		String month = null;
		String year = null;
		String hour = null;
		String minute = null;
		String second = null;
		String dayReg = "(.*?)D";
		String monthReg = "(.*?)M";
		String yearReg = "(.*?)Y";
		String hourReg = "(.*?)h";
		String minuteReg = "(.*?)m";
		String secondReg = "(.*?)s";
		Pattern days = Pattern.compile(dayReg);
		Pattern months = Pattern.compile(monthReg);
		Pattern years = Pattern.compile(yearReg);
		Pattern hours = Pattern.compile(hourReg);
		Pattern minutes = Pattern.compile(minuteReg);
		Pattern seconds = Pattern.compile(secondReg);
		if(args.length > 0) {
			if(args[0].length() > 1) {
				for(int i = 1; i < args.length; i++) {
					Matcher dayMatch = days.matcher(args[i]);
					Matcher monthMatch = months.matcher(args[i]);
					Matcher yearMatch = years.matcher(args[i]);
					Matcher hourMatch = hours.matcher(args[i]);
					Matcher minuteMatch = minutes.matcher(args[i]);
					Matcher secondMatch = seconds.matcher(args[i]);
					if(dayMatch.find()) {
						day = dayMatch.group(0).replace("D", "");
						date.setDate(date.getDate() + Integer.parseInt(day));
					} else if(monthMatch.find()) {
						month = monthMatch.group(0).replace("M", "");
						date.setMonth(date.getMonth() + Integer.parseInt(month));
					} else if(yearMatch.find()) {
						year = yearMatch.group(0).replace("Y", "");
						date.setYear(date.getYear() + Integer.parseInt(year));
					} else if(hourMatch.find()) {
						hour = hourMatch.group(0).replace("h", "");
						date.setHours(date.getHours() + Integer.parseInt(hour));
					} else if(minuteMatch.find()) {
						minute = minuteMatch.group(0).replace("m", "");
						date.setMinutes(date.getMinutes() + Integer.parseInt(minute));
					} else if(secondMatch.find()) {
						second = secondMatch.group(0).replace("s", "");
						date.setSeconds(date.getSeconds() + Integer.parseInt(second));
					}
				}
			} else {
				for(int i = 0; i < args.length; i++) {
					Matcher dayMatch = days.matcher(args[i]);
					Matcher monthMatch = months.matcher(args[i]);
					Matcher yearMatch = years.matcher(args[i]);
					Matcher hourMatch = hours.matcher(args[i]);
					Matcher minuteMatch = minutes.matcher(args[i]);
					Matcher secondMatch = seconds.matcher(args[i]);
					if(dayMatch.find()) {
						day = dayMatch.group(0).replace("D", "");
						date.setDate(date.getDate() + Integer.parseInt(day));
					} else if(monthMatch.find()) {
						month = monthMatch.group(0).replace("M", "");
						date.setMonth(date.getMonth() + Integer.parseInt(month));
					} else if(yearMatch.find()) {
						year = yearMatch.group(0).replace("Y", "");
						date.setYear(date.getYear() + Integer.parseInt(year));
					} else if(hourMatch.find()) {
						hour = hourMatch.group(0).replace("h", "");
						date.setHours(date.getHours() + Integer.parseInt(hour));
					} else if(minuteMatch.find()) {
						minute = minuteMatch.group(0).replace("m", "");
						date.setMinutes(date.getMinutes() + Integer.parseInt(minute));
					} else if(secondMatch.find()) {
						second = secondMatch.group(0).replace("s", "");
						date.setSeconds(date.getSeconds() + Integer.parseInt(second));
					}
				}
			}
		}
		} catch(NumberFormatException e) { 
			Bukkit.getScheduler().scheduleSyncDelayedTask(xEssentials.getPlugin(), new Runnable() {

				@Override
				public void run() {
					sender.sendMessage(ChatColor.RED + "oops something went wrong in the time machine!, the tempban is invalid");
					sender.sendMessage(ChatColor.RED + "only the first argument could be a name others only could be called as:");
					sender.sendMessage(ChatColor.GRAY + "1D equals 1 Day, 1M equals 1 Month, 1Y equals 1 Year, 1h equals 1 Hour, 1m equals 1 minute, 1s equals 1 second");
				}
				
			}, 20);
		}
		return date.getTime();
	}

}
