import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class audioCD {

	String title, artist, genre, releaseDate, totalTime;
	private audioCD head;
	private audioCD next;
	private int counter = 0;

	public audioCD() {
	}

	public audioCD(String title, String artist, String genre, String releaseDate, String totalTime) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.totalTime = totalTime;
	}

	public boolean isEmpty() { // Checks the album is empty or full ?
		if (counter == 0) {
			return true;
		} else
			return false;
	}

	public boolean Add(String title, String artist, String genre, String releaseDate, String totalTime) {

		audioCD temp = new audioCD(title, artist, genre, releaseDate, totalTime);

		if (head == null)
			head = new audioCD();
		else
			temp.next = head.next;

		head.next = temp;
		counter++;

		return true;
	}

	public boolean Remove(String title) {

		if (isEmpty() == true)
			return false;

		else {
			audioCD current = head.next;
			while (current != null) {
				if (current.title.equals(title)) {
					head.next = current.next;
					counter--;

					break;
				}
				current = current.next;
			}
			return true;
		}

	}

	public boolean Search(String title) {

		if (isEmpty() == true) {
			return false;
		} else {
			audioCD current = head.next;

			while (current != null) {
				if (current.title.equals(title)) {
					System.out.println("Title: " + current.title + " Artist: " + current.artist + " Genre: "
							+ current.genre + " Release Date: " + current.releaseDate + " Total Time: "
							+ current.totalTime + "\n");

					break;
				}
				current = current.next;
			}

			return true;
		}
	}

	public boolean List(String genre) {
		if (isEmpty() == true) {
			return false;
		} else {
			audioCD current = head.next;

			while (current != null) {
				if (current.title.equals(genre)) {
					System.out.println("Title: " + current.title + " Artist: " + current.artist + " Genre: "
							+ current.genre + " Release Date: " + current.releaseDate + " Total Time: "
							+ current.totalTime + "\n");

					break;
				}
				current = current.next;
			}

			return true;
		}
	}

	public void Display(audioCD playlist) {
		audioCD current = playlist;
		while (current != null) {
			System.out.println("Title: " + current.title + " Artist: " + current.artist + " Genre: " + current.genre
					+ " Release Date: " + current.releaseDate + " Total Time: " + current.totalTime + "\n");
			current = current.next;
		}
	}

	public void Save() {

		try {
			PrintWriter writer = new PrintWriter("C:\\Users\\Asus\\Desktop\\albums.txt"); // create
																							// a
																							// new
																							// file
																							// for
																							// writing

			audioCD current = head.next;

			while (current != null) {
				writer.print(current.title);
				writer.print(" ," + current.artist);
				writer.print(" ," + current.genre);
				writer.print(" ," + current.releaseDate);
				writer.print(" ," + current.totalTime);
				String newline = System.getProperty("line.separator"); // create
																		// a new
																		// line
																		// to
																		// file.
				writer.print(newline); // add a new line to file.
				current = current.next;
			}
			writer.close(); // ends the writing processing to file.

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Load() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader("albums.txt")); // file
																											// reading

			String currentLine;

			while ((currentLine = reader.readLine()) != null) {
				String[] playList = new String[5];
				playList = currentLine.split(","); // delete commas function

				this.Add(playList[0], playList[1], playList[2], playList[3], playList[4]);
			}

			reader.close(); // this function finish to reading the file.

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		audioCD playList = new audioCD();

		Scanner input1 = new Scanner(System.in); // this input give integer
		Scanner input2 = new Scanner(System.in); // this input give string

		int choice = 0;
		String CDInfo;

		System.out.println(
				" 1. Add \n 2. Remove \n 3. Display \n 4. Search \n 5. List \n 6. Save \n 7. Load \n 8. Exit \n Your choice: ");

		do {
			choice = input1.nextInt();
			switch (choice) {
			case 1: {
				System.out.println(" Add information to be added: ");
				String title = null, artist = null, genre = null, releaseDate = null, totalTime = null;

				System.out.println("Title : ");
				title = input2.nextLine();

				System.out.println("Artist : ");
				artist = input2.nextLine();

				System.out.println("Genre : ");
				genre = input2.nextLine();

				System.out.println("ReleaseDate : ");
				releaseDate = input2.nextLine();

				System.out.println("TotalTime : ");
				totalTime = input2.nextLine();

				playList.Add(title, artist, genre, releaseDate, totalTime);
				break;
			}
			case 2: {
				System.out.println(" Enter title of the album to be removed: ");
				CDInfo = input2.nextLine();
				playList.Remove(CDInfo);
				break;
			}
			case 3: {
				playList.Display(playList.head.next);
				break;
			}
			case 4: {
				System.out.println(" Enter title of the album to be searched: ");
				CDInfo = input2.nextLine();
				playList.Search(CDInfo);
				break;
			}
			case 5: {
				System.out.println(" Enter the genre of the albums to be listed: ");
				CDInfo = input2.nextLine();
				playList.List(CDInfo);
				break;
			}
			case 6: {
				playList.Save();
				System.out.println(" Your playlist is saved.");
				break;
			}
			case 7: {
				playList.Load();
				System.out.println(" Your playlist is loaded.");
				break;
			}

			}
		} while (choice != 8);
		System.out.println("Bye!");

	}
}
