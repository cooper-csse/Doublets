import java.util.LinkedList;

public class QueueChainManager extends ChainManager {
	private LinkedList <Chain> queue;
	
	public QueueChainManager (){
		this.queue = new LinkedList<>();
	}
	
	@Override
	public void add(Chain chain) {
		this.queue.addLast(chain);
		this.updateMax(this.queue.size());
	}

	@Override
	public Chain next() {
		// Return next chain in the queue unless the queue is empty
		if (this.queue.size() != 0) {
			this.incrementNumNexts();
			return this.queue.removeFirst();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return this.queue.size() == 0;
	}
}
