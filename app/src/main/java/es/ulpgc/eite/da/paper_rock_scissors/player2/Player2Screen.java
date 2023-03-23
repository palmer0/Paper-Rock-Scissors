package es.ulpgc.eite.da.paper_rock_scissors.player2;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.paper_rock_scissors.game.AppMediator;


/**
 * Created by Luis on marzo, 2023
 */

public class Player2Screen {

  public static void configure(Player2Contract.View view) {

    /*
    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);
    */

    AppMediator mediator = AppMediator.getInstance();
    Player2Contract.Presenter presenter = new Player2Presenter(mediator);

    Player2Contract.Model model = new Player2Model();

    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}