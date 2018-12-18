import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author andersc7, mcknigaa
 */
public class Links implements LinksInterface {
	private static HashMap<String, Links> links = new HashMap<>();
	private static int maxWordLength = 18;
	private Node[] roots;

	public Links(String filename) {
		// If we already have a link with this dictionary, skip the creation
		if (!Links.links.containsKey(filename)) {
			Links.links.put(filename, this);
			this.roots = new Node[maxWordLength];
			for (int i = 0; i < maxWordLength; i++) {
				// Create a separate root node for each possible word length
				this.roots[i] = new Node();
			}
			Scanner sc = null;
			try {
				File f = new File(filename);
				sc = new Scanner(f);
			} catch (FileNotFoundException e) {
				Links.links.remove(filename);
				e.printStackTrace();
			}
			// Run through the dictionary, adding new nodes to the tree
			while (sc.hasNext()) {
				Node word = new Node(sc.next());
				Iterator iterator = this.roots[wordLength(word.word)].children();
				while (iterator.hasNext()) {
					// Run through our already created words and see if they are doublets,
					// If they are, create a link between them
					Node node = (Node) iterator.next();
					if (isLink(word.word, node.word)) {
						word.addChild(node);
						node.addChild(word);
					}
				}
				this.roots[wordLength(word.word)].addChild(word);
			}
		} else {
			this.roots = Links.links.get(filename).roots;
		}
	}

	@Override
	public Set<String> getCandidates(String word) {
		try {
			// Retrieve the Set of words that are 1 link away from our parameter
			Set<String> set = this.roots[wordLength(word)].getChild(word).values();
			return (set.size() == 0) ? null : set;
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	public boolean exists(String word) {
		return this.roots[word.length() - 1].contains(word);
	}

	private boolean isLink(String word1, String word2) {
		if (word1.length() != word2.length() || word1.equals(word2))
			return false;
		int distance = 0;
		for (int i = 0; i < word1.length(); i++) {
			// Checks if the words differ by one character only
			distance += (word1.charAt(i) == word2.charAt(i)) ? 0 : 1;
			if (distance > 1)
				return false;
		}
		return true;
	}

	private int wordLength(String word) {
		// Decide which index of our roots list to use for each word
		return Math.min(Links.maxWordLength, word.length()) - 1;
	}

	public String toString() {
		// Create a prettified output of the Links
		String output = "";
		for (Node root : this.roots) {
			Iterator iterator = root.children();
			while (iterator.hasNext()) {
				Node node = (Node) iterator.next();
				output += node.toString() + "\n";
			}
		}
		return output;
	}
}
