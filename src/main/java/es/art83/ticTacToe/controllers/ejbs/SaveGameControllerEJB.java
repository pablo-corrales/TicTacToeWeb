package es.art83.ticTacToe.controllers.ejbs;

import java.util.List;

import es.art83.ticTacToe.controllers.SaveGameController;
import es.art83.ticTacToe.models.daos.DAOFactory;

public class SaveGameControllerEJB extends ControllerEJB implements SaveGameController {

    public SaveGameControllerEJB(TicTacToeStatesManager ticTacToeStatesManager) {
        super(ticTacToeStatesManager);
    }

    @Override
    public List<String> gameNames() {
        return DAOFactory.getFactory().getGameDAO().findPlayerNamesGames(this.getTicTacToeStatesManager().getPlayer());
    }

    @Override
    public void saveGame(String gameName) {
        this.getTicTacToeStatesManager().getGame().setName(gameName);
        DAOFactory.getFactory().getGameDAO().create(this.getTicTacToeStatesManager().getGame());
        this.getTicTacToeStatesManager().setSaved(true);
    }

}
