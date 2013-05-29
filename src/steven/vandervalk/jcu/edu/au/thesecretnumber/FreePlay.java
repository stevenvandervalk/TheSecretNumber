package steven.vandervalk.jcu.edu.au.thesecretnumber;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class FreePlay extends Activity {

	MediaPlayer player;
	MediaPlayer player2;

	MediaPlayer player_theme;
	// RadioButton RB0;
	// RadioButton RB1;
	// RadioButton RB2;
	ViewFlipper VF;

	GestureDetector detector_FreePlay;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		player = MediaPlayer.create(this, R.raw.mario_jump);
		player2 = MediaPlayer.create(this, R.raw.mario_pipe);
		player_theme = MediaPlayer.create(this, R.raw.mario_themesong);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_free_play);

		// ** put on async task **

		// generating TextViews for each card and then adding to the Layout
		// LinearLayout linear = (LinearLayout)
		// findViewById(R.layout.activity_free_play);
		System.out.println(Model.getMagic_numbers_size());
		TextView[] txt = new TextView[Model.magic_numbers.size()];
		VF = (ViewFlipper) findViewById(R.id.ViewFlipper01);

		// VF.setOutAnimation(AnimationUtils
		// .loadAnimation(this, R.anim.slide_left));

		for (int i = 0; i < txt.length; i++) {

			txt[i] = new TextView(FreePlay.this);
			String cardsToString = (Arrays.toString(Model
					.convertIntegers(Model.modelOfCards.get(i))));
			String formattedString = cardsToString.replace(",", "")
					.replace("[", "").replace("]", "");
			txt[i].setText(formattedString);
			txt[i].setTextAppearance(this, R.style.CodeFont);
			VF.addView(txt[i]);
		}

		// int[] value1 = Model.convertIntegers(Model.modelOfCards.get(0));
		// String cardNumbers1 = Arrays.toString(value1);
		// System.out.println("cardNumbers : " + value1);
		// // System.out.println("cardsNumbers1 toString " +
		// // cardNumbers1.toString());
		// TextView TV1 = (TextView) findViewById(R.id.TextView01);
		// TV1.setText(cardNumbers1.toString());
		//
		// int[] value2 = Model.convertIntegers(Model.modelOfCards.get(0));
		// String cardNumbers2 = Arrays.toString(value2);
		// TextView TV2 = (TextView) findViewById(R.id.TextView02);
		// TV2.setText(cardNumbers2);

		/*
		 * Find the views declared in main.xml.
		 */
		// RB0 = (RadioButton) findViewById(R.id.radio0);
		// RB1 = (RadioButton) findViewById(R.id.radio1);
		// RB2 = (RadioButton) findViewById(R.id.radio2);
		// VF = (ViewFlipper) findViewById(R.id.ViewFlipper01);

		/*
		 * Set a listener that will listen for clicks on the radio buttons and
		 * perform suitable actions.
		 */
		// RB0.setOnClickListener(radio_listener);
		// RB1.setOnClickListener(radio_listener);
		// RB2.setOnClickListener(radio_listener);

		// gesture code for switching cards

		detector_FreePlay = new GestureDetector(this,
				new SimpleOnGestureListener() {

					@Override
					public boolean onDoubleTap(MotionEvent e) {
						System.out.println("double tap!");

						return true;
					}

					@Override
					public boolean onFling(MotionEvent start, MotionEvent end,
							float velocityX, float velocityY) {
						// TODO Auto-generated method stub

						if (start.getX() < end.getX()) {
							System.out.println("swiped right");
							player.start();
							VF.setInAnimation(AnimationUtils.loadAnimation(
									FreePlay.this, R.anim.slide_right));
							VF.setDisplayedChild(VF.getDisplayedChild() + 1);

							return true;
						}

						if (start.getX() > end.getX()) {
							System.out.println("swiped left");
							if (VF.getDisplayedChild() == 0) {
								return true;
							} else {
								player.start();
								VF.setInAnimation(AnimationUtils.loadAnimation(
										FreePlay.this, R.anim.slide_left));
								VF.setDisplayedChild(VF.getDisplayedChild() - 1);

							}
						}

						return false;
					}

				});

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector_FreePlay.onTouchEvent(event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
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
			player2.start();
			NavUtils.navigateUpFromSameTask(this);
		case R.id.action_settings:
			Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
					.show();
			player2.start();
			Intent intent = new Intent(this, Settings.class);
			// EditText editText = (EditText) findViewById (R.id.edit_message);
			// String message = editText.getText().toString();
			// intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent);
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
			break;
		case R.id.action_help:
			Toast.makeText(this, "Help selected", Toast.LENGTH_SHORT).show();
			player2.start();
			Intent intent2 = new Intent(this, HelpActivity.class);
			// EditText editText = (EditText) findViewById (R.id.edit_message);
			// String message = editText.getText().toString();
			// intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent2);
			overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
			break;
		case R.id.action_scores:
			Toast.makeText(this, "Scores selected", Toast.LENGTH_SHORT).show();
			player2.start();
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

	/*
	 * Define a OnClickListener that will change which view that is displayed by
	 * the ViewFlipper
	 */
	// private final OnClickListener radio_listener = new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// switch (v.getId()) {
	// case R.id.radio0:
	// VF.setDisplayedChild(0);
	// break;
	// case R.id.radio1:
	// VF.setDisplayedChild(1);
	// break;
	// case R.id.radio2:
	// VF.setDisplayedChild(2);
	// break;
	// }
	// }
	// };
}