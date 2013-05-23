package steven.vandervalk.jcu.edu.au.thesecretnumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class TimeTrial extends Activity {

	BackgroundSound mBackgroundSound = new BackgroundSound();

	MediaPlayer player;

	MediaPlayer player2;

	CountDownTimer countDownTimer;

	ViewFlipper VF;

	TextView[] txt;

	Drawable bg1;

	Drawable bg2;

	// int rollingSum;

	Set<Integer> numbersAdded = new HashSet<Integer>();

	GestureDetector detector_TimeTrial;

	long s1;

	long s2;

	float countDownTimeRemaining;

	long StartTime = System.currentTimeMillis();

	long elapseTime;

	float timeRemaining;

	int seconds;

	int minutes;

	private final Handler handler = new Handler();

	protected int secondsRemaining;

	protected int minutesRemaining;

	// Runnable task = new Runnable()

	private final Runnable task = new Runnable() {
		@Override
		public void run() {
			// my methods

			final long start = StartTime;
			elapseTime = System.currentTimeMillis() - start;
			seconds = (int) (elapseTime / 1000);
			minutes = seconds / 60;
			seconds = seconds % 60;
			Model.completed_timer = seconds;

			long s2 = seconds;

			timeRemaining = (Model.timer_clock - seconds);

			System.out.println("timer clock : " + Model.timer_clock);

			System.out.println("timer clock - seconds : "
					+ (Model.timer_clock - seconds));

			System.out.println("time remaining : " + timeRemaining);

			secondsRemaining = (int) (timeRemaining / 1000);

			minutesRemaining = secondsRemaining / 60;

			secondsRemaining = secondsRemaining % 60;

			TextView TimeText = (TextView) findViewById(R.id.TimeLabel);

			if (!Model.beat_the_clock_mode) {

				// TimeText.setText("Time Remaining : "
				// + (Model.timer_clock - seconds));
				// }
				//
				// else {

				if (seconds < 10) {
					TimeText.setText("Time : " + minutes + ":0" + seconds);
				} else {
					TimeText.setText("Time : " + minutes + ":" + seconds);
				}
			}
			// add a delay to adjust for computation time
			long delay = (1000 - (elapseTime % 1000));

			handler.postDelayed(this, delay);

			// handler.postDelayed(this, 100);

		}
	};

	public void stopHandler() {
		handler.removeCallbacks(task);

	}

	// stop timer with
	// handler.removeCallbacks(task);

	// handler.post(task)

	public class BackgroundSound extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			boolean isCancelled = mBackgroundSound.isCancelled();

			MediaPlayer player = MediaPlayer.create(TimeTrial.this,
					R.raw.action);

			if (Model.beat_the_clock_mode) {
				player = MediaPlayer.create(TimeTrial.this, R.raw.countdown);
			}

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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_trial);

		// player = MediaPlayer.create(this, R.raw.action);
		player2 = MediaPlayer.create(this, R.raw.mario_jump);
		// player.start();

		// start a new model with new static variables

		new Thread(new Model()).start();

		if (Model.beat_the_clock_mode) {
			countDownTimer = new CountDownTimer(
					(long) (Model.timer_clock * 1000), 1000) {

				@Override
				public void onTick(long millisUntilFinished) {
					TextView TimeText = (TextView) findViewById(R.id.TimeLabel);
					countDownTimeRemaining = (Model.timer_clock - seconds);
					TimeText.setText("Time : " + (Model.timer_clock - seconds));
					s1 = millisUntilFinished;

				}

				@Override
				public void onFinish() {
					// TODO Auto-generated method stub
					System.out.println(" hit 0 time");
					stopHandler();
					player.stop();
					startGoodGameIntent();

				}
			};
		}

		// timer
		handler.post(task);

		if (!Model.player_guess_mode) {
			Button button = (Button) findViewById(R.id.guess_button);
			button.setVisibility(View.INVISIBLE);
			overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		}

		// Show the Up button in the action bar.
		setupActionBar();

		// put on async task

		txt = new TextView[Model.magic_numbers.size()];

		VF = (ViewFlipper) findViewById(R.id.viewFlipper1);

		for (int i = 0; i < txt.length; i++) {

			txt[i] = new TextView(TimeTrial.this);

			String cardsToString = (Arrays.toString(Model
					.convertIntegers(Model.modelOfCards.get(i))));
			String formattedString = cardsToString.replace(",", "")
					.replace("[", "").replace("]", "");
			txt[i].setText(formattedString);
			txt[i].setTextAppearance(this, R.style.CodeFont);
			VF.addView(txt[i]);
		}

		if (Model.player_guess_mode) {

			Button b1 = (Button) findViewById(R.id.yes_button);
			bg1 = b1.getBackground();

			Button b2 = (Button) findViewById(R.id.no_button);
			bg2 = b2.getBackground();

			// Model.computer_secret_number

			int i = VF.getDisplayedChild();

			Set<Integer> a = new HashSet<Integer>();

			String csv = txt[i].getText().toString();
			if (csv.contains(" ")) {
				// Split it.
				String[] s = csv.split(" ");
				for (String x : s) {
					a.add(Integer.parseInt(x));
				}
			} else {
				throw new IllegalArgumentException("String " + csv
						+ " does not contain ,");
			}

			//
			if (a.contains(Model.computer_secret_number)) {
				Button button = (Button) findViewById(R.id.yes_button);
				button.setBackgroundResource(R.drawable.green_btn);

			} else {
				Button button2 = (Button) findViewById(R.id.no_button);
				button2.setBackgroundResource(R.drawable.red_btn);
			}

		}

	}

	@Override
	protected void onPause() {
		super.onPause();

		if (Model.beat_the_clock_mode) {
			countDownTimer.cancel();
		}
		handler.removeCallbacks(task);
		mBackgroundSound.cancel(true);

	}

	@Override
	protected void onStop() {

		if (Model.beat_the_clock_mode) {
			countDownTimer.cancel();
		}

		handler.removeCallbacks(task);
		// player.stop();
		mBackgroundSound.cancel(true);

		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// player.start();
		mBackgroundSound.cancel(true);
		new BackgroundSound().execute(null, null, null);

		if (Model.beat_the_clock_mode) {
			long timervalue = s1;

			countDownTimer = new CountDownTimer(
					(long) (Model.timer_clock * 1000), 1000) {

				@Override
				public void onTick(long millisUntilFinished) {
					TextView TimeText = (TextView) findViewById(R.id.TimeLabel);
					TimeText.setText("Time Remaining : "
							+ (Model.timer_clock - seconds));
					countDownTimeRemaining = (Model.timer_clock - seconds);
					s1 = millisUntilFinished;

				}

				@Override
				public void onFinish() {
					// TODO Auto-generated method stub
					System.out.println(" hit 0 time");
					stopHandler();
					mBackgroundSound.cancel(true);

					startGoodGameIntent();

				}
			};
			countDownTimer.start();

		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);

		}

		detector_TimeTrial = new GestureDetector(this,
				new SimpleOnGestureListener() {

					@Override
					public boolean onDoubleTap(MotionEvent e) {
						System.out.println("double tap!");

						return true;
					}

					@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
					@SuppressLint("NewApi")
					@Override
					public boolean onFling(MotionEvent start, MotionEvent end,
							float velocityX, float velocityY) {

						// set two button colors back to start

						// TODO Auto-generated method stub

						if (start.getX() < end.getX()) {
							System.out.println("swiped right");
							player2.start();

							// if at last game start player guessing activity
							VF.setInAnimation(AnimationUtils.loadAnimation(
									TimeTrial.this, R.anim.slide_right));

							VF.setDisplayedChild(VF.getDisplayedChild() + 1);

							// put on async task

							// if set to player_guess mode

							if (Model.player_guess_mode) {

								Button b1 = (Button) findViewById(R.id.yes_button);
								b1.setBackground(bg1);

								Button b2 = (Button) findViewById(R.id.no_button);
								b2.setBackground(bg2);

								// Button b = (Button)
								// findViewById(R.id.yes_button);
								// Button button2 = (Button)
								// findViewById(R.id.no_button);
								// Drawable d = b.getBackground();

								// Model.computer_secret_number

								int i = VF.getDisplayedChild();

								Set<Integer> a = new HashSet<Integer>();

								String csv = new String();
								csv = txt[i].getText().toString();
								if (csv.contains(" ")) {
									// Split it.
									String[] s = csv.split(" ");
									for (String x : s) {
										a.add(Integer.parseInt(x));
									}
								} else {
									throw new IllegalArgumentException(
											"String " + csv
													+ " does not contain ,");
								}
								//
								if (a.contains(Model.computer_secret_number)) {

									b1.setBackgroundResource(R.drawable.green_btn);
									// Button b = (Button)
									// findViewById(R.id.yes_button);
									// Drawable d = b.getBackground();

									// b.setBackgroundResource(R.drawable.green_btn);

									// b.getBackground().setColorFilter(
									// 0xFF00FF00,
									// PorterDuff.Mode.MULTIPLY);

									a.clear();

								} else {

									b2.setBackgroundResource(R.drawable.red_btn);
									// Button b = (Button)
									// findViewById(R.id.yes_button);

									a.clear();

								}

							}
							// // code to check if card displayed contains
							// // secret
							// // number selected at random by computer
							//

							//
							// // code to turn appropriate button background
							// // color.
							// // and grey out other?
							// }

							return true;
						}

						if (start.getX() > end.getX()) {
							System.out.println("swiped left");
							if (VF.getDisplayedChild() == 0) {
								return true;
							} else {
								player2.start();
								VF.setInAnimation(AnimationUtils.loadAnimation(
										TimeTrial.this, R.anim.slide_left));
								VF.setDisplayedChild(VF.getDisplayedChild() - 1);

								// if set to player guess mode

								if (Model.player_guess_mode) {

									Button b1 = (Button) findViewById(R.id.yes_button);
									b1.setBackground(bg1);

									Button b2 = (Button) findViewById(R.id.no_button);
									b2.setBackground(bg2);

									// Button b = (Button)
									// findViewById(R.id.yes_button);
									// Button button2 = (Button)
									// findViewById(R.id.no_button);
									// Drawable d = b.getBackground();

									// Model.computer_secret_number

									int i = VF.getDisplayedChild();

									Set<Integer> a = new HashSet<Integer>();

									String csv = new String();
									csv = txt[i].getText().toString();
									if (csv.contains(" ")) {
										// Split it.
										String[] s = csv.split(" ");
										for (String x : s) {
											a.add(Integer.parseInt(x));
										}
									} else {
										throw new IllegalArgumentException(
												"String " + csv
														+ " does not contain ,");
									}
									//
									if (a.contains(Model.computer_secret_number)) {

										b1.setBackgroundResource(R.drawable.green_btn);
										// Button b = (Button)
										// findViewById(R.id.yes_button);
										// Drawable d = b.getBackground();

										// b.setBackgroundResource(R.drawable.green_btn);

										// b.getBackground().setColorFilter(
										// 0xFF00FF00,
										// PorterDuff.Mode.MULTIPLY);

										a.clear();

									} else {

										b2.setBackgroundResource(R.drawable.red_btn);
										// Button b = (Button)
										// findViewById(R.id.yes_button);

										a.clear();

									}

								}

							}
						}

						return false;
					}

				});

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector_TimeTrial.onTouchEvent(event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_trial, menu);
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void GuessButtonPressed(View view) {
		if (Model.player_guess_mode) {
			Intent intent = new Intent(this, PlayerGuesses.class);
			ActivityOptions options = ActivityOptions.makeScaleUpAnimation(
					view, 0, 0, view.getWidth(), view.getHeight());
			startActivity(intent, options.toBundle());
		}
	}

	public void IfNoButtonPressed(View view) {
		if (!Model.player_guess_mode) {

			int i = VF.getDisplayedChild();

			System.out.println("i value is : " + i);

			System.out.println("rolling sum is : " + Model.rolling_sum);

			System.out.println("txt length is : " + txt.length);
			System.out
					.println("displayed child is : " + VF.getDisplayedChild());

			if (VF.getDisplayedChild() == txt.length - 1) {

				stopHandler();

				Intent intent = new Intent(this, ComputerGuesses.class);
				startActivity(intent);
			} else {

				VF.setDisplayedChild(VF.getDisplayedChild() + 1);

			}
		}
	}

	public void IfYesButtonPressed(View view) {
		// if set to computer guess mode

		if (!Model.player_guess_mode) {
			int i = VF.getDisplayedChild();
			System.out.println("i value is : " + i);
			ArrayList<Integer> a = Model.modelOfCards.get(i);

			Integer numberToAddToSum = a.get(0);

			if (numbersAdded.contains(numberToAddToSum)) {
				Toast.makeText(this, "Number already confirmed",
						Toast.LENGTH_SHORT).show();

			} else {

				numbersAdded.add(numberToAddToSum);

				Model.rolling_sum += numberToAddToSum;

				System.out.println("i value is : " + i);
				System.out.println(" numberto add to sum is : "
						+ numberToAddToSum);
				System.out.println("rolling sum is : " + Model.rolling_sum);

				System.out.println("txt length is : " + txt.length);
				System.out.println("displayed child is : "
						+ VF.getDisplayedChild());

				if (VF.getDisplayedChild() == txt.length - 1) {

					stopHandler();

					Intent intent = new Intent(this, ComputerGuesses.class);
					startActivity(intent);

				}

				// turn off button

				// Button button = (Button) findViewById(R.id.yes_button);
				// button.setOnClickListener(new OnClickListener() {
				//
				// @Override
				// public void onClick(View v) {
				// Button button = (Button) v;
				// button.setBackgroundColor(R.color.Green);
				// }
				// });

				VF.setDisplayedChild(VF.getDisplayedChild() + 1);
			}
		}

	}

	public void startGoodGameIntent() {
		Intent intent = new Intent(this, GoodGame.class);
		intent.putExtra("Time_Up", true);
		startActivity(intent);
		overridePendingTransition(R.anim.shake, R.anim.push_left_out);
	}

}
