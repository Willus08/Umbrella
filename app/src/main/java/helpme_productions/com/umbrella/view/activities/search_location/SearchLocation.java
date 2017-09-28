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
        if (zipCode.getText()!=null){
            if(zipCode.getText().toString().length() ==5){
                editor.putString(this.getString(R.string.zip_key),zipCode.getText().toString());
                editor.apply();
                switch (tempChoice.getSelectedItemPosition()){
                    case 0:
                        editor.putString(this.getString(R.string.temp_type_key),"f");
                        editor.apply();
                        break;
                    case 1:
                        editor.putString(this.getString(R.string.temp_type_key),"c");
                        editor.apply();
                        break;
                }
                Intent intent = new Intent(this, DetailedWeather.class);
                startActivity(intent);
            }else{
                showError("A Zip Code is 5 digits long.");
            }
        }else {
            showError("You must supply a Zip code");
          //  zipCode.setBackgroundColor(getColor(R.color.colorAccent));
        }

    }

}
