import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*This is the main program, and it needs user input of the file name (e.g. 3.in ) in the terminal
 *Other than the maze itself, I also print out each coordinate
 *When print each string in the list, the number of Thread is also added
 *a Thread is created only if the 'rute' can move two steps further, if a 'rute' can only move one step further and get to an end, 
 * it will not generate a thread, it will be added to the list directly
 * 
 * 
 * */




class hovedprogram {
	public static void main(String[] args) throws FileNotFoundException {
		String filnavn = "1.in";

		File fil = new File(filnavn);
		Labyrint l = null;
		l = Labyrint.lesFraFil(fil);

		System.out.println(l);
		l.printMazeC();

		// les start-koordinater fra standard input
		Scanner inn = new Scanner(System.in);
		System.out.println("Skriv inn koordinater <kolonne> <rad> ('a' for aa avslutte)");
		String[] ord = inn.nextLine().split(" ");
		while (!ord[0].equals("a")) {

			try {
				int startKol = Integer.parseInt(ord[0]);
				int startRad = Integer.parseInt(ord[1]);
				Liste<String> utveier = l.finnUtveiFra(startKol, startRad);
				if (utveier.stoerrelse() != 0) {
					for (String s : utveier) {
						System.out.println(s);
					}
					System.out.println("size of the list: " + utveier.stoerrelse());
				} else {
					System.out.println("Ingen utveier.");
				}
				System.out.println();
			} catch (NumberFormatException e) {
				System.out.println("Ugyldig input!");
			}

			System.out.println("Skriv inn nye koordinater ('a' for aa avslutte)");
			ord = inn.nextLine().split(" ");
		}
	}
}