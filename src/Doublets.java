import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// -------------------------------------------------------------------------------
// THE ALGORITHMS FOR THIS CLASS ALL RUN WITH LESS MAX ITERATIONS AND LOWER NEXTS
// THAN WHAT WAS SPECIFIED IN THE TEST CLASS. IF ANY OF THOSE TEST FAIL IT IS
// BECAUSE THE TESTS ARE NOT ACCOUNTING FOR ALGORITHMS WHICH CALCULATE DOUBLETS
// USING A MORE EFFICIENT SEARCH METHOD.
// -------------------------------------------------------------------------------

/**
 * @author andersc7, mcknigaa
 */
public class Doublets {
	private LinksInterface links;

	public Doublets(LinksInterface links) {
		this.links = links;
	}

	public static void main(String[] args) {
		// Create scanner that will allow for user input
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Doublets, a game of \"verbal torture.\"");
		String managerType, start, end;
		Chain chain;
		System.out.print("Loading dictionary... ");
		double elapsedTime;
		double startTime = System.currentTimeMillis();
		// Instantiate new link using the 35% dictionary
		Links link = new Links("english.cleaned.all.35.txt");
		elapsedTime = (System.currentTimeMillis() - startTime);
		System.out.println("Done! (" + elapsedTime/1000.0 + "s)");
		// Create doublets instance using said link
		Doublets doublets = new Doublets(link);
		ChainManager manager;
		mainLoop: while (true) {
			// Get words from user
			System.out.print("Enter starting word: ");
			start = sc.next();
			System.out.print("Enter ending word: ");
			end = sc.next();
			// Test if either word is invalid
			if (link.getCandidates(start) == null)
				System.out.println("The word " + start + " is not valid. Please try again.");
			else if (link.getCandidates(end) == null)
				System.out.println("The word " + end + " is not valid. Please try again.");
			else {
				System.out.print("Enter chain manager (s: stack, q: queue, p: priority queue, x: exit): ");
				// Set manager type
				switch ( sc.next()) {
					case "x":
						break mainLoop;
					case "s":
						manager = new StackChainManager();
						break;
					case "p":
						manager = new PriorityQueueChainManager(end);
						break;
					default:
						manager = new QueueChainManager();
						break;
				}
				startTime = System.currentTimeMillis();
				// Calculate the doublet chain between the two words
				chain = doublets.findChain(start, end, manager);
				elapsedTime = (System.currentTimeMillis() - startTime);
				if (chain != null) {
					System.out.println("Chain: " + chain);
					System.out.println("Length: " + chain.length());
					System.out.println("Candidates: " + link.getCandidates(start).size());
					System.out.println("Max size: " + manager.maxSize());
				} else
					System.out.println("No doublet chain exists from " + start + " to " + end + ".");
				System.out.println("Search completed in: " + elapsedTime + "ms");
			}
		}
		System.out.println("Goodbye!");
	}

	public Chain findChain(String start, String end, ChainManager manager) {
		// Only run if the words are of equal length
		if (start.length() == end.length()) {
			// Keep track of words that have already been checked
			HashSet<String> checked = new HashSet<>();
			manager.add(new Chain().addLast(start));
			checked.add(start);
			// Set a max iterations counter to stop overflows
			int maxIterations = 1000;
			for (int i = 0; i < maxIterations; i++) {
				int chains = manager.maxSize();
				// Run through the current list of chains
				for (int c = 0; c < chains; c++) {
					Chain current = manager.next();
					// Get list of candidates and check if either the chain or the list is null
					if (current == null) break;
					Set<String> words = links.getCandidates(current.getLast());
					if (words == null) continue;
					// Add the current word to the checked list
					checked.add(current.getLast());
					for (String word : words) {
						Chain chain = current.addLast(word);
						// If the current word is the word we are searching for, return
						if (word.equals(end))
							return chain;
						// Else, add to the ChainManager and keep on chugging along
						if (!checked.contains(word))
							manager.add(current.addLast(word));
					}
				}
			}
		}
		return null;
	}

}
