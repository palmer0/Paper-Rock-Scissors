package es.ulpgc.eite.da.paper_rock_scissors.player2;

/**
 * Created by Luis on marzo, 2023
 */

public class Player2Model implements Player2Contract.Model {

  //public static String TAG = Player2Model.class.getSimpleName();
  public static String TAG = "Paper-Rock-Scissors.Player2Model";

  private String playerOption;

  /*
  public Player2Model(String playerOption) {
    this.playerOption = playerOption;
  }
  */

  public Player2Model() {
    this.playerOption = "?";
  }

  @Override
  public String getStoredData() {
    // Log.e(TAG, "getStoredData()");

    return playerOption;
  }

  @Override
  public String getUpdatedDataDuringPause() {
    // Log.e(TAG, "getUpdatedDataDuringPause()");

    return playerOption;
  }

  @Override
  public void onUpdatedDataFromRestartedScreen(String data) {
    // Log.e(TAG, "onUpdatedDataFromRestartedScreen()");


  }

  @Override
  public void onUpdatedDataFromNextScreen(String data) {
    // Log.e(TAG, "onUpdatedDataFromNextScreen()");


  }

  @Override
  public void onUpdatedDataFromPreviousScreen(String data) {
    // Log.e(TAG, "onUpdatedDataFromPreviousScreen()");


  }
}
