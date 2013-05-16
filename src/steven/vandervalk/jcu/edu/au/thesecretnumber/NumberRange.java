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
import android.widget.SeekBar;
import android.widget.Toast;

public class NumberRange extends Activity {

	int number_value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_number_range);
		// Show the Up button in the action bar.
		setupActionBar();

		SeekBar numberRangeSeekBar = (SeekBar) findViewById(R.id.seekBar1);

		numberRangeSeekBar.setMax(50);
		numberRangeSeekBar.setProgress(25);

		numberRangeSeekBar.setOnSeekBarChangeListener(new Listener());

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
		getMenuInflater().inflate(R.menu.time_range, menu);
		return true;
	}

	private class Listener implements SeekBar.OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {

			number_value = progress;

			System.out.println("Progress is: " + progress);
			System.out.println("Max number is: " + number_value);

			Toast.makeText(getApplicationContext(),
					String.valueOf(number_value), Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {

		}
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

	public void StartNumberTypeActivity(View view) {
		Model.max_length = number_value;
		Intent intent = new Intent(this, NumberType.class);
		startActivity(intent);

	}

}
