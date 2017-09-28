package helpme_productions.com.umbrella.view.activities.search_location;


import helpme_productions.com.umbrella.BasePresenter;
import helpme_productions.com.umbrella.BaseView;

interface SearchLocatioContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{
        boolean checkIfSavedArea();
        void getPreviousSearch();
    }
}
