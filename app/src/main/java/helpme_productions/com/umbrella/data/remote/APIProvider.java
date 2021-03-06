package helpme_productions.com.umbrella.data.remote;


import helpme_productions.com.umbrella.model.FullWeather;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIProvider {
    private static final String BASE_URL = "http://api.wunderground.com/";
    private static final String API_KEY = "195106b65fb7b0b4";
    private static final String SEARCH_TEMP ="hourly";
    private static final String SEARCH_LOCATION ="geolookup";

    private static Retrofit create(){

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static Call<FullWeather> fullWeatherCall(String zip){
        Retrofit retrofit = create();
        APIService services = retrofit.create(APIService.class);
        return services.getFullWeather(API_KEY,SEARCH_TEMP, SEARCH_LOCATION,Integer.parseInt(zip));
    }

}
