package com.example.myapplication;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firestore.v1.WriteResult;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.RezeptController;
import Model.Benutzer;
import Model.Rezept;
import Model.Zutat;
import Model.Zutateninformation;

public class RezeptErstellen extends Fragment  {
    private Spinner kategorie,ernährungsform,portionen,spinnerzutat1,spinnerzutat2,spinnerzutat3,spinnerzutat4,spinnerzutat5;
    private Button bnt;
    private String ktg;
    private String ernährungsformText;
    private String portionText;
    private EditText zutaten1,zutaten2,zutaten3,zutaten4,zutaten5,rezeptName;
    private EditText anleitung;
    private ImageView imageView;
    private Uri filePath;
    StorageReference storageReference;
    FirebaseStorage storage;
    private String bildid;
    private String dateneinheitzuatat1,dateneinheitzuatat2,dateneinheitzuatat3,dateneinheitzuatat4,dateneinheitzuatat5;
    private FirebaseAuth mAuth;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_rezept_erstellen, container, false);
        kategorie = v.findViewById(R.id.spinnerKategorie);
        ernährungsform = v.findViewById(R.id.spinnerErnährungsform);
        portionen = v.findViewById(R.id.spinnerPortionen);
        bnt = v.findViewById(R.id.button1);
        zutaten1 = v.findViewById(R.id.zutat1);
        zutaten2 = v.findViewById(R.id.zutat2);
        zutaten3 = v.findViewById(R.id.zutat3);
        zutaten4 = v.findViewById(R.id.zutat4);
        zutaten5 = v.findViewById(R.id.zutat5);
        anleitung = v.findViewById(R.id.rezeptErstellen_anleitung);
        rezeptName = v.findViewById(R.id.rezept_erstellen_name);
        spinnerzutat1 = v.findViewById(R.id.spinner_zutat1);
        spinnerzutat2 = v.findViewById(R.id.spinner_zutat2);
        spinnerzutat3 = v.findViewById(R.id.spinner_zutat3);
        spinnerzutat4 = v.findViewById(R.id.spinner_zutat4);
        spinnerzutat5 = v.findViewById(R.id.spinner_zutat5);




        imageView = v.findViewById(R.id.imgView);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentuser = mAuth.getCurrentUser();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RezeptController controller = new RezeptController();

                ArrayList<String> zutaten = zutatenmixer();
                ArrayList<String> mengeAngabe = new ArrayList<>();
                ArrayList<String> mengenEinheit = dateinheitmixer();
                ArrayList<String> zutatennamen = new ArrayList<>();
                String dig = "";
                String zutatname;
                for (String zutat : zutaten) {

                    for(int i = 0;  i < zutat.length(); i++) {
                        if (Character.isDigit(zutat.charAt(i))) {
                            dig += zutat.charAt(i);
                        }
                    }
                    zutatname = zutat.replaceAll("[0-9]", "");
                    mengeAngabe.add(dig);
                    zutatennamen.add(zutatname);
                }

                // Bild upload


                ArrayList<Zutateninformation> zutateninformationsarray = new ArrayList<>();
                for (int i = 0; i < zutaten.size(); i++) {
                    zutateninformationsarray.add(new Zutateninformation(mengeAngabe.get(i), mengenEinheit.get(i),new Zutat(zutatennamen.get(i))));
                }
                final String randomKey = UUID.randomUUID().toString();

                // leert das Textfeld nach dem bestätigen
                //zutateninfo.setText("");

                uploadPicture();

                if (!rezeptName.getText().toString().isEmpty() && !ktg.isEmpty() && !zutaten.isEmpty() &&  !ernährungsformText.isEmpty() && !bildid.isEmpty() && !portionText.isEmpty() && !anleitung.getText().toString().isEmpty()) {


                    Rezept rezept = controller.rezeptErstellen(randomKey,rezeptName.getText().toString(),
                            Integer.parseInt(portionText),anleitung.getText().toString(),zutateninformationsarray,ktg,ernährungsformText, bildid);
                    db.collection("rezepte").document(randomKey).set(rezept).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity().getApplicationContext(), "Rezept wurde erstellt", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), "Rezept wurde nicht erstellt", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Pflichtfeld wurde nicht ausgefüllt", Toast.LENGTH_SHORT).show();
                }
                DocumentReference ref = db.collection("Users").document(currentuser.getUid());

                ref.update("rezepte", FieldValue.arrayUnion(randomKey));
            }

        });





        // Spinner für die drei Dropdown Aktionen
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.kategorie_items, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kategorie.setAdapter(adapter3);
        kategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                ktg = text;
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.ernährungsform_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ernährungsform.setAdapter(adapter);
        ernährungsform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                ernährungsformText = text;
                Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.portion, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        portionen.setAdapter(adapter1);
        portionen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                portionText = text;
                Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

