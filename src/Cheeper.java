import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.byteslounge.collections.ReversedIterator;


/**
 * @author mac
 *
 */

public class Cheeper {

	private HashMap<String, User> users;
	
	public static void main(String[] args) {
		
		Cheeper platform = new Cheeper();
		
		// Assume users are added automatically
		platform.addUser("Alice");
		platform.addUser("Bob");
		platform.addUser("Charlie");
		
		
		try {
			//platform.testCase();
			
			IInputHandler io = new DefaultConsoleInterpreter();
			
			io.start(platform);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Cheeper(){
		users = new HashMap<String, User>();
	}

	// Specification test cases
	public void testCase() {

		System.out.println(users.toString());
		
		try {
			
			post("Alice", "I love the weather today");
			Thread.sleep(1000);
			
			post("Bob", "Damn! We lost!");
			Thread.sleep(1000);
			
			post("Bob", "Good game though.");
			Thread.sleep(1000);
			
			read("Alice");
			read("Bob");
			
			post("Charlie", "I'm in New York today! Anyone wants to have a coffee?");
			Thread.sleep(1000);
			
			follow("Charlie", "Alice");
			
			displayWall("Charlie");
			
			follow("Charlie", "Bob");

			displayWall("Charlie");
			
		} catch (InterruptedException ex) {
		    Thread.currentThread().interrupt();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Carries out the posting command.
	 * 
	 * @param username name of the user posting
	 * @param content content of the post
	 * @throws UserNotFoundException
	 */
	public void post(String username, String content) throws UserNotFoundException {
		User user = users.get(username);
		if (user != null) {
			user.addCheep(new Cheep(user, content));
		} else {
			throw new UserNotFoundException("Cannot post the cheep.");
		}
	}
	
	
	/**
	 * Carries out the reading command. View user's posts.
	 * 
	 * @param username name of the user for which posts are requested
	 * @throws UserNotFoundException
	 */
	public void read(String username) throws UserNotFoundException {
		User user = users.get(username);
		if (user != null) {
			// Iterate in reverse to get the most recent posts first
			printCheeps(new ReversedIterator<Cheep>(user.getCheeps()), false);
		} else {
			throw new UserNotFoundException("Cannot read user cheeps.");
		}
	}
	
	
	/**
	 * Carries out the following command. Subscribe to user's posts.
	 * 
	 * @param user1 follower user name
	 * @param user2 user name of the user to be followed
	 * @throws UserNotFoundException
	 */
	public void follow(String user1, String user2) throws UserNotFoundException {
		User follower = users.get(user1);
		if (follower == null) {
			throw new UserNotFoundException("Follower not found.");
		}
		User followee = users.get(user2);
		if (followee == null) {
			throw new UserNotFoundException("Followee not found.");
		}
		
		follower.subscribe(followee);
	}
	
	/**
	 * Carries out the wall command. Displays user's wall.
	 * 
	 * @param user user name for the wall
	 * @throws UserNotFoundException
	 */
	public void displayWall(String user) throws UserNotFoundException {
		User current = users.get(user);
		if (current == null) {
			throw new UserNotFoundException("Cannot display wall.");
		}
		
		List<List<Cheep>> lists = new ArrayList<List<Cheep>>();
		for (User u : current.getFollowing()) {
	
			lists.add(u.getCheeps());	
		}	

		// Merge sorted lists in the most efficient way by polling minimum value from all tops
		printCheeps(CollectionHelper.mergeSorted(lists), true);
		
		lists = null;
	}
	
	// Adds a new user
	private boolean addUser(String username){
		if (!users.containsKey(username)) {
			users.put(username, new User(username));
			return true;
		} else { 
			return false;
		}
	}
	
	// Utility printing method for collections of posts
	private void printCheeps(Iterable<Cheep> cheeps, boolean username) {
		for (Cheep c : cheeps) {
			if (username) {
				System.out.println(c.toStringWithUsername());
			} else {
				System.out.println(c.toString());
			}
		}
		System.out.println();
	}
}
	
	
	

