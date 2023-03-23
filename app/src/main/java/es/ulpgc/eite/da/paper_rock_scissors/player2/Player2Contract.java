package es.ulpgc.eite.da.paper_rock_scissors.player2;

import java.lang.ref.WeakReference;


/**
 * Created by Luis on marzo, 2023
 */

public interface Player2Contract {

  interface View {
    void injectPresenter(Presenter presenter);

    void onViewModelDataUpdated(Player2ViewModel viewModel);

    void navigateToNextScreen();

    void navigateToPreviousScreen();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void onResume();

    void onStart();

    void onRestart();

    void onBackPressed();

    void onPause();

    void onDestroy();

    void onButtonClicked(String option);
  }

  interface Model {
    String getStoredData();

    String getUpdatedDataDuringPause();

    void onUpdatedDataFromRestartedScreen(String data);

    void onUpdatedDataFromNextScreen(String data);

    void onUpdatedDataFromPreviousScreen(String data);
  }

}