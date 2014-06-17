import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DefaultConsoleInterpreter implements IInputHandler {

	public static final String SEPARATOR = " ";
	public static final String DISPLAY_WALL = "wall";
	public static final String FOLLOW = "follows";
	public static final String POST = "->";
	public static final String EXIT = "exit";
	public static final String PROMPT = "> ";
	
	@Override
	public void start(Cheeper cheeper) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String command = null;
		try {
			do {
				
				try {
				
					System.out.print(PROMPT);
					command = br.readLine();
					
					String[] commandTokens = command.split(SEPARATOR, 3);
					
					if (commandTokens.length == 1) {
						cheeper.read(commandTokens[0]);
					} else if (commandTokens[1].equalsIgnoreCase(POST)) {
						cheeper.post(commandTokens[0], commandTokens[2]);
					} else if (commandTokens[1].equalsIgnoreCase(DISPLAY_WALL)) {
						cheeper.displayWall(commandTokens[0]);
					} else if (commandTokens[1].equalsIgnoreCase(FOLLOW)) {
						cheeper.follow(commandTokens[0], commandTokens[2]);
					} else {
						System.err.println("Unknown command. Please try again.");
					}
					
				} catch (UserNotFoundException e) {
					System.err.println("User not found.");
				}
				
			} while (command != null && command !="" && !command.equalsIgnoreCase(EXIT));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
