import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Test;


/**
 * Tests the Links class.
 *
 * @author Matt Boutell. Created Mar 18, 2011.
 */
public class LinkTest {

	private static double sPoints = 0;
	
	/**
	 * Test method for {@link Links#getCandidates(java.lang.String)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetCandidatesTinyThreeLetterWords() throws FileNotFoundException {
		Links linksTiny = new Links("tiny.dictionary.txt");
		Set<String> fooCandidates = linksTiny.getCandidates("foo");
		assertTrue(fooCandidates.contains("for"));
		assertTrue(fooCandidates.contains("too"));
		assertFalse(fooCandidates.contains("bar"));
		assertEquals(2, fooCandidates.size());
		sPoints += 2;
	}

	/**
	 * Test method for {@link Links#getCandidates(java.lang.String)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetCandidatesTinyFourLetterWords() throws FileNotFoundException {
		Links linksTiny = new Links("tiny.dictionary.txt");
		Set<String> candidates = linksTiny.getCandidates("math");
		assertTrue(candidates.contains("path"));
		assertTrue(candidates.contains("moth"));
		assertFalse(candidates.contains("mouth"));
		assertEquals(2, candidates.size());
		sPoints += 2;
	}

	/**
	 * Test method for {@link Links#getCandidates(java.lang.String)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetCandidatesTinyMissingWords() throws FileNotFoundException {
		Links linksTiny = new Links("tiny.dictionary.txt");
		Set<String> candidates = linksTiny.getCandidates("fwump");
		assertNull(candidates);
		candidates = linksTiny.getCandidates("bryllyg");
		assertNull(candidates);
		sPoints += 2;
	}

	/**
	 * Test method for {@link Links#getCandidates(java.lang.String)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetCandidatesTinyNoCandidates() throws FileNotFoundException {
		Links linksTiny = new Links("tiny.dictionary.txt");
		Set<String> candidates = linksTiny.getCandidates("silk");
		assertNull(candidates);
		candidates = linksTiny.getCandidates("mouth");
		assertNull(candidates);
		sPoints += 2;
	}

	/**
	 * Test method for {@link Links#getCandidates(java.lang.String)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetCandidates10() throws FileNotFoundException {
		Links links10 = new Links("english.cleaned.all.10.txt");
		Set<String> candidates = links10.getCandidates("bar");
		assertTrue(candidates.contains("bad"));
		assertTrue(candidates.contains("ban"));
		assertTrue(candidates.contains("car"));
		assertTrue(candidates.contains("far"));
		assertTrue(candidates.contains("war"));
		assertEquals(5, candidates.size());

		candidates = links10.getCandidates("left");
		assertTrue(candidates.contains("lift"));
		assertEquals(1, candidates.size());
		sPoints += 5;
	}

	/**
	 * Test method for {@link Links#getCandidates(java.lang.String)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetCandidates20() throws FileNotFoundException {
		Links links20 = new Links("english.cleaned.all.20.txt");
		Set<String> candidates = links20.getCandidates("row");
		assertTrue(candidates.contains("bow"));
		assertTrue(candidates.contains("cow"));
		assertTrue(candidates.contains("how"));
		assertTrue(candidates.contains("low"));
		assertTrue(candidates.contains("now"));
		assertTrue(candidates.contains("raw"));
		assertTrue(candidates.contains("rod"));
		assertTrue(candidates.contains("rot"));
		assertTrue(candidates.contains("wow"));
		assertEquals(9, candidates.size());

		candidates = links20.getCandidates("parse");
		assertTrue(candidates.contains("pause"));
		assertEquals(1, candidates.size());

		candidates = links20.getCandidates("reduction");
		assertTrue(candidates.contains("deduction"));
		assertEquals(1, candidates.size());

		candidates = links20.getCandidates("love");
		assertTrue(candidates.contains("live"));
		assertTrue(candidates.contains("lose"));
		assertTrue(candidates.contains("move"));
		sPoints += 5;
	}
	
	/**
	 * Test method for {@link Links#getCandidates(java.lang.String)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetCandidatesInvalidString() throws FileNotFoundException {
		Links links20 = new Links("english.cleaned.all.20.txt");
		Set<String> candidates = links20.getCandidates("aaaa");
		assertNull(candidates);
		sPoints += 2;
	}
	
	@Test
	public void testGetCandidatesEfficiency() throws FileNotFoundException {		
		// Can take a few seconds to load, no efficiency requirement for this.
		Links links20 = new Links("english.cleaned.all.20.txt");

		double elapsedTime;
		double startTime = System.currentTimeMillis();
		int NUM_CALLS = 100000;
	
		for (int i = 0; i < NUM_CALLS; i++) {
			Set<String> candidates = links20.getCandidates("row");
		}

		elapsedTime = (System.currentTimeMillis() - startTime);
		System.out.println(elapsedTime + " milliseconds");
		if (elapsedTime > 15) {
			System.out.println("You should check the efficiency of getCandidates() to see if it is O(1), reardless of the size of the dictionary.");
			System.out.println("If the elapsed time is close to 15ms, it should be OK. ");
			System.out.println("This is just a warning that a slow getCandidates() will cause troubles later.");
		} else {
			System.out.println("Passes a conservative speed test - not a guarantee of O(1) efficiency.");
		}
	}
	
	@AfterClass
	public static void printScore() {
		System.out.printf("Links tests %.0f/20\n", sPoints);
	}
}
