package edu.birzeit.todo3;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "StudentNotes.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NOTES = "notes";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_TITLE = "title";
        private static final String COLUMN_CONTENT = "content";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NOTES + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_CONTENT + " TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
            onCreate(sqLiteDatabase);
        }
        public long addNote(String title, String content) {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", title);
            contentValues.put("content", content);
            return sqLiteDatabase.insert("notes", null, contentValues);
        }

        public Cursor getLatestNote() {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            return sqLiteDatabase.rawQuery("SELECT * FROM notes ORDER BY id DESC LIMIT 1", null);
        }

        public int getNoteCount() {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(*) FROM notes", null);
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count;
        }

        public int deleteAllNotes() {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            return sqLiteDatabase.delete("notes", null, null);
        }
    }