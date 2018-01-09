package thejournal.ie.catalog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAddContact;
    private Button btnJson;
    private TextView tvDisplayContact;
    private EditText etContactFirstName;
    private EditText etContactLastName;
    private EditText etContactNumber;
    private ContactsDAO contactsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactsDao = new ContactsDAO(this);
        btnAddContact = findViewById(R.id.btn_add_contact);
        btnJson = findViewById(R.id.btn_json);
        tvDisplayContact = findViewById(R.id.tv_contact);
        etContactFirstName = findViewById(R.id.et_contact_first_name);
        etContactLastName = findViewById(R.id.et_contact_last_name);
        etContactNumber = findViewById(R.id.et_contact_number);
        btnAddContact.setOnClickListener(this);
        btnJson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_contact:
                ContactPOJO contact;
                List<ContactPOJO> contacts;
                contact = new ContactPOJO(etContactFirstName.getText().toString(), etContactLastName.getText().toString(), etContactNumber.getText().toString());
                contactsDao.insert(contact);
                contacts = contactsDao.readContacts();
                StringBuilder builder = new StringBuilder();
                for (ContactPOJO c : contacts) {
                    builder.append(c.getId()).append(" ").append(c.getFirstName()).append(" ").append(c.getLastName()).append(" ").append(c.getPhoneNumber()).append("\n");
                }
                tvDisplayContact.setText(builder.toString());
                break;
            case R.id.btn_json:
                startActivity(new Intent(this, LocationActivity.class));
                break;
        }
    }
}
