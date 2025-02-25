
public class Spieler {
	private int punkte;
	private String name;

	Spieler(String name) {
		this.name = name;
	}

	public int getPunkte() {
		return punkte;
	}

	public String getName() {
		return this.name;
	}

	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}

	public void setName(String name) {
		this.name = name;
	}

}