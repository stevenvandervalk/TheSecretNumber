package steven.vandervalk.jcu.edu.au.thesecretnumber;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "constants.db";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Cursor c = db
				.rawQuery(
						"SELECT name FROM sqlite_master WHERE type='table' AND name='constants'",
						null);

		try {
			if (c.getCount() == 0) {
				db.execSQL("CREATE TABLE constants (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, value REAL);");

				ContentValues cv = new ContentValues();

				cv.put(Provider.Constants.TITLE, "Kara Martin");
				cv.put(Provider.Constants.VALUE, "3:15");
				db.insert("constants", Provider.Constants.TITLE, cv);

				cv.put(Provider.Constants.TITLE, "Steven, V");
				cv.put(Provider.Constants.VALUE, "3:10");
				db.insert("constants", Provider.Constants.TITLE, cv);

			}
		} finally {
			c.close();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		android.util.Log.w("Constants",
				"Upgrading database, which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS constants");
		onCreate(db);
	}
}