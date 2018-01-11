package thejournal.ie.catalog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bogdan Roatis on 1/11/2018.
 */

public class ViewDataFragment extends Fragment {
    private RecyclerView dataList;
    private DataAdapter dataAdapter;
    private ContactsDAO contactsDAO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_data, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataList = view.findViewById(R.id.data_list);
        contactsDAO = new ContactsDAO(getActivity());
        dataAdapter = new DataAdapter(contactsDAO.readContacts());
        dataList.setAdapter(dataAdapter);
        dataList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
