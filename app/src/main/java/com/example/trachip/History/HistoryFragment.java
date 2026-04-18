package com.example.trachip.History;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trachip.History.DB.HistoryModel;
import com.example.trachip.R;

import java.util.ArrayList;
import java.util.List;

public class
HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<HistoryModel> historyList;
    private HistoryViewModel viewModel;
    Button btnClearAll;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_history, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rcy_history);
        historyList = new ArrayList<>();

        // Step 1: Setup adapter with empty list
        adapter = new HistoryAdapter(requireContext(), new ArrayList<>());

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        // Step 2: Initialize ViewModel
        viewModel = new androidx.lifecycle.ViewModelProvider(this).get(HistoryViewModel.class);


        // Step 3: Observe LiveData
        viewModel.getAll().observe(getViewLifecycleOwner(), historyList -> {
            adapter.setHistoryList(historyList);
        });

        btnClearAll = view.findViewById(R.id.btnClearHistory);

        btnClearAll.setOnClickListener(v -> {
            viewModel.clearAll();
        });

    }
}
