package utility;

import java.util.List;

public interface MessageLogger {
	
	boolean archiveMessage(String message);
	
	List<String> restoreMessages(int amount);
}
