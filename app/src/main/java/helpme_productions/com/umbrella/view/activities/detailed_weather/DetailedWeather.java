package helpme_productions.com.umbrella.view.activities.detailed_weather;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import helpme_productions.com.umbrella.R;
import helpme_productions.com.umbrella.injection.detailed_weather.DaggerDetailedWeatherComponent;
import helpme_productions.com.umbrella.model.FullWeather;
import helpme_productions.com.umbrella.model.HourlyForecast;

public class DetailedWeather extends AppCompatActivity implements DetailedWeatherContract.View {
    private static final String TAG = "test";
    @Inject DetailedWeatherPresenter presenter;

    @BindView(R.id.tvCityName)
    TextView city;
    @BindView(R.id.tvCurrentTemp)
    TextView temp;
    @BindView(R.id.tvCurrentWeatherType)
    TextView weatherType;
    @BindView(R.id.flCurrentTempHolder)
    FrameLayout tempHolder;
    @BindView(R.id.rvWeatherForecast)
    RecyclerView forecast;

    DetailedWeatherRecyclerAdapter adapter;
    DefaultItemAnimator animate;
    RecyclerView.LayoutManager layoutM;

    List<List<HourlyForecast>> daysForecast = new ArrayList<>();
    List<HourlyForecast> holder = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather);
        ButterKnife.bind(this);
        setupDagger();
        presenter.addView(this);
        presenter.getWeather(getIntent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupDagger() {
        DaggerDetailedWeatherComponent.create().inject(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void setupUI(FullWeather fullWeather, String tempSetting) {
        List<HourlyForecast> forecasts = fullWeather.getHourlyForecast() ;
        String cityState = fullWeather.getLocation().getCity()+", "+fullWeather.getLocation().getState();
        int currentTemp;
        if (tempSetting.equals("f")) {
            currentTemp = Integer.parseInt(forecasts.get(0).getTemp().getEnglish());
            temp.setText(forecasts.get(0).getTemp().getEnglish());
            if (currentTemp>60){
                tempHolder.setBackgroundColor(getColor(R.color.highTemp));
            }else
                tempHolder.setBackgroundColor(getColor(R.color.lowTemp));
        }else if (tempSetting.equals("c")){
            currentTemp = Integer.parseInt(forecasts.get(0).getTemp().getMetric());
            temp.setText(forecasts.get(0).getTemp().getMetric());
            if (currentTemp>16){
                tempHolder.setBackgroundColor(getColor(R.color.highTemp));
            }else
                tempHolder.setBackgroundColor(getColor(R.color.lowTemp));
        }

        city.setText(cityState);
        weatherType.setText(forecasts.get(0).getCondition());
        // takes the list of all hours and compiles it into a list of hour lists seperated by day
        List<Integer> endOfDays = new ArrayList<>();
        for (int i = 1; i <forecasts.size() ; i++) {
            if(forecasts.get(i).getFCTTIME().getHour().equals("0")){
                endOfDays.add(i);
            }
        }
        for (int i = 0; i < endOfDays.size() ; i++) {
            if(i == 0){
                daysForecast.add(forecasts.subList(1,endOfDays.get(i)));
            }else if (i + 1 < endOfDays.size()){
                daysForecast.add(forecasts.subList(endOfDays.get(i-1),endOfDays.get(i)));
            }else {
                daysForecast.add(forecasts.subList(endOfDays.get(i),forecasts.size()));
            }
        }

        adapter = new DetailedWeatherRecyclerAdapter(daysForecast,tempSetting);
        layoutM = new LinearLayoutManager(this);
        animate = new DefaultItemAnimator();

        forecast.setAdapter(adapter);
        forecast.setItemAnimator(animate);
        forecast.setLayoutManager(layoutM);
    }

    public void changeLocation(View view) {
        finish();
    }
}
