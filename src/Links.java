import java.util.Set;

public class Links implements LinksInterface {
	public Links(String path) {
	}

	@Override
	public Set<String> getCandidates(String word) {
		return null;
	}

	@Override
	public boolean exists(String word) {
		return false;
	}
}
