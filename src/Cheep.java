import java.util.Date;


public class Cheep implements Comparable<Cheep> {

	private User user;
	private String content;
	private Date timestamp;
	
	public Cheep(User user, String content) {
		this.user = user;
		this.content = content;
		timestamp = new Date();
	}
	
	@Override
	public String toString() {
		return content + " " + TimeHelper.ago(timestamp);
	}
	
	public String toStringWithUsername() {
		return user.toString() + " - " + toString();
	}

	@Override
	public int compareTo(Cheep o) {
		// Reverse ordering with "-"
		return -this.timestamp.compareTo(o.timestamp);
	}
	
	
}
