import org.junit.Before;
import org.junit.Test;

public class NodeTest {
	@Test
	public void maintest() {
		//Links linksTiny = new Links("tiny.dictionary.txt");
		Links linksTiny = new Links("english.cleaned.all.10.txt");
		//System.out.println(linksTiny.getCandidates("are"));
		System.out.println(linksTiny);
	}
}
