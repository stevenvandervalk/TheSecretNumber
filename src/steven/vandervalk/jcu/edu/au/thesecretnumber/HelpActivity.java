package steven.vandervalk.jcu.edu.au.thesecretnumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * This is the Help activity for the application. It displays some basic help
 * information. Clicking on the icons takes the user to a detailed description.
 * 
 */

public class HelpActivity extends Activity {

	static public final String ARG_TEXT_ID = "text_id";

	/**
	 * onCreate - called when the activity is first created. Called when the
	 * activity is first created. This is where you should do all of your normal
	 * static set up: create views, bind data to lists, etc. This method also
	 * provides you with a Bundle containing the activity's previously frozen
	 * state, if there was one.
	 * 
	 * Always followed by onStart().
	 * 
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.settings, menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
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
			Toast.makeText(this, "Help already selected", Toast.LENGTH_SHORT)
					.show();
			break;

		default:
			break;
		}
		// return true;

		return super.onOptionsItemSelected(item);
	}

	/**
 */
	// Methods

	/**
	 * Handle the click of one of the help buttons on the page. Start an
	 * activity to display the help text for the topic selected.
	 * 
	 * @param v
	 *            View
	 * @return void
	 */

	/**
	 * Start a TopicActivity and show the text indicated by argument 1.
	 * 
	 * @param textId
	 *            int - resource id of the text to show
	 * @return void
	 */

	/**
	 * Show a string on the screen via Toast.
	 * 
	 * @param msg
	 *            String
	 * @param longLength
	 *            boolean - show message a long time
	 * @return void
	 */

	public void toast(String msg, boolean longLength) {
		Toast.makeText(getApplicationContext(), msg,
				(longLength ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)).show();
	}

} // end class
