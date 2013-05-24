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

public class NumberRange extends Activity {

	int number_value;
	TextView tv;

	MediaPlayer player_theme;

	MediaPlayer player_pipe;

	MediaPlayer player_coin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_number_range);
		// Show the Up button in the action bar.
		setupActionBar();

		player_theme = MediaPlayer.create(this, R.raw.mario_themesong);
		player_pipe = MediaPlayer.create(this, R.raw.mario_pipe);
		player_coin = MediaPlayer.create(this, R.raw.mario_coin);

		SeekBar numberRangeSeekBar = (SeekBar) findViewById(R.id.seekBar1);

		numberRangeSeekBar.setMax(50);
		// numberRangeSeekBar.setProgress(25);

		// tv.setText("0 : " + number_value);

		numberRangeSeekBar.setOnSeekBarChangeListener(new Listener());

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

	private class Listener implements SeekBar.OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {

			number_value = progress;

			tv = (TextView) findViewById(R.id.seekbar_progress);

			tv.setText("0 : " + number_value);

			System.out.println("Progress is: " + progress);
			System.out.println("Max number is: " + number_value);

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

	public void StartNumberTypeActivity(View view) {
		String saved = "Settings Saved";
		player_pipe.start();
		Model.max_length = number_value;
		Model.generateComputerSecretNumber();
		Toast.makeText(getApplicationContext(), saved, Toast.LENGTH_SHORT)
				.show();
		Intent intent = new Intent(this, NumberType.class);
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

	}

}
