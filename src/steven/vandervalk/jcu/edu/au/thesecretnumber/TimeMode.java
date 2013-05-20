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
import android.widget.TextView;
import android.widget.Toast;

public class TimeMode extends Activity {

	float time_value;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_mode);
		// Show the Up button in the action bar.
		setupActionBar();

		new Thread(new Model()).start();

		SeekBar timeSeekBar = (SeekBar) findViewById(R.id.seekBar1);

		timeSeekBar.setMax(50);
		timeSeekBar.setProgress(1);

		timeSeekBar.setOnSeekBarChangeListener(new yourListener());
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
		getMenuInflater().inflate(R.menu.time_mode, menu);
		return true;
	}

	private class yourListener implements SeekBar.OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {

			time_value = (progress);

			System.out.println("Progress is: " + progress);
			System.out.println("Value / Time is: " + time_value);

			tv = (TextView) findViewById(R.id.time_progress_value);

			tv.setText(time_value + " Seconds");

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			Model.timer_clock = time_value;

			String time_minutes = String.valueOf(time_value);
			StringBuilder ss = new StringBuilder();
			String minutes = " Seconds";
			ss.append(time_minutes);
			ss.append(minutes);

			Toast.makeText(getApplicationContext(), ss.toString(),
					Toast.LENGTH_SHORT).show();

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

	public void StartBeatTheClockGame(View view) {

		Intent intent = new Intent(this, TimeTrial.class);
		Model.beat_the_clock_mode = true;
		startActivity(intent);
	}

	public void StartTimeTrialGame(View view) {

		Intent intent = new Intent(this, TimeTrial.class);
		startActivity(intent);
	}

}
