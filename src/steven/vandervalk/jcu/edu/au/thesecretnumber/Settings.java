package steven.vandervalk.jcu.edu.au.thesecretnumber;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Settings extends Activity {

	MediaPlayer player_pipe;

	MediaPlayer player_theme;

	MediaPlayer player_coin;

	RadioGroup rg;
	RadioButton rb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		// Show the Up button in the action bar.
		setupActionBar();

		player_pipe = MediaPlayer.create(this, R.raw.mario_pipe);
		player_theme = MediaPlayer.create(this, R.raw.mario_themesong);
		player_coin = MediaPlayer.create(this, R.raw.mario_coin);
	}

	@Override
	public void onResume() {
		super.onResume();

		player_theme.start();

	}

	@Override
	public void onPause() {
		super.onPause();
		player_theme.stop();

	}

	@Override
	public void onStop() {
		super.onStop();
		player_theme.stop();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		player_theme.stop();
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
		// getMenuInflater().inflate(R.menu.settings, menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	public void SavePressed(View view) {

		player_coin.start();

		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);

		// Intent data = new Intent();
		//
		// // //SeekBar seek = (SeekBar) findViewById(R.id.seekBar1);
		// //
		// // setBrushWidth(seek.getProgress());
		// //
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		RadioButton radiovalue = (RadioButton) this.findViewById(rg
				.getCheckedRadioButtonId());

		// //
		// // data.putExtra("seekValue", getBrushWidth());
		// //
		// // data.putExtra("color", brushColor);
		// //
		// // setResult(RESULT_OK, data);
		// //
		// //// finish();
		// // }
		// //
		// // public int getBrushWidth() {
		// // return brushWidth;
		// // }
		// //
		// // public void setBrushWidth(int brushWidth) {
		// // this.brushWidth = brushWidth;
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
			// Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
			player_pipe.start();
			NavUtils.navigateUpFromSameTask(this);
		case R.id.action_settings:

			player_pipe.start();
			Intent intent = new Intent(this, Settings.class);
			// EditText editText = (EditText) findViewById (R.id.edit_message);
			// String message = editText.getText().toString();
			// intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent);
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
			break;
		case R.id.action_help:
			Toast.makeText(this, "Help selected", Toast.LENGTH_SHORT).show();
			player_pipe.start();
			Intent intent2 = new Intent(this, HelpActivity.class);
			// EditText editText = (EditText) findViewById (R.id.edit_message);
			// String message = editText.getText().toString();
			// intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent2);
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
			break;
		case R.id.action_scores:
			Toast.makeText(this, "Scores selected", Toast.LENGTH_SHORT).show();
			player_pipe.start();
			Intent intent3 = new Intent(this, ConstantsBrowser.class);
			// EditText editText = (EditText) findViewById (R.id.edit_message);
			// String message = editText.getText().toString();
			// intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent3);
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
			break;

		default:
			break;
		}
		// return true;

		return super.onOptionsItemSelected(item);
	}

}
