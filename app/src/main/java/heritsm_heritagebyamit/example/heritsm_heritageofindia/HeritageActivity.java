package heritsm_heritagebyamit.example.heritsm_heritageofindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HeritageActivity extends AppCompatActivity {

    DatabaseReference RootRef;
    TextView place_namee,place_periodd,place_loc,place_desccc;
    LinearLayout place_go_to;
    String go_to_link = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heritage);
        Intent intent = getIntent();
        String key = intent.getStringExtra("ID");

        place_go_to = findViewById(R.id.place_name_go_to);
        place_namee = findViewById(R.id.place_name_detail);
        place_periodd = findViewById(R.id.place_name_period);
        place_loc = findViewById(R.id.place_name_location);
        place_desccc = findViewById(R.id.place_name_desc);


        RootRef = FirebaseDatabase.getInstance ().getReference ().child("Sites");

        RetriveData(key);

        place_go_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void RetriveData(String key) {

        RootRef.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {

                    String place_name = dataSnapshot.child("place_name").getValue().toString();
                    String place_location = dataSnapshot.child("location").getValue().toString();
                    String place_period = dataSnapshot.child("period").getValue().toString();
                    String place_go_to = dataSnapshot.child("go_to").getValue().toString();
                    String place_description = dataSnapshot.child("description").getValue().toString();
                    String place_image = dataSnapshot.child("image").getValue().toString();


                    place_namee.setText(place_name);
                    place_periodd.setText(place_period);
                    place_loc.setText(place_location);
                    go_to_link = place_go_to;

                    place_desccc.setText(place_description);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}