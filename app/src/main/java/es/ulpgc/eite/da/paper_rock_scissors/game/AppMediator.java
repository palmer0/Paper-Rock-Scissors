package es.ulpgc.eite.da.paper_rock_scissors.game;


import es.ulpgc.eite.da.paper_rock_scissors.player1.Player1State;
import es.ulpgc.eite.da.paper_rock_scissors.player2.Player2State;

/**
 * Created by Luis on marzo, 2023
 */

@SuppressWarnings("unused")
public class AppMediator {

  private static AppMediator INSTANCE;

  private Player1State mPlayer1State;
  private Player2State mPlayer2State;


  private Player2ToPlayer1State mPlayer2ToPlayer1State;
  private Player1ToPlayer2State mPlayer1ToPlayer2State;


  private AppMediator() {
    //mPlayer1State = new Player1State();
    //mPlayer2State = new Player2State();
  }


  public static AppMediator getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new AppMediator();
    }

    return INSTANCE;
  }

  // to reset the state when testing
  public static void resetInstance() {
    INSTANCE = null;
  }

  public Player1State getPlayer1ScreenState() {
    return mPlayer1State;
  }

  public void setPlayer1ScreenState(Player1State state) {
    mPlayer1State = state;
  }


  public Player2State getPlayer2ScreenState() {
    return mPlayer2State;
  }

  public void setPlayer2ScreenState(Player2State state) {
    mPlayer2State=state;
  }

  public void setPlayer1ToPlayer2ScreenState(Player1ToPlayer2State state) {
    mPlayer1ToPlayer2State = state;
  }


  public Player1ToPlayer2State getPlayer1ToPlayer2ScreenState() {
    return mPlayer1ToPlayer2State;
  }

  public Player2ToPlayer1State getPlayer2ToPlayer1ScreenState() {
    return mPlayer2ToPlayer1State;
  }

  public void setPlayer2ToPlayer1ScreenState(Player2ToPlayer1State state) {
    mPlayer2ToPlayer1State =state;
  }

}