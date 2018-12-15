import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Links implements LinksInterface {
	private Node root;
	private static HashMap<String, Links> links = new HashMap<>();

	public Links(String filename) {
		if (!Links.links.containsKey(filename)) {
			Links.links.put(filename, this);
			this.root = new Node(null);
			Scanner sc = null;
			try {
				File f = new File(filename);
				sc = new Scanner(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			while (sc.hasNext()) {
				Node word = new Node(sc.next());
				Iterator iterator = this.root.children();
				while (iterator.hasNext()) {
					Node node = (Node) iterator.next();
					if (isLink(word.word, node.word)) {
						word.addChild(node);
						node.addChild(word);
					}
				}
				this.root.addChild(word);
			}
		} else {
			this.root = Links.links.get(filename).root;
		}
	}

	@Override
	public Set<String> getCandidates(String word) {
		try {
			Set<String> set = this.root.getChild(word).values();
			return (set.size() == 0) ? null : set;
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	public boolean exists(String word) {
		return this.root.contains(word);
	}

	private boolean isLink(String word1, String word2) {
		if (word1.length() != word2.length() || word1.equals(word2))
			return false;
		int distance = 0;
		for (int i = 0; i < word1.length(); i++) {
			distance += (word1.charAt(i) == word2.charAt(i)) ? 0 : 1;
			if (distance > 1)
				return false;
		}
		return true;
	}

	public String toString() {
		String output = "";
		Iterator iterator = this.root.children();
		while (iterator.hasNext()) {
			Node node = (Node) iterator.next();
			output += node.toString() + "\n";
		}
		return output;
	}
}
