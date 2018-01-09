package thejournal.ie.catalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAddContact;
    private TextView tvDisplayContact;
    private EditText etContactFirstName;
    private EditText etContactLastName;
    private EditText etContactNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ContactsDAO contactsDao = new ContactsDAO(this);
        //contactsDao.insertContact();
        btnAddContact = (Button) findViewById(R.id.btn_add_contact);
        tvDisplayContact = (TextView) findViewById(R.id.tv_contact);
        etContactFirstName = (EditText) findViewById(R.id.et_contact_first_name);
        etContactLastName = (EditText) findViewById(R.id.et_contact_last_name);
        etContactNumber = (EditText) findViewById(R.id.et_contact_number);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            ContactPOJO contact;
            List<ContactPOJO> contacts;
            @Override
            public void onClick(View view) {
                contact = new ContactPOJO(etContactFirstName.getText().toString(),etContactLastName.getText().toString(),etContactNumber.getText().toString());
                contactsDao.insert(contact);
                contacts = contactsDao.readContacts();
                StringBuilder builder = new StringBuilder();
                for (ContactPOJO c : contacts) {
                    builder.append(c.getId()).append(" ").append(c.getFirstName()).append(" ").append(c.getLastName()).append(" ").append(c.getPhoneNumber()).append("\n");
                }
                tvDisplayContact.setText(builder.toString());
            }
        });
    }

}
