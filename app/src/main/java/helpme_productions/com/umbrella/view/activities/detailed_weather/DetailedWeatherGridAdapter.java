package helpme_productions.com.umbrella.view.activities.detailed_weather;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import helpme_productions.com.umbrella.R;
import helpme_productions.com.umbrella.model.HourlyForecast;

class DetailedWeatherGridAdapter extends BaseAdapter{
    private Context context;
    private List<HourlyForecast> forecasts = new ArrayList<>();
    private String tempType;

    DetailedWeatherGridAdapter(Context context, List<HourlyForecast> forecasts, String tempType) {
        this.context = context;
        this.forecasts = forecasts;
        this.tempType = tempType;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.grid_items,parent,false);

            TextView time = gridView.findViewById(R.id.tvGridTime);
            int timeInt = Integer.parseInt(forecasts.get(position).getFCTTIME().getHour());
            String timeText;
            if(timeInt == 0){
                timeText = "12:00 AM";
            }else if ( timeInt > 12){
                timeText = timeInt-12 +":00 PM";
            }else if(timeInt == 12 ){
                timeText = timeInt +":00 PM";
            }else {
                timeText = timeInt +":00 AM";
            }
            time.setText(timeText);
            TextView temp = gridView.findViewById(R.id.tvGridTemp);
            if(tempType.equals("f")){
                temp.setText(forecasts.get(position).getTemp().getEnglish());
            }else {
                temp.setText(forecasts.get(position).getTemp().getMetric());
            }

            ImageView imageView = gridView.findViewById(R.id.ivGridImage);



          switch (forecasts.get(position).getCondition()){
              case "Clear":
                  Glide.with(context).load(R.drawable.weather_sunny).into(imageView);
                  break;
              default:
                  Glide.with(context).load(R.drawable.weather_partlycloudy).into(imageView);
          }

        } else {
            gridView = convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return forecasts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
