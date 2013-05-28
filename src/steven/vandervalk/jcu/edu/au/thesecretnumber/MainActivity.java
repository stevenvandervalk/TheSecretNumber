package steven.vandervalk.jcu.edu.au.thesecretnumber;

import steven.vandervalk.jcu.edu.au.thesecretnumber.Model.Array_Type;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
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
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	MediaPlayer player_pipe;

	MediaPlayer player_theme;

	MediaPlayer player_coin;

	boolean isCancelled = false;

	private GestureDetector detector;

	ImageView imageView;

	AnimationDrawable anim;

	AnimationDrawable anim_mario;

	// BackgroundSound themeMusic = new BackgroundSound();

	// public class BackgroundSound extends AsyncTask<Void, Void, Void> {
	//
	// @Override
	// protected Void doInBackground(Void... params) {
	//
	// MediaPlayer player = MediaPlayer.create(MainActivity.this,
	// R.raw.mario_themesong);
	//
	// player.setVolume(100, 100);
	// try {
	// player.prepare();
	// } catch (IllegalStateException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// while (!themeMusic.isCancelled()) {
	// player.start();
	// }
	// if (Model.stop_the_theme_music) {
	// player.stop();
	// }
	//
	// return null;
	// }
	//
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("running model");

		// animate me coins yar

		imageView = (ImageView) findViewById(R.id.imageView1);
		imageView.setBackgroundResource(R.drawable.coin_movie);
		anim = (AnimationDrawable) imageView.getBackground();
		anim.start();

		// sing me mateys

		player_pipe = MediaPlayer.create(this, R.raw.mario_pipe);
		player_coin = MediaPlayer.create(this, R.raw.mario_coin);
		player_theme = MediaPlayer.create(this, R.raw.mario_themesong);

		String value = "BINARY"; // assume input from gui #still to be wired

		Model.array_type = Array_Type.valueOf(value);

		Model.max_length = 20; // assume later input from gui

		// test running this on async
		new Thread(new Model()).start();

		System.out.println("created first model in main : ");
		// Model.PrintStatus();
	}

	@Override
	public void onResume() {
		super.onResume();

		player_theme.start();
		anim.start();

	}

	@Override
	public void onPause() {
		super.onPause();
		player_theme.stop();
		anim.stop();

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
					player_pipe.start();

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

					player_pipe.start();

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

	public void StartSettings(View view) {
		// Make magics
		player_pipe.start();
		Intent intent = new Intent(this, Settings.class);
		// EditText editText = (EditText) findViewById (R.id.edit_message);
		// String message = editText.getText().toString();
		// intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
	}

	public void StartHelp(View view) {
		// Make magics
		player_pipe.start();
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
		player_pipe.start();
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
		anim.stop();
		imageView = (ImageView) findViewById(R.id.imageView1);
		imageView.setBackgroundResource(R.drawable.mario_movie);
		anim_mario = (AnimationDrawable) imageView.getBackground();

		anim_mario.start();
		player_coin.start();
		while (!player_coin.isPlaying()) {
			Intent intent = new Intent(this, PlayActivity.class);
			// // // EditText editText = (EditText) findViewById
			// (R.id.edit_message);
			// // // String message = editText.getText().toString();
			// // // intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent);
			overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		}
	}

}
