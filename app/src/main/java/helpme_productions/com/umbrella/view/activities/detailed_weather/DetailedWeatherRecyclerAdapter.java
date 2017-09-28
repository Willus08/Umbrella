package helpme_productions.com.umbrella.view.activities.detailed_weather;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import helpme_productions.com.umbrella.R;
import helpme_productions.com.umbrella.model.HourlyForecast;

class DetailedWeatherRecyclerAdapter extends RecyclerView.Adapter<DetailedWeatherRecyclerAdapter.ViewHolder> {
    private List<List<HourlyForecast>> forecasts = new ArrayList<>();
    private String tempType;

    public DetailedWeatherRecyclerAdapter(List<List<HourlyForecast>> forecasts, String tempType) {
        this.forecasts = forecasts;
        this.tempType = tempType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_forcast_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<HourlyForecast> currentDay = forecasts.get(position);
        holder.day.setText(currentDay.get(0).getFCTTIME().getWeekdayName());
        holder.forcastHolder.setAdapter(new DetailedWeatherGridAdapter(holder.itemView.getContext(),currentDay,tempType));

    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView day;
        GridView forcastHolder;
        ViewHolder(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.tvDay);
            forcastHolder = itemView.findViewById(R.id.gvForcastContainer);
        }
    }
}
