/**
 *
 *  @author Nowak Piotr PD3023
 *
 */

/**
 *
Wyobraźmy sobie, że z sieci przychodzą i zapisywane są na nasz dysk jakieś dane. Każdą kolejną porcję przychodzących danych nazwiemy pakietem.
Każdy kolejny przychodzący pakiet (licząc od drugiego do piątego włącznie) ma rozmiar dwa razy większy niż poprzedni, a poczynając od szóstego - ma rozmiar 3 razy większy niż poprzedni.
Program ma określić i podać  ile pakietów zmieści się na dysku,  dla każdego mieszczącego się pakietu wypisać informację o jego numerze i wielkości oraz podać zajmowaną przez pakiety przestrzeń dyskową.
Dane (pobierane z pliku {user.home}/pakiety.txt):
•	rozmiar pierwszego pakietu w bajtach,
•	rozmiar dostępnej przestrzeni dyskowej w megabajtach.
Dane są zapisane jako liczby całkowite rozdzielone białymi znakami.
Wyniki (wypisywane na konsolę w kolejnych wierszach):
•	liczba pakietów mieszczących się w dostępnej przestrzeni dyskowej,
•	dla każdego zaakceptowanego (mieszczącego się jeszcze na dysku) pakietu jego numer i wielkość w bajtach.
•	rozmiar zajmowanej przez pakiety przestrzeni dyskowej
Przykładowo, jeśli w pliku {user.home}/pakiety.txt mamy następujące dane:
100 1
to wynik na konsoli winien wyglądać tak:
10
1 100
2 200
3 400
4 800
5 1600
6 4800
7 14400
8 43200
9 129600
10 388800
583900
W przypadku wystąpienia błędów (np. brak pliku, wadliwe dane) należy wypisać na konsoli TYLKO trzy gwiazdki:
***
i zakończyć działanie programu.
Nazwa pliku, jego umiejscowienie i postać oraz postać wyniku na konsoli jest obowiązkowa. Rozwiązanie nie spełniające tych wymagań otrzymują 0 punktów.
Uwaga: proszę w żadnym razie nie linkować pliku jako zasobu do projektu Eclipse.
Utworzona przez generator projektów klasa Main zawiera fragment pomocny dla uzyskania wymaganej nazwy pliku.
Klasę Main należy uzupełnić, tak, aby uzyskać właściwe rozwiązanie.
 *
 */

package zad3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String fname = System.getProperty("user.home") + "/pakiety.txt";
		/**
		 * Lista wszystkich pakietow zapisanych na dysku
		 */
		List<Pakiet> wszystkiePakiety = new ArrayList<Pakiet>();

		BufferedReader br = null;
		/**
		 * uzyta przestrzen dyskowa
		 */
		long useDiskSpace = 0;
		/**
		 * Przeformatowane dane z pliku
		 */
		String result = "";

		/**
		 * Licznik pakietow zapisanych na dysku
		 */
		int cnt = 0;

		/**
		 * flaga okreslajaca koniec zapisu pakietow na dysku
		 */
		boolean isFinished=false;

		try {
			br = new BufferedReader(new FileReader(fname));
			String line;
			// wczytanie danych z pliku
			while ((line = br.readLine()) != null) {
				// usuniecie bialych znakow
				line = format(line);

				if (!line.trim().isEmpty())
					result += (line+" ");
			}

			String[] nums = result.split(" ");

			// sprawdzenie poprawnosci danych w pliku - dane powinny
			// zawierac rozmiar pakietu i pojemnosc dysku
			if (nums.length != 2)
				throw new Exception();

			// wczytanie danych z pliku, Metoda trim dodatkowo usuwa biale
			// znaki z poczatku i konca wczytanej danej
			long size = Long.parseLong(nums[0].trim());
			long diskSpace = Long.parseLong(nums[1].trim());

			// zamiana jednostek MB->bajt
			diskSpace *= 1048576;

			// analiza danych
			while (!isFinished) {
				cnt++;
				if (cnt >= 2 && cnt <= 5)
					size *= 2;
				else if (cnt >= 6)
					size *= 3;

				if (useDiskSpace + size <= diskSpace) {
					Pakiet pakiet = new Pakiet(cnt, size);
					wszystkiePakiety.add(pakiet);
					useDiskSpace += size;
				}else
					isFinished=true;
			}

			// wypisanie wyników - liczba pakietow, pakiety i uzyta przestrzen
			// dyskowa
			//test
			System.out.println(--cnt);

			for (Pakiet p : wszystkiePakiety)
				System.out.println(p);

			System.out.println(useDiskSpace);
		} catch (Exception e) {
			System.out.println("***");

		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("***");
				}
		}
	}

	private static String format(String line) {
		line = line.replace("\n", " ");
		line = line.replace("\t", " ");
		line = line.trim();

		return line;
	}
}