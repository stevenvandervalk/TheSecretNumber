package steven.vandervalk.jcu.edu.au.thesecretnumber;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class SaveTimeTrialScore extends ListActivity {

	private GestureDetector detector;
	MediaPlayer player;

	// Key for Option Menu
	private static final int ADD_ID = Menu.FIRST + 1;
	private static final int DELETE_ID = Menu.FIRST + 3;
	// SQLite Database Projection(Column names)
	private static final String[] PROJECTION = new String[] {
			Provider2.Constants2._ID, Provider2.Constants2.TITLE,
			Provider2.Constants2.VALUE };
	private Cursor constantsCursor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		player = MediaPlayer.create(this, R.raw.mario_powerup);

		// call Provider.query() to get Cursor
		// Wrapper around ContentResolver.query(android.net.Uri, String[],
		// String, String[],
		// String) that gives the resulting Cursor to call startManagingCursor
		// so that the activity will manage its lifecycle for you.

		constantsCursor = managedQuery(Provider2.Constants2.CONTENT_URI,
				PROJECTION, null, null, null);
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.row2,
				constantsCursor, new String[] { Provider2.Constants2.TITLE,
						Provider2.Constants2.VALUE }, new int[] { R.id.title,
						R.id.value });
		setListAdapter(adapter);
		registerForContextMenu(getListView());

		// put a button or flag when calling add is appropriate

		add();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ADD_ID, Menu.NONE, "Add").setIcon(R.drawable.add)
				.setAlphabeticShortcut('a');
		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ADD_ID:
			add();
			return (true);
		}
		return (super.onOptionsItemSelected(item));
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
		View addView = inflater.inflate(R.layout.add_edit2, null);
		final DialogWrapper wrapper2 = new DialogWrapper(addView);
		new AlertDialog.Builder(this)
				.setTitle(R.string.add_title)
				.setView(addView)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int whichButton) {
								processAdd(wrapper2);
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
			values.put(Provider2.Constants2.TITLE, "Computer ");
			values.put(Provider2.Constants2.VALUE, Model.completed_timer);
		}

		values.put(Provider2.Constants2.TITLE, wrapper.getTitle());
		values.put(Provider2.Constants2.VALUE, Model.completed_timer);

		getContentResolver().insert(Provider2.Constants2.CONTENT_URI, values);
		constantsCursor.requery();
	}

	/**
	 * Delete Constant Value to SQLite database via ContentProvider
	 */
	private void processDelete(long rowId) {
		String[] args = { String.valueOf(rowId) };

		getContentResolver().delete(Provider2.Constants2.CONTENT_URI,
				Provider2.Constants2._ID + "=?", args);
		constantsCursor.requery();
	}

	class DialogWrapper {
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