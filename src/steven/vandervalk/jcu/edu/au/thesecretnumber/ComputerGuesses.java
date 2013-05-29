package steven.vandervalk.jcu.edu.au.thesecretnumber;

import java.util.Random;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ComputerGuesses extends Activity {

	int computer_guess;
	Random rn;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_computer_guesses);
		// Show the Up button in the action bar.
		setupActionBar();

		new GenerateRandomComputerGuess().execute();

		// firstone.execute();
		tv = (TextView) findViewById(R.id.textView1);

	}

	private class GenerateRandomComputerGuess extends
			AsyncTask<String, Void, Integer> {

		@Override
		protected Integer doInBackground(String... params) {
			// TODO Auto-generated method stub

			rn = new Random();
			int answer = Model.rolling_sum;
			Model.rolling_sum = 0; // reset so doesn't add to next time around.

			System.out.println("correct answer is : " + answer);

			int range = 10;

			System.out.println("range is : " + range);
			int right_or_not = rn.nextInt(range);

			if (right_or_not < 6) {
				computer_guess = answer;
			} else {
				computer_guess = right_or_not;
			}

			System.out.println("computer guess is : " + computer_guess);

			return computer_guess;
		}

		@Override
		protected void onPostExecute(Integer result) {
			tv.setText(String.valueOf(computer_guess));
		}

	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void YesMyNumberPressed(View view) {

		// if computer guesses correctly record the score.

		Model.enter_computer_score = true;

		if (Model.beat_the_clock_mode) {

			Intent intent = new Intent(this, ConstantsBrowser.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, ConstantsBrowser2.class);
			startActivity(intent);
		}
	}

	public void NoNotMyNumberPressed(View view) {
		Intent intent = new Intent(this, GoodGame.class);
		startActivity(intent);
	}

}
