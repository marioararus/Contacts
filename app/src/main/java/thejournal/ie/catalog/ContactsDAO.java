package thejournal.ie.catalog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan Roatis on 12/19/2017.
 */

public class ContactsDAO {
    private SQLiteHelperCustom dbHelper;
    private String[] projection = {
            ContactsTabel.COLUMN_LAST_NAME,
            ContactsTabel.COLUMN_FIRST_NAME,
            ContactsTabel.COLUMN_PHONE_NUMBER};
    private String selection = ContactsTabel.COLUMN_FIRST_NAME + " = ?";

    public ContactsDAO(Context context) {
        dbHelper = new SQLiteHelperCustom(context);
    }

    public void insert(ContactPOJO contact) {
        ContentValues values = new ContentValues();
        values.put(ContactsTabel.COLUMN_FIRST_NAME, contact.getFirstName());
        values.put(ContactsTabel.COLUMN_LAST_NAME, contact.getLastName());
        values.put(ContactsTabel.COLUMN_PHONE_NUMBER, contact.getPhoneNumber());

        dbHelper.getWritableDatabase().insert(ContactsTabel.TABLE_NAME, null, values);
    }

    public ContactPOJO readContact(int id) {
        ContactPOJO contact = null;

        String[] contactsDetails = {
                ContactsTabel._ID,
                ContactsTabel.COLUMN_FIRST_NAME,
                ContactsTabel.COLUMN_LAST_NAME,
                ContactsTabel.COLUMN_PHONE_NUMBER
        };
        String selection = ContactsTabel._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = dbHelper.getReadableDatabase().query(
                ContactsTabel.TABLE_NAME,
                contactsDetails,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsTabel._ID));
            String itemFirstName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsTabel.COLUMN_FIRST_NAME));
            String itemLastName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsTabel.COLUMN_LAST_NAME));
            String itemNumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsTabel.COLUMN_PHONE_NUMBER));
            contact = new ContactPOJO(itemId, itemFirstName, itemLastName, itemNumber);
        }
        cursor.close();
        return contact;
    }

    public List<ContactPOJO> readContacts() {
        String[] contactColumns = {
                ContactsTabel._ID,
                ContactsTabel.COLUMN_FIRST_NAME,
                ContactsTabel.COLUMN_LAST_NAME,
                ContactsTabel.COLUMN_PHONE_NUMBER
        };
        Cursor cursor = dbHelper.getReadableDatabase().query(
                ContactsTabel.TABLE_NAME,
                contactColumns,
                null,
                null,
                null,
                null,
                null
        );
        List<ContactPOJO> itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsTabel._ID));
            String itemFirstName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsTabel.COLUMN_FIRST_NAME));
            String itemLastName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsTabel.COLUMN_LAST_NAME));
            String itemNumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsTabel.COLUMN_PHONE_NUMBER));
            itemIds.add(new ContactPOJO(itemId, itemFirstName, itemLastName, itemNumber));
        }
        cursor.close();
        return itemIds;
    }

}
