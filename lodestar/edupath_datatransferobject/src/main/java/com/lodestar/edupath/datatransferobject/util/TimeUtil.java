package com.lodestar.edupath.datatransferobject.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtil
{
	private static final Logger			out									= LoggerFactory.getLogger(TimeUtil.class);

	public static final String			DISPLAY_DATE_FORMAT					= "dd-MMM-yyyy HH:mm:ss";

	public static final String			REPORT_DATE_FORMAT					= "dd-MMM-yyyy HH:mm";

	public static final String			QUERY_DATE_FORMAT					= "yyyy-MM-dd HH:mm:ss";

	public static final String			DDMMMYYYY_FORMAT					= "dd MMM yyyy";

	public static final String			DISPLAY_DATE_YEAR_FORMAT			= "dd-MMM-yyyy";

	public static final String			DISPLAY_DAY_FORMAT					= "yyyy-MM-dd";

	public static final String			DISPLAY_DATE_MONTH_FORMAT			= "dd MMM";

	public static final String			DISPLAY_MONTH_FORMAT				= "MMM";

	public static final String			FILTER_DATE_FORMAT					= "MM/dd/yyyy";

	public static final String			FILTER_DATE_TIME_FORMAT				= "dd/MM/yyyy HH:mm";

	public static final String			FILTER_DATE_DATE_MONTH_YEAR_FORMAT	= "dd/MM/yyyy";

	public static final String			FILE_TIME_FORMAT					= "yyyy_MM_dd_HH_mm_ss";

	public static final String			AM_PM_REPORT_FORMAT					= "dd-MM-yyyy hh:mm a";

	public static final String			DISPLAY_DATE_MONTH_YEAR_FORMAT		= "dd-MM-yyyy";

	private static final DecimalFormat	format								= new DecimalFormat("#.##");

	public static final String			AM_PM_FORMAT						= "dd MMMMM yyyy hh:mm a";

	public static final String			EXCEL_DATE_FORMAT					= "yyyy-MM-dd HH:mm:ss";

	public static final String			AM_PM								= "hh:mm a";

	public static final String			EMAIL_FORMAT						= "dd MMMMM yyyy";
	public static final String			SUMMARY_REPORT_FORMAT				= "MMMMM yyyy";

	public static long getTime(String timeZoneId)
	{
		Calendar now = new GregorianCalendar(TimeZone.getTimeZone(timeZoneId));
		return now.getTimeInMillis();
	}

	/**
	 * @param timeInMills
	 * @return
	 */
	public static String convertTimeAsDisplayString(long timeInMills)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DDMMMYYYY_FORMAT);
		String dateFormat = simpleDateFormat.format(new Date(timeInMills));
		return dateFormat;
	}

	/**
	 * @param timeInMills
	 * @return
	 */
	public static String convertTimeAsString(long timeInMills)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DISPLAY_DATE_FORMAT);
		String dateFormat = simpleDateFormat.format(new Date(timeInMills));
		return dateFormat;
	}

	/**
	 * @param timeInMills
	 * @return
	 */
	public static String convertTimeAsStringDayFormat(long timeInMills)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DISPLAY_DAY_FORMAT);
		String dateFormat = simpleDateFormat.format(new Date(timeInMills));
		return dateFormat;
	}

	/**
	 * @param timeInMills
	 * @return
	 */
	public static String convertTimeAsString(long timeInMills, String dateFormat)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		String dateString = simpleDateFormat.format(new Date(timeInMills));
		return dateString;
	}

	public static String convertTimeAsString(Date date, String dateFormat)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		String dateString = simpleDateFormat.format(date);
		return dateString;
	}

	/**
	 * @param userTimestamp
	 * @return
	 */
	public static Timestamp getServerTime(Timestamp userTimestamp)
	{
		TimeZone serverTimeZone = Calendar.getInstance().getTimeZone();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(QUERY_DATE_FORMAT);
		String dateFormat = simpleDateFormat.format(new Date(userTimestamp.getTime()));
		simpleDateFormat.setTimeZone(serverTimeZone);
		try
		{
			Date parsedDate = simpleDateFormat.parse(dateFormat);
			return new Timestamp(parsedDate.getTime());
		}
		catch (ParseException e)
		{
			out.error("Exception ", e);
		}
		return userTimestamp;
	}

	/**
	 * @param dateAsString
	 * @return
	 */
	public static String getServerTime(String dateAsString)
	{
		TimeZone serverTimeZone = Calendar.getInstance().getTimeZone();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DISPLAY_DATE_FORMAT);
		simpleDateFormat.setTimeZone(serverTimeZone);
		try
		{
			Date parsedDate = simpleDateFormat.parse(dateAsString);
			simpleDateFormat = new SimpleDateFormat(QUERY_DATE_FORMAT);
			return simpleDateFormat.format(parsedDate);
		}
		catch (ParseException e)
		{
			out.error("Exception ", e);
		}
		return null;
	}

	/**
	 * @param userTimezone
	 * @param serverTimestamp
	 * @return
	 */
	public static Timestamp getUserTime(TimeZone userTimezone, Timestamp serverTimestamp)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DISPLAY_DATE_FORMAT);
		String dateFormat = simpleDateFormat.format(new Date(serverTimestamp.getTime()));
		simpleDateFormat.setTimeZone(userTimezone);
		try
		{
			Date parsedDate = simpleDateFormat.parse(dateFormat);
			return new Timestamp(parsedDate.getTime());
		}
		catch (ParseException e)
		{
			out.error("Exception ", e);
		}
		return serverTimestamp;
	}

	/**
	 * @param userTimezone
	 * @param serverTimestamp
	 * @return
	 */
	public static String getUserTimeAsString(TimeZone userTimezone, Timestamp serverTimestamp)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DISPLAY_DATE_FORMAT);
		String dateFormat = simpleDateFormat.format(new Date(serverTimestamp.getTime()));
		simpleDateFormat.setTimeZone(userTimezone);
		try
		{
			Date parsedDate = simpleDateFormat.parse(dateFormat);
			return simpleDateFormat.format(parsedDate);
		}
		catch (ParseException e)
		{
			out.error("Exception ", e);
		}
		return null;
	}

	public static Date getDate(String stringDate, String format) throws ParseException
	{
		return (new SimpleDateFormat(format)).parse(stringDate);

	}

	public static boolean isValidFormat(String stringDate, String format)
	{
		Date date = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(stringDate);
			if (!stringDate.equals(sdf.format(date)))
			{
				date = null;
			}
		}
		catch (ParseException ex)
		{
			ex.printStackTrace();
		}
		return date != null;
	}

	/**
	 * @param timeInMills
	 * @return
	 */
	public static String convertDateAsString(long timeInMills)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DISPLAY_DATE_YEAR_FORMAT);
		String dateFormat = simpleDateFormat.format(new Date(timeInMills));
		return dateFormat;
	}

	public static String getDateFromMillis(long dateTime, String format)
	{
		DateFormat formatter = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateTime);
		return formatter.format(calendar.getTime());
	}

	public static String formatTime(long milli)
	{
		long millisec = milli;
		millisec = 1000 * Math.round(millisec / 1000.0);
		int hours = (int) (millisec / (1000 * 60 * 60));
		millisec = millisec % (1000 * 60 * 60);
		int minutes = (int) (millisec / (1000 * 60));
		millisec = millisec % (1000 * 60);
		int seconds = (int) (millisec / 1000);
		String sec = replaceToPrecedingZero(seconds);
		String min = replaceToPrecedingZero(minutes);
		String hr = replaceToPrecedingZero(hours);
		return hr + " : " + min + (seconds > 0 ? " : " + sec : "");
	}

	public static String formatTimeDouble(double milli)
	{
		double millisec = milli;
		millisec = 1000 * Math.round(millisec / 1000.0);
		int hours = (int) (millisec / (1000 * 60 * 60));
		millisec = millisec % (1000 * 60 * 60);
		int minutes = (int) (millisec / (1000 * 60));
		millisec = millisec % (1000 * 60);
		int seconds = (int) (millisec / 1000);
		String sec = replaceToPrecedingZero(seconds);
		String min = replaceToPrecedingZero(minutes);
		String hr = replaceToPrecedingZero(hours);
		return hr + " : " + min + " : " + sec;
	}

	public static String formatTimeInDays(double milli)
	{
		double millisec = milli;
		int days = 0;
		millisec = 1000 * Math.round(millisec / 1000.0);
		int hours = (int) (millisec / (1000 * 60 * 60));
		if (hours > 23)
		{
			days = hours / 24;
			hours = hours % 24;
		}
		millisec = millisec % (1000 * 60 * 60);
		int minutes = (int) (millisec / (1000 * 60));
		millisec = millisec % (1000 * 60);
		int seconds = (int) (millisec / 1000);
		String sec = replaceToPrecedingZero(seconds);
		String min = replaceToPrecedingZero(minutes);
		String hr = replaceToPrecedingZero(hours);
		String day = days + "";
		if (days > 0)
			return day + "d " + hr + " : " + min + " : " + sec;
		return hr + " : " + min + " : " + sec;
	}

	public static String replaceToPrecedingZero(int value)
	{
		String replaced = null;
		if (value < 10)
			replaced = "0" + value;
		else
			replaced = "" + value;
		return replaced;
	}

	public static void main(String[] args) throws ParseException
	{
		// TimeUtil.getTime("America/Los_Angeles");
		// String s = getDateFromMillis(System.currentTimeMillis(), QUERY_DATE_FORMAT);
		// String dat = getFromDate(s, QUERY_DATE_FORMAT);
		// SimpleDateFormat reportDateFormat = new SimpleDateFormat("hh:mm a");
		// System.out.println(reportDateFormat.format(new Date()));
		// System.out.println(new Date());
	}

	public static String getDateInString(String oldDate, String oldFormat, String newFormat)
	{
		String dateInString = "";
		Date newDate;

		try
		{
			newDate = new SimpleDateFormat(oldFormat).parse(oldDate);
			dateInString = new SimpleDateFormat(newFormat).format(newDate);
		}
		catch (ParseException e)
		{
			out.error("Exception ", e);
		}
		return dateInString;
	}

	public static int daysInMonth(long timeInMillis)
	{
		Calendar c = Calendar.getInstance(); // new GregorianCalendar();
		c.setTimeInMillis(timeInMillis);
		int year = c.get(Calendar.YEAR);
		int[] daysInMonths =
		{
				31,
				28,
				31,
				30,
				31,
				30,
				31,
				31,
				30,
				31,
				30,
				31
		};
		daysInMonths[1] += isLeapYear(year) ? 1 : 0;
		return daysInMonths[c.get(Calendar.MONTH)];
	}

	public static String formatTimeToStringInDays(double milli)
	{
		return formatTimeToStringInDays(milli, true);
	}

	public static String formatTimeToStringInMinutes(double milli)
	{
		return format.format(milli / (60 * 1000));
	}

	public static String formatTimeToStringInDays(double milli, boolean allowSeconds)
	{
		double millisec = milli;
		int days = 0;
		millisec = 1000 * Math.round(millisec / 1000.0);
		int hours = (int) (millisec / (1000 * 60 * 60));
		if (hours > 23)
		{
			days = hours / 24;
			hours = hours % 24;
		}
		millisec = millisec % (1000 * 60 * 60);
		int minutes = (int) (millisec / (1000 * 60));
		millisec = millisec % (1000 * 60);
		int seconds = (int) (millisec / 1000);
		String sec = replaceToPrecedingZero(seconds);
		String min = replaceToPrecedingZero(minutes);
		String hr = replaceToPrecedingZero(hours);
		String day = days + "";
		if (days > 0)
			return day + "d " + hr + "h " + min + "m " + (allowSeconds ? (sec + "s ") : "");
		else if (hours > 0)
			return hours + "h " + min + "m " + (allowSeconds ? (sec + "s ") : "");
		else if (minutes > 0)
			return min + "m " + (allowSeconds ? (sec + "s ") : "");
		return (allowSeconds ? (sec + "s ") : "");
	}

	// Utility Method to find whether an Year is a Leap year or Not
	public static boolean isLeapYear(int year)
	{
		if ((year % 100 != 0) && (year % 400 == 0))
		{
			return true;
		}
		return false;
	}

	public static double getTotalHours(double milli)
	{

		double millisec = milli;
		millisec = 1000 * Math.round(millisec / 1000.0);
		double hours = (millisec / (1000 * 60 * 60));
		return hours;
	}

	public static double getTotalMenutes(double milli)
	{

		double millisec = milli;
		millisec = 1000 * Math.round(millisec / 1000.0);
		double hours = (millisec / (1000 * 60));
		return hours;
	}

	/**
	 * @param timeAsString
	 * @return
	 */
	public static long convertStringTimeToLong(String timeAsString)
	{
		String[] time = timeAsString.split(":");
		long days = TimeUnit.MILLISECONDS.convert(Integer.parseInt(time[0]), TimeUnit.DAYS);
		long hours = TimeUnit.MILLISECONDS.convert(Integer.parseInt(time[1]), TimeUnit.HOURS);
		long minutes = TimeUnit.MILLISECONDS.convert(Integer.parseInt(time[2]), TimeUnit.MINUTES);
		long duration = days + hours + minutes;
		return duration;
	}

	/**
	 * @param timeInString
	 * @param dateFormat
	 * @return
	 */
	public static long convertStringAsTime(String timeInString, String dateFormat)
	{
		Date dateString = null;
		try
		{
			SimpleDateFormat reportDateFormat = new SimpleDateFormat(dateFormat);
			dateString = reportDateFormat.parse(timeInString);
			return dateString.getTime();
		}
		catch (ParseException e)
		{
			out.error("Exception", e);
		}
		return -1;
	}

	/**
	 * This method will convert the given Time to User Time according to offset.
	 * Use this method while fetching data from DB
	 * 
	 * @param timeInMillies
	 * @param offset
	 * @return
	 */
	public static long getFetchTimeForTimezone(long timeInMillies, String offset)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeInMillies);
		long convertedMillies = timeInMillies;
		if (offset == null || "".equals(offset))
		{
			return convertedMillies;
		}
		try
		{
			String[] offsetArray = offset.split(":");
			String hourStr = offsetArray[0];
			String minStr = offsetArray[1];
			if (hourStr.contains("+"))
			{
				hourStr = hourStr.substring(1, hourStr.length());
			}
			int offsetHour = Integer.parseInt(hourStr);
			int offsetMinute = Integer.parseInt(minStr);
			if (offsetHour < 0)
			{
				offsetMinute = -offsetMinute;
			}
			cal.add(Calendar.HOUR_OF_DAY, offsetHour);
			cal.add(Calendar.MINUTE, offsetMinute);
			convertedMillies = cal.getTimeInMillis();
		}
		catch (Exception e)
		{
			out.error("Exception", e);
			return convertedMillies;
		}
		return convertedMillies;
	}

	/**
	 * This method will convert the given Time to Server time according to offset
	 * Use this method while Inserting to DB Or for Query the data fromDB
	 * 
	 * @param timeInMillies
	 * @param offset
	 * @return
	 */
	public static long getInsertTimeForTimezone(long timeInMillies, String offset)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeInMillies);
		long convertedMillies = timeInMillies;
		if (offset == null || "".equals(offset))
		{
			return convertedMillies;
		}
		try
		{
			String[] offsetArray = offset.split(":");
			String hourStr = offsetArray[0];
			String minStr = offsetArray[1];
			if (hourStr.contains("+"))
			{
				hourStr = hourStr.substring(1, hourStr.length());
			}
			int offsetHour = Integer.parseInt(hourStr);
			int offsetMinute = Integer.parseInt(minStr);

			// convert the hours to positive minute is always stored as positive
			if (offsetHour < 0)
			{
				offsetHour = Math.abs(offsetHour);
			}
			else
			// if hours is positive convert the hours and minutes to negative
			{
				offsetHour = -offsetHour;
				offsetMinute = -offsetMinute;
			}
			cal.add(Calendar.HOUR_OF_DAY, offsetHour);
			cal.add(Calendar.MINUTE, offsetMinute);
			convertedMillies = cal.getTimeInMillis();
		}
		catch (Exception e)
		{
			out.error("Exception", e);
			return convertedMillies;
		}
		return convertedMillies;
	}

	/**
	 * This method will give the Current time for the specified timezone
	 * 
	 * @param offset
	 * @return
	 */
	public static long getTimeZoneCurrentTime(String offset)
	{
		long serverTimeMillies = System.currentTimeMillis();
		long timeZoneCurrentTime = getFetchTimeForTimezone(serverTimeMillies, offset);
		return timeZoneCurrentTime;
	}

	/**
	 * @param dateAsString
	 * @return
	 */
	public static Date getDate(String dateAsString)
	{
		if (dateAsString == null || dateAsString.trim().isEmpty())
			return null;
		List<SimpleDateFormat> simpleDateFormatList = new ArrayList<SimpleDateFormat>();

		simpleDateFormatList.add(new SimpleDateFormat("yyyy-MM-dd"));
		simpleDateFormatList.add(new SimpleDateFormat("dd MMM yyyy"));
		try
		{
			for (SimpleDateFormat simpleDateFormat : simpleDateFormatList)
			{
				try
				{
					Date parsedDate = simpleDateFormat.parse(dateAsString);
					return parsedDate;
				}
				catch (Exception e)
				{
					out.warn("Worn Date Format: " + simpleDateFormat.getDateFormatSymbols());
				}
			}
		}
		catch (Exception e)
		{
			out.error("Exception ", e);
		}
		return null;
	}

	public static int getNumDaysFromTimeAsInt(long millisec)
	{
		int days = 0;
		millisec = 1000 * Math.round(millisec / 1000.0);
		int hours = (int) (millisec / (1000 * 60 * 60));
		if (hours > 23)
		{
			days = hours / 24;
		}
		return days;
	}

	public static String getFromDate(String strFromData, String format) throws ParseException
	{
		DateFormat formatter = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		Date parsedDate = formatter.parse(strFromData);
		parsedDate.setHours(00);
		parsedDate.setMinutes(00);
		parsedDate.setSeconds(00);
		calendar.setTime(parsedDate);
		return formatter.format(calendar.getTime());
	}

	public static String getToDate(String strFromData, String format) throws ParseException
	{
		DateFormat formatter = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		Date parsedDate = formatter.parse(strFromData);
		parsedDate.setHours(23);
		parsedDate.setMinutes(59);
		parsedDate.setSeconds(59);
		calendar.setTime(parsedDate);
		return formatter.format(calendar.getTime());
	}

	public static String getAMOrPM(Date date, String format)
	{
		DateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * @param date
	 * @return
	 */
	public static String convertDateToString(Date date, String dateFormat)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DATE);

		if (!((day > 10) && (day < 19)))
			switch (day % 10)
			{
				case 1:
					return new SimpleDateFormat("d'st' " + dateFormat).format(date);
				case 2:
					return new SimpleDateFormat("d'nd' " + dateFormat).format(date);
				case 3:
					return new SimpleDateFormat("d'rd' " + dateFormat).format(date);
				default:
					return new SimpleDateFormat("d'th' " + dateFormat).format(date);
			}
		return new SimpleDateFormat("d'th' " + dateFormat).format(date);
	}
}
