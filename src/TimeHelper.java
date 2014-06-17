import java.util.Date;
import java.util.concurrent.TimeUnit;


public final class TimeHelper {
	
	private TimeHelper() {
		// Prevent instantiation
	}
	
	public static String ago(Date d) {
		
		Date now = new Date();
		String message = "(";
		
		long diff = now.getTime() - d.getTime();
		
		if (diff < 0) {
			System.err.println("Cheep in the future.");
			return null;
		}
		
		long days = TimeUnit.MILLISECONDS.toDays(diff);
		long hours = TimeUnit.MILLISECONDS.toHours(diff);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
		
		if (days > 0) {
			message += String.format("%d days", days);
		} else if (hours > 0) {
			message += String.format("%d hours", hours);
		} else if (minutes > 0) {
			message += String.format("%d minutes", minutes);
		} else {
			message += String.format("%d seconds", TimeUnit.MILLISECONDS.toSeconds(diff));
		}
		
		return message + " ago)";
	}

}
