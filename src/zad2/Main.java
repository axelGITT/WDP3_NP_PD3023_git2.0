/**
 *
 *  @author Nowak Piotr PD3023
 *
 */

/**
 *
Napisać program, który z pliku {user.home}/tab.txt  wczytuje liczby całkowite (liczby w pliku są rozdzielone dowolnymi białymi znakami)  i tworzy tablicę, zawierającą te liczby .
W tak zainicjowanej tablicy program znajduje wartość maksymalną oraz wszystkie indeksy w tablicy gdzie taka wartość się znajduje.
Program wypisuje na konsoli:
w pierwszym wierszu - wszystkie elementy tablicy, rozdzielone spacjami
w drugim wierszu - wartość  maksymalną,
w trzecim wierszu - indeksy tablicy gdzie ta wartość się znajduje.
Przykładowo, jeśli plik {user.home}/tab.txt zawiera:
1 5 5 3
-1 2 5 4
to na konsoli otrzymujemy:
1 5 5 3 -1 2 5 4
5
1 2 6
W przypadku wystąpienia błędów (np. brak pliku, wadliwe dane) należy obsłużyć błąd -  wypisać na konsoli TYLKO trzy gwiazdki:
***
i zakończyć działanie programu.
Nazwa pliku, jego umiejscowienie i postać oraz postać wyniku na konsoli jest obowiązkowa. Rozwiązanie nie spełniające tych wymagań otrzymują 0 punktów.
Uwaga: proszę w żadnym razie nie linkować pliku jako zasobu do projektu Eclipse.
Uwagi dodatkowe:
•	należy się przygotować na to, że w pliku jest dowolna ilość liczb,
•	wobec tego należy użyć klasy ArrayList do wczytywania danych, ale wymaganie stworzenia tablicy też jest obowiązkowe.
Utworzona przez generator projektów klasa Main zawiera fragment pomocny dla uzyskania wymaganej nazwy pliku.
Klasę Main należy uzupełnić, tak, aby uzyskać właściwe rozwiązanie.
 *
 */

package zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String fname = System.getProperty("user.home") + "/tab.txt";

		BufferedReader br = null;
		String line = null;
		String result = "";
		int temp = Integer.MIN_VALUE;
		List<Integer> indexes = new ArrayList<>();

		try {
			br = new BufferedReader(new FileReader(fname));
			// wczytanie danych z pliku
			while ((line = br.readLine()) != null) {
				// usuniecie bialych znakow
				line = format(line);

				if (!line.trim().isEmpty())
					result += (line + " ");
			}

			// tablica liczb wczytanych z pliku
			String[] nums = result.split(" ");

			// analiza danych - iteracja po elementach tablicy
			for (int i = 0; i < nums.length; i++) {

				if (temp < Integer.parseInt(nums[i])) {
					temp = Integer.parseInt(nums[i]);
					indexes.clear();
					indexes.add(i);
				} else if (temp == Integer.parseInt(nums[i]))
					indexes.add(i);
			}

			// wyswietlenie wynikow
			for (int i = 0; i < nums.length; i++) {
				// wszystkie liczby tablicy
				System.out.print(nums[i] + " ");
			}
			// wartosc max
			System.out.println("\n" + temp);

			// indexy wartosci max
			for (Integer i : indexes) {
				System.out.print(i + " ");
			}

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
