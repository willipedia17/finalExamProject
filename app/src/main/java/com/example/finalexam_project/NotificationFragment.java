package com.example.finalexam_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Vector;

public class NotificationFragment extends Fragment {

    private RecyclerView notifRV;
    public DatabaseHelper databaseHelper;

    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notifRV = view.findViewById(R.id.notif_RV);
        notifRV.setLayoutManager(new LinearLayoutManager(getContext()));
        notifRV.setHasFixedSize(true);

        NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(), getNotificationHistory());
        notifRV.setAdapter(notificationAdapter);

    }
    private Vector<NotificationModel> getNotificationHistory() {
        databaseHelper = new DatabaseHelper(getContext());
        return databaseHelper.getAllNotifications();
    }
}