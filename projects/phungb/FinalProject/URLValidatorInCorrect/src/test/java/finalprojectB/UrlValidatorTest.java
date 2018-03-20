
package finalprojectB;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase{


	public UrlValidatorTest(String testName){
		super(testName);
	}

	public void testManualTest(){
		//You can use this function to implement your manual testing	   
		String[] urlSamples = {"http", "htp", "htpt", "https"};
		UrlValidator urlVal = new UrlValidator(urlSamples, UrlValidator.ALLOW_ALL_SCHEMES);
		assertTrue(urlVal.isValid("http://www.reddit.com/read"));
		assertTrue(urlVal.isValid("http://google.com/read"));
		assertTrue(urlVal.isValid("http://TSMTSMTSMTSM.nets/read"));
		assertFalse(urlVal.isValid("http://wwff.dfasdfdsa.com/fdsafdsf/"));
		assertFalse(urlVal.isValid("http://www.reddit.io/r/leagueoflegends"));
		assertFalse(urlVal.isValid("https:/www.reddit.com"));
		assertFalse(urlVal.isValid("htp:/www.google.io"));
		assertFalse(urlVal.isValid("htp:/google.com"));
		assertFalse(urlVal.isValid("htpt:/www.google.com"));
		assertFalse(urlVal.isValid("htpt:/google.com"));

		assertTrue(urlVal.isValid("http://www.reddit.com/r")); //WORKS WITH ONLY ONE / after .com
	}

	public void testYourFirstPartition(){
		//first partition based on starting http, htp, etc.
		String[] urlSamples = {"http", "hp"};
		String[] no = null;
		UrlValidator urlVal = new UrlValidator(urlSamples, UrlValidator.ALLOW_ALL_SCHEMES);
		assertTrue(urlVal.isValid("http://"));
		assertFalse(urlVal.isValid("hp:/")); //fail this
		assertFalse(urlVal.isValid("hp"));
		assertFalse(urlVal.isValid("http/"));
		assertFalse(urlVal.isValid("https:/")); //bug: this should at least pass, but doesn't
	}

	public void testYourSecondPartition(){
		//second partition gets the rest of the website contents: http://www.google.com is a valid URL for example
		String[] urlSamples = {"http", "hp"};
		UrlValidator urlVal = new UrlValidator(urlSamples, UrlValidator.ALLOW_ALL_SCHEMES);
		assertTrue(urlVal.isValid(urlSamples[0] + "://www.reddit.com"));
		assertTrue(urlVal.isValid(urlSamples[0] + "://www.google.com/SEARCH"));
		assertFalse(urlVal.isValid(urlSamples[1] + ":/www.goofy.com/r/ffff"));
		assertFalse(urlVal.isValid(urlSamples[0] + ":/google.com/reddit/leagueoflegends"));
		assertFalse(urlVal.isValid(urlSamples[1] + ":/reddit.org/r/r/rr/f"));
		assertFalse(urlVal.isValid(urlSamples[1] + ":/blah.org.org.org/oror/orororo"));
		assertTrue(urlVal.isValid(urlSamples[0] + "://fjdask;lfjdsa;jfsldajfdsaf"));

	}

	public void testIsValid(){
	   //You can use this function for programming based testing
		//Essentially, a valid URL consists of http:// (based on bug)
		//Next up, there is an option of either www. or not at all
		//We ignore what's on the middle (google, facebook, reddit, twitter, yahoo, etc.)
		//The last token either contains .com, .org, .edu, .gov, .tv, or .net
		//Will NOT account for extraneous backslashes
		String[] sampleURLS = {"http://www.reddit.com", "htp:/ww.google.org", "http://yibberish.gov", "htp:/www.facebook.org", "http://twitch.tv/c9sneaky", "http://www.reddit.com/redirect"};
		UrlValidator urlVal = new UrlValidator(sampleURLS, UrlValidator.ALLOW_ALL_SCHEMES);
		for(int i = 0; i < sampleURLS.length; i++) {
			/*if(i % 2 == 0)
				assertTrue(urlVal.isValid(sampleURLS[i]));
			else
				assertFalse(urlVal.isValid(sampleURLS[i]));*/
			if(sampleURLS[i].contains("http://")){
				if(sampleURLS[i].contains(".com") || sampleURLS[i].contains(".edu") || sampleURLS[i].contains(".org") || sampleURLS[i].contains(".tv") || sampleURLS[i].contains(".gov"))
					assertTrue(urlVal.isValid(sampleURLS[i]));
				else
					assertFalse(urlVal.isValid(sampleURLS[i]));
			}
			else
				assertFalse(urlVal.isValid(sampleURLS[i]));
		}
	}

	public static void main(String[] argv){
		UrlValidatorTest test = new UrlValidatorTest("http");
		test.testManualTest();
		test.testYourFirstPartition();
		test.testYourSecondPartition();
		test.testIsValid();
	}


}
