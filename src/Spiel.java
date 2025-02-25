import java.util.Random;

public class Spiel {
    private static final int REIHEN = 4;
    private static final int SPALTEN = 6;

    char[][] feldOffen = new char[REIHEN][SPALTEN];
    char[][] feldBedeckt = new char[REIHEN][SPALTEN];

    Random zufall = new Random();

    public void verstecktesFeldErstellen() {
        char buchstabeGroß = 'A';
        char buchstabeKlein = 'a';
        for (int i = 0; i < REIHEN; i++) {
            for (int j = 0; j < SPALTEN; j++) {
                if (i < 2) {
                    feldBedeckt[i][j] = buchstabeGroß++;
                } else {
                    feldBedeckt[i][j] = buchstabeKlein++;
                }
            }
        }
    }

    public void verstecktesFeldAusgeben() {
        for (int i = 0; i < REIHEN; i++) {
            for (int j = 0; j < SPALTEN; j++) {
                System.out.print(feldBedeckt[i][j] + "    ");
            }
            System.out.println();
        }
    }

    public void zufälligMischen() {
        for (int i = REIHEN - 1; i >= 0; i--) {
            for (int j = SPALTEN - 1; j >= 0; j--) {
                int zufallsReihe = zufall.nextInt(i + 1);
                int zufallsSpalte = zufall.nextInt(j + 1);
                char temp = feldBedeckt[i][j];
                feldBedeckt[i][j] = feldBedeckt[zufallsReihe][zufallsSpalte];
                feldBedeckt[zufallsReihe][zufallsSpalte] = temp;
            }
        }
    }

    public void spielFeldInitialisieren() {
        for (int i = 0; i < REIHEN; i++) {
            for (int j = 0; j < SPALTEN; j++) {
                feldOffen[i][j] = '_';
            }
        }
    }

    public void spielfeldAusgeben() {
        for (int i = 0; i < REIHEN; i++) {
            for (int j = 0; j < SPALTEN; j++) {
                System.out.print(feldOffen[i][j] + "    ");
            }
            System.out.println();
        }
    }

    public void start() {
        spielFeldInitialisieren();
        verstecktesFeldErstellen();
        zufälligMischen();
    }

    public boolean istGewinn(int reihe1, int spalte1, int reihe2, int spalte2) {
        if (feldBedeckt[reihe1][spalte1] + 32 == feldBedeckt[reihe2][spalte2]
                || feldBedeckt[reihe1][spalte1] - 32 == feldBedeckt[reihe2][spalte2]) {
            feldOffen[reihe1][spalte1] = feldBedeckt[reihe1][spalte1];
            feldOffen[reihe2][spalte2] = feldBedeckt[reihe2][spalte2];
            return true;
        }
        return false;
    }

    public boolean spielfeldKomplettAufgedeckt() {
        for (int i = 0; i < REIHEN; i++) {
            for (int j = 0; j < SPALTEN; j++) {
                if (feldOffen[i][j] != feldBedeckt[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
