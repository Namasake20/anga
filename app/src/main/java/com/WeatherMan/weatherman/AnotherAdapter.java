package com.WeatherMan.weatherman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnotherAdapter extends RecyclerView.Adapter<AnotherAdapter.ViewHolder> {
    Context context;
    List<WeatherModel> weatherRVModals;

    public AnotherAdapter(Context context, List<WeatherModel> weatherRVModals) {
        this.context = context;
        this.weatherRVModals = weatherRVModals;
    }

    @NonNull
    @Override
    public AnotherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.another_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherModel weatherRVModal = weatherRVModals.get(position);;
        double temp = weatherRVModal.getMax_temp();
        int roundedValue = (int) Math.rint(temp);
        holder.MaxTV.setText("Max:"+Integer.toString(roundedValue)+"°c");
        double tempM = weatherRVModal.getMin_temp();
        int roundedTemp = (int) Math.rint(tempM);
        holder.MinTV.setText("Min:"+Integer.toString(roundedTemp)+"°c");
        double wind = weatherRVModal.getWind_speed();
        int roundedWind = (int) Math.rint(wind);
        holder.WindTV.setText("Wind: "+Integer.toString(roundedWind)+"mph");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("EEE");
        try {
            Date date = input.parse(weatherRVModal.getApplicable_date());
            holder.DayTV.setText(output.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return weatherRVModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView DayTV,MinTV,MaxTV,WindTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            DayTV = itemView.findViewById(R.id.TVDay);
            MinTV = itemView.findViewById(R.id.MinTemp);
            MaxTV = itemView.findViewById(R.id.MaxTem);
            WindTV = itemView.findViewById(R.id.TVWind);
        }
    }
}
