package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;



import static org.junit.Assert.*;



/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"setTitle","setRecurrence"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
        }
	
		
	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur 
        }	
	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur 
        }	
   /**
     * Generate Random Tests that tests Appt Class.
     */
	 /*@Test
	  public void radnomtest()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
	//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);
				
				int startHour=ValuesGenerator.RandInt(random);
				int startMinute=ValuesGenerator.RandInt(random);
				int startDay=ValuesGenerator.RandInt(random);;
				int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				int startYear=ValuesGenerator.RandInt(random);
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
				if(!appt.getValid())continue;
				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);
						if (methodName.equals("setTitle")){
						   String newTitle=(String) ValuesGenerator.getString(random);
						   appt.setTitle(newTitle);						   
						}
						else if (methodName.equals("setRecurrence")){
						   int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
						   int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
						   int recur=ApptRandomTest.RandomSelectRecur(random);
						   int recurIncrement = ValuesGenerator.RandInt(random);
						   int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
						   appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
						}				
				}
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			    if((iteration%10000)==0 && iteration!=0 )
					System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
		}
		catch(NullPointerException e){
			
		}
	}*/
	 //test isValid() and setRecurDays(int[] recurDays)
	 @Test
	 public void random01() throws Throwable{
		//long randomseed =System.currentTimeMillis();
		Random random = new Random();
		String title="Birthday Party";
		String description="This is my birthday party.";
		//Construct a new Appointment object with the initial data	 
		Appt appt = new Appt(3, 9, 10, 2, 2017, title, description);
		assertEquals(true, appt.getValid());
		Random r = new Random();
		for(int i = 0; i < 1000; i++){
			appt.setStartHour(100);
			appt.setStartMinute(100);
			appt.setStartDay(1);
			appt.setStartMonth(12);
			assertEquals(false, appt.getValid());
			int randMinute = r.nextInt(60); //0-59, for bug, only 0-10 is true
			appt.setStartMinute(randMinute);
			if(appt.getStartMinute() > 10) 
				assertEquals(false, appt.getValid()); 
			else{
				assertTrue(appt.getStartMinute() >= 0 && appt.getStartMinute() <= 10);
				int randHour = r.nextInt(51); //0 to 50, only 0-23 is true
				appt.setStartHour(randHour);
				if(randHour > 23)
					assertEquals(false, appt.getValid());
				else{
					assertEquals(true, appt.getValid());
					assertTrue(appt.getStartHour() >= 0 && appt.getStartHour() <= 23);
					int randMonth = r.nextInt(50) + 1; //1-50, for bug, only 1-12 is true 
					if(randMonth > 12){
						randMonth = 12;
						appt.setStartMonth(12);
						int randYear = r.nextInt(5) + 2016; //2016 and 2020 are leap years, but bug makes those 2 years NOT leap, rest are
						appt.setStartYear(randYear);
						int randDay = r.nextInt(5) + 28;  //only choose between 28 and 32, to determine max # of days per month
						appt.setStartDay(randDay);
						//to account for bug on leap year: (NOT %100 AND NOT %400) OR NOT %4
						//also, if a month follows divisibility above, then check if month is NOT February
						//if 2 conditions above follow, then add maximum day by 1
						//31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
						//leap year: 32, 28, 32, 31, 32, 31, 32, 32, 31, 32, 31, 32
						if(randYear != 2016 && randYear != 2020) //bug: non-leap year, always on month 12
							assertEquals(true, appt.getValid());
						else{ //leap year otherwise
							if(randDay <= 31)
								assertEquals(true, appt.getValid());
						}
					}
					else{
						appt.setStartMonth(randMonth);
						assertTrue(appt.getStartMonth() >= 1 && appt.getStartMonth() <= 12);
						int randYear = r.nextInt(5) + 2016; //2016 and 2020 are leap years, but bug makes those 2 years NOT leap, rest are
						appt.setStartYear(randYear);
						int randDay = r.nextInt(5) + 28;  //only choose between 28 and 32, to determine max # of days per month
						appt.setStartDay(randDay);
						//to account for bug on leap year: (NOT %100 AND NOT %400) OR NOT %4
						//also, if a month follows divisibility above, then check if month is NOT February
						//if 2 conditions above follow, then add maximum day by 1
						//31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
						//leap year: 32, 28, 32, 31, 32, 31, 32, 32, 31, 32, 31, 32
						if(randYear != 2016 && randYear != 2020){ //bug: non-leap year
							if(randMonth == 2){
								if(randDay > 28)
									assertEquals(false, appt.getValid());
								else
									assertEquals(true, appt.getValid());
							}
							else if(randMonth == 1 || randMonth == 3 || randMonth == 5 || randMonth == 7 || randMonth == 8 || randMonth == 10 || randMonth == 12)
								assertEquals(true, appt.getValid());
							else{
								if(randDay <= 31)
									assertEquals(true, appt.getValid());
								else
									assertEquals(false, appt.getValid());
							}
						}
						else{ //leap year otherwise
							if(randMonth == 2){
								if(randDay > 28)
									assertEquals(false, appt.getValid());
								else
									assertEquals(true, appt.getValid());
							}
							else if(randMonth == 1 || randMonth == 3 || randMonth == 5 || randMonth == 7 || randMonth == 8 || randMonth == 10 || randMonth == 12){
								if(randDay <= 31)
									assertEquals(true, appt.getValid());
							}
							else{
								if(randDay > 30)
									assertEquals(false, appt.getValid());
								else
									assertEquals(true, appt.getValid());
							}
						}
					}
				}
			}
		}
		
	 }
	
}
