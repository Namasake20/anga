package com.WeatherMan.weatherman;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    Context context;
    List<WeatherModel> weatherRVModals;


    public WeatherAdapter(Context context, List<WeatherModel> weatherRVModals) {
        this.context = context;
        this.weatherRVModals = weatherRVModals;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        WeatherModel weatherRVModal = weatherRVModals.get(position);
        double temp = weatherRVModal.getThe_temp();
        int roundedValue = (int) Math.rint(temp);
        holder.temperatureTv.setText(Integer.toString(roundedValue)+"°c");
        holder.TVCondition.setText(weatherRVModal.getWeather_state_name());
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("EEE");
        try {
            Date date = input.parse(weatherRVModal.getApplicable_date());
            holder.TimeTV.setText(output.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        switch (weatherRVModal.getWeather_state_name()) {
            case "Heavy Cloud": {
                String uri = "@drawable/ic_hcloud";
                int imageResource = context.getResources().getIdentifier(uri,null,context.getPackageName());
                Drawable res = context.getResources().getDrawable(imageResource);
                holder.conditionIV.setImageDrawable(res);
                break;
            }
            case "Light Rain": {
                String uri = "@drawable/ic_lrain";
                int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                Drawable res = context.getResources().getDrawable(imageResource);
                holder.conditionIV.setImageDrawable(res);

                break;
            }
            case "Light Cloud": {
                String uri = "@drawable/ic_lcloud";
                int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                Drawable res = context.getResources().getDrawable(imageResource);
                holder.conditionIV.setImageDrawable(res);

                break;
            }
            case "Heavy Rain": {
                String uri = "@drawable/ic_hrain";
                int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                Drawable res = context.getResources().getDrawable(imageResource);
                holder.conditionIV.setImageDrawable(res);

                break;
            }
            case "Clear": {
                String uri = "@drawable/ic_clears";
                int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                Drawable res = context.getResources().getDrawable(imageResource);
                holder.conditionIV.setImageDrawable(res);

                break;
            }
            case "Showers": {
                String uri = "@drawable/ic_showers";
                int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                Drawable res = context.getResources().getDrawable(imageResource);
                holder.conditionIV.setImageDrawable(res);

                break;
            }
            case "Sleet": {
                String uri = "@drawable/ic_sleet";
                int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                Drawable res = context.getResources().getDrawable(imageResource);
                holder.conditionIV.setImageDrawable(res);

                break;
            }
            case "Hail": {
                String uri = "@drawable/ic_hail";
                int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                Drawable res = context.getResources().getDrawable(imageResource);
                holder.conditionIV.setImageDrawable(res);

                break;
            }
            case "Thunderstorm": {
                String uri = "@drawable/ic_thunder";
                int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                Drawable res = context.getResources().getDrawable(imageResource);
                holder.conditionIV.setImageDrawable(res);

                break;
            }
            case "Snow": {
                String uri = "@drawable/ic_snow";
                int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                Drawable res = context.getResources().getDrawable(imageResource);
                holder.conditionIV.setImageDrawable(res);

                break;
            }
            default: {
                String uri = "@drawable/partlycloudy";
                int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                Drawable res = context.getResources().getDrawable(imageResource);
                holder.conditionIV.setImageDrawable(res);

                break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return weatherRVModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TVCondition,temperatureTv,TimeTV;
        ImageView conditionIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            TVCondition = itemView.findViewById(R.id.ConditionTV);
            temperatureTv = itemView.findViewById(R.id.TVTemperature);
            conditionIV = itemView.findViewById(R.id.IVCondition);
            TimeTV = itemView.findViewById(R.id.TVTime);
        }
    }
}
