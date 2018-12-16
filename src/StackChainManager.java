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
		return this.stack.size() == 0 ? null : this.stack.pop();
	}

	@Override
	public boolean isEmpty() {
		return this.stack.size() == 0;
	}
}
