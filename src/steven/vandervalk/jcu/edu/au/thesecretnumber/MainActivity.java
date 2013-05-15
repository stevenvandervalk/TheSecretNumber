package steven.vandervalk.jcu.edu.au.thesecretnumber;

import android.app.Activity;
import android.content.Intent;
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

public class MainActivity extends Activity {

	private GestureDetector detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("running model");
		Model test = new Model();
		test.run();
		Model.ToString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);

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

				if (start.getY() > end.getY()) {
					System.out.println("swiped down");

					// EditText editText = (EditText) findViewById
					// (R.id.edit_message);
					// String message = editText.getText().toString();
					// intent.putExtra(EXTRA_MESSAGE, message);

					startActivity(intent1);
					return true;
				}
				if (start.getY() < end.getY()) {
					System.out.println("swiped up");

					startActivity(intent2);

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
		case R.id.action_scores:
			Toast.makeText(this, "Scores selected", Toast.LENGTH_SHORT).show();
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

	public void StartSettings(View view) {
		// Make magics
		Intent intent = new Intent(this, Settings.class);
		// EditText editText = (EditText) findViewById (R.id.edit_message);
		// String message = editText.getText().toString();
		// intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	public void StartHelp(View view) {
		// Make magics
		Intent intent = new Intent(this, HelpActivity.class);
		// EditText editText = (EditText) findViewById (R.id.edit_message);
		// String message = editText.getText().toString();
		// intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	public void StartHighScore(View view) {
		// // // Make magics
		Intent intent = new Intent(this, ConstantsBrowser.class);
		// // // EditText editText = (EditText) findViewById
		// (R.id.edit_message);
		// // // String message = editText.getText().toString();
		// // // intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	public void StartPlay(View view) {
		// // // Make magics
		Intent intent = new Intent(this, PlayActivity.class);
		// // // EditText editText = (EditText) findViewById
		// (R.id.edit_message);
		// // // String message = editText.getText().toString();
		// // // intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

}
