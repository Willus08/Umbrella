package helpme_productions.com.umbrella.view.activities.detailed_weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import helpme_productions.com.umbrella.R;
import helpme_productions.com.umbrella.injection.detailed_weather.DaggerDetailedWeatherComponent;
import helpme_productions.com.umbrella.model.FullWeather;
import helpme_productions.com.umbrella.model.HourlyForecast;
import helpme_productions.com.umbrella.view.activities.search_location.SearchLocation;

public class DetailedWeather extends AppCompatActivity implements DetailedWeatherContract.View {
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

    SharedPreferences preferences;
    DetailedWeatherRecyclerAdapter adapter;
    DefaultItemAnimator animate;
    RecyclerView.LayoutManager layoutM;
    String zip ="";
    String tempType = "";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather);
        ButterKnife.bind(this);
        setupDagger();
        presenter.addView(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
   ;

    }

    @Override
    protected void onStart() {
        super.onStart();
        zip =preferences.getString(this.getString(R.string.zip_key),"94043");
        tempType = preferences.getString(this.getString(R.string.temp_type_key),"f");
        presenter.getWeather(zip);
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
    public void setupUI(FullWeather fullWeather) {
        List<HourlyForecast> forecasts = fullWeather.getHourlyForecast() ;
        String cityState = fullWeather.getLocation().getCity()+", "+fullWeather.getLocation().getState();
        int currentTemp;
        if (tempType.equals("f")) {
            currentTemp = Integer.parseInt(forecasts.get(0).getTemp().getEnglish());
            temp.setText(forecasts.get(0).getTemp().getEnglish());
            if (currentTemp>60){
                tempHolder.setBackgroundColor(getColor(R.color.highTemp));
            }else
                tempHolder.setBackgroundColor(getColor(R.color.lowTemp));
        }else if (tempType.equals("c")){
            currentTemp = Integer.parseInt(forecasts.get(0).getTemp().getMetric());
            temp.setText(forecasts.get(0).getTemp().getMetric());
            if (currentTemp>16){
                tempHolder.setBackgroundColor(getColor(R.color.highTemp));
            }else
                tempHolder.setBackgroundColor(getColor(R.color.lowTemp));
        }

        city.setText(cityState);
        weatherType.setText(forecasts.get(0).getCondition());


        adapter = new DetailedWeatherRecyclerAdapter(presenter.compressHoursToDays(forecasts),tempType);
        layoutM = new LinearLayoutManager(this);
        animate = new DefaultItemAnimator();

        forecast.setAdapter(adapter);
        forecast.setItemAnimator(animate);
        forecast.setLayoutManager(layoutM);
    }

    public void changeLocation(View view) {
        Intent intent = new Intent(this, SearchLocation.class);
        startActivity(intent);
    }
}
