package helpme_productions.com.umbrella.view.activities.search_location;


import helpme_productions.com.umbrella.BasePresenter;
import helpme_productions.com.umbrella.BaseView;

interface SearchLocatioContract {
    interface View extends BaseView{
        void continueFromLastSearch(String zip, String tempType);

    }
    interface Presenter extends BasePresenter<View>{
        boolean checkIfSavedArea();
        void getPreviousSearch();
    }
}
