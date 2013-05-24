package steven.vandervalk.jcu.edu.au.thesecretnumber;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
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

	MediaPlayer player_theme;

	MediaPlayer player_pipe;

	MediaPlayer player_coin;

	float time_value;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_mode);
		// Show the Up button in the action bar.
		setupActionBar();

		player_theme = MediaPlayer.create(this, R.raw.mario_themesong);
		player_pipe = MediaPlayer.create(this, R.raw.mario_pipe);
		player_coin = MediaPlayer.create(this, R.raw.mario_coin);

		new Thread(new Model()).start();

		SeekBar timeSeekBar = (SeekBar) findViewById(R.id.seekBar1);

		timeSeekBar.setMax(300);
		timeSeekBar.setProgress(10);

		timeSeekBar.setOnSeekBarChangeListener(new yourListener());
	}

	@Override
	protected void onPause() {
		super.onPause();
		player_theme.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		player_theme.start();
	}

	@Override
	protected void onStart() {
		super.onStart();
		player_theme.start();
	}

	@Override
	protected void onStop() {
		super.onStop();
		player_theme.stop();
		player_theme.release();

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

			player_pipe.start();
			NavUtils.navigateUpFromSameTask(this);
		case R.id.action_settings:
			player_pipe.start();
			Intent intent = new Intent(this, Settings.class);
			// EditText editText = (EditText) findViewById (R.id.edit_message);
			// String message = editText.getText().toString();
			// intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent);
			break;
		case R.id.action_help:
			player_pipe.start();
			Intent intent2 = new Intent(this, HelpActivity.class);
			// EditText editText = (EditText) findViewById (R.id.edit_message);
			// String message = editText.getText().toString();
			// intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent2);
			break;
		case R.id.action_scores:
			player_pipe.start();
			Intent intent3 = new Intent(this, ConstantsBrowser.class);
			// EditText editText = (EditText) findViewById (R.id.edit_message);
			// String message = editText.getText().toString();
			// intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent3);
			break;

		default:
			break;
		}
		// return true;

		return super.onOptionsItemSelected(item);
	}

	public void StartBeatTheClockGame(View view) {

		Intent intent = new Intent(this, TimeTrial.class);
		Model.beat_the_clock_mode = true;
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void StartTimeTrialGame(View view) {

		Model.beat_the_clock_mode = false;
		Intent intent = new Intent(this, TimeTrial.class);
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

}
