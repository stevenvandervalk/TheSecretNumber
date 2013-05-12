package steven.vandervalk.jcu.edu.au.thesecretnumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class CardGenerator {
	public static ArrayList<ArrayList<Integer>> main(Integer[] magic_numbers) {

		Integer[] brownNumbers = magic_numbers;
		int n = brownNumbers.length;
		ArrayList<ArrayList<Integer>> cards = new ArrayList<ArrayList<Integer>>();
		int maxBitSetNumber = (int) Math.pow(2, n);
		Set<Integer> numbersGenerated = new HashSet<Integer>();

		for (int i = 0; i < n; ++i) {
			cards.add(new ArrayList<Integer>());
		}

		for (int bitSetNumber = maxBitSetNumber; bitSetNumber > 0; --bitSetNumber) {
			System.out.println(bitSetNumber);
			int numberForCards = 0;
			int[] bitSet = new int[n];

			for (int indexIntoBits = 0; indexIntoBits < n; ++indexIntoBits) {
				int onOrOff = (bitSetNumber >> indexIntoBits) % 2;
				bitSet[indexIntoBits] = onOrOff;
				numberForCards += (onOrOff * brownNumbers[indexIntoBits]);
			}
			if (!numbersGenerated.contains(numberForCards)) {
				numbersGenerated.add(numberForCards);
				System.out.println("numberForCards : " + numberForCards);

				for (int indexIntoBits = 0; indexIntoBits < n; ++indexIntoBits) {
					if (bitSet[indexIntoBits] == 1
							&& !cards.get(indexIntoBits).contains(
									numberForCards)) {
						cards.get(indexIntoBits).add(0, numberForCards);
					}
				}
			}
		}

		for (int i = 0; i < n; ++i) {
			for (Integer j : cards.get(i)) {
				System.out.print(j + " ");
			}
			System.out.println();
		}

		System.out.println("Cards done!");
		return cards;
	}

}
