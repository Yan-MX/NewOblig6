import java.io.File;
	import java.io.FileNotFoundException;
	import java.util.Scanner;
class hovedprogram{
    public static void main(String[] args) throws FileNotFoundException {
    	 String filnavn ="7.in";
	       
	        
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
//                System.out.println(startKol);
//                System.out.println(startRad);
                Liste<String> utveier = l.finnUtveiFra(startKol, startRad);
//                System.out.println("here");
                if (utveier.stoerrelse() != 0) {
//                	System.out.println("size of the list: "+utveier.stoerrelse());
                    for (String s : utveier) {
                        System.out.println(s);
                    }
                    System.out.println("size of the list: "+utveier.stoerrelse());
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