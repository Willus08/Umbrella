package helpme_productions.com.umbrella;



public interface BasePresenter<V extends BaseView> {
    void addView(V view);
    void removeView();
}
