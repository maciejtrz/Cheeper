import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


public class User {

	
	private String username;
	private ArrayList<Cheep> cheeps;
	private HashSet<User> following;
	
	public User(String username) {
		this.username = username;
		// No need for explicit ordering since posting time ensures consecutive indices
		cheeps = new ArrayList<Cheep>();
		following = new HashSet<User>();
		following.add(this);
	}

	public boolean addCheep(Cheep c) {
		return cheeps.add(c);
	}
	
	public boolean subscribe(User u) {
		return following.add(u);
	}
	
	public List<Cheep> getCheeps() {
		return cheeps;
	}
	
	public Collection<User> getFollowing() {
		return following;
	}
	
	@Override
	public String toString(){
		return username;
	}
}
