package helpme_productions.com.umbrella.view.activities.detailed_weather;


import java.util.List;

import helpme_productions.com.umbrella.BasePresenter;
import helpme_productions.com.umbrella.BaseView;
import helpme_productions.com.umbrella.model.FullWeather;
import helpme_productions.com.umbrella.model.HourlyForecast;

interface DetailedWeatherContract {
    interface View extends BaseView{
        void setupUI(FullWeather fullWeather);

    }
    interface Presenter extends BasePresenter<View>{
        void getWeather(String zip);
        List<List<HourlyForecast>> compressHoursToDays(List<HourlyForecast> hourlyForecasts);
    }
}
