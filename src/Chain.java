import java.util.Iterator;

public class Chain {
	
	private int length = 0;
	private String value;
	private Chain prev;
	private Chain next;
	private Chain head;
	
	public Chain(){
		value = null;
		prev = null;
		next = null;
		head = this;
	}
	
	
	public Chain addLast(String word) {
		Chain chain = new Chain();
		chain.head = this.head;
		chain.value = word;
		chain.length = this.length + 1;
		this.next = chain;
		chain.prev = this;
		return chain;
	}

	public boolean contains(String word) {
		Iterator iterator = this.iterator();
		while (iterator.hasNext()){
			if(iterator.next() == word)
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
	
	class ChainIterator implements Iterator<String> {
		Chain current;
		
		public ChainIterator (Chain chain){
			this.current = chain.head;
		}
		
		@Override
		public boolean hasNext () {
			return this.current.next != null;
		}
		
		@Override
		public String next() {
			this.current = this.current.next;
			return this.current.value;
		}
	}
}
