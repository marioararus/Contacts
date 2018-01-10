package thejournal.ie.catalog;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

public class LocationFragment extends Fragment {
    private TextView tvTitle;
    private TextView tvDescription;
    private ImageView ivImg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = view.findViewById(R.id.tv_title);
        tvDescription = view.findViewById(R.id.tv_description);
        ivImg = view.findViewById(R.id.iv_img);

        //TODO move this logic somewhere else
        Ion.with(this)
                .load("http://eoapps.solenix.ch/sentinel-images/index.json")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        JsonElement jsonElement = result.get(0);
                        JsonObject jsonObj = jsonElement.getAsJsonObject();
                        tvTitle.setText(jsonObj.get("locationName").getAsString());
                        tvDescription.setText(jsonObj.get("text").getAsString());
                        Picasso.with(getActivity()).load(jsonObj.get("imagePath").getAsString()).into(ivImg);
                    }
                });
    }
}
