package helpme_productions.com.umbrella.view.activities.search_location;


public class SearchLocationPresenter implements SearchLocatioContract.Presenter{
    private SearchLocatioContract.View view;

    @Override
    public void addView(SearchLocatioContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public boolean checkIfSavedArea() {

        return false;
    }

    @Override
    public void getPreviousSearch() {

    }
}
