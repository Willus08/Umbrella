package helpme_productions.com.umbrella.view.activities.detailed_weather;


import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import helpme_productions.com.umbrella.data.RetrofitHelper;
import helpme_productions.com.umbrella.model.FullWeather;
import helpme_productions.com.umbrella.model.HourlyForecast;
import retrofit2.Call;
import retrofit2.Response;

public class DetailedWeatherPresenter implements DetailedWeatherContract.Presenter{
  private DetailedWeatherContract.View view;

    @Override
    public void addView(DetailedWeatherContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void getWeather(Intent intent) {
      String zip = intent.getStringExtra("zip");
      final String tempType = intent.getStringExtra("temp");
      retrofit2.Call<FullWeather> fullWeatherCall = RetrofitHelper.fullWeatherCall(zip);
      fullWeatherCall.enqueue(new retrofit2.Callback<FullWeather>() {

        @Override
        public void onResponse(@NonNull Call<FullWeather> call, @NonNull Response<FullWeather> response) {
             view.setupUI(response.body(),tempType);
        }

        @Override
        public void onFailure(@NonNull Call<FullWeather> call, @NonNull Throwable t) {
          view.showError(t.toString());
        }


      });

    }

  @Override
  public List<List<HourlyForecast>> compressHoursToDays(List<HourlyForecast> hourlyForecasts) {
    List<List<HourlyForecast>> compressedForecast = new ArrayList<>();
    int startPoint =1;
    String weekday =hourlyForecasts.get(0).getFCTTIME().getWeekdayName();
    for (int i = 1; i < hourlyForecasts.size() ; i++) {
          if (!hourlyForecasts.get(i).getFCTTIME().getWeekdayName().equals(weekday)){
            weekday =hourlyForecasts.get(i).getFCTTIME().getWeekdayName();
            compressedForecast.add(hourlyForecasts.subList(startPoint,i));
            startPoint = i;
          }
    }
    compressedForecast.add(hourlyForecasts.subList(startPoint,hourlyForecasts.size()-1));


    return compressedForecast;
  }


}
