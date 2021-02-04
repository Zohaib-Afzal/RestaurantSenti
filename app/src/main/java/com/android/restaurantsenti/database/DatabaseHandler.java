package com.android.restaurantsenti.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.android.restaurantsenti.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler {
    HashMap<String, Object> data;
    Integer statusTemp;
    ArrayList<User> userArrayList = new ArrayList<>();

    public ArrayList<User> getAllData() {

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if(task.getResult()!=null) {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    User fireBaseData = new User();
                                    data = (HashMap<String, Object>) document.getData();
                                    fireBaseData.setEmail(data.get("email").toString());
                                    fireBaseData.setFullName(data.get("fullName").toString());
                                    fireBaseData.setPassword(data.get("password").toString());
                                    fireBaseData.setUserName(data.get("userName").toString());
                                    userArrayList.add(new User(fireBaseData));
                                    Log.d("Data", document.getId() + " => " + document.getData());
                                }
                            }
                        } else {
                            Log.w("error", "Error getting documents.", task.getException());
                        }
                    }
                });

        return userArrayList;
    }

    public void addToDatabase(User user){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        Map<String, Object> data = new HashMap<>();
        data.put("email", user.getEmail());
        data.put("fullName", user.getFullName());
        data.put("password", user.getPassword());
        data.put("userName", user.getUserName());
        firebaseFirestore.collection("users")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });



    }
}
