package Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {
	private static final ArrayList<Integer> arvotutnumerot = new ArrayList<Integer>();
	private static final ArrayList<Integer> arvotutLisanumerot = new ArrayList<Integer>();
	private static final ArrayList<Integer> syotetytnumerot = new ArrayList<Integer>();
	private static final ArrayList<Integer> syotetytLisanumerot = new ArrayList<Integer>();
	private static final ArrayList<Integer> yksiOikein = new ArrayList<Integer>();
	private static final ArrayList<Integer> kaksiOikein = new ArrayList<Integer>();
	private static final ArrayList<Integer> kolmeOikein = new ArrayList<Integer>();
	private static final ArrayList<Integer> neljaOikein = new ArrayList<Integer>();
	private static final ArrayList<Integer> viisiOikein = new ArrayList<Integer>();
	private static final ArrayList<Integer> nollaplusyks = new ArrayList<Integer>();
	private static final ArrayList<Integer> yksplusyks = new ArrayList<Integer>();
	private static final ArrayList<Integer> kaksplusyks = new ArrayList<Integer>();
	private static final ArrayList<Integer> kolmeplusyks = new ArrayList<Integer>();
	private static final ArrayList<Integer> neljaplusyks = new ArrayList<Integer>();
	private static final ArrayList<Integer> viisplusyks = new ArrayList<Integer>();
	private static final ArrayList<Integer> nollapluskaks = new ArrayList<Integer>();
	private static final ArrayList<Integer> ykspluskaks = new ArrayList<Integer>();
	private static final ArrayList<Integer> kakspluskaks = new ArrayList<Integer>();
	private static final ArrayList<Integer> kolmepluskaks = new ArrayList<Integer>();
	private static final ArrayList<Integer> neljapluskaks = new ArrayList<Integer>();
	private static final ArrayList<Integer> viispluskaks = new ArrayList<Integer>();
	private static final Scanner arpa = new Scanner(System.in);
	public static int kierrokset = 0;
	public static int osu;
	public static int lisa;
	public static int miljoonat = 0;
	public static int miltsit = 1;
	public static boolean ehto = true;
	
	
	/** Tämä ohjelma käy läpi käyttäjän syöttämän eurojackpot-rivin ja selvittää, kuinka kauan kyseisellä rivillä kestäisi voittaa
	 * päävoitto eurojackpotissa.
	 * @author Osasu Ehirhieme
	 * @param args
	 * @throws FileNotFoundException
	 */

	public static void main(String[] args) throws FileNotFoundException {

		try {
			valinta();
			lisavalinta();
		} catch (InputMismatchException e) {
			System.out.println("Virhe " + e + " aja ohjelma uudelleen uudelleensuorittamiseksi. ");

		}
		try {
			historia();
		} catch (Exception e) {
			System.out.println("Tapahtui virhe: " + e);
			System.exit(0);
		}

		while (viispluskaks.size() < 1) {
			lotto();
			lisanumerot();
			kierrosVertailu();
			kierrokset += 1;
			miljoonat += 1;
		}
		tulostus();

	}

	
	/** historia-metodi etsii tekstitiedostosta eurojackpotin aiemmin arvotut tulokset, muuttaa ne stringeiksi ja k�y l�pi ne
	 * verraten niitä käyttäjän syöttämään riviin.
	 * Lopussa käyttäjälle kerrotaan, minä päivämääränä hän olisi voittanut päävoiton syöttämällään rivillä.
	 * @throws FileNotFoundException
	 */
	
	public static void historia() throws FileNotFoundException {

		final Scanner lukija = new Scanner(new File("tulokset.txt"));
		String rivit = "";
		while (lukija.hasNext()) {
			rivit = rivit + lukija.nextLine() + " ";
		}
		String[] lista = rivit.split(" ");

		String[] paiva = new String[lista.length];
		String[] numero = new String[lista.length];
		String[] paanumero = new String[lista.length];
		String[] lisanumero = new String[lista.length];

		for (int i = 0; i < lista.length; i++) {
			String[] jako = lista[i].split(":");
			paiva[i] = jako[0];
			numero[i] = jako[1];

		}
		for (int i = 0; i < numero.length; i++) {
			String[] jako2 = numero[i].split("/");
			paanumero[i] = jako2[0];
			lisanumero[i] = jako2[1];
		}

		String syotettyrivi = "";
		Collections.sort(syotetytnumerot);
		Collections.sort(syotetytLisanumerot);
		for (int i = 0; i < syotetytnumerot.size(); i++) {
			if (i != 0) {
				syotettyrivi = syotettyrivi + ",";
			}
			syotettyrivi = syotettyrivi + syotetytnumerot.get(i);
		}
		syotettyrivi = syotettyrivi + "/" + syotetytLisanumerot.get(0) + "," + syotetytLisanumerot.get(1);

		for (int i = 0; i < numero.length; i++) {
			if (syotettyrivi.equals(numero[i])) {
				System.out.println("Olisit saanut 5+2 oikein: " + paiva[i]);
				break;
			}
		}
	}
	
	/** Kierrosvertailu-metodin tarkoituksena on käydä läpi koneen arpomat numerot ja verrata niitä käyttäjän syöttämiin numeroihin.
	 * Kaikki tulokset lisätään array-listiin, joka vastaa rivin osumia ja loppuun arvotut rivit arraylist tyhjennetään ja kierrosvertailu-metodi alkaa alusta. Lisäsin loppuun
	 * myös miljoonalaskurin, joka tulostaa kierroslukumäärän ohjelman arpoessa uusia kierroksia. 
	 * 
	 * Huom: Tässä kyseisessä kohdassa switch case:n käyttö ei onnistu, kahden eri arvon johdosta. Tästä syystä, jouduin käyttämään if-lauseita, jotka vaativat enemmän tehoa.
	 */

	public static void kierrosVertailu() {

		for (int i : arvotutnumerot) {
			if (syotetytnumerot.contains(i)) {
				osu += 1;

			}

		}
		for (int i : arvotutLisanumerot) {
			if (syotetytLisanumerot.contains(i)) {
				lisa += 1;
			}
		}

		if ((osu == 1) && (lisa == 0)) {
			yksiOikein.add(1);
		}
		if ((osu == 2) && (lisa == 0)) {
			kaksiOikein.add(1);
		}
		if ((osu == 3) && (lisa == 0)) {
			kolmeOikein.add(1);
		}
		if ((osu == 4) && (lisa == 0)) {
			neljaOikein.add(1);
		}
		if ((osu == 5) && (lisa == 0)) {
			viisiOikein.add(1);
		}
		if ((osu == 0) && (lisa == 1)) {
			nollaplusyks.add(1);
		}
		if ((osu == 1) && (lisa == 1)) {
			yksplusyks.add(1);
		}
		if ((osu == 2) && (lisa == 1)) {
			kaksplusyks.add(1);
		}
		if ((osu == 3) && (lisa == 1)) {
			kolmeplusyks.add(1);
		}
		if ((osu == 4) && (lisa == 1)) {
			neljaplusyks.add(1);
		}
		if ((osu == 5) && (lisa == 1)) {
			viisplusyks.add(1);
		}
		if ((osu == 0) && (lisa == 2)) {
			nollapluskaks.add(1);
		}
		if ((osu == 1) && (lisa == 2)) {
			ykspluskaks.add(1);
		}
		if ((osu == 2) && (lisa == 2)) {
			kakspluskaks.add(1);
		}
		if ((osu == 3) && (lisa == 2)) {
			kolmepluskaks.add(1);
		}
		if ((osu == 4) && (lisa == 2)) {
			neljapluskaks.add(1);
		}
		if ((osu == 5) && (lisa == 2)) {
			viispluskaks.add(1);
		}
		if (miljoonat == 1000000) {
			System.out.println(miltsit + " miljoonaa rivi� k�yty l�pi");
			miljoonat = 0;
			miltsit = miltsit + 1;
		}
		osu = 0;
		lisa = 0;
		arvotutnumerot.clear();
		arvotutLisanumerot.clear();

	}

	
	/**lotto-metodi arpoo uudet päänumerot, joita tullaan vertailemaan kierrosvertailu-metodissa 
	 * 
	 */
	
	public static void lotto() {
		Random rand = new Random();
		while (arvotutnumerot.size() <= 4) {
			int luku = (rand.nextInt(50) + 1);
			if (arvotutnumerot.contains(luku)) {
				int luku1 = (rand.nextInt(50) + 1);
				arvotutnumerot.add(luku1);
			} else {
				arvotutnumerot.add(luku);
			}
		}

	}

	/** lisarvonta-metodi arpoo uudet päänumerot, joita tullaan vertailemaan kierrosvertailu-metodissa 
	 * 
	 */
	
	public static void lisanumerot() {
		Random rand = new Random();
		while (arvotutLisanumerot.size() <= 1) {
			int luku = (rand.nextInt(10) + 1);
			if (arvotutLisanumerot.contains(luku)) {
				int luku1 = (rand.nextInt(10) + 1);
				arvotutLisanumerot.add(luku1);
			} else {
				arvotutLisanumerot.add(luku);
			}
		}

	}
	
	/** tulostus-metodi alkaa, kun käyttäjän syöttämä rivi täsmää koneen arpomaa riviä ja tulostaa tietoa käydyistä kierroksista.
	 * 
	 */

	public static void tulostus() {
		int vuodet = kierrokset / 52;
		NumberFormat nf = NumberFormat.getNumberInstance();
		String uudetvuodet = nf.format(vuodet);

		int kiekat = kierrokset;
		String yht = nf.format(kiekat);

		int rahat = kierrokset * 2;
		NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		String hillo = currency.format(rahat);

		Collections.sort(syotetytnumerot);
		System.out.println("Valitsemasi rivi: ");
		for (int i : syotetytnumerot)
			System.out.print(i + " ");
		System.out.printf(" + ");

		for (int i : syotetytLisanumerot)
			System.out.print(i + " ");

		System.out.println("      ");
		System.out.println("Tulokset: ");
		System.out.println("------------------------------------");
		System.out.println("5+2 oikein: " + viispluskaks.size());
		System.out.println("5+1 oikein: " + viisplusyks.size());
		System.out.println("5+0 oikein: " + viisiOikein.size());
		System.out.println("4+2 oikein: " + neljapluskaks.size());
		System.out.println("4+1 oikein: " + neljaplusyks.size());
		System.out.println("4+0 oikein: " + neljaOikein.size());
		System.out.println("3+2 oikein: " + kolmepluskaks.size());
		System.out.println("3+1 oikein: " + kolmeplusyks.size());
		System.out.println("3+0 oikein: " + kolmeOikein.size());
		System.out.println("2+2 oikein: " + kakspluskaks.size());
		System.out.println("2+1 oikein: " + kaksplusyks.size());
		System.out.println("Arvontakierroksia: " + yht);
		System.out.println("Vuosia eurojackpot voittoon valitsemallasi rivillä: " + uudetvuodet);
		System.out.println("K�ytetty euromäärä viikottaisiin kierroksiin: " + hillo);
	}
	
	/** Valinta-metodi antaa käyttäjän valita itselleen 5 päänumeroa, mutta ei anna lisätä niitä listaan, jos tietyt ehdot eivät täyty.
	 * 
	 */

	public static void valinta() {
		System.out.println("Syötä haluamasi numerot (1-50): ");
		int valinta = 0;
		while (syotetytnumerot.size() <= 4) {
			valinta = arpa.nextInt();

			if ((valinta > 0) && (valinta < 51) && !(syotetytnumerot.contains(valinta))) {
				syotetytnumerot.add(valinta);
			} else {
				System.out.println("Tämä numero ei kelpaa. Yritä uudestaan.");
			}

		}

	}
	
	/** Lisavalinta-metodi antaa käyttäjän valita itselleen 2 lisänumeroa, mutta ei anna lisätä niitä listaan, jos tietyt ehdot eivät täyty.
	 * 
	 */

	public static void lisavalinta() {
		System.out.println("Syötä haluamasi lisänumerot (1-10): ");
		int valinta = 0;
		while (syotetytLisanumerot.size() <= 1) {
			valinta = arpa.nextInt();

			if ((valinta > 0) && (valinta < 11) && !(syotetytLisanumerot.contains(valinta))) {
				syotetytLisanumerot.add(valinta);
			} else {
				System.out.println("Tämä numero ei kelpaa. Yritä uudestaan.");
			}

		}

	}
}