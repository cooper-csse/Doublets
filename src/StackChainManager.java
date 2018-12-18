import java.util.Stack;

public class StackChainManager extends ChainManager {
	private Stack<Chain> stack;

	public StackChainManager() {
		this.stack = new Stack<>();
	}

	@Override
	public void add(Chain chain) {
		this.stack.push(chain);
		this.updateMax(this.stack.size());
	}

	@Override
	public Chain next() {
		// Return next chain in the stack unless the stack is empty
		if (this.stack.size() != 0) {
			this.incrementNumNexts();
			return this.stack.pop();
		}
		return null;
	}

	public int size() {
		return this.stack.size();
	}

	@Override
	public boolean isEmpty() {
		return this.stack.size() == 0;
	}
}
