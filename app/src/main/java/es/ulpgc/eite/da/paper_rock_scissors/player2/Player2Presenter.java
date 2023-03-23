package es.ulpgc.eite.da.paper_rock_scissors.player2;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.paper_rock_scissors.game.AppMediator;
import es.ulpgc.eite.da.paper_rock_scissors.game.Player1ToPlayer2State;
import es.ulpgc.eite.da.paper_rock_scissors.game.Player2ToPlayer1State;


/**
 * Created by Luis on marzo, 2023
 */

public class Player2Presenter implements Player2Contract.Presenter {

  public static String TAG = "Paper-Rock-Scissors.Player2Presenter";

  private WeakReference<Player2Contract.View> view;
  private Player2State state;
  private Player2Contract.Model model;
  private AppMediator mediator;

  public Player2Presenter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void onStart() {
    Log.e(TAG, "onStart()");

    // call the mediator initialize the state
    state = new Player2State();

    // call the model and initialize the state
    state.playerOption = model.getStoredData();

    // use passed state if is necessary
    Player1ToPlayer2State savedState=mediator.getPlayer1ToPlayer2ScreenState();
    if (savedState != null) {

      // update the state if is necessary
      state.playerOption = savedState.playerOption;
    }
  }

  @Override
  public void onRestart() {
    Log.e(TAG, "onRestart()");

    // call the mediator initialize the state
    state=mediator.getPlayer2ScreenState();

  }

  @Override
  public void onResume() {
    Log.e(TAG, "onResume()");

    // update the view
    view.get().onViewModelDataUpdated(state);

  }

  @Override
  public void onBackPressed() {
    Log.e(TAG, "onBackPressed()");

    Player2ToPlayer1State newState = new Player2ToPlayer1State();
    newState.playerOption= "?";
    mediator.setPlayer2ToPlayer1ScreenState(newState);
  }

  @Override
  public void onPause() {
    Log.e(TAG, "onPause()");

    // update the state
    mediator.setPlayer2ScreenState(state);
  }

  @Override
  public void onDestroy() {
    Log.e(TAG, "onDestroy()");

  }

  @Override
  public void onButtonClicked(String option) {
    Log.e(TAG, "onButtonClicked()");

    Player2ToPlayer1State newState = new Player2ToPlayer1State();
    newState.playerOption= option;
    mediator.setPlayer2ToPlayer1ScreenState(newState);
    view.get().navigateToPreviousScreen();
  }


  @Override
  public void injectView(WeakReference<Player2Contract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(Player2Contract.Model model) {
    this.model = model;
  }

}