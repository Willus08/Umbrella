package helpme_productions.com.umbrella.view.activities.detailed_weather;


import android.content.Intent;
import android.support.annotation.NonNull;

import helpme_productions.com.umbrella.data.RetrofitHelper;
import helpme_productions.com.umbrella.model.FullWeather;
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
}
