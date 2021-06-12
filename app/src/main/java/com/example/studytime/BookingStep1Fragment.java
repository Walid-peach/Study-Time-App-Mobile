package com.example.studytime;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BookingStep1Fragment extends Fragment implements IAllSalleLoadListener {
    //Variables
    CollectionReference allSalleRef;
    CollectionReference branchRef;
    IAllSalleLoadListener iAllSalleLoadListener;

    static BookingStep1Fragment instance;


    // View
    @BindView(R.id.spinnernew)
    MaterialSpinner spinnernew;

    Unbinder unbinder;
    private View itemView;


    public static BookingStep1Fragment getInstance() {
        if(instance == null)
            instance = new BookingStep1Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        allSalleRef = FirebaseFirestore.getInstance().collection("Salle");
        iAllSalleLoadListener = this;
        loadAllSalle();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        itemView = inflater.inflate(R.layout.fragment_booking_1,container,false);
        unbinder = ButterKnife.bind(this.itemView);

        //loadAllSalle();

        return itemView;
    }

    private void loadAllSalle() {
        allSalleRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<String> list = new ArrayList<>();
                            list.add("choisissez une Salle");
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.fragment_booking_1,list);
                            arrayAdapter.setDropDownViewResource(R.layout.fragment_booking_1);
                           // spinnernew.setAdapter(arrayAdapter);
                            for (QueryDocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                                list.add(documentSnapshot.getId());

                            }
                            Log.d("Salle"," "+list);
                            iAllSalleLoadListener.onAllSalleloadSuccess(list);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                iAllSalleLoadListener.onAllSalleloadFailed(e.getMessage());
            }
        });

    }

    @Override
    public void onAllSalleloadSuccess(List<String> areaNameList) {
        spinnernew.setItems(areaNameList);
    }

    @Override
    public void onAllSalleloadFailed(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}
