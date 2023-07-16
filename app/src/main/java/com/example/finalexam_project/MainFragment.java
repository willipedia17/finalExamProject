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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Vector;

public class MainFragment extends Fragment {

    private Vector<Data> dataList = new Vector<>();
    private RecyclerView recyclerView;

    public MainFragment() {
        // Required empty public constructor
    }
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.main_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        MainAdapter adapter = new MainAdapter(getContext(), dataList);
        recyclerView.setAdapter(adapter);

        // Call fetchData() to fetch the data from the JSON API
        fetchData();
    }

    void fetchData(){
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        String url ="https://jsonplaceholder.typicode.com/posts";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int userId, id;
                            String title, body;
                            Data data;

                            userId = jsonObject.getInt("userId");
                            id = jsonObject.getInt("id");
                            title = jsonObject.getString("title");
                            body = jsonObject.getString("body");

                            data = new Data(userId, id, title, body);
                            Log.i("onResponse : ", title);
                            dataList.add(data);

                        } catch (Exception e){

                        }
                    }
                    if (recyclerView != null && recyclerView.getAdapter() != null) {
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }


        );
        requestQueue.add(jsonArrayRequest);
    }

}