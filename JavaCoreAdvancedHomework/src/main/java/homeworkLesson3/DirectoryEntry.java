package homeworkLesson3;

import java.util.ArrayList;

public class DirectoryEntry {

	private String surname;
	private ArrayList<String> phoneNunbers;
	
	public DirectoryEntry(String surname, ArrayList<String> phoneNunbers) {
		this.surname = surname;
		this.phoneNunbers = phoneNunbers;
	}
	
	public DirectoryEntry(String surname, String phoneNumber) {
		this.surname = surname;
		phoneNunbers = new ArrayList<>();
		phoneNunbers.add(phoneNumber);
	}
	
	public void addPhoneNumber(String phoneNumber) {
		phoneNunbers.add(phoneNumber);
	}

	public String getSurname() {
		return surname;
	}

	public ArrayList<String> getPhoneNunbers() {
		return phoneNunbers;
	}
	
	
}
