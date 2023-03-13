//package Model;
//
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class RezeptsammlungRepo {
//
//	private Rezeptsammlung rezeptsammlung;
//
//	private Benutzer benutzer;
//
//	private RezeptRepo rezeptRepo;
//
//	private Rezept rezept;
//
//	private FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//	public void deleteRezeptsammlung(Rezeptsammlung rezeptsammlung) {
//		db.collection("Rezeptsammlung").document(rezeptsammlung.getID())
//				.delete()
//				.addOnSuccessListener(new OnSuccessListener<Void>() {
//					@Override
//					public void onSuccess(Void aVoid) {
////						Log.d("DocumentSnapshot successfully deleted!");
//					}
//				});
//	}
//
//	public void addRezeptsammlung(Rezeptsammlung rezeptsammlung) {
//
//	}
//
//	public void deleteRezeptFromSammlung(Rezept rezept, Rezeptsammlung rezeptsammlung) {
//		db.collection("Rezeptsammlung").document(rezeptsammlung.getID()).collection("Rezepte").whereEqualTo("")
//				.addOnSuccessListener(new OnSuccessListener<Void>() {
//					@Override
//					public void onSuccess(Void aVoid) {
//
////						Log.d("DocumentSnapshot successfully deleted!");
//					}
//				});
//	}
//
//	public void addRezeptRezeptsammlung(Rezeptsammlung rezeptsammlung, Rezept rezept) {
//
//	}
//
////	// ref: https://firebase.google.com/docs/firestore/query-data/get-data#java_2
//	public Rezeptsammlung getRezeptsammlung(Benutzer benutzer) {
//		Rezeptsammlung resultSet = null;
//		return db.collection("rezepte").whereEqualTo("name", benutzer.getName()).get().addOnCompleteListener(new OnCompleteListener<QueryDocumentSnapshot>() {
//			@Override
//			public void onComplete(@NonNull Task<QueryDocumentSnapshot> task) {
//				if (task.isSuccessful()) {
//					resultSet = (Rezeptsammlung) task.getResult().getData();
//				}
//			}
//		});
//	}
//
//}
