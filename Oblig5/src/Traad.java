
public class Traad implements Runnable{
	Rute rute;
	Rute previousRute;
	String str;
	LIsteMonitor t;
	String name;
	public Traad(Rute r, String s, LIsteMonitor t2, Rute l, String nn) {
		rute = r;
		str = s;
		t= t2;
		previousRute = l;
		name = nn;
	}

	public void run() {
		str = str + "--> " + rute.coordinate();
		while (rute.edge()!= true) {
			if (rute.getNord().tilTegn() == '.' && rute.getNord().equals(previousRute) == false) {
				previousRute = rute;
				rute = rute.gaa("n");
			} else if (rute.getSyd().tilTegn() == '.' && rute.getSyd().equals(previousRute) == false) {
				previousRute = rute;
				rute = rute.gaa("s");
			} else if (rute.getVest().tilTegn() == '.' && rute.getVest().equals(previousRute) == false) {
				previousRute = rute;
				rute = rute.gaa("v");
			} else if (rute.getOst().tilTegn() == '.' && rute.getOst().equals(previousRute) == false) {
				previousRute = rute;
				rute = rute.gaa("e");
			}
			   str = str + "--> " + rute.coordinate();
		}
			
			t.addString(str);
			System.out.println(name+":     "+ str);
		}
	

	public void run222() {
		String n = rute.getValidNeighboursNumber();
		
		if(n.length()-1==1) {
			
			System.out.println(this+ ":"+str);
			while (rute.getNord() instanceof HvitRute) {
				
				str=str+rute.coordinate() + "--> ";
				rute = rute.getNord();
				System.out.println(this+ ":"+str);	
		}	str=str+rute.coordinate();
		    System.out.println(this+ ":"+str);
		    System.out.println(rute.coordinate());
			if(rute.edge()) {
			t.addString(str);
			System.out.println("added a string");
			
		}
			

		}
		
	}
}
