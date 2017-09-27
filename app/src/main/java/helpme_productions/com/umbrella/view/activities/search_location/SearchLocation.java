package helpme_productions.com.umbrella.view.activities.search_location;

import android.content.Intent;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);
        ButterKnife.bind(this);
        setupDagger();
        presenter.addView(this);


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
        if (zipCode.getText() !=null){
            getTemps.putExtra("zip", zipCode.getText().toString());
        }else {
            showError("You must supply a Zip code");
          //  zipCode.setBackgroundColor(getColor(R.color.colorAccent));
        }
        switch (tempChoice.getSelectedItemPosition()){
            case 0:
                getTemps.putExtra("temp","f");
                break;
            case 1:
                getTemps.putExtra("temp", "c");
                break;
        }
        startActivity(getTemps);
    }

    @Override
    public void continueFromLastSearch(String zip, String tempType) {
        
    }
}
