import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author andersc7, mcknigaa
 */
@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class NodeTest {
	private boolean enable95Percent = false;

	@Test
	public void test2TinyDictionaryRuntime() {
		double elapsedTime;
		double startTime = System.currentTimeMillis();
		Links linksTiny = new Links("tiny.dictionary.txt");
		elapsedTime = (System.currentTimeMillis() - startTime);
		assertTrue(elapsedTime < 10);
	}

	@Test
	public void test3EnglishCleanedAll10Runtime() {
		double elapsedTime;
		double startTime = System.currentTimeMillis();
		Links links10 = new Links("english.cleaned.all.10.txt");
		elapsedTime = (System.currentTimeMillis() - startTime);
		assertTrue(elapsedTime < 100);
	}

	@Test
	public void test1EnglishCleanedAll20Runtime() {
		double elapsedTime;
		double startTime = System.currentTimeMillis();
		Links links20 = new Links("english.cleaned.all.20.txt");
		elapsedTime = (System.currentTimeMillis() - startTime);
		assertTrue(elapsedTime < 1000);
	}

	@Test
	public void test4EnglishCleanedAll35Runtime() {
		double elapsedTime;
		double startTime = System.currentTimeMillis();
		Links links35 = new Links("english.cleaned.all.35.txt");
		elapsedTime = (System.currentTimeMillis() - startTime);
		assertTrue(elapsedTime < 30000);
	}

	@Test
	public void test5EnglishCleanedAll95Runtime() {
		double elapsedTime;
		double startTime = System.currentTimeMillis();
		if (enable95Percent) {
			Links links35 = new Links("english.cleaned.all.95.txt");
		} else {
			System.out.println("95 percent is disabled");
		}
		elapsedTime = (System.currentTimeMillis() - startTime);
		assertTrue(elapsedTime < 300000);
	}
}
