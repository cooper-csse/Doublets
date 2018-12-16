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
		if (this.queue.size() == 0)
			return null;
		return this.queue.size() == 0 ? null : this.queue.removeFirst();
	}

	@Override
	public boolean isEmpty() {
		return this.queue.size() == 0;
	}
}
