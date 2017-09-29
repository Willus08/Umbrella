package helpme_productions.com.umbrella.view.activities.detailed_weather;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
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
        holder.day.setText(currentDay.get(0).getFCTTIME().getWeekdayName());

        DetailedWeatherGridAdapter adapter = new DetailedWeatherGridAdapter(
                holder.itemView.getContext(),
                currentDay,
                tempType,
                highest,
                lowest);

        holder.forcastHolder.setAdapter(adapter);
        setDynamicHeight(holder.forcastHolder);

    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }


    private void setDynamicHeight(GridView gridView) {
        ListAdapter gridViewAdapter = gridView.getAdapter();
        if (gridViewAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = gridViewAdapter.getCount();
        int rows = 0;

        View listItem = gridViewAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if( items > 3 ){
            x = items/(3);
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
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
