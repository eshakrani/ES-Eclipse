import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class TestFileStuff {

	public static void main(String[] args) throws IOException {
		String s = "";
		char ch;
		int i;
		FileReader reader;
		BufferedReader br;
		FileWriter writer;
		BufferedWriter bw;
		try {
			reader = new FileReader("C:\\Users\\"
					+ "eshak\\Desktop\\TestInput.txt");
			br = new BufferedReader(reader);
			writer = new FileWriter("C:\\Users\\eshak\\"
					+ "Desktop\\TestOutput.txt");
			bw = new BufferedWriter(writer);

			while ((s = br.readLine()) != null) {
				bw.write(s + "\n");
			}
			br.close();
			bw.close();
		}
		catch (IOException e) {
			System.out.println("FILE NOT FOUND.\n");
		}
		catch (NullPointerException e) {
			System.out.println("NULL");
		}
	}

}
