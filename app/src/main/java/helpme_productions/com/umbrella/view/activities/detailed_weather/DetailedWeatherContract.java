package helpme_productions.com.umbrella.view.activities.detailed_weather;


import android.content.Intent;

import java.util.List;

import helpme_productions.com.umbrella.BasePresenter;
import helpme_productions.com.umbrella.BaseView;
import helpme_productions.com.umbrella.model.FullWeather;
import helpme_productions.com.umbrella.model.HourlyForecast;

interface DetailedWeatherContract {
    interface View extends BaseView{
        void setupUI(FullWeather fullWeather, String tempSeting);

    }
    interface Presenter extends BasePresenter<View>{
        void getWeather(Intent intent);
        List<List<HourlyForecast>> compressHoursToDays(List<HourlyForecast> hourlyForecasts);
    }
}