// zutat1
        ArrayAdapter<CharSequence> adapter_zutat1 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.Dateneinheit, android.R.layout.simple_spinner_item);
        adapter_zutat1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerzutat1.setAdapter(adapter_zutat1);
        spinnerzutat1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                dateneinheitzuatat1 = text;
                Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // zutat2
        ArrayAdapter<CharSequence> adapter_zutat2 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.Dateneinheit, android.R.layout.simple_spinner_item);
        adapter_zutat2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerzutat2.setAdapter(adapter_zutat2);
        spinnerzutat2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                dateneinheitzuatat2 = text;
                Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // zutat3
        ArrayAdapter<CharSequence> adapter_zutat3 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.Dateneinheit, android.R.layout.simple_spinner_item);
        adapter_zutat3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerzutat3.setAdapter(adapter_zutat3);
        spinnerzutat3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                dateneinheitzuatat3 = text;
                Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // zutat4
        ArrayAdapter<CharSequence> adapter_zutat4 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.Dateneinheit, android.R.layout.simple_spinner_item);
        adapter_zutat4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerzutat4.setAdapter(adapter_zutat4);
        spinnerzutat4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                dateneinheitzuatat4 = text;
                Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // zutat5
        ArrayAdapter<CharSequence> adapter_zutat5 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.Dateneinheit, android.R.layout.simple_spinner_item);
        adapter_zutat5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerzutat5.setAdapter(adapter_zutat5);
        spinnerzutat5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                dateneinheitzuatat5 = text;
                Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return v;
    }




    private void selectImage() {
        // Defining Implicit Intent to mobile gallery

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }
// Path auflösen
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null) {
            filePath = data.getData();
            imageView.setImageURI(filePath);
            //uploadPicture();
        }
    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("waiting");
        pd.show();
        final String randomKey = UUID.randomUUID().toString();
        bildid = randomKey;
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        //Snackbar.make(imageView.findViewById(R.id.imgView), "Imageuploaded", Snackbar.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Percent"  + (int) progressPercent + "%");
            }
        });


    }

    private ArrayList<String> zutatenmixer() {
        ArrayList<String> result = new ArrayList<>();
        if (!zutaten1.getText().toString().isEmpty()) result.add(zutaten1.getText().toString());
        if (!zutaten2.getText().toString().isEmpty()) result.add(zutaten2.getText().toString());
        if (!zutaten3.getText().toString().isEmpty()) result.add(zutaten3.getText().toString());
        if (!zutaten4.getText().toString().isEmpty()) result.add(zutaten4.getText().toString());
        if (!zutaten5.getText().toString().isEmpty()) result.add(zutaten5.getText().toString());
        return result;

    }
    private ArrayList<String> dateinheitmixer() {
        ArrayList<String> result = new ArrayList<>();
        if (!zutaten1.getText().toString().isEmpty()) result.add(dateneinheitzuatat1);
        if (!zutaten2.getText().toString().isEmpty()) result.add(dateneinheitzuatat2);
        if (!zutaten3.getText().toString().isEmpty()) result.add(dateneinheitzuatat3);
        if (!zutaten4.getText().toString().isEmpty()) result.add(dateneinheitzuatat4);
        if (!zutaten5.getText().toString().isEmpty()) result.add(dateneinheitzuatat5);
        return result;
    }


}
