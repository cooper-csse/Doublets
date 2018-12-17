import java.util.HashSet;
import java.util.Set;

/**
 * @author <<TODO>>
 */
public class Doublets {
	private LinksInterface links;

	public Doublets(LinksInterface links) {
		this.links = links;
	}

	public static void main(String[] args) {
		// TODO: write a text-based UI to find doublets.
	}

	public Chain findChain(String start, String end, ChainManager manager) {
		if (start.length() == end.length()) {
			HashSet<String> checked = new HashSet<>();
			manager.add(new Chain().addLast(start));
			checked.add(start);
			int maxIterations = 1000;
			for (int i = 0; i < maxIterations; i++) {
				int chains = manager.maxSize();
				for (int c = 0; c < chains; c++) {
					Chain current = manager.next();
					if (current == null) break;
					Set<String> words = links.getCandidates(current.getLast());
					if (words == null) continue;
					checked.add(current.getLast());
					for (String word : words) {
						Chain chain = current.addLast(word);
						if (word.equals(end))
							return chain;
						if (!checked.contains(word))
							manager.add(current.addLast(word));
					}
				}
			}
		}
		return null;
	}

}
