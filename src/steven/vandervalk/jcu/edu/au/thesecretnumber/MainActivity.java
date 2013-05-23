package steven.vandervalk.jcu.edu.au.thesecretnumber;

import steven.vandervalk.jcu.edu.au.thesecretnumber.Model.Array_Type;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	MediaPlayer player;

	private GestureDetector detector;
	BackgroundSound mBackgroundSound = new BackgroundSound();

	public class BackgroundSound extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			boolean isCancelled = mBackgroundSound.isCancelled();

			MediaPlayer player = MediaPlayer.create(MainActivity.this,
					R.raw.snooze);
			player.setLooping(false); // Set looping?
			player.setVolume(100, 100);
			player.start();
			if (!isCancelled) {
				player.stop();
			}

			return null;
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		mBackgroundSound.cancel(true);
		new BackgroundSound().execute(null, null, null);
	}

	@Override
	public void onPause() {
		mBackgroundSound.cancel(true);
		super.onPause();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mBackgroundSound.cancel(true);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("running model");

		player = MediaPlayer.create(this, R.raw.mario_pipe);

		String value = "BINARY"; // assume input from gui #still to be wired

		Model.array_type = Array_Type.valueOf(value);

		Model.max_length = 20; // assume later input from gui

		new Thread(new Model()).start();

		System.out.println("created first model in main : ");
		// Model.PrintStatus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);

		// sounds for swiping

		final Intent intent1 = new Intent(this, HelpActivity.class);
		final Intent intent2 = new Intent(this, PlayActivity.class);

		// change to play activity
		// Intent intent4 = new Intent(this, HelpActivity.class);

		detector = new GestureDetector(this, new SimpleOnGestureListener() {

			@Override
			public boolean onDoubleTap(MotionEvent e) {
				System.out.println("double tap!");

				return true;
			}

			@Override
			public boolean onFling(MotionEvent start, MotionEvent end,
					float velocityX, float velocityY) {
				// TODO Auto-generated method stub

				if (start.getY() < end.getY()) {
					System.out.println("swiped down");

					// EditText editText = (EditText) findViewById
					// (R.id.edit_message);
					// String message = editText.getText().toString();
					// intent.putExtra(EXTRA_MESSAGE, message);

					startActivity(intent1);
					overridePendingTransition(R.anim.zoom_enter,
							R.anim.zoom_exit);
					return true;
				}
				if (start.getY() > end.getY()) {
					System.out.println("swiped up");

					startActivity(intent2);
					overridePendingTransition(R.anim.zoom_enter,
							R.anim.zoom_exit);

					return true;
				}

				return false;
			}

		});
		return true;

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
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
			Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
			player.start();
			NavUtils.navigateUpFromSameTask(this);
		case R.id.action_settings:
			Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
					.show();
			player.start();
			Intent intent = new Intent(this, Settings.class);
			// EditText editText = (EditText) findViewById (R.id.edit_message);
			// String message = editText.getText().toString();
			// intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent);
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
			break;
		case R.id.action_help:
			Toast.makeText(this, "Help selected", Toast.LENGTH_SHORT).show();
			player.start();
			Intent intent2 = new Intent(this, HelpActivity.class);
			// EditText editText = (EditText) findViewById (R.id.edit_message);
			// String message = editText.getText().toString();
			// intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent2);
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
			break;
		case R.id.action_scores:
			Toast.makeText(this, "Scores selected", Toast.LENGTH_SHORT).show();
			player.start();
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

	public void StartSettings(View view) {
		// Make magics
		player.start();
		Intent intent = new Intent(this, Settings.class);
		// EditText editText = (EditText) findViewById (R.id.edit_message);
		// String message = editText.getText().toString();
		// intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
	}

	public void StartHelp(View view) {
		// Make magics
		player.start();
		Intent intent = new Intent(this, HelpActivity.class);
		// EditText editText = (EditText) findViewById (R.id.edit_message);
		// String message = editText.getText().toString();
		// intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		overridePendingTransition(R.anim.fade, R.anim.fade);
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void StartHighScore(View view) {
		// // // Make magics
		player.start();
		Intent intent = new Intent(this, ConstantsBrowser.class);
		ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0,
				0, view.getWidth(), view.getHeight());
		startActivity(intent, options.toBundle());
		// // // EditText editText = (EditText) findViewById
		// (R.id.edit_message);
		// // // String message = editText.getText().toString();
		// // // intent.putExtra(EXTRA_MESSAGE, message);
		// startActivity(intent);
	}

	public void StartPlay(View view) {
		// // // Make magics
		player.start();
		Intent intent = new Intent(this, PlayActivity.class);
		// // // EditText editText = (EditText) findViewById
		// (R.id.edit_message);
		// // // String message = editText.getText().toString();
		// // // intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

}
