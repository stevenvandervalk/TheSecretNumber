package steven.vandervalk.jcu.edu.au.thesecretnumber;

import steven.vandervalk.jcu.edu.au.thesecretnumber.Model.Array_Type;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class NumberType extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_number_type);
		// Show the Up button in the action bar.
		setupActionBar();

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
		getMenuInflater().inflate(R.menu.number_type, menu);

		return true;
	}

	public void setArrayTypeBinary(View view) {
		Array_Type BINARY = Array_Type.BINARY;
		Model.setArray_type(BINARY);
		System.out.println("starting timer class - current model : "
				+ Model.PrintStatus());
		Intent intent = new Intent(this, TimeMode.class);
		startActivity(intent);

	}

	public void setArrayTypePrime(View view) {
		Array_Type PRIME = Array_Type.PRIME;
		Model.setArray_type(PRIME);
		System.out.println("starting timer class - current model : "
				+ Model.PrintStatus());
		Intent intent = new Intent(this, TimeMode.class);
		startActivity(intent);

	}

	public void setArrayTypeFibonacci(View view) {
		Array_Type FIBONACCI = Array_Type.FIBONACCI;
		Model.setArray_type(FIBONACCI);
		Intent intent = new Intent(this, TimeMode.class);
		System.out.println("starting timer class - current model : "
				+ Model.modelOfCardsToString());
		startActivity(intent);

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

}
