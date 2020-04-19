
public class Traad implements Runnable {
	Rute rute;
	Rute previousRute;
	String str;
	LIsteMonitor t;
	String name;

	public Traad(Rute r, String s, LIsteMonitor t2, Rute l, String nn) {
		rute = r;
		str = s;
		t = t2;
		previousRute = l;
		name = nn;
	}

	public void run() {
		str = str + "--> " + rute.coordinate();
		Rute check = rute;
		boolean status = false;
		Rute checkP = previousRute;
		while (rute.getValidNeighboursNumber().length() > 1) {
			
			
				if (check.getNord().tilTegn() == '.' && check.getNord().equals(previousRute) == false) {
					previousRute = rute;
					rute = rute.gaa("n");
					status = true;
				}
				if (check.getSyd().tilTegn() == '.' && check.getSyd().equals(checkP) == false) {
					if (status) {

						check.Sthread(check, t, str);
					} else {
						previousRute = rute;
						rute = rute.gaa("s");
						status = true;
					}
				}
				if (check.getVest().tilTegn() == '.' && check.getVest().equals(checkP) == false) {
					if (status) {

						check.Vthread(check, t, str);
					} else {
						previousRute = rute;
						rute = rute.gaa("v");
						status = true;
					}
				}
				if (check.getOst().tilTegn() == '.' && check.getOst().equals(checkP) == false) {
					if (status) {

						check.Ethread(check, t, str);
					} else {
						previousRute = rute;
						rute = rute.gaa("e");
					}
				}
				check = rute;
				checkP = previousRute;
				str = str + "--> " + rute.coordinate();

				status = false;
			
		}
		if(rute.edge()) {
			t.addString(str);
			}
		}
		
	

	public void run222() {
		String n = rute.getValidNeighboursNumber();

		if (n.length() - 1 == 1) {

			System.out.println(this + ":" + str);
			while (rute.getNord() instanceof HvitRute) {

				str = str + rute.coordinate() + "--> ";
				rute = rute.getNord();
				System.out.println(this + ":" + str);
			}
			str = str + rute.coordinate();
			System.out.println(this + ":" + str);
			System.out.println(rute.coordinate());
			if (rute.edge()) {
				t.addString(str);
				System.out.println("added a string");

			}

		}

	}
}
