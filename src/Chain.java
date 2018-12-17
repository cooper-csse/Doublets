import java.util.Iterator;
import java.util.Stack;

public class Chain {
	
	private int length = 0;
	private String value;
	private Chain prev;
	private Chain head;
	
	public Chain() {
		this.value = null;
		this.prev = null;
		this.head = this;
	}
	
	
	public Chain addLast(String word) {
		Chain chain = new Chain();
		chain.head = this.head;
		chain.value = word;
		chain.length = this.length + 1;
		chain.prev = this;
		return chain;
	}

	public boolean contains(String word) {
		Iterator iterator = this.iterator();
		while (iterator.hasNext()) {
			if (iterator.next() == word)
				return true;
		}
		return false;
	}

	public Iterator<String> iterator() {
		return new ChainIterator(this);
	}

	public String getLast() {
		return this.value;
	}

	public int length() {
		return this.length;
	}

	public String toString() {
		String output = "";
		Iterator<String> itr = this.iterator();
		while (itr.hasNext()) {
			output += itr.next() + ", ";
		}
		return "[" + output.substring(0, Math.max(0, output.length() - 2)) + "]";
	}
	
	class ChainIterator implements Iterator<String> {
		Stack<Chain> stack;

		public ChainIterator(Chain chain) {
			this.stack = new Stack<>();
			Chain current = chain;
			if (chain.length != 0) {
				while (current.prev != null) {
					stack.push(current);
					current = current.prev;
				}
			} else {
				this.stack.push(chain);
			}
		}

		@Override
		public boolean hasNext() {
			return this.stack.size() != 0;
		}
		
		@Override
		public String next() {
			return this.stack.pop().value;
		}
	}
}
