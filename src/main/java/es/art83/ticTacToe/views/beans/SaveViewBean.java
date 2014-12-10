package es.art83.ticTacToe.views.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;

import es.art83.ticTacToe.controllers.SaveGameController;

@ManagedBean
public class SaveViewBean extends ViewBean {
    private List<String> gameNames;

    private String gameName;

    @PostConstruct
    public void update() {
        this.gameNames = this.getControllerFactory().getSaveGameController().gameNames();
    }

    public List<String> getGameNames() {
        return gameNames;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public boolean isZeroGameNames() {
        return this.gameNames.size() == 0;
    }

    public String process() {
        SaveGameController saveGameController = this.getControllerFactory().getSaveGameController();
        String next = null;
        if (this.gameNames.contains(this.gameName)) {
            FacesContext.getCurrentInstance().addMessage("saveViewBean:gameName",
                    new FacesMessage("Game name exist!!!"));
        } else {
            saveGameController.saveGame(this.gameName);
            LogManager.getLogger(saveGameController.getClass().getName()).info(
                    "Partida salvada: " + this.gameName);
            next = "game";
        }
        return next;
    }
}
