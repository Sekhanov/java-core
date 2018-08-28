package homeworkLesson3;

import java.util.HashMap;
import java.util.List;

public class PhoneDirectory {

	private HashMap<String, DirectoryEntry> directoryEnries;

	public PhoneDirectory(HashMap<String, DirectoryEntry> directoryEnries) {
		this.directoryEnries = directoryEnries;
	}

	public PhoneDirectory() {
		directoryEnries = new HashMap<>();
	}

	public void add(String surname, String phoneNumber) {
		if (!directoryEnries.containsKey(surname)) {
			directoryEnries.put(surname, new DirectoryEntry(surname, phoneNumber));
		} else {
			directoryEnries.get(surname).addPhoneNumber(phoneNumber);
		}
	}

	public String getMainPhoneNumber(String surname) {
		return directoryEnries.get(surname).getPhoneNunbers().get(0);
	}

	public List<String> getPhoneNumbers(String surname) {
		return directoryEnries.get(surname).getPhoneNunbers();
	}

}
