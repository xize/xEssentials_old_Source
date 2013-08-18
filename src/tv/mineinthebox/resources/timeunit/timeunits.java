/*
 * 
 * TimeUnit api wroten by Xeph0re(xize) © 2013,
 * allows you to convert Milliseconds into dates and reverse dates back to milliseconds
 * and get elapsed time between dates 
 * 
 * 
 */
package tv.mineinthebox.resources.timeunit;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	@SuppressWarnings("deprecation")
	public static String getElapsedTime(Long time) {
		Date current_date = new Date(System.currentTimeMillis());
		Date newTime = new Date(time);
		String daysLeft = "" + (current_date.getDay() - newTime.getDay());
		String monthsLeft = "" + (current_date.getMonth() - newTime.getMonth());
		String yearsLeft = "" + (current_date.getYear() - newTime.getYear());
		String hoursLeft = "" + (current_date.getHours() - newTime.getHours());
		String minutesLeft = "" + (current_date.getMinutes() - newTime.getMinutes());
		String secondsLeft = "" + (current_date.getSeconds() - newTime.getSeconds());
		return "days: " + daysLeft + " months: " + monthsLeft + " years: " + yearsLeft + " hours: " + hoursLeft + " minutes: " + minutesLeft + " seconds: " + secondsLeft;
	}
	
	@SuppressWarnings("deprecation")
	public static String getElapsedTimeBetweenAnotherTime(Long time, Long otherTime) {
		Date current_date = new Date(otherTime);
		Date newTime = new Date(time);
		String daysLeft = "" + (current_date.getDay() - newTime.getDay());
		String monthsLeft = "" + (current_date.getMonth() - newTime.getMonth());
		String yearsLeft = "" + (current_date.getYear() - newTime.getYear());
		String hoursLeft = "" + (current_date.getHours() - newTime.getHours());
		String minutesLeft = "" + (current_date.getMinutes() - newTime.getMinutes());
		String secondsLeft = "" + (current_date.getSeconds() - newTime.getSeconds());
		return "days: " + daysLeft + " months: " + monthsLeft + " years: " + yearsLeft + " hours: " + hoursLeft + " minutes: " + minutesLeft + " seconds: " + secondsLeft;
	}
	
	@SuppressWarnings("deprecation")
	public static Long convertDateArguments(final String[] args) {
		Date date = new Date(System.currentTimeMillis());
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
		return date.getTime();
	}

}
