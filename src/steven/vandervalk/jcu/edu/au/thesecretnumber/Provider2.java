package steven.vandervalk.jcu.edu.au.thesecretnumber;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;

public class Provider2 extends ContentProvider {
	private static final int CONSTANTS = 1;
	private static final int CONSTANT_ID = 2;
	private static final UriMatcher MATCHER;
	private static final String TABLE = "constants";

	public static final class Constants2 implements BaseColumns {
		public static final Uri CONTENT_URI = Uri
				.parse("content://steven.vandervalk.jcu.edu.au.Provider/constants");
		// Expose a content URI for this provider. This URI will be used to
		// access the Content Provider
		// from within application components using a ContentResolver
		/**
		 * SQL table columns
		 */
		public static final String DEFAULT_SORT_ORDER = "title";
		public static final String TITLE = "title";
		public static final String VALUE = "value";
	}

	static {
		MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		// MATCHER.addURI("com.nineandroid.constants.Provider", "constants",
		// CONSTANTS);
		// MATCHER.addURI("com.nineandroid.constants.Provider", "constants/#",
		// CONSTANT_ID);
	}

	private DatabaseHelper dbHelper = null;

	@Override
	public boolean onCreate() {
		Log.d("Provider", "onCreate");
		dbHelper = new DatabaseHelper(getContext());
		return ((dbHelper == null) ? false : true);
	}

	@Override
	public Cursor query(Uri url, String[] projection, String selection,
			String[] selectionArgs, String sort) {
		Log.d("Provider", "query");
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(TABLE);
		String orderBy;
		if (TextUtils.isEmpty(sort)) {
			orderBy = Constants2.DEFAULT_SORT_ORDER;
		} else {
			orderBy = sort;
		}
		Cursor c = qb.query(dbHelper.getReadableDatabase(), projection,
				selection, selectionArgs, null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), url);
		return (c);
	}

	@Override
	public String getType(Uri url) {
		Log.d("Provider", "getType");
		if (isCollectionUri(url)) {
			return ("vnd.android.cursor.dir/vnd.nineandroid.constant");
		}
		return ("vnd.android.cursor.item/vnd.nineandroid.constant");
	}

	@Override
	public Uri insert(Uri url, ContentValues initialValues) {
		long rowID = dbHelper.getWritableDatabase().insert(TABLE,
				Constants2.TITLE, initialValues);
		if (rowID > 0) {
			Uri uri = ContentUris.withAppendedId(
					Provider2.Constants2.CONTENT_URI, rowID);
			getContext().getContentResolver().notifyChange(uri, null);
			return (uri);
		}
		throw new SQLException("Failed to insert row into " + url);
	}

	@Override
	public int delete(Uri url, String where, String[] whereArgs) {
		int count = dbHelper.getWritableDatabase().delete(TABLE, where,
				whereArgs);
		getContext().getContentResolver().notifyChange(url, null);
		return (count);
	}

	@Override
	public int update(Uri url, ContentValues values, String where,
			String[] whereArgs) {
		int count = dbHelper.getWritableDatabase().update(TABLE, values, where,
				whereArgs);
		getContext().getContentResolver().notifyChange(url, null);
		return (count);
	}

	private boolean isCollectionUri(Uri url) {
		return (MATCHER.match(url) == CONSTANTS);
	}
}