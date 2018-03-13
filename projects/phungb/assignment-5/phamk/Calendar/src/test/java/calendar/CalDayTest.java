package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.*;


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



	@Test
	public void mutant01() throws Throwable{
		Appt appt = new Appt(20, 5, 11, 9, 2011, "ANELE", "Just a Twitch Emote"); //hour, minute, day, month, year
		GregorianCalendar today = new GregorianCalendar(appt.getStartYear(),appt.getStartMonth(),appt.getStartDay());
		CalDay test = new CalDay(today);
		assertEquals(11, test.getDay());
		assertEquals(9, test.getMonth());
		assertEquals(2011, test.getYear());
		assertEquals(0, test.getSizeAppts());
		test.addAppt(appt);
		
		Appt appt2 = new Appt(21, 8, 5, 5, 2005, "Jebaited", "Just a Twitch Emote"); //hour, minute, day, month, year
		assertEquals(false, appt.getStartHour() > appt2.getStartHour());
		assertEquals(true, appt.getStartHour() <= appt2.getStartHour());
		test.addAppt(appt2);
		assertEquals(2, test.getSizeAppts());
		assertEquals(21, test.getAppts().get(1).getStartHour());
		assertEquals(true, test.getAppts().get(test.getSizeAppts() - 1).getStartHour() == 21);
		
		Appt appt3 = new Appt(20, 5, 11, 9, 2011, "ANELE", "Just a Twitch Emote"); //hour, minute, day, month, year
		assertEquals(false, test.getAppts().get(0).getStartHour() > appt3.getStartHour());
		assertEquals(true, test.getAppts().get(test.getSizeAppts() - 1).getStartHour() > appt3.getStartHour());
		test.addAppt(appt3);
		assertEquals(20, test.getAppts().get(1).getStartHour());
		assertEquals(21, test.getAppts().get(test.getSizeAppts() - 1).getStartHour());
	}
	
	@Test
	public void mutant02() throws Throwable{
		Appt appt = new Appt(20, 5, 11, 9, 2011, "ANELE", "Just a Twitch Emote"); //hour, minute, day, month, year
		GregorianCalendar today = new GregorianCalendar(appt.getStartYear(),appt.getStartMonth(),appt.getStartDay());
		CalDay test = new CalDay(today);
		assertEquals(true, test.isValid());
		assertNotEquals(null, test.getAppts());
		assertEquals(0, test.getSizeAppts());
		
		Appt appt2 = new Appt(20, 15, 11, 9, 2011, "ANELE", "Just a Twitch Emote"); //hour, minute, day, month, year
		GregorianCalendar todays = new GregorianCalendar(appt.getStartYear(),appt.getStartMonth(),appt.getStartDay());
		CalDay test2 = new CalDay(todays);
		assertTrue(test2.isValid());
		test2.addAppt(appt);
		assertEquals(false, test2.getAppts().get(test2.getSizeAppts() - 1).getStartHour() > appt2.getStartHour());
		assertEquals(true, (test2.getAppts().get(test2.getSizeAppts() - 1).getStartHour() < appt2.getStartHour()) || (test2.getAppts().get(test2.getSizeAppts() - 1).getStartHour() == appt2.getStartHour()));
		test2.addAppt(appt2);
	}
	
	@Test
	public void mutant03() throws Throwable{
		CalDay BibleThump = new CalDay();
		assertEquals(false, BibleThump.isValid());
		assertEquals(null, BibleThump.getAppts());
		assertEquals(null, BibleThump.iterator());
		
		
		Appt appts = new Appt(20, 5, 11, 9, 2011, "ANELE", "Just a Twitch Emote"); //hour, minute, day, month, year
		GregorianCalendar today = new GregorianCalendar(appts.getStartYear(),appts.getStartMonth(),appts.getStartDay());
		CalDay test = new CalDay(today);
		assertNotEquals(null, test.getAppts());
		assertNotEquals(null, test.iterator());
		assertTrue(test.getSizeAppts() == 0);
		assertNotEquals(null, test.getAppts().iterator());
			
	}
	
	@Test
	public void mutant04() throws Throwable{
		Appt cakeday = new Appt(2, 8, 26, 2, 2018, "Happy Cakeday", "Reddit wishes me a happy birthday");
		GregorianCalendar today = new GregorianCalendar(cakeday.getStartYear(),cakeday.getStartMonth(),cakeday.getStartDay());
		CalDay test = new CalDay(today);
		test.addAppt(cakeday);
		assertNotEquals(null, test.getAppts());
		assertNotEquals(0, test.getSizeAppts());
		String hi = test.toString();
		assertEquals(true, test.isValid());
		test.addAppt(cakeday);
		assertNotEquals(false, test.getAppts().iterator().hasNext());
		
		CalDay fake = new CalDay();
		assertEquals(false, fake.isValid());
		assertEquals("", fake.toString());
		assertEquals(true, test.iterator().hasNext());
		
		LinkedList<Appt> apart = null;
		assertEquals(null, apart);
		apart = new LinkedList<Appt>();
		assertNotEquals(null, apart);
		assertEquals(0, apart.size());
	}
}
