import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {
	@Test
	public void testTinyDictionaryRuntime() {
		double elapsedTime;
		double startTime = System.currentTimeMillis();
		Links linksTiny = new Links("tiny.dictionary.txt");
		elapsedTime = (System.currentTimeMillis() - startTime);
		assertTrue(elapsedTime < 10);
	}

	@Test
	public void testEnglishCleanedAll10Runtime() {
		double elapsedTime;
		double startTime = System.currentTimeMillis();
		Links links10 = new Links("english.cleaned.all.10.txt");
		elapsedTime = (System.currentTimeMillis() - startTime);
		assertTrue(elapsedTime < 100);
	}

	@Test
	public void testEnglishCleanedAll20Runtime() {
		double elapsedTime;
		double startTime = System.currentTimeMillis();
		Links links20 = new Links("english.cleaned.all.20.txt");
		elapsedTime = (System.currentTimeMillis() - startTime);
		assertTrue(elapsedTime < 1000);
	}

	@Test
	public void testEnglishCleanedAll35Runtime() {
		double elapsedTime;
		double startTime = System.currentTimeMillis();
		Links links35 = new Links("english.cleaned.all.35.txt");
		elapsedTime = (System.currentTimeMillis() - startTime);
		assertTrue(elapsedTime < 30000);
	}
}
