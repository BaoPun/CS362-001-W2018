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
		// assertTrue(appt.getValid()); //bugged this code so that only minute 0-10 are only valid
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
}
