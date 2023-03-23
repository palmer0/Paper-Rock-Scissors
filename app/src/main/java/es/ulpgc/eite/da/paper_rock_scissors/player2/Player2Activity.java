package es.ulpgc.eite.da.paper_rock_scissors.player2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.paper_rock_scissors.R;


/**
 * Created by Luis on marzo, 2023
 */

public class Player2Activity
    extends AppCompatActivity implements Player2Contract.View {

  public static String TAG = "Paper-Rock-Scissors.Player2Activity";

  private Player2Contract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player2);
    getSupportActionBar().setTitle(R.string.player2_title);

    // Log.e(TAG, "onCreate()");

    // do the setup
    Player2Screen.configure(this);

    if (savedInstanceState == null) {
      presenter.onStart();

    } else {
      presenter.onRestart();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    // Log.e(TAG, "onResume()");

    // load the data
    presenter.onResume();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();

    // Log.e(TAG, "onBackPressed()");

    presenter.onBackPressed();
  }

  @Override
  protected void onPause() {
    super.onPause();

    // Log.e(TAG, "onPause()");

    presenter.onPause();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    // Log.e(TAG, "onDestroy()");

    presenter.onDestroy();
  }


  public void onRockButtonClicked(View view) {
    presenter.onButtonClicked("Rock");

  }

  public void onPaperButtonClicked(View view) {
    presenter.onButtonClicked("Paper");

  }

  public void onScissorsButtonClicked(View view) {
    presenter.onButtonClicked("Scissors");

  }

  public void onPassTurnButtonClicked(View view) {
    presenter.onButtonClicked("?");

  }


  @Override
  public void onViewModelDataUpdated(Player2ViewModel viewModel) {
    //Log.e(TAG, "onViewModelDataUpdated()");

    // deal with the data
    ((TextView) findViewById(R.id.player1Option)).setText(viewModel.playerOption);
  }


  @Override
  public void navigateToNextScreen() {
    // Log.e(TAG, "navigateToNextScreen()");

    Intent intent = new Intent(this, Player2Activity.class);
    startActivity(intent);
  }

  @Override
  public void navigateToPreviousScreen() {
    // Log.e(TAG, "navigateToPreviousScreen()");

    finish();
  }

  @Override
  public void injectPresenter(Player2Contract.Presenter presenter) {
    this.presenter = presenter;
  }
}
