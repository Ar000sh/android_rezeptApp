package com.example.myapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Adapter.RezeptListAdapter;
import Model.RezeptSammlungModel;

public class meineRezepte extends Fragment {
    private Spinner kategorie;
    private TextView txt;
    private ListView listView;

    private ArrayList<RezeptSammlungModel> rezeptSammlungModelArrayList;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public meineRezepte() {
    }

    public meineRezepte(int contentLayoutId) {
        super(contentLayoutId);
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.activity_meine_rezepte, container, false);

        listView = (ListView) v.findViewById(R.id.idListView);
        rezeptSammlungModelArrayList = new ArrayList<>();

        Query docRef = db.collection("rezepte");
        docRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        RezeptSammlungModel model = d.toObject(RezeptSammlungModel.class);
                        rezeptSammlungModelArrayList.add(model);
                    }
                    RezeptListAdapter adapter = new RezeptListAdapter(getContext(), rezeptSammlungModelArrayList);
                    listView.setAdapter(adapter);
                }
            }
        });
        return v;
    }
}