package steven.vandervalk.jcu.edu.au.thesecretnumber;

import java.util.ArrayList;
import java.util.List;

public class Model implements Runnable {

	private enum Array_Type {
		fibonnaci, prime, binary;
	}

	Array_Type array_type;

	static int[] FIBONNACI_ARRAY = { 1, 2, 3, 5, 8, 13, 21, 34 };
	static int[] PRIME_ARRAY = { 1, 2, 3, 5, 7, 11, 13, 17, 19, 23 };
	static int[] BINARY_ARRAY = { 1, 2, 4, 8, 16, 32 };

	static List<Integer> magic_numbers = null;

	private static int max_length;

	@Override
	public void run() {
		magic_numbers = new ArrayList<Integer>();
		// dummy data settings
		max_length = 50; // assume input from gui
		String value = "binary"; // assume input from gui #still to be wired

		array_type = Array_Type.valueOf(value); // surround with
												// try/catch

		// actual runtime

		BuildChosenArray();
		makeCards();

	}

	private void makeCards() {

		Integer[] magic_numbers_array = new Integer[magic_numbers.size()];
		magic_numbers.toArray(magic_numbers_array);
		ArrayList<ArrayList<Integer>> maintrol = CardGenerator
				.main(magic_numbers_array);

	}

	public void BuildChosenArray() {

		// using the max number and limit the array to the number nearest to but
		// less than max_length

		switch (array_type) {
		case prime:

			for (int i : PRIME_ARRAY) {
				if (i < max_length) {
					magic_numbers.add(i);
				}
			}
			System.out.println(magic_numbers.toString());
			break;
		case binary:

			for (int i : BINARY_ARRAY) {
				if (i < max_length) {
					magic_numbers.add(i);
				}
			}
			System.out.println(magic_numbers.toString());
			break;
		case fibonnaci:

			for (int i : FIBONNACI_ARRAY) {
				if (i < max_length) {
					magic_numbers.add(i);
				}
			}

			System.out.println(magic_numbers.toString());
			break;
		}
	}

	/**
	 * @return the mAX_LENGTH
	 */
	public static int getMAX_LENGTH() {
		return max_length;
	}

	/**
	 * @param mAX_LENGTH
	 *            the mAX_LENGTH to set
	 */
	public static void setMAX_LENGTH(int mAX_LENGTH) {
		max_length = mAX_LENGTH;
	}

}
