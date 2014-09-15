/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.entity.Craftsman;
import model.facade.CraftsmanFacade;

/**
 *
 * @author bmf
 */
@Named(value = "craftsmanView")
@ViewScoped
public class CraftsmanView implements Serializable {

    @EJB
    private CraftsmanFacade craftsmanFacade;

    private Craftsman currentCraftsman;

    public CraftsmanView() {
    }

    @PostConstruct
    private void init() {
        String craftsmanId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("craftsmanId");
        currentCraftsman = craftsmanFacade.find(Long.valueOf(craftsmanId));
    }

    public Craftsman getCurrentCraftsman() {
        return currentCraftsman;
    }

}
