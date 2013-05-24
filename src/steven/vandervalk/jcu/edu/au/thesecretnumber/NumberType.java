package steven.vandervalk.jcu.edu.au.thesecretnumber;

import steven.vandervalk.jcu.edu.au.thesecretnumber.Model.Array_Type;
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

public class NumberType extends Activity {

	MediaPlayer player_theme;

	MediaPlayer player_pipe;

	MediaPlayer player_coin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_number_type);
		// Show the Up button in the action bar.
		setupActionBar();

		player_theme = MediaPlayer.create(this, R.raw.mario_themesong);
		player_pipe = MediaPlayer.create(this, R.raw.mario_pipe);
		player_coin = MediaPlayer.create(this, R.raw.mario_coin);

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

	public void setArrayTypeBinary(View view) {
		Array_Type BINARY = Array_Type.BINARY;
		player_coin.start();
		Model.setArray_type(BINARY);
		System.out.println("starting timer class - current model : "
				+ Model.PrintStatus());
		Intent intent = new Intent(this, TimeMode.class);
		startActivity(intent);
		overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);

	}

	public void setArrayTypePrime(View view) {
		Array_Type PRIME = Array_Type.PRIME;
		Model.setArray_type(PRIME);
		player_coin.start();
		System.out.println("starting timer class - current model : "
				+ Model.PrintStatus());
		Intent intent = new Intent(this, TimeMode.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_top_to_bottom,
				R.anim.push_left_out);

	}

	public void setArrayTypeFibonacci(View view) {
		player_coin.start();
		Array_Type FIBONACCI = Array_Type.FIBONACCI;
		Model.setArray_type(FIBONACCI);
		Intent intent = new Intent(this, TimeMode.class);
		System.out.println("starting timer class - current model : "
				+ Model.modelOfCardsToString());
		startActivity(intent);
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

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

}
