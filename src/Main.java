import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Spiel spiel = new Spiel();
        int wahl;
        String name1, name2;
        int punkte1 = 0, punkte2 = 0;
        int spielerDran = 1;

        do {
            menüAusgeben();
            wahl = sc.nextInt();

            if (wahl == 2) {
                System.out.println("Spiel beendet!");
                break;
            } else if (wahl == 1) {
                spiel.start();
                spiel.spielfeldAusgeben();
                spiel.verstecktesFeldAusgeben();

                System.out.println("Geben Sie den Namen von Spieler 1 ein: ");
                name1 = sc.next();
                Spieler spieler1 = new Spieler(name1);

                System.out.println("Geben Sie den Namen von Spieler 2 ein: ");
                name2 = sc.next();
                Spieler spieler2 = new Spieler(name2);

                do {
                    Spieler aktuellerSpieler = (spielerDran == 1) ? spieler1 : spieler2;
                    System.out.println(aktuellerSpieler.getName() + " ist an der Reihe");

                    int[] karte1 = karteWählen(sc, spiel);
                    int[] karte2 = karteWählen(sc, spiel);

                    if (spiel.istGewinn(karte1[0], karte1[1], karte2[0], karte2[1])) {
                        System.out.println("Treffer!");
                        if (spielerDran == 1) {
                            spieler1.setPunkte(++punkte1);
                        } else {
                            spieler2.setPunkte(++punkte2);
                        }
                        spiel.spielfeldAusgeben();
                    }

                    System.out.println("Aktueller Spielstand:\n" + spieler1.getName() + ": " + punkte1 + "\n"
                            + spieler2.getName() + ": " + punkte2);

                    spielerDran = (spielerDran == 1) ? 2 : 1;
                } while (!spiel.spielfeldKomplettAufgedeckt());

                if (punkte1 > punkte2) {
                    System.out.println(spieler1.getName() + " gewinnt!");
                } else if (punkte2 > punkte1) {
                    System.out.println(spieler2.getName() + " gewinnt!");
                } else {
                    System.out.println("Unentschieden!");
                }
                break;
            }
        } while (wahl != 1 && wahl != 2);

        sc.close();
    }

    public static void menüAusgeben() {
        System.out.println("MEMORY\n1 - Ein neues Spiel starten\n2 - Programm beenden");
    }

    public static int[] karteWählen(Scanner sc, Spiel spiel) {
        int[] koordinaten = new int[2];
        System.out.println("Zeile:");
        koordinaten[0] = sc.nextInt();
        System.out.println("Spalte:");
        koordinaten[1] = sc.nextInt();
        System.out.println("An der Stelle befindet sich ein: " + spiel.feldBedeckt[koordinaten[0]][koordinaten[1]]);
        return koordinaten;
    }
}
