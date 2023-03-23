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

  //public static String TAG = Player2Presenter.class.getSimpleName();
  public static String TAG = "Paper-Rock-Scissors.Player2Presenter";

  private WeakReference<Player2Contract.View> view;
  private Player2State state;
  private Player2Contract.Model model;
  private AppMediator mediator;

  public Player2Presenter(AppMediator mediator) {
    this.mediator = mediator;
    //state = mediator.getPlayer2State();
  }

  @Override
  public void onStart() {
    Log.e(TAG, "onStart()");

    // call the mediator initialize the state
    state = new Player2State();
    //state = getScreenState();

    // call the model and initialize the state
    state.playerOption = model.getStoredData();

    // use passed state if is necessary
    Player1ToPlayer2State savedState=mediator.getPlayer1ToPlayer2ScreenState();
    //Player1ToPlayer2State savedState = getStateFromPreviousScreen();
    if (savedState != null) {

      // update the model if is necessary
      //model.onUpdatedDataFromPreviousScreen(savedState.playerOption);

      // update the state if is necessary
      state.playerOption = savedState.playerOption;
    }
  }

  @Override
  public void onRestart() {
    Log.e(TAG, "onRestart()");

    // call the mediator initialize the state
    state=mediator.getPlayer2ScreenState();
    //state = getScreenState();

    // update the model if is necessary
    //model.onUpdatedDataFromRestartedScreen(state.playerOption);
  }

  @Override
  public void onResume() {
    Log.e(TAG, "onResume()");

    /*
    // call the model and update the state if is necessary
    String data = model.getUpdatedDataDuringPause();
    if (data != null) {
      state.playerOption = data;
    }
    */

    /*
    // use passed state if is necessary
    NextToPlayer2State savedState = getStateFromNextScreen();
    if (savedState != null) {

      // update the model if is necessary
      model.onUpdatedDataFromNextScreen(savedState.data);

      // update the state if is necessary
      state.data = savedState.data;
    }
    */

    // update the view
    view.get().onViewModelDataUpdated(state);

  }

  @Override
  public void onBackPressed() {
    Log.e(TAG, "onBackPressed()");

    Player2ToPlayer1State newState = new Player2ToPlayer1State();
    newState.playerOption= "?";
    //passStateToPreviousScreen(newState);
    mediator.setPlayer2ToPlayer1ScreenState(newState);
  }

  @Override
  public void onPause() {
    Log.e(TAG, "onPause()");

    // update the state
    mediator.setPlayer2ScreenState(state);
    //setScreenState(state);
  }

  @Override
  public void onDestroy() {
    Log.e(TAG, "onDestroy()");

    // reset the state if is necessary
    //resetScreenState();
  }

  @Override
  public void onButtonClicked(String option) {
    Log.e(TAG, "onButtonClicked()");

    Player2ToPlayer1State newState = new Player2ToPlayer1State();
    newState.playerOption= option;
    //passStateToPreviousScreen(newState);
    mediator.setPlayer2ToPlayer1ScreenState(newState);
    view.get().navigateToPreviousScreen();
  }

  /*
  private Player2State getScreenState() {
    return mediator.getPlayer2ScreenState();
  }

  private void setScreenState(Player2State state) {
    mediator.setPlayer2ScreenState(state);
  }


  private void passStateToPreviousScreen(Player2ToPlayer1State state) {
    mediator.setPlayer2ToPlayer1ScreenState(state);
  }

  private Player1ToPlayer2State getStateFromPreviousScreen() {
    return mediator.getPlayer1ToPlayer2ScreenState();
  }
  */


  /*
  private void resetScreenState() {
    mediator.resetPlayer2ScreenState();
  }

  private NextToPlayer2State getStateFromNextScreen() {
    return mediator.getNextPlayer2ScreenState();
  }

  private void passStateToNextScreen(Player2ToNextState state) {
    mediator.setNextPlayer2ScreenState(state);
  }
  */


  @Override
  public void injectView(WeakReference<Player2Contract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(Player2Contract.Model model) {
    this.model = model;
  }

}