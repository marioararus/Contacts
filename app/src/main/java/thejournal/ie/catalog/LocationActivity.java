package thejournal.ie.catalog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

public class LocationActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvDescription;
    private ImageView ivImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvTitle = findViewById(R.id.tv_title);
        tvDescription = findViewById(R.id.tv_description);
        ivImg = findViewById(R.id.iv_img);

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
                        Picasso.with(LocationActivity.this).load(jsonObj.get("imagePath").getAsString()).into(ivImg);
                    }
                });
    }
}
