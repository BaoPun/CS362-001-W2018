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
		 assertEquals(5, testing.size());

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
		 assertEquals(null, testing);

	 }
	 @Test
	  public void mutant01()  throws Throwable  {
		Appt appt = new Appt(20, 11, 11, 9, 2011, "ANELE", "Just a Twitch Emote");
		GregorianCalendar todays = new GregorianCalendar(appt.getStartYear(),appt.getStartMonth(),appt.getStartDay());
		LinkedList<Appt> testing = new LinkedList<Appt>();
		TimeTable test = new TimeTable();
		
		assertEquals(true, appt.getValid());
		appt.setStartMinute(7);
		assertEquals(true, appt.getValid());
		testing.add(appt);
		for(int i = 0; i < 3; i++){
			testing.add(new Appt(i + 12, 5, 29, 1, 2018, "I love LoL", "reddit.com/r/leagueoflegends"));
			assertEquals(true, testing.get(i).getValid());
		}
		testing.add(new Appt(5, 20, 4, 5, 2018, "?", "ok"));
		//assertEquals(true, testing.get(testing.size() - 1).getValid());
		
		assertEquals(true, testing.get(0).getValid());
		
		assertNotEquals(null, testing);
		assertNotEquals(null, appt);
		assertNotEquals(false, appt.getValid());
		if(appt.getValid() == false)
			testing = null;
		assertNotEquals(null, testing);
		
		
		LinkedList<Appt> nol = null;
		if(nol == null)
			nol = null;
		assertEquals(null, nol);
		
		LinkedList<CalDay> tester = new LinkedList<CalDay>();
		
		
		
		/*tester = test.getApptRange(testing, todays, new GregorianCalendar(2018, 1, 30));
		assertNotEquals(null, tester);
		assertNotEquals(0, tester.size());	*/
		
	 }
//add more unit tests as you needed


	@Test
	public void mutant03() throws Throwable{
		TimeTable test = new TimeTable();
		Appt appt = new Appt(20, 11, 11, 9, 2011, "ANELE", "Just a Twitch Emote");
		GregorianCalendar todays = new GregorianCalendar(appt.getStartYear(),appt.getStartMonth(),appt.getStartDay());
		GregorianCalendar tomorrow = new GregorianCalendar(2010, 9, 11);
		assertEquals(true, tomorrow.before(todays));
		assertEquals(false, todays.before(tomorrow));
		assertNotEquals(false, tomorrow.before(todays));
		assertNotEquals(true, todays.before(tomorrow));
		LinkedList<CalDay> range = new LinkedList<CalDay>();
		
		LinkedList<Appt> testing = new LinkedList<Appt>();
		LinkedList<Appt> rip = new LinkedList<Appt>();
		testing.add(appt);
		testing.add(appt);
		testing.add(appt);
		assertEquals(3, testing.size());
		
		range = test.getApptRange(testing, tomorrow, todays);
		assertEquals(true, testing.size() > 0);
		assertEquals(true, testing.get(0).getValid());
		
		testing.get(0).setStartMinute(10);
		testing.get(2).setStartMinute(1);
		range = test.getApptRange(testing, tomorrow, todays);
		assertEquals(true, testing.get(0).getValid());
		//assertEquals(false, testing.get(1).getValid());
		assertEquals(true, testing.get(2).getValid());
		
		range = test.getApptRange(rip, tomorrow, todays);
		assertEquals(0, rip.size());
		
		int[] perm = {1, 2, 0};
		testing = test.permute(testing, perm);
		assertEquals(true, perm.length == testing.size());
		int[] perm2 = {1, 0};
		//testing = test.permute(testing, perm2);
		assertFalse(perm2.length == testing.size());
		assertEquals(true, testing.get(0).getStartYear() == testing.get(1).getStartYear());
		assertEquals(true, testing.get(0) == testing.get(1));
		
		testing.get(1).setStartMinute(1);
		testing = test.deleteAppt(testing, testing.get(1));
		assertEquals(true, testing.get(1).getValid());
		assertEquals(2, testing.size());
		assertEquals(true, testing.size() > 0);
		testing.get(0).setStartMinute(900);
		testing = test.deleteAppt(testing, testing.get(0));
		assertEquals(null, testing);
		testing = test.deleteAppt(testing, appt);
		assertTrue(testing == null);
		
	}
	
	@Test
	public void mutant04() throws Throwable{
		TimeTable test = new TimeTable();
		Appt appt = new Appt(20, 11, 11, 9, 2011, "ANELE", "Just a Twitch Emote");
		GregorianCalendar todays = new GregorianCalendar(appt.getStartYear(),appt.getStartMonth(),appt.getStartDay());
		GregorianCalendar tomorrow = new GregorianCalendar(2010, 9, 11);
		LinkedList<Appt> testing = new LinkedList<Appt>();
		
		testing.add(appt);
		testing.get(testing.size() - 1).setStartMinute(30);
		testing.add(appt);
		testing.get(testing.size() - 1).setStartMinute(30);
		
		
		LinkedList<CalDay> cow = new LinkedList<CalDay>();
		cow = test.getApptRange(testing, tomorrow, todays);
		for(int i = 0; i < testing.size(); i++)
			assertEquals(true, testing.get(i).getValid());
		assertEquals(false, todays.before(tomorrow));
		assertEquals(true, tomorrow.before(todays));
		//cow = test.getApptRange(testing, tomorrow, tomorrow);
		assertEquals(false, tomorrow.before(tomorrow));
		
		GregorianCalendar today	= new GregorianCalendar();
		assertEquals(false, appt.isRecurring());
		today = null;
	}
}
