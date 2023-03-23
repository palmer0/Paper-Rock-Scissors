package es.ulpgc.eite.da.paper_rock_scissors.player1;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.paper_rock_scissors.game.AppMediator;


/**
 * Created by Luis on marzo, 2023
 */

public class Player1Screen {

  public static void configure(Player1Contract.View view) {

    /*
    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);
    */

    AppMediator mediator = AppMediator.getInstance();
    Player1Contract.Presenter presenter = new Player1Presenter(mediator);

    Player1Contract.Model model = new Player1Model();

    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}