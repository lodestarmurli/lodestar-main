package com.lodestar.edupath.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.lodestar.edupath.datatransferobject.util.TimeUtil;



public class ReportFilterDurationService
{

	public long getFixedFromDate(String reportPeriod)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		if (reportPeriod == null || reportPeriod.trim().isEmpty())
		{
			return cal.getTimeInMillis();
		}

		if ("Yesterday".equalsIgnoreCase(reportPeriod))
		{
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}

		else if ("CurrentWeek".equalsIgnoreCase(reportPeriod))
		{
			cal.set(Calendar.DAY_OF_WEEK, 1);
		}
		else if ("LastWeek".equalsIgnoreCase(reportPeriod))
		{
			cal.set(Calendar.DAY_OF_WEEK, 1);
			cal.add(Calendar.DAY_OF_YEAR, -7);
		}
		else if ("CurrentMonth".equalsIgnoreCase(reportPeriod))
		{
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		else if ("LastMonth".equalsIgnoreCase(reportPeriod))
		{
			cal.add(Calendar.MONTH, -1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		else if ("CurrentYear".equalsIgnoreCase(reportPeriod))
		{
			cal.set(Calendar.DAY_OF_YEAR, 1);
		}
		else if ("LastYear".equalsIgnoreCase(reportPeriod))
		{
			cal.add(Calendar.YEAR, -1);
			cal.set(Calendar.DAY_OF_YEAR, 1);
		}
		return cal.getTimeInMillis();
	}

	public long getFixedToDate(String reportPeriod)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		if (reportPeriod == null || reportPeriod.trim().isEmpty())
		{
			return cal.getTimeInMillis();
		}

		if ("Yesterday".equalsIgnoreCase(reportPeriod))
		{
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}

		else if ("CurrentWeek".equalsIgnoreCase(reportPeriod))
		{
			cal.set(Calendar.DAY_OF_WEEK, 7);
		}
		else if ("LastWeek".equalsIgnoreCase(reportPeriod))
		{
			cal.set(Calendar.DAY_OF_WEEK, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}
		else if ("CurrentMonth".equalsIgnoreCase(reportPeriod))
		{
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}
		else if ("LastMonth".equalsIgnoreCase(reportPeriod))
		{
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}
		else if ("CurrentYear".equalsIgnoreCase(reportPeriod))
		{
			cal.set(Calendar.DAY_OF_YEAR, 1);
			cal.add(Calendar.YEAR, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}
		else if ("LastYear".equalsIgnoreCase(reportPeriod))
		{
			cal.set(Calendar.DAY_OF_YEAR, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTimeInMillis();
	}

	public long getCustomFromDate(String viewBy, String selectedFromDay, String selectedtoDay, String selectedYear, String selectedMonth, String selectedWeek)
	{
		Calendar cal = Calendar.getInstance();
		if ("Day".equalsIgnoreCase(viewBy))
		{
			if (selectedFromDay != null && !selectedFromDay.trim().isEmpty())
			{
				try
				{
					cal.setTimeInMillis(TimeUtil.getDate(selectedFromDay, TimeUtil.DDMMMYYYY_FORMAT).getTime());
				}
				catch (ParseException e)
				{
					return cal.getTimeInMillis();
				}
			}
		}
		else if ("Week".equalsIgnoreCase(viewBy))
		{
			if (selectedWeek != null)
			{
				// date format- 02/18/2013:02
				String startDate = selectedWeek.split(":")[0];
				try
				{
					cal.setTimeInMillis(TimeUtil.getDate(startDate.trim(), "MM/dd/yyyy").getTime());
				}
				catch (ParseException e)
				{
					return cal.getTimeInMillis();
				}
			}
		}
		else if ("Month".equalsIgnoreCase(viewBy))
		{
			if (selectedMonth != null)
			{
				String startDate = selectedMonth.split(":")[0];
				try
				{
					cal.setTimeInMillis(TimeUtil.getDate(startDate.trim(), "MM/dd/yyyy").getTime());
				}
				catch (ParseException e)
				{
					return cal.getTimeInMillis();
				}
			}
		}
		else if ("Year".equalsIgnoreCase(viewBy))
		{
			if (selectedYear != null)
			{
				cal.set(Calendar.YEAR, Integer.parseInt(selectedYear));
				cal.set(Calendar.DAY_OF_YEAR, 1);
			}
		}
		else if ("DateRange".equalsIgnoreCase(viewBy))
		{
			if (selectedFromDay != null && !selectedFromDay.trim().isEmpty())
			{
				try
				{
					cal.setTimeInMillis(TimeUtil.getDate(selectedFromDay, TimeUtil.DDMMMYYYY_FORMAT).getTime());
				}
				catch (ParseException e)
				{
					return cal.getTimeInMillis();
				}
			}
		}

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTimeInMillis();
	}

	public long getCustomToDate(String viewBy, String selectedFromDay, String selectedtoDay, String selectedYear, String selectedMonth, String selectedWeek)
	{
		Calendar cal = Calendar.getInstance();
		if ("Day".equalsIgnoreCase(viewBy))
		{
			if (selectedtoDay != null && !selectedtoDay.trim().isEmpty())
			{
				try
				{
					cal.setTimeInMillis(TimeUtil.getDate(selectedtoDay, TimeUtil.DDMMMYYYY_FORMAT).getTime());

				}
				catch (ParseException e)
				{
					return cal.getTimeInMillis();
				}
			}
		}
		else if ("Week".equalsIgnoreCase(viewBy))
		{
			if (selectedWeek != null)
			{
				// date format- 02/18/2013:02
				String startDate = selectedWeek.split(":")[1];
				try
				{
					cal.setTimeInMillis(TimeUtil.getDate(startDate.trim(), "MM/dd/yyyy").getTime());
				}
				catch (ParseException e)
				{
					return cal.getTimeInMillis();
				}
			}
		}
		else if ("Month".equalsIgnoreCase(viewBy))
		{
			if (selectedMonth != null)
			{
				String startDate = selectedMonth.split(":")[1];
				try
				{
					cal.setTimeInMillis(TimeUtil.getDate(startDate.trim(), "MM/dd/yyyy").getTime());
				}
				catch (ParseException e)
				{
					return cal.getTimeInMillis();
				}
			}
		}
		else if ("Year".equalsIgnoreCase(viewBy))
		{
			if (selectedYear != null)
			{
				cal.set(Calendar.YEAR, Integer.parseInt(selectedYear));
				cal.set(Calendar.DAY_OF_YEAR, 1);
				cal.add(Calendar.YEAR, 1);
				cal.set(Calendar.DAY_OF_YEAR, -1);
			}
		}
		else if ("DateRange".equalsIgnoreCase(viewBy))
		{
			if (selectedtoDay != null && !selectedtoDay.trim().isEmpty())
			{
				try
				{
					cal.setTimeInMillis(TimeUtil.getDate(selectedtoDay, TimeUtil.DDMMMYYYY_FORMAT).getTime());
				}
				catch (ParseException e)
				{
					return cal.getTimeInMillis();
				}
			}
		}

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTimeInMillis();
	}

	/**
	 * @param toDate
	 * @return
	 */
	public boolean isCurrentDay(long toDate)
	{
		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTimeInMillis(toDate);
		Calendar calendar = Calendar.getInstance();
		if (toCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && toCalendar.get(Calendar.DAY_OF_YEAR) >= calendar.get(Calendar.DAY_OF_YEAR))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	public String getCustomDayString()
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat smpDateFormat = new SimpleDateFormat("dd MMM yyyy");
		return smpDateFormat.format(calendar.getTime());
	}
}
