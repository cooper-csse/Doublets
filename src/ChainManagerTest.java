import static org.junit.Assert.*;

import org.junit.*;

public class ChainManagerTest {
	Chain chain1 = new Chain();
	Chain chain2 = new Chain();
	Chain chain3 = new Chain();
	Chain chain4 = new Chain();
	Chain chain5 = new Chain();

	@Before
	public void createChains() {
		chain1 = chain1.addLast("asdf");
		chain2 = chain2.addLast("qwer").addLast("tyui");
		chain3 = chain3.addLast("zxcv").addLast("bnmm").addLast("0987");
		chain4 = chain4.addLast("1357").addLast("2468").addLast("1928").addLast("1234");
		chain5 = chain5.addLast("foo").addLast("bar").addLast("baz").addLast("1234").addLast("6754");
	}

	@Test
	public void testStackChainManagerAdd() {
		ChainManager stackChainManager = new StackChainManager();
		stackChainManager.add(chain1);
		assertEquals(1, stackChainManager.maxSize());
		stackChainManager.add(chain2);
		assertEquals(2, stackChainManager.maxSize());
		stackChainManager.add(chain3);
		stackChainManager.add(chain4);
		stackChainManager.add(chain5);
		assertEquals(5, stackChainManager.maxSize());
	}

	@Test
	public void testQueueChainManagerAdd() {
		ChainManager queueChainManager = new QueueChainManager();
		queueChainManager.add(chain1);
		assertEquals(1, queueChainManager.maxSize());
		queueChainManager.add(chain2);
		assertEquals(2, queueChainManager.maxSize());
		queueChainManager.add(chain3);
		queueChainManager.add(chain4);
		queueChainManager.add(chain5);
		assertEquals(5, queueChainManager.maxSize());
	}

	@Test
	public void testStackChainManagerNext() {
		ChainManager stackChainManager = new StackChainManager();
		stackChainManager.add(chain1);
		stackChainManager.add(chain2);
		stackChainManager.add(chain3);
		assertEquals(3, stackChainManager.next().length());
		assertEquals(2, stackChainManager.next().length());
		assertEquals(1, stackChainManager.next().length());
	}

	@Test
	public void testQueueChainManagerNext() {
		ChainManager queueChainManager = new QueueChainManager();
		queueChainManager.add(chain1);
		queueChainManager.add(chain2);
		queueChainManager.add(chain3);
		assertEquals(1, queueChainManager.next().length());
		assertEquals(2, queueChainManager.next().length());
		assertEquals(3, queueChainManager.next().length());
	}

	@Test
	public void testStackChainManagerIsEmpty() {
		ChainManager stackChainManager = new StackChainManager();
		stackChainManager.add(chain1);
		stackChainManager.add(chain2);
		assertFalse(stackChainManager.isEmpty());
		stackChainManager.next();
		stackChainManager.next();
		assertTrue(stackChainManager.isEmpty());
	}

	@Test
	public void testQueueChainManagerIsEmpty() {
		ChainManager queueChainManager = new QueueChainManager();
		queueChainManager.add(chain1);
		queueChainManager.add(chain2);
		assertFalse(queueChainManager.isEmpty());
		queueChainManager.next();
		queueChainManager.next();
		assertTrue(queueChainManager.isEmpty());
	}
}
