import java.util.ArrayList;
import java.util.List;

public abstract class Rute {
	private Rute nord, syd, vest, ost;
	private int x, y;
	private char tegn;
	private static Labyrint la;
	public static int count;
	private List<Rute> currentRutelist = new ArrayList<Rute>();

	public Rute(int a, int b, char c, Labyrint g) {
//		this x is actually colNum, y is actually a row num
		x = a;
		y = b;
		tegn = c;
		setLabyrint(g);
	}

	public abstract char tilTegn();

	public String coordinate() {
		return "(" + x + "," + y + ")";
	}

	public boolean CurrentRuteHasNext(Rute c) {
		if (c.getSyd().tilTegn() == '.' || c.getVest().tilTegn() == '.' || c.getOst().tilTegn() == '.'
				|| c.getNord().tilTegn() == '.') {
			return true;
		} else {
			return false;
		}
	}

	public Liste<String> finnUtvei(Liste<String> t) {

		LIsteMonitor monitor = new LIsteMonitor(t);
		String s = this.coordinate();
		Rute theRute = this;
		Nthread(theRute, monitor, s);
		Sthread(theRute, monitor, s);
		Vthread(theRute, monitor, s);
		Ethread(theRute, monitor, s);
		System.out.println("Total number of Threads: "+ count);
		return t;
	}

	public void Nthread(Rute currentRute, LIsteMonitor m, String currentString) {
		if (currentRute.hasN()) {
			if (currentRute.getNord().tilTegn() == '.') {

				if (currentRute.getNord().edge()) {
					currentString += "--> " + currentRute.getNord().coordinate();
					m.addString(currentString);
//				System.out.println("NOR end add a string "+currentString);
				} else {

					Thread newthread = new Thread(
							new Traad(currentRute.getNord(), currentString, m, currentRute, "N Direction"));
					newthread.start();
//					System.out.println("a N thread started");
					try {
						newthread.join();
						count++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
	}

	public void Sthread(Rute currentRute, LIsteMonitor m, String currentString) {
		if (currentRute.hasS()) {

			if (currentRute.getSyd().tilTegn() == '.') {

				if (currentRute.getSyd().edge()) {
					currentString = currentString + "--> " + currentRute.getSyd().coordinate();
					m.addString(currentString);
//				System.out.println("South end add a string "+ currentString);
				} else {
					Thread newthread = new Thread(
							new Traad(currentRute.getSyd(), currentString, m, currentRute, "S Direction"));
					newthread.start();
//					System.out.println("a S thread started");
					try {
						newthread.join();
						count++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}

	public void Vthread(Rute currentRute, LIsteMonitor m, String currentString) {
		if (currentRute.hasV()) {

			if (currentRute.getVest().tilTegn() == '.') {

				if (currentRute.getVest().edge()) {
					currentString = currentString + "--> " + currentRute.getVest().coordinate();
					m.addString(currentString);
//				System.out.println("South end add a string "+ currentString);
				} else {
					Thread newthread = new Thread(
							new Traad(currentRute.getVest(), currentString, m, currentRute, "V Direction"));
					newthread.start();
//					System.out.println("a V thread started");
					try {
						newthread.join();
						count++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}

	public void Ethread(Rute currentRute, LIsteMonitor m, String currentString) {
		if (currentRute.hasE()) {
			if (currentRute.getOst().tilTegn() == '.') {

				if (currentRute.getOst().edge()) {
					currentString = currentString + "--> " + currentRute.getOst().coordinate();
					m.addString(currentString);
//				System.out.println("South end add a string "+ currentString);
				} else {
					Thread newthread = new Thread(
							new Traad(currentRute.getOst(), currentString, m, currentRute, "E Direction"));
					newthread.start();
//					System.out.println("a E thread started");
					try {
						newthread.join();
						count++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	public Rute gaa(String s) {
		switch (s) {
		case "n":
			return this.getNord();

		case "s":
			return this.getSyd();

		case "v":
			return this.getVest();

		default:
			return this.getOst();
		}

	}

	public String getValidNeighboursNumber() {
		String n = "";
		if (this.getNord() instanceof HvitRute) {
			n += "n";
		}
		if (this.getSyd() instanceof HvitRute) {
			n += "s";
		}
		if (this.getOst() instanceof HvitRute) {
			n += "e";
		}
		if (this.getVest() instanceof HvitRute) {
			n += "v";
		}
		return n;
	}

	public boolean edge() {
		if (getX() == 0 || getY() == 0 || getX() == getLabyrint().getColNum() - 1
				|| getY() == getLabyrint().getRowNum() - 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasN() {
		if (this.getY() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean hasS() {
		if (this.getY() == getLabyrint().getRowNum() - 1) {
			return false;
		} else {
			return true;
		}
	}

	public boolean hasV() {
		if (this.getX() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean hasE() {
		if (this.getX() == getLabyrint().getColNum() - 1) {
			return false;
		} else {
			return true;
		}
	}

	public Rute getNord() {
		return nord;
	}

	public void setNord(Rute nord) {
		this.nord = nord;
	}

	public Rute getSyd() {
		return syd;
	}

	public void setSyd(Rute syd) {
		this.syd = syd;
	}

	public Rute getVest() {
		return vest;
	}

	public void setVest(Rute vest) {
		this.vest = vest;
	}

	public Rute getOst() {
		return ost;
	}

	public void setOst(Rute ost) {
		this.ost = ost;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getTegn() {
		return tegn;
	}

	public void setTegn(char tegn) {
		this.tegn = tegn;
	}

	public Labyrint getLabyrint() {
		return la;
	}

	public void setLabyrint(Labyrint la) {
		this.la = la;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Rute))
			return false;
		Rute other = (Rute) obj;
		if (nord == null) {
			if (other.nord != null)
				return false;
		} else if (!nord.equals(other.nord))
			return false;
		if (ost == null) {
			if (other.ost != null)
				return false;
		} else if (!ost.equals(other.ost))
			return false;
		if (syd == null) {
			if (other.syd != null)
				return false;
		} else if (!syd.equals(other.syd))
			return false;
		if (tegn != other.tegn)
			return false;
		if (vest == null) {
			if (other.vest != null)
				return false;
		} else if (!vest.equals(other.vest))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
