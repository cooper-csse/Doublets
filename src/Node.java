import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @author andersc7, mcknigaa
 */
public class Node {
	public String word;
	private HashMap<String, Node> children;
	private int size = 0;

	public Node(String value) {
		this.word = value;
		this.children = new HashMap<>();
	}

	public Node() {
		this.word = null;
		this.children = new HashMap<>();
	}

	public void addChild(Node node) {
		// Add the child to this nodes HashMap of children
		this.children.put(node.word, node);
		this.size++;
	}

	public Node getChild(String word) {
		return this.children.getOrDefault(word, null);
	}

	public boolean contains(String word) {
		return this.children.containsKey(word);
	}

	public int size() {
		return this.size;
	}

	public Iterator<Node> children() {
		return this.children.values().iterator();
	}

	public Set<String> values() {
		return this.children.keySet();
	}

	public String toString() {
		return this.word + ": " + this.values();
	}
}
