package steven.vandervalk.jcu.edu.au.thesecretnumber;

import java.util.ArrayList;
import java.util.List;

public class Model implements Runnable {

	private enum Array_Type {
		fibonnaci, prime, binary;
	}

	public static Array_Type array_type;

	static int[] FIBONNACI_ARRAY = { 1, 2, 3, 5, 8, 13, 21, 34 };
	static int[] PRIME_ARRAY = { 1, 2, 3, 5, 7, 11, 13, 17, 19, 23 };
	static int[] BINARY_ARRAY = { 1, 2, 4, 8, 16, 32 };

	static List<Integer> magic_numbers = null;

	public static int magic_numbers_size;

	public static int getMagic_numbers_size() {
		return magic_numbers_size;
	}

	private static int max_length;

	public static ArrayList<ArrayList<Integer>> modelOfCards = new ArrayList<ArrayList<Integer>>();

	public static int[] convertIntegers(List<Integer> integers) {
		int[] ret = new int[integers.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = integers.get(i).intValue();
		}
		return ret;
	}

	// Code for obtaining cards in string form

	// EditText editText1 = (EditText) findViewById(R.id.TextView01);
	// int[] value = Model.convertIntegers(Model.modelOfCards.get(0));
	// String cardNumbers = Arrays.toString(value);
	// editText1.setText(cardNumbers);

	@Override
	// change to use Async Task
	public void run() {
		magic_numbers = new ArrayList<Integer>();
		// dummy data settings
		max_length = 20; // assume input from gui
		String value = "binary"; // assume input from gui #still to be wired

		array_type = Array_Type.valueOf(value); // surround with
												// try/catch

		// actual runtime

		System.out.println("making chosen array");

		BuildChosenArray();

		magic_numbers_size = magic_numbers.size();

		System.out.println(magic_numbers_size);

		modelOfCards = makeCards();

	}

	private ArrayList<ArrayList<Integer>> makeCards() {

		Integer[] magic_numbers_array = new Integer[magic_numbers.size()];
		magic_numbers.toArray(magic_numbers_array);
		ArrayList<ArrayList<Integer>> maintrol = CardGenerator
				.main(magic_numbers_array);
		return maintrol;

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

			System.out.println("completed magic numbers : "
					+ magic_numbers.toString());
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
