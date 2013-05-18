package steven.vandervalk.jcu.edu.au.thesecretnumber;

import java.util.Random;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_computer_guesses);
		// Show the Up button in the action bar.
		setupActionBar();

		generateRandomComputerGuess();

	}

	public void generateRandomComputerGuess() {
		rn = new Random();
		int minimum = Model.rolling_sum;
		int range = (minimum + 1) - minimum;
		computer_guess = rn.nextInt(range) + minimum;

		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText(String.valueOf(computer_guess));
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
		getMenuInflater().inflate(R.menu.computer_guesses, menu);
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

		Intent intent = new Intent(this, GoodGame.class);
		startActivity(intent);
	}

	public void NoNotMyNumberPressed(View view) {
		generateRandomComputerGuess();
	}

}
