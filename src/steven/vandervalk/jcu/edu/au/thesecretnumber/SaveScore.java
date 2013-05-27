package steven.vandervalk.jcu.edu.au.thesecretnumber;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SaveScore extends ListActivity {

	MediaPlayer player_pipe;

	MediaPlayer player;
	// Key for Option Menu
	private static final int ADD_ID = Menu.FIRST + 1;
	private static final int DELETE_ID = Menu.FIRST + 3;
	// SQLite Database Projection(Column names)
	private static final String[] PROJECTION = new String[] {
			Provider.Constants._ID, Provider.Constants.TITLE,
			Provider.Constants.VALUE };
	private Cursor constantsCursor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		player = MediaPlayer.create(this, R.raw.mario_powerup);
		player_pipe = MediaPlayer.create(this, R.raw.mario_pipe);

		// call Provider.query() to get Cursor
		// Wrapper around ContentResolver.query(android.net.Uri, String[],
		// String, String[],
		// String) that gives the resulting Cursor to call startManagingCursor
		// so that the activity will manage its lifecycle for you.

		constantsCursor = managedQuery(Provider.Constants.CONTENT_URI,
				PROJECTION, null, null, null);
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.row,
				constantsCursor, new String[] { Provider.Constants.TITLE,
						Provider.Constants.VALUE }, new int[] { R.id.title,
						R.id.value });
		setListAdapter(adapter);
		registerForContextMenu(getListView());
		add();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		menu.add(Menu.NONE, ADD_ID, Menu.NONE, "Add").setIcon(R.drawable.add)
				.setAlphabeticShortcut('a');
		return (super.onCreateOptionsMenu(menu));
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

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenu.ContextMenuInfo menuInfo) {
		menu.add(Menu.NONE, DELETE_ID, Menu.NONE, "Delete")
				.setIcon(R.drawable.delete).setAlphabeticShortcut('d');
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
					.getMenuInfo();
			delete(info.id);
			return (true);
		}
		return (super.onOptionsItemSelected(item));
	}

	/**
	 * Add Constant Value to SQLite database via ContentProvider
	 */
	public void add() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View addView = inflater.inflate(R.layout.add_edit, null);
		final DialogWrapper wrapper = new DialogWrapper(addView);
		new AlertDialog.Builder(this)
				.setTitle(R.string.add_title)
				.setView(addView)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int whichButton) {
								processAdd(wrapper);
								player.start();
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// ignore, just dismiss
							}
						}).show();
	}

	/**
	 * 
	 * @param rowId
	 */
	private void delete(final long rowId) {
		if (rowId > 0) {
			new AlertDialog.Builder(this)
					.setTitle(R.string.delete_title)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {
									processDelete(rowId);
								}
							})
					.setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// ignore, just dismiss
								}
							}).show();
		}
	}

	public void processAdd(DialogWrapper wrapper) {
		ContentValues values = new ContentValues(2);

		if (!Model.player_guess_mode) {
			values.put(Provider.Constants.TITLE, "Computer ");
			values.put(Provider.Constants.VALUE, Model.completed_timer);
		}

		values.put(Provider.Constants.TITLE, wrapper.getTitle());
		values.put(Provider.Constants.VALUE, Model.completed_timer);

		getContentResolver().insert(Provider.Constants.CONTENT_URI, values);
		constantsCursor.requery();
	}

	public void processAddComputerScore() {
		ContentValues values = new ContentValues(2);

		values.put(Provider.Constants.TITLE, "Computer ");
		values.put(Provider.Constants.VALUE, Model.completed_timer);

		getContentResolver().insert(Provider.Constants.CONTENT_URI, values);
		constantsCursor.requery();
	}

	/**
	 * Delete Constant Value to SQLite database via ContentProvider
	 */
	private void processDelete(long rowId) {
		String[] args = { String.valueOf(rowId) };

		getContentResolver().delete(Provider.Constants.CONTENT_URI,
				Provider.Constants._ID + "=?", args);
		constantsCursor.requery();
	}

	class DialogWrapper {

		public void processAddComputerScore() {
			ContentValues values = new ContentValues(2);

			values.put(Provider.Constants.TITLE, "Computer ");
			values.put(Provider.Constants.VALUE, Model.completed_timer);

			getContentResolver().insert(Provider.Constants.CONTENT_URI, values);
			constantsCursor.requery();
		}

		EditText titleField = null;
		EditText valueField = null;
		View base = null;

		DialogWrapper(View base) {
			this.base = base;
			valueField = (EditText) base.findViewById(R.id.value);
		}

		String getTitle() {
			return (getTitleField().getText().toString());
		}

		float getValue() {
			return (new Float(getValueField().getText().toString())
					.floatValue());
		}

		private EditText getTitleField() {
			if (titleField == null) {
				titleField = (EditText) base.findViewById(R.id.title);
			}

			return (titleField);
		}

		private EditText getValueField() {
			if (valueField == null) {
				valueField = (EditText) base.findViewById(R.id.value);
			}

			return (valueField);
		}
	}
}