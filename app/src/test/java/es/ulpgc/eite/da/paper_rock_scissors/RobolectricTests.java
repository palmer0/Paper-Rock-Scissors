package es.ulpgc.eite.da.paper_rock_scissors;

/**
* Created by Luis on marzo, 2023
*/

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import es.ulpgc.eite.da.paper_rock_scissors.game.AppMediator;
import es.ulpgc.eite.da.paper_rock_scissors.player1.Player1Activity;
import es.ulpgc.eite.da.paper_rock_scissors.player2.Player2Activity;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Config.OLDEST_SDK})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RobolectricTests {

   private ActivityController<Player1Activity> player1ActivityCtrl;
   private ActivityController<Player2Activity> player2ActivityCtrl;


   private Player1Activity player1Activity;
   private Player2Activity player2Activity;

   private void rotatePlayer1Activity() {
      Bundle bundle = new Bundle();

      player1ActivityCtrl
          .saveInstanceState(bundle)
          .pause()
          .stop()
          .destroy();

      player1ActivityCtrl = Robolectric.buildActivity(Player1Activity.class)
          .create(bundle)
          .restoreInstanceState(bundle)
          .resume()
          .visible();

      player1Activity = player1ActivityCtrl.get();
   }


   private void rotatePlayer2Activity() {
      Bundle bundle = new Bundle();

      player2ActivityCtrl
          .saveInstanceState(bundle)
          .pause()
          .stop()
          .destroy();

      player2ActivityCtrl = Robolectric.buildActivity(Player2Activity.class)
          .create(bundle)
          .restoreInstanceState(bundle)
          .resume()
          .visible();

      player2Activity = player2ActivityCtrl.get();
   }

   @Before
   public void setUp() {

      AppMediator.resetInstance();

      player1ActivityCtrl = Robolectric
          .buildActivity(Player1Activity.class)
          .create().resume().visible();

      player1Activity = player1ActivityCtrl.get();


   }


   // ---------------  Without Rotation ----------------

   @Test
   public void t01CheckInitialPlayer2OptionIsEmpty() {

      // GIVEN Se inicia la app y se crea y resume la pantalla "Player 1"

      // THEN La opcion elegida por el "player2" deberia ser desconocida 

      TextView player2Option = player1Activity.findViewById(R.id.player2Option);
      assertEquals("?", player2Option.getText().toString());
   }



   @Test
   public void t02ClickPlayer1ScissorsButtonShouldUpdatePlayer1Option() {

      // GIVEN Se inicia la app y se crea y resume la pantalla "Player 1"
      // AND La opcion elegida por el "player2" es desconocida
      
      // WHEN Se pulsa el botón "Take Scissors" en la pantalla "Player 1"

      Button player1Button = player1Activity.findViewById(R.id.player1Scissors);
      player1Button.performClick();

      // THEN Se deberia crear y resumir la pantalla "Player 2"
      // AND La opcion elegida por el "player1" deberia ser "Scissors"

      player2ActivityCtrl = Robolectric
          .buildActivity(Player2Activity.class)
          .create().resume().visible();

      player2Activity = player2ActivityCtrl.get();


      TextView player1Option = player2Activity.findViewById(R.id.player1Option);
      assertThat(player1Option.getText().toString(), equalTo("Scissors"));


   }


   @Test
   public void t03ClickPlayer1ScissorsButtonShouldResetPlayer1Option() {

      // GIVEN Se inicia la app y se crea y resume la pantalla "Player 1"
      // AND La opcion elegida por el "player2" es desconocida

      // WHEN Se pulsa el botón "Pass Turn" en la pantalla "Player 1"
      
      Button player1Button = player1Activity.findViewById(R.id.player1PassTurn);
      player1Button.performClick();

      // THEN Se deberia crear y resumir la pantalla "Player 2"
      // AND La opcion elegida por el "player1" deberia ser desconocida
      
      player2ActivityCtrl = Robolectric
          .buildActivity(Player2Activity.class)
          .create().resume().visible();

      player2Activity = player2ActivityCtrl.get();

      TextView player1Option = player2Activity.findViewById(R.id.player1Option);
      assertThat(player1Option.getText().toString(), equalTo("?"));

   }


   @Test
   public void t04ClickPlayer2RockButtonShouldUpdatePlayer2Option() {

      // GIVEN Se inicia la app y se crea y resume la pantalla "Player 1"
      // AND La opcion elegida por el "player2" es desconocida
      // AND Se pulsa el botón "Take Scissors" en la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 2"
      // AND La opcion elegida por el "player1" es "Scissors"
      
      Button player1Button = player1Activity.findViewById(R.id.player1Scissors);
      player1Button.performClick();

      player2ActivityCtrl = Robolectric
          .buildActivity(Player2Activity.class)
          .create().resume().visible();

      player2Activity = player2ActivityCtrl.get();

      // WHEN Se pulsa el botón "Take Rock" en la pantalla "Player 2"

      Button player2Button = player2Activity.findViewById(R.id.player2Rock);
      player2Button.performClick();

      // THEN Se deberia pausar y destruir la pantalla "Player 2"
      // AND Se deberia resumir la pantalla "Player 1"
      // AND La opcion elegida por el "player2" deberia "Rock"

      player1ActivityCtrl.resume().visible();

      TextView player2Option = player1Activity.findViewById(R.id.player2Option);
      assertEquals("Rock", player2Option.getText().toString());

   }



   @Test
   public void t05ClickPlayer2PassTurnButtonShouldResetPlayer2Option() {

      // GIVEN Se inicia la app y se crea y resume la pantalla "Player 1"
      // AND La opcion elegida por el "player2" es desconocida
      // AND Se pulsa el botón "Take Scissors" en la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 2"
      // AND La opcion elegida por el "player1" es "Scissors"

      Button player1Button = player1Activity.findViewById(R.id.player1Scissors);
      player1Button.performClick();


      player2ActivityCtrl = Robolectric
          .buildActivity(Player2Activity.class)
          .create().resume().visible();

      player2Activity = player2ActivityCtrl.get();

      // WHEN Se pulsa el botón "Pass Turn" en la pantalla "Player 2"

      Button player2Button = player2Activity.findViewById(R.id.player2PassTurn);
      player2Button.performClick();

      // THEN Se deberia pausar y destruir la pantalla "Player 2"
      // AND Se deberia resumir la pantalla "Player 1"
      // AND La opcion elegida por el "player2" deberia ser desconocida
      
      player1ActivityCtrl.resume().visible();

      TextView player2Option = player1Activity.findViewById(R.id.player2Option);
      assertEquals("?", player2Option.getText().toString());

   }

   @Test
   public void t06ClickPlayer2BackButtonNotShouldUpdatePlayer2Option() {

      // GIVEN Se inicia la app y se crea y resume la pantalla "Player 1"
      // AND La opcion elegida por el "player2" es desconocida
      // AND Se pulsa el botón "Take Scissors" en la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 2"
      // AND La opcion elegida por el "player1" es "Scissors"

      Button player1Button = player1Activity.findViewById(R.id.player1Scissors);
      player1Button.performClick();


      player2ActivityCtrl = Robolectric
          .buildActivity(Player2Activity.class)
          .create().resume().visible();

      player2Activity = player2ActivityCtrl.get();

      // WHEN Se pulsa el botón "Back" en la pantalla "Player 2"
      
      player2Activity.onBackPressed();

      // THEN Se deberia pausar y destruir la pantalla "Player 2"
      // AND Se deberia resumir la pantalla "Player 1"
      // AND La opcion elegida por el "player2" deberia ser desconocida
      
      player1ActivityCtrl.resume().visible();

      TextView player2Option = player1Activity.findViewById(R.id.player2Option);
      assertEquals("?", player2Option.getText().toString());

   }

   
   // ---------------  With Rotation ----------------

   @Test
   public void t07CheckInitialPlayer2OptionIsEmptyWithRotation() {

      // GIVEN Se inicia la app y se crea y resume la pantalla "Player 1"
      // AND La opcion elegida por el "player2" es desconocida

      
      // WHEN Se rota la pantalla "Player 1"
      
      rotatePlayer1Activity();

      // THEN Se deberia pausar y destruir la pantalla "Player 1"
      // AND Se deberia crear y resumir la pantalla "Player 1"
      // AND La opcion elegida por el "player2" deberia ser desconocida

      TextView player2Option = player1Activity.findViewById(R.id.player2Option);
      assertEquals("?", player2Option.getText().toString());
   }


   @Test
   public void t08ClickPlayer1ScissorsButtonShouldUpdatePlayer1OptionWithRotation() {

      // GIVEN Se inicia la app y se crea y resume la pantalla "Player 1"
      // AND La opcion elegida por el "player2" es desconocida
      // AND Se rota la pantalla "Player 1"
      // AND Se pausa y destruye la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 1"
      // AND Se pulsa el botón "Take Scissors" en la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 2"

      rotatePlayer1Activity();

      Button player1Button = player1Activity.findViewById(R.id.player1Scissors);
      player1Button.performClick();

      player2ActivityCtrl = Robolectric
          .buildActivity(Player2Activity.class)
          .create().resume().visible();

      player2Activity = player2ActivityCtrl.get();

      // WHEN Se rota la pantalla "Player 2"

      rotatePlayer2Activity();

      // THEN Se deberia pausar y destruir la pantalla "Player 2"
      // AND Se deberia crear y resumir la pantalla "Player 2"
      // AND La opcion elegida por el "player1" deberia ser "Scissors"

      TextView player1Option = player2Activity.findViewById(R.id.player1Option);
      assertThat(player1Option.getText().toString(), equalTo("Scissors"));

   }


   @Test
   public void t09ClickPlayer1ScissorsButtonShouldResetPlayer1OptionWithRotation() {

      // GIVEN Se inicia la app y se crea y resume la pantalla "Player 1"
      // AND La opcion elegida por el "player2" es desconocida
      // AND Se rota la pantalla "Player 1"
      // AND Se pausa y destruye la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 1"
      // AND Se pulsa el botón "Pass Turn" en la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 2"

      rotatePlayer1Activity();

      Button player1Button = player1Activity.findViewById(R.id.player1PassTurn);
      player1Button.performClick();

      player2ActivityCtrl = Robolectric
          .buildActivity(Player2Activity.class)
          .create().resume().visible();

      player2Activity = player2ActivityCtrl.get();

      // WHEN Se rota la pantalla "Player 2"

      rotatePlayer2Activity();

      // THEN Se deberia pausar y destruir la pantalla "Player 2"
      // AND Se deberia crear y resumir la pantalla "Player 2"
      // AND La opcion elegida por el "player1" deberia ser desconocida

      TextView player1Option = player2Activity.findViewById(R.id.player1Option);
      assertThat(player1Option.getText().toString(), equalTo("?"));

   }


   @Test
   public void t10ClickPlayer2RockButtonShouldUpdatePlayer2OptionWithRotation() {

      // GIVEN Se inicia la app y se crea y resume la pantalla "Player 1"
      // AND La opcion elegida por el "player2" es desconocida
      // AND Se rota la pantalla "Player 1"
      // AND Se pausa y destruye la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 1"
      // AND Se pulsa el botón "Take Scissors" en la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 2"
      // AND Se rota la pantalla "Player 2"
      // AND Se pausa y destruye la pantalla "Player 2"
      // AND Se crea y resume la pantalla "Player 2"
      // AND Se pulsa el botón "Take Rock" en la pantalla "Player 2"
      // AND Se pausa y destruye la pantalla "Player 2"
      // AND Se resume la pantalla "Player 1"

      rotatePlayer1Activity();

      Button player1Button = player1Activity.findViewById(R.id.player1Scissors);
      player1Button.performClick();


      player2ActivityCtrl = Robolectric
          .buildActivity(Player2Activity.class)
          .create().resume().visible();

      player2Activity = player2ActivityCtrl.get();

      rotatePlayer2Activity();

      Button player2Button = player2Activity.findViewById(R.id.player2Rock);
      player2Button.performClick();


      player1ActivityCtrl.resume().visible();

      // WHEN Se rota la pantalla "Player 1"

      rotatePlayer1Activity();

      // THEN Se deberia pausar y destruir la pantalla "Player 1"
      // AND Se deberia crear y resumir la pantalla "Player 1"
      // AND La opcion elegida por el "player2" deberia ser "Rock"


      TextView player2Option = player1Activity.findViewById(R.id.player2Option);
      assertEquals("Rock", player2Option.getText().toString());

   }



   @Test
   public void t11ClickPlayer2PassTurnButtonShouldResetPlayer2OptionWithRotation() {

      // GIVEN Se inicia la app y se crea y resume la pantalla "Player 1"
      // AND La opcion elegida por el "player2" es desconocida
      // AND Se rota la pantalla "Player 1"
      // AND Se pausa y destruye la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 1"
      // AND Se pulsa el botón "Take Scissors" en la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 2"
      // AND Se rota la pantalla "Player 2"
      // AND Se pausa y destruye la pantalla "Player 2"
      // AND Se crea y resume la pantalla "Player 2"
      // AND Se pulsa el botón "Pass Turn" en la pantalla "Player 2"
      // AND Se pausa y destruye la pantalla "Player 2"
      // AND Se resume la pantalla "Player 1"


      rotatePlayer1Activity();

      Button player1Button = player1Activity.findViewById(R.id.player1Scissors);
      player1Button.performClick();

      player2ActivityCtrl = Robolectric
          .buildActivity(Player2Activity.class)
          .create().resume().visible();

      player2Activity = player2ActivityCtrl.get();

      rotatePlayer2Activity();

      Button player2Button = player2Activity.findViewById(R.id.player2PassTurn);
      player2Button.performClick();

      player1ActivityCtrl.resume().visible();

      // WHEN Se rota la pantalla "Player 1"
      
      rotatePlayer1Activity();

      // THEN Se deberia pausar y destruir la pantalla "Player 1"
      // AND Se deberia crear y resumir la pantalla "Player 1"
      // AND La opcion elegida por el "player2" deberia ser desconocida

      TextView player2Option = player1Activity.findViewById(R.id.player2Option);
      assertEquals("?", player2Option.getText().toString());

   }

   @Test
   public void t12ClickPlayer2BackButtonNotShouldUpdatePlayer2OptionWithRotation() {

      // GIVEN Se inicia la app y se crea y resume la pantalla "Player 1"
      // AND La opcion elegida por el "player2" es desconocida
      // AND Se rota la pantalla "Player 1"
      // AND Se pausa y destruye la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 1"
      // AND Se pulsa el botón "Take Scissors" en la pantalla "Player 1"
      // AND Se crea y resume la pantalla "Player 2"
      // AND Se rota la pantalla "Player 2"
      // AND Se pausa y destruye la pantalla "Player 2"
      // AND Se crea y resume la pantalla "Player 2"
      // AND Se pulsa el botón "Back" en la pantalla "Player 2"
      // AND Se pausa y destruye la pantalla "Player 2"
      // AND Se resume la pantalla "Player 1"


      rotatePlayer1Activity();

      Button player1Button = player1Activity.findViewById(R.id.player1Scissors);
      player1Button.performClick();

      player2ActivityCtrl = Robolectric
          .buildActivity(Player2Activity.class)
          .create().resume().visible();

      player2Activity = player2ActivityCtrl.get();

      rotatePlayer2Activity();

      player2Activity.onBackPressed();

      player1ActivityCtrl.resume().visible();

      // WHEN Se rota la pantalla "Player 1"
      
      rotatePlayer1Activity();

      // THEN Se deberia pausar y destruir la pantalla "Player 1"
      // AND Se deberia crear y resumir la pantalla "Player 1"
      // AND La opcion elegida por el "player2" deberia ser desconocida

      TextView player2Option = player1Activity.findViewById(R.id.player2Option);
      assertEquals("?", player2Option.getText().toString());

   }


}
