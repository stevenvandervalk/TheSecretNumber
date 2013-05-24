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
import android.widget.TextView;
import android.widget.Toast;

public class ComputerWon extends Activity {

	MediaPlayer player;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_good_game);

		player = MediaPlayer.create(this, R.raw.mario_coin);

		String value = null;
		// Show the Up button in the action bar.
		setupActionBar();

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			value = "The Computer Won";
		}
		if (String.valueOf(value) != null) {
			TextView tv1 = (TextView) findViewById(R.id.textView1);
			tv1.setText(value);
		}

		TextView time_completed = (TextView) findViewById(R.id.time_completed);
		time_completed.setText("Completed in : "
				+ String.valueOf(Model.completed_timer) + " seconds!");
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
		case R.id.action_settings:
			Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
					.show();
			Intent intent = new Intent(this, Settings.class);
			// EditText editText = (EditText) findViewById (R.id.edit_message);
			// String message = editText.getText().toString();
			// intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent);
			break;
		case R.id.action_help:
			Toast.makeText(this, "Help selected", Toast.LENGTH_SHORT).show();
			Intent intent2 = new Intent(this, HelpActivity.class);
			// EditText editText = (EditText) findViewById (R.id.edit_message);
			// String message = editText.getText().toString();
			// intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent2);
			break;

		default:
			break;
		}
		// return true;

		return super.onOptionsItemSelected(item);
	}

	public void PlayAgainButtonPressed(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_out, R.anim.push_left_out);
	}

	public void QuitButtonPressed(View view) {
		Intent intent = new Intent(this, ConstantsBrowser.class);
		startActivity(intent);
		overridePendingTransition(R.anim.fade, R.anim.push_left_out);
	}

	public void SaveScoreButtonPressed(View view) {

		if (Model.beat_the_clock_mode) {

			player.start();

			Intent intent = new Intent(this, SaveScore.class);
			startActivity(intent);
			overridePendingTransition(R.anim.fade, R.anim.push_left_out);
		} else {
			player.start();
			Intent intent = new Intent(this, SaveTimeTrialScore.class);
			startActivity(intent);
			overridePendingTransition(R.anim.fade, R.anim.push_left_out);
		}
	}

}
