package thejournal.ie.catalog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bogdan Roatis on 1/11/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private List<ContactPOJO> contacts;

    public DataAdapter(List<ContactPOJO> contactsList) {
        contacts = contactsList;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new list item with the item_data layout
        return new DataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false));
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        // Take the element from position position
        ContactPOJO contactPOJO = contacts.get(position);

        //Put the data from the element into the Views
        holder.tvFirstName.setText(contactPOJO.getFirstName());
        holder.tvLastName.setText(contactPOJO.getLastName());
        holder.tvPhoneNumber.setText(contactPOJO.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contacts != null ? contacts.size() : 0;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView tvFirstName;
        TextView tvLastName;
        TextView tvPhoneNumber;

        public DataViewHolder(View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tv_first_name);
            tvLastName = itemView.findViewById(R.id.tv_last_name);
            tvPhoneNumber = itemView.findViewById(R.id.tv_phone_number);
        }
    }
}
