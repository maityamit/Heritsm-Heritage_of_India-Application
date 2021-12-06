package heritsm_heritagebyamit.example.heritsm_heritageofindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class HeritageListActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    DatabaseReference RootRef;
    GridLayoutManager gridLayoutManager;
    ImageView cart_butoon;
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heritage_list);

        recyclerView = findViewById(R.id.recycler_view_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(HeritageListActivity.this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        RootRef = FirebaseDatabase.getInstance ().getReference ().child("Sites");

        FirebaseRecyclerOptions<productModel> options =
                new FirebaseRecyclerOptions.Builder<productModel> ()
                        .setQuery ( RootRef,productModel.class )
                        .build ();

        FirebaseRecyclerAdapter<productModel,StudentViewHolder_Feature> adapter =
                new FirebaseRecyclerAdapter<productModel, StudentViewHolder_Feature>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final StudentViewHolder_Feature holder, final int position, @NonNull final productModel model) {


                        String listPostKey = getRef(position).getKey();


                        holder.prDate.setText(model.getPeriod());
                        holder.prName.setText(model.getPlace_name());
                        holder.prState.setText(model.getLocation());
                        Picasso.with(HeritageListActivity.this).load(model.getImage()).into(holder.prImg);


                       holder.itemView.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               Intent intent1 = new Intent(HeritageListActivity.this,HeritageActivity.class);
                               intent1.putExtra("ID",listPostKey);
                               startActivity(intent1);
                           }
                       });



                    }

                    @NonNull
                    @Override
                    public StudentViewHolder_Feature onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view  = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.heritage_site_layout,viewGroup,false );
                        StudentViewHolder_Feature viewHolder  = new StudentViewHolder_Feature(  view);
                        return viewHolder;

                    }

                    @Override
                    public void onDataChanged() {
                        super.onDataChanged();




                    }


                };


        recyclerView.setAdapter ( adapter );


        adapter.startListening ();
    }

    public static class StudentViewHolder_Feature extends  RecyclerView.ViewHolder
    {

        TextView prName,prDate,prState;
        ImageView prImg;
        public StudentViewHolder_Feature(@NonNull View itemView) {
            super ( itemView );
            prName = itemView.findViewById(R.id.place_name);
            prState = itemView.findViewById(R.id.place_state);
            prDate = itemView.findViewById(R.id.place_date);
            prImg = itemView.findViewById(R.id.place_image);
        }
    }

}