package thejournal.ie.catalog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Bogdan Roatis on 1/11/2018.
 */

public class InsertDataFragment extends Fragment implements View.OnClickListener {
    private Button btnAddContact;
    private EditText etContactFirstName;
    private EditText etContactLastName;
    private EditText etContactNumber;
    private ContactsDAO contactsDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_insert_data, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactsDao = new ContactsDAO(getActivity());
        btnAddContact = view.findViewById(R.id.btn_add_contact);
        etContactFirstName = view.findViewById(R.id.et_contact_first_name);
        etContactLastName = view.findViewById(R.id.et_contact_last_name);
        etContactNumber = view.findViewById(R.id.et_contact_number);
        btnAddContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_contact:
                ContactPOJO contact;
                List<ContactPOJO> contacts;
                contact = new ContactPOJO(etContactFirstName.getText().toString(), etContactLastName.getText().toString(), etContactNumber.getText().toString());
                if (isEmpty(etContactFirstName.getText().toString()) && isEmpty(etContactLastName.getText().toString())
                    && isEmpty(etContactNumber.getText().toString()) && isNumber(etContactNumber.getText().toString())) {
                    contactsDao.insert(contact);
                }
                break;
        }
    }

    public boolean isNumber(String number) {
        if (number.matches("\\d+(?:\\.\\d+)?")) {
            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(getActivity(), "Invalid number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean isEmpty(String etName) {
        if (etName.length() == 0) {
            Toast.makeText(getActivity(), "Write your name", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (etName.contains(" ") | etName.contains("\\W")) {
            Toast.makeText(getActivity(), "The name contains spaces or special characters", Toast.LENGTH_SHORT).show();
            return true;
        }
        return true;
//        if (etName.matches(".*[^a-z^0-9].*"))
//        return true;
    }
}
