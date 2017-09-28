package helpme_productions.com.umbrella.view.activities.search_location;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import helpme_productions.com.umbrella.R;
import helpme_productions.com.umbrella.injection.search_location.DaggerSearchLocationComponent;
import helpme_productions.com.umbrella.view.activities.detailed_weather.DetailedWeather;

public class SearchLocation extends AppCompatActivity implements SearchLocatioContract.View{
    @Inject SearchLocationPresenter presenter;
    @BindView(R.id.spinTemperturChoice)
    Spinner tempChoice;
    @BindView(R.id.etSearchZipCodes)
    EditText zipCode;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private static final String TEMP_TYPE_KEY = "helpme_productions.com.umbrella.view.activities.search_location.TempType";
    private static final String ZIP_CODE_KEY ="helpme_productions.com.umbrella.view.activities.search_location.ZipCode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);
        ButterKnife.bind(this);
        setupDagger();
        presenter.addView(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        editor.apply();
        String zip =preferences.getString(ZIP_CODE_KEY,"");
        String tempType = preferences.getString(TEMP_TYPE_KEY,"f");
        if(!zip.equals("") ){
            continueFromLastSearch(zip,tempType);
        }

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
        DaggerSearchLocationComponent.create().inject(this);
    }


    public void searchTemp(View view) {
        Intent getTemps = new Intent(this, DetailedWeather.class);
        if (zipCode.getText()!=null){
            if(zipCode.getText().toString().length() ==5){
                editor.putString(ZIP_CODE_KEY,zipCode.getText().toString());
                editor.apply();
                getTemps.putExtra("zip", zipCode.getText().toString());
                switch (tempChoice.getSelectedItemPosition()){
                    case 0:
                        editor.putString(TEMP_TYPE_KEY,"f");
                        editor.apply();
                        getTemps.putExtra("temp","f");
                        break;
                    case 1:
                        editor.putString(TEMP_TYPE_KEY,"c");
                        editor.apply();
                        getTemps.putExtra("temp", "c");
                        break;
                }

                startActivity(getTemps);
            }else{
                showError("A Zip Code is 5 digits long.");
            }
        }else {
            showError("You must supply a Zip code");
          //  zipCode.setBackgroundColor(getColor(R.color.colorAccent));
        }

    }

    @Override
    public void continueFromLastSearch(String zip, String tempType) {
        Intent intent = new Intent(this, DetailedWeather.class);
        intent.putExtra("zip",zip);
        intent.putExtra("temp", tempType);
        startActivity(intent);

    }
}
