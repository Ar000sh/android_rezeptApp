package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.ArrayList;

import Model.RezeptSammlungModel;
/*
Quelle: https://www.geeksforgeeks.org/how-to-create-dynamic-listview-in-android-using-firebase-firestore/
 */

public class RezeptListAdapter extends ArrayAdapter<RezeptSammlungModel> {
    public RezeptListAdapter(@NonNull Context context, ArrayList<RezeptSammlungModel> dataModalRezeptSammlungModel) {
        super(context, 0, dataModalRezeptSammlungModel);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        RezeptSammlungModel dataModal = getItem(position);

        // initializing our UI components of list view item.
        TextView rezeptName = listitemView.findViewById(R.id.idRezeptName);
        ImageView rezeptÄndern = listitemView.findViewById(R.id.idIbtnRezeptLöschen);
        ImageView rezeptLöschen = listitemView.findViewById(R.id.idbtnRezeptÄndern);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        rezeptName.setText(dataModal.getName());

        // in below line we are using Picasso to
        // load image from URL in our Image VIew.
//        Picasso.get().load(dataModal.getImgUrl()).into(courseIV);

        // below line is use to add item click listener
        // for our item of list view.
        rezeptÄndern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                Toast.makeText(getContext(), "Item clicked is : Rezept ändern" , Toast.LENGTH_SHORT).show();
            }
        });
        rezeptLöschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                Toast.makeText(getContext(), "Item clicked is : Rezept löschen" , Toast.LENGTH_SHORT).show();
            }
        });

        return listitemView;
    }
}
