package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	 @Test
	  public void test01()  throws Throwable  {
	 	//test the linked list initializations (add)
		 int startHour=12;
		 int startMinute=10;
		 int startDay=15;
		 int startMonth=10;
		 int startYear=2018;
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data
		 Appt appt = new Appt(startHour,
				 startMinute ,
				 startDay ,
				 startMonth ,
				 startYear ,
				 title,
				 description);
		 GregorianCalendar today = new GregorianCalendar(appt.getStartYear(),appt.getStartMonth(),appt.getStartDay());
		 CalDay test = new CalDay(today);
		 test.addAppt(appt);
		 assertEquals(2018, appt.getStartYear());
		 assertEquals(10, appt.getStartMonth());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(1, test.getSizeAppts()); //
		 for(int i = 0; i < 5; i++) //adding 5 more of the same appointments to the linked list
		 	test.addAppt(appt);
		 assertEquals(6, test.getSizeAppts()); //1 + 5 = 6, check if this is correct

	 }
	 @Test
	  public void test02()  throws Throwable  {
	 	//be able to manually remove an element from the linked list, and display the accurate size after doing so
		 Appt appt = new Appt(20, 5, 11, 9, 2011, "ANELE", "Just a Twitch Emote");
		 GregorianCalendar today = new GregorianCalendar(appt.getStartYear(),appt.getStartMonth(),appt.getStartDay());
		 CalDay test = new CalDay(today);
		 test.addAppt(appt);
		 for(int i = 0; i < 4; i++)
		 	test.addAppt(new Appt(i + 1, 5, 15, 2, i + 2010, "I love LoL", "reddit.com/r/leagueoflegends"));
		 assertEquals(5, test.getSizeAppts());
		 for(int i = 0; i < 3; i++) //remove 3 things
		 	test.getAppts().remove(test.getSizeAppts() - 1);
		 assertEquals(2, test.getSizeAppts());
	 }
//add more unit tests as you needed	
}
