package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	 @Test
	  public void test01()  throws Throwable  {
		 Appt appt = new Appt(20, 5, 11, 9, 2011, "ANELE", "Just a Twitch Emote");
		 GregorianCalendar todays = new GregorianCalendar(appt.getStartYear(),appt.getStartMonth(),appt.getStartDay());
		 LinkedList<Appt> testing = new LinkedList<Appt>();
		 testing.add(appt);
		 for(int i = 0; i < 4; i++)
			 testing.add(new Appt(i + 12, 5, 29, 1, 2018, "I love LoL", "reddit.com/r/leagueoflegends"));
		 TimeTable test = new TimeTable();
		 LinkedList<CalDay> lul = new LinkedList<CalDay>();

		 Calendar rightnow = Calendar.getInstance();
		 //current month/year/date is today
		 int thisMonth = rightnow.get(Calendar.MONTH)+1;
		 int thisYear = rightnow.get(Calendar.YEAR);
		 int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		 GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		 GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		 tomorrow.add(Calendar.DAY_OF_MONTH,1);
		 String todatDate=today.get(Calendar.MONTH)+ "/"+ today.get(Calendar.DAY_OF_MONTH)+"/"+today.get(Calendar.YEAR);
		 String tomorrowDate=tomorrow.get(Calendar.MONTH)+ "/"+ tomorrow.get(Calendar.DAY_OF_MONTH)+"/"+tomorrow.get(Calendar.YEAR);
		 lul = test.getApptRange(testing, today, tomorrow);
		 assertEquals(1, lul.size());
		 testing = test.deleteAppt(testing, appt);
		 assertEquals(0, testing.size());

	 }
	 @Test
	  public void test02()  throws Throwable  {
		 
	 }
//add more unit tests as you needed
}
