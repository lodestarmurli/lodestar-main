package com.lodestar.edupath.vark.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.dataaccessobject.dao.school.SchoolDAO;
import com.lodestar.edupath.datatransferobject.dto.SchoolDTO;

public class LSTResultService {
	private static Logger OUT = LoggerFactory.getLogger(LSTResultService.class);

	
	public List<SchoolDTO> getAllSchools() throws Exception {
		return new SchoolDAO().getAllSchoolList();
	}
	
	public List<String> getYears() throws Exception {
		Calendar presentYear = Calendar.getInstance();
        presentYear.add(Calendar.YEAR, 1);
        List<String> listYears = new ArrayList<String>();
        String date1 = "2015";
        String date2 = Integer.toString(presentYear.get(Calendar.YEAR));

        DateFormat formater = new SimpleDateFormat("yyyy");

        Calendar beginCalendar = Calendar.getInstance();
        Calendar finishCalendar = Calendar.getInstance();

        try {
            beginCalendar.setTime(formater.parse(date1));
            finishCalendar.setTime(formater.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        while (beginCalendar.before(finishCalendar)) {
            // add one month to date per loop
            String date =     formater.format(beginCalendar.getTime());
            listYears.add(date);
            
            beginCalendar.add(Calendar.YEAR, 1);
        }
        Collections.reverse(listYears);
        OUT.debug("LSTResultService getPreviousYear: previous years from today: {} ",listYears);
        
    return listYears;
	}


}
