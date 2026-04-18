package com.example.trachip.History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trachip.History.DB.HistoryModel;
import com.example.trachip.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HistoryModel> historyList;
    public HistoryAdapter(@NonNull Context context, List<HistoryModel> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HistoryViewHolder hvh = (HistoryViewHolder) holder;
        HistoryModel item = historyList.get(position);
        // Convert timestamp to readable date
        String date = new java.text.SimpleDateFormat("dd MMM yyyy  HH:mm",
                java.util.Locale.getDefault())
                .format(new java.util.Date(item.getTimestamp()));
        hvh.tv_1.setText(date);

        item = historyList.get(position);
        hvh.tvIp.setText(item.getIp());
        hvh.tvCountry.setText(item.getCountry());
        hvh.tvCity.setText(item.getCity());
        hvh.tvLatLon.setText(item.getLat() + "°N, " + item.getLon() + "°E");

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_1, tvIp,tvCountry,tvCity,tvLatLon,tvLoc;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_1= itemView.findViewById(R.id.tv_1);
            tvIp= itemView.findViewById(R.id.tvIp);
            tvCountry= itemView.findViewById(R.id.tvCountry);
            tvCity= itemView.findViewById(R.id.tvCity);
            tvLoc= itemView.findViewById(R.id.tvLoc);
            tvLatLon=itemView.findViewById(R.id.tvLatLon);


        }
    }
    // Call this from Fragment when LiveData updates
    public void setHistoryList(List<HistoryModel> newList) {
        this.historyList = newList;
        notifyDataSetChanged();
    }
}
