package helpme_productions.com.umbrella.view.activities.detailed_weather;


import android.os.Build;
import android.support.annotation.RequiresApi;
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

    DetailedWeatherRecyclerAdapter(List<List<HourlyForecast>> forecasts, String tempType) {
        this.forecasts = forecasts;
        this.tempType = tempType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_forcast_items,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<HourlyForecast> currentDay = forecasts.get(position);
        int highest = 0;
        int lowest = 100;
        for (int i = 0; i < currentDay.size(); i++) {
            int current = Integer.parseInt(currentDay.get(i).getTemp().getEnglish());
            if( current > highest){
                highest = current;
            }
            if (current < lowest){
                lowest = current;
            }
        }

        String dayText;
        switch (position){
            case 0:
                dayText = "Today";
                break;
            case 1:
                dayText = "Tomorrow";
                break;
            default:
                dayText =currentDay.get(0).getFCTTIME().getWeekdayName();
        }
        holder.day.setText(dayText);

        DetailedWeatherGridAdapter adapter = new DetailedWeatherGridAdapter(
                holder.itemView.getContext(),
                currentDay,
                tempType,
                highest,
                lowest);

        holder.forcastHolder.setAdapter(adapter);


        int hieght = 400;
        if(currentDay.size() >3){
            hieght *= (((currentDay.size()-1)/3)+1);
        }
        ViewGroup.LayoutParams params = holder.forcastHolder.getLayoutParams();
        params.height = hieght;
        holder.forcastHolder.setLayoutParams(params);


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
