package steven.vandervalk.jcu.edu.au.thesecretnumber;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

public class PlayerGuesses extends Activity {

	int player_guess;
	int minValue;
	int maxValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_guesses);
		// Show the Up button in the action bar.
		setupActionBar();

		final NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker1);

		maxValue = Model.max_length;

		np.setMinValue(minValue);
		np.setMaxValue(maxValue);
		np.setOnValueChangedListener(new OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				// do something here
				player_guess = np.getValue();
				if (player_guess == Model.computer_secret_number) {

					TextView tv = (TextView) findViewById(R.id.message_textview);
					String text = "Congrats";
					tv.setText(text);

					StartGoodGame(tv);

				} else {
					TextView tv = (TextView) findViewById(R.id.message_textview);
					String text = "Guess again";
					tv.setText(text);
				}
			}
		});
	}

	//
	// TextView.OnEditorActionListener exampleListener = new
	// TextView.OnEditorActionListener() {
	//
	// @Override
	// public boolean onEditorAction(TextView v, int actionId,
	// KeyEvent event) {
	// if (actionId == EditorInfo.IME_NULL
	// && event.getAction() == KeyEvent.ACTION_DOWN) {
	// // example_confirm();//match this behavior to your 'Send'
	// // (or
	// // Confirm) button
	//
	// // if player guess edittext.gettext == computer secret
	// // number start good game activity
	//
	// EditText et = (EditText) findViewById(R.id.mfield);
	// player_guess = Integer.valueOf(et.getText().toString());
	//
	// // else Say guess again
	//
	// }
	// return true;
	// }
	// };

	// EditText et = (EditText) findViewById(R.id.player_guess_field);
	//
	// et.setOnEditorActionListener(exampleListener);
	//
	// }

	public void onClick(View v) {

		NumberPicker numPicker = (NumberPicker) findViewById(R.id.numberPicker1);
		int x = numPicker.getValue();
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
		getMenuInflater().inflate(R.menu.player_guesses, menu);
		return true;
	}

	public void StartGoodGame(View view) {
		Intent intent = new Intent(getBaseContext(), GoodGame.class);
		startActivity(intent);
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

}
