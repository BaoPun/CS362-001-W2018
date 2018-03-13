package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {
		 int startHour=21;
		 int startMinute=10;
		 int startDay=15;
		 int startMonth=01;
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
	// assertions
		 assertTrue(appt.getValid()); //bugged this code so that only minute 0-10 are only valid
		 assertEquals(21, appt.getStartHour());
		 assertEquals(10, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());         		
	 }

	 @Test
	  public void test02()  throws Throwable  {
		 //bugged code: getValid only works between 0-10 minutes, so let's test outside the range
		 int startHour=21;
		 int startMinute=40; //40 minutes
		 int startDay=15;
		 int startMonth=01;
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
		 // assertions
		 assertTrue(appt.getValid()); //bugged this code so that only minute 0-10 are only valid; new edit: between 0-59 is false, anything else is true
		 assertEquals(21, appt.getStartHour());
		 assertEquals(40, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());
	 }
	//add more unit tests as you needed
		@Test
		public void test03() throws Throwable {
	 		//test the recurrence days
			int startHour=21;
			int startMinute=40; //40 minutes
			int startDay=15;
			int startMonth=01;
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
			int[] recurDaysArr={2,3,4};
			appt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
			assertEquals(Appt.RECUR_NUMBER_FOREVER, 1000);
			assertNotEquals(false, appt.isRecurring());
			assertEquals(Appt.RECUR_BY_WEEKLY, appt.getRecurBy());
			assertEquals(2, appt.getRecurIncrement());
		}

		@Test
		public void test04() throws Throwable {
	 		//test the appointment string
			int startHour=21;
			int startMinute=05;
			int startDay=15;
			int startMonth=01;
			int startYear=2018;
			String title="Midterm exam";
			String description="I have a midterm coming up soon.";
			//Construct a new Appointment object with the initial data
			Appt appt = new Appt(startHour,
					startMinute ,
					startDay ,
					startMonth ,
					startYear ,
					title,
					description);
			String s1 = appt.toString();
			assertEquals(true, s1.contains(title));
			assertNotEquals(false, s1.contains(description));
		}
		
		@Test
		public void mutant01() throws Throwable{
			int startHour=21;
			int startMinute=05;
			int startDay=15;
			int startMonth=01;
			int startYear=2018;
			String title="Midterm exam";
			String description="I have a midterm coming up soon.";
			//Construct a new Appointment object with the initial data
			Appt appt = new Appt(startHour,
					startMinute ,
					startDay ,
					startMonth ,
					startYear ,
					title,
					description);
			appt.setStartHour(startHour);
			appt.setStartMinute(startMinute);
			appt.setStartDay(startDay);
			appt.setStartMonth(startMonth);
			appt.setStartYear(startYear);
			appt.setTitle(title);
			appt.setDescription(description);
			assertTrue(appt.getValid());
			appt.setStartMinute(70);
			assertEquals(true, appt.getValid()); //from assertNotEquals to assertEquals
			appt.setStartHour(24);
			assertEquals(false, appt.getValid());
			appt.setStartHour(70);
			assertNotEquals(true, appt.getValid());
			appt.setStartDay(100);
			assertNotEquals(true, appt.getValid());
		
			/*assertEquals(0, appt.getRecurNumber());
			assertEquals(0, appt.getRecurBy());
			assertEquals(false, appt.isRecurring());
			assertNotEquals(null, appt.getRecurDays());*/
		}
		
		@Test
		public void mutant02() throws Throwable{
			Appt appt = new Appt(3,
					10,
					1 ,
					2 ,
					2000 ,
					"ok",
					"i don't know what i'm doing");
			appt.setRecurrence(null, 0, 0, 0);
			assertNotEquals(null, appt.getRecurDays());
			int [] omegalul = new int[3];
			appt.setRecurrence(omegalul, 0, 0, 0);
			assertNotEquals(null, appt.getRecurDays());
			appt.setStartHour(100);
			assertNotEquals(true, appt.getValid());
			appt.setStartHour(2);
			assertTrue(appt.getValid());
			appt.setStartDay(9001);
			assertFalse(appt.getValid());
			appt.setStartDay(13);
			assertTrue(appt.getValid());
			appt.setStartMonth(01);
			assertNotEquals(false, appt.getValid());
			appt.setStartMonth(04);
			assertTrue(appt.getValid());
			//appt.setStartYear(14);
			//assertFalse(appt.getValid());
			//appt.setStartYear(2);
			//assertTrue(appt.getValid());
			appt.setStartMinute(11); //new getValid() == false
			assertNotEquals(null, appt.toString()); //from assertEquals to assertNotEquals
			
		}
		@Test
		public void mutant03() throws Throwable{
			int startHour=21;
			int startMinute=05;
			int startDay=15;
			int startMonth=01;
			int startYear=2018;
			String title="Midterm exam";
			String description="I have a midterm coming up soon.";
			//Construct a new Appointment object with the initial data
			Appt appt = new Appt(startHour,
					startMinute ,
					startDay ,
					startMonth ,
					startYear ,
					title,
					description);
			appt.setStartMonth(12);
			assertTrue(appt.getValid());
			appt.setStartDay(35);
			appt.setStartMonth(4);
			assertFalse(appt.getValid());
			appt.setStartDay(15);
			assertTrue(appt.getValid());
			appt.setRecurrence(null, 1, 2, -1);
			assertNotEquals(null, appt.getRecurDays());
		}
		
		@Test
		public void mutant04() throws Throwable{
			int startHour=21;
			int startMinute=05;
			int startDay=15;
			int startMonth=01;
			int startYear=2018;
			String title="Midterm exam";
			String description="I have a midterm coming up soon.";
			//Construct a new Appointment object with the initial data
			Appt appt = new Appt(startHour,
					startMinute ,
					startDay ,
					startMonth ,
					startYear ,
					title,
					description);
			appt.setStartHour(0);
			assertTrue(appt.getValid());
			appt.setStartHour(24);
			assertFalse(appt.getValid());
			appt.setStartHour(23);
			assertTrue(appt.getValid());
			appt.setStartMinute(-1);
			assertTrue(appt.getValid());
			appt.setStartDay(0);
			assertTrue(appt.getValid());
			appt.setStartDay(12);
			assertTrue(appt.getValid());
			appt.setStartDay(50);
			assertTrue(appt.getValid());
			appt.setStartDay(30);
			assertTrue(appt.getValid());
			appt.setStartMonth(6);
			assertTrue(appt.getValid());
			appt.setStartMonth(1);
			assertTrue(appt.getValid());
			appt.setStartYear(2019);
			appt.setStartMonth(3);
			appt.setStartDay(29);
			assertTrue(appt.getValid());
			appt.setStartMonth(2);
			assertTrue(appt.getValid());
			appt.setStartYear(2020);
			assertTrue(appt.getValid());
		}
		
		@Test
		public void mutant05() throws Throwable{
			int startHour=21;
			int startMinute=05;
			int startDay=15;
			int startMonth=01;
			int startYear=2016;
			String title="Midterm exam";
			String description="I have a midterm coming up soon.";
			//Construct a new Appointment object with the initial data
			Appt appt = new Appt(startHour,
					startMinute ,
					startDay ,
					startMonth ,
					startYear ,
					title,
					description);
			appt.setStartDay(1);
			assertTrue(appt.getValid());
			appt.setStartDay(-1);
			assertFalse(appt.getValid());
			appt.setStartDay(28);
			appt.setStartMonth(2);
			assertTrue(appt.getValid());
			appt.setStartDay(30);
			assertFalse(appt.getValid());
			appt.setStartDay(28);
			appt.setStartYear(2017);
			assertTrue(appt.getValid());
			appt.setStartYear(2016);
			appt.setStartDay(30);
			appt.setStartMonth(3);
			assertTrue(appt.getValid());
		}
		
		@Test
		public void mutant06() throws Throwable{
			int startHour=21;
			int startMinute=05;
			int startDay=15;
			int startMonth=01;
			int startYear=2016;
			String title="Midterm exam";
			String description="I have a midterm coming up soon.";
			//Construct a new Appointment object with the initial data
			Appt appt = new Appt(startHour,
					startMinute ,
					startDay ,
					startMonth ,
					startYear ,
					title,
					description);
			appt.setStartHour(11);
			assertEquals(true, appt.toString().contains("11:5"));
			appt.setStartHour(12);
			assertEquals(true, appt.toString().contains("12:5pm"));
			assertEquals(false, appt.toString().contains("12:5am"));
			appt.setStartHour(11);
			assertEquals(false, appt.toString().contains("11:5pm"));
		}
		
		@Test
		public void mutant07() throws Throwable{
			int startHour=21;
			int startMinute=05;
			int startDay=15;
			int startMonth=01;
			int startYear=2016;
			String title="Midterm exam";
			String description="I have a midterm coming up soon.";
			//Construct a new Appointment object with the initial data
			Appt appt = new Appt(startHour,
					startMinute ,
					startDay ,
					startMonth ,
					startYear ,
					title,
					description);
			Appt compare = new Appt(7, 9, 20, 10, 2018, "Final Exam", "Get me out of here");
			
			assertEquals(-6, appt.compareTo(compare));
			assertEquals(6, compare.compareTo(appt));
		}
}
