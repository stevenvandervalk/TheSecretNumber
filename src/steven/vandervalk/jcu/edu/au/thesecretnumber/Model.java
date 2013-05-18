package steven.vandervalk.jcu.edu.au.thesecretnumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Model implements Runnable {

	public enum Array_Type {
		FIBONACCI, PRIME, BINARY;
	}

	public static Array_Type array_type;

	public static boolean player_guess_mode = false; // if false then in
														// computer guess mode

	static int[] FIBONACCI_ARRAY = { 1, 2, 3, 5, 8, 13, 21, 34 };
	static int[] PRIME_ARRAY = { 1, 2, 3, 5, 7, 11, 13, 17, 19, 23 };
	static int[] BINARY_ARRAY = { 1, 2, 4, 8, 16, 32 };

	static List<Integer> magic_numbers = null;

	static float timer_clock = 0;

	static float time_trial_clock;

	static float completed_timer;

	static int computer_secret_number;

	static int rolling_sum;

	public static int magic_numbers_size;

	public static int getMagic_numbers_size() {
		return magic_numbers_size;
	}

	public static int max_length;

	public static ArrayList<ArrayList<Integer>> modelOfCards = new ArrayList<ArrayList<Integer>>();

	public static int[] convertIntegers(List<Integer> integers) {
		int[] ret = new int[integers.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = integers.get(i).intValue();
		}
		return ret;
	}

	public static void generateComputerSecretNumber() {
		Random rn = new Random();
		int minimum = 0;
		int range = max_length - minimum + 1;
		computer_secret_number = rn.nextInt(range) + minimum;
		// computer_secret_number = (int) (Random() * (max_length - 0));
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

		// // surround with
		// try/catch

		// actual runtime

		System.out.println("making chosen array");

		BuildChosenArray();

		// magic_numbers_size = magic_numbers.size();

		System.out.println(magic_numbers_size);

		modelOfCards = makeCards();

	}

	public static Array_Type getArray_type() {
		return array_type;
	}

	public static void setArray_type(Array_Type array_type) {
		Model.array_type = array_type;
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
		case PRIME:

			for (int i : PRIME_ARRAY) {
				if (i < max_length) {
					magic_numbers.add(i);
				}
			}
			System.out.println(magic_numbers.toString());
			break;
		case BINARY:

			for (int i : BINARY_ARRAY) {
				if (i < max_length) {
					magic_numbers.add(i);
				}
			}
			System.out.println(magic_numbers.toString());
			break;
		case FIBONACCI:

			for (int i : FIBONACCI_ARRAY) {
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

	public static String modelOfCardsToString() {
		String cardsToString = "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < magic_numbers_size; i++) {
			// sb = new StringBuilder();
			cardsToString = (Arrays.toString(Model
					.convertIntegers(Model.modelOfCards.get(i))));
			sb.append(cardsToString);
		}
		String model = sb.toString();

		return model;
	}

	public static String PrintStatus() {
		System.out.println("Current model variables :");
		System.out.println("Array type : " + Model.array_type.toString());
		System.out.println("Timer : " + Model.timer_clock);
		System.out.println("Player guess mode? : " + Model.player_guess_mode);
		System.out
				.println("magic_numbers  : " + Model.magic_numbers.toString());
		System.out.println("modelofCards : " + Model.modelOfCardsToString());
		return "tim loves pants";
	}

}
