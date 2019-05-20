package framework.applications.gw;

import framework.guidewire.GuidewireInteract;
import framework.guidewire.pages.GWIDs;

public class ServerPages {

    private GuidewireInteract interact;

    public ServerPages(GuidewireCenter center){
        this.interact = center.getInteractObject();
    }

    public void clickSetLogLevel(){
        interact.withElement(GWIDs.ServerPages.ServerTools.SET_LOG_LEVEL).click();
    }

    public void returnToCenter(){
        interact.withElement(GWIDs.ServerPages.ACTIONS_MENU).click();
        interact.withElement(GWIDs.ServerPages.ActionsMenu.RETURN_TO_APP).click();
    }
}
