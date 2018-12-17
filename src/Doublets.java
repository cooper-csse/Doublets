import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author <<TODO>>
 */
public class Doublets {
	private LinksInterface links;

	public Doublets(LinksInterface links) {
		this.links = links;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Doublets, a game of \"verbal torture.\"");
		String managerType, start, end;
		Chain chain;
		Links link = new Links("english.cleaned.all.35.txt");
		Doublets doublets = new Doublets(link);
		ChainManager manager;
		do {
			System.out.print("Enter starting word: ");
			start = sc.next();
			System.out.print("Enter ending word: ");
			end = sc.next();
			System.out.print("Enter chain manager (s: stack, q: queue, p: priority queue, x: exit)");
			managerType = sc.next();
			if (link.getCandidates(start) != null && link.getCandidates(end) != null) {
				if (managerType.equals("s")) {
					manager = new StackChainManager();
					
				} else if (managerType.equals("q")) {
					manager = new QueueChainManager();
					
				} else if (managerType.equals("p")){
					manager = new PriorityQueueChainManager(end);
					
				}else
					break;
				chain = doublets.findChain(start, end, manager);
				if (chain != null) {
					System.out.println("Chain: " + chain);
					System.out.println("Length: " + chain.length());
					System.out.println("Candidates: " + link.getCandidates(start).size());
					System.out.println("Max size: " + manager.maxSize());
				} else
					System.out.println("No doublet chain exsists from " + start + " to " + end + ".");
				
			} else {
				if (link.getCandidates(start) == null)
					System.out.println("The word " + start + " is not valid. Please try again.");
				else
					System.out.println("The word " + end + " is not valid. Please try again.");

			}
		} while (managerType.equals("s") || managerType.equals("q"));
		System.out.println("Goodbye!");
	}

	public Chain findChain(String start, String end, ChainManager manager) {
		if (start.length() == end.length()) {
			HashSet<String> checked = new HashSet<>();
			manager.add(new Chain().addLast(start));
			checked.add(start);
			int maxIterations = 1000;
			for (int i = 0; i < maxIterations; i++) {
				int chains = manager.maxSize();
				for (int c = 0; c < chains; c++) {
					Chain current = manager.next();
					if (current == null)
						break;
					Set<String> words = links.getCandidates(current.getLast());
					if (words == null)
						continue;
					checked.add(current.getLast());
					for (String word : words) {
						Chain chain = current.addLast(word);
						if (word.equals(end))
							return chain;
						if (!checked.contains(word))
							manager.add(current.addLast(word));
					}
				}
			}
		}
		return null;
	}

}
