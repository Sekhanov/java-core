package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileMessageLogger implements MessageLogger {
	
	private BufferedWriter bufferWriter;
	private BufferedReader bufferedReader;
	
	public FileMessageLogger() throws IOException  {
		this.bufferWriter = new BufferedWriter(new FileWriter("messageArchive.txt", true));
		this.bufferedReader = new BufferedReader(new FileReader("messageArchive.txt"));
	}

	@Override
	public boolean archiveMessage(String message) {
		boolean result = false;
		try {
			bufferWriter.write(message + "\n");
			bufferWriter.flush();
			result = true;
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public List<String> restoreMessages(int amount) {
		List<String> stringFromFile = new ArrayList<>();
		List<String> result = null;
		String string = null;
		try {
			while((string = bufferedReader.readLine()) != null) {
				stringFromFile.add(string);
			}
			if(stringFromFile.size() < amount) {
				result = stringFromFile;
			} else {
				result = new ArrayList<>();
				int startIndex = stringFromFile.size() - amount;
				for (int i = startIndex; i < stringFromFile.size(); i++) {
					result.add(stringFromFile.get(i));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

}
