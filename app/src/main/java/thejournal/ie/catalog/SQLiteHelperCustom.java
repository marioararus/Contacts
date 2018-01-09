package thejournal.ie.catalog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bogdan Roatis on 12/19/2017.
 */

public class SQLiteHelperCustom extends SQLiteOpenHelper {
    private static final String DB_NAME = "catalog.db";
    private static final int VERSION = 2;
    private static final String CREATE_CONTACTS_TABEL = "CREATE TABLE " + ContactsTabel.TABLE_NAME + " ("
            + ContactsTabel._ID + " INTEGER PRIMARY KEY,"
            + ContactsTabel.COLUMN_FIRST_NAME + " TEXT,"
            + ContactsTabel.COLUMN_LAST_NAME + " TEXT,"
            + ContactsTabel.COLUMN_PHONE_NUMBER + " TEXT)";
    private static final String DELETE_CONTACTS_TABEL = "DROP TABLE IF EXISTS " + ContactsTabel.TABLE_NAME;

    public SQLiteHelperCustom(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         sqLiteDatabase.execSQL(CREATE_CONTACTS_TABEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          sqLiteDatabase.execSQL(DELETE_CONTACTS_TABEL);
          onCreate(sqLiteDatabase);
    }
}
