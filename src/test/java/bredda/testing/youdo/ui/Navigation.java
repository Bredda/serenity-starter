package bredda.testing.youdo.ui;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

public class Navigation extends UIInteractions {

    @FindBy(css = ".shopping_cart_link")
    private WebElementFacade cartLink;

    @Step("Se rendre sur l'application")
    public void seRendreSurLaHome() {
        this.open();
    }

    @Step("Se rendre dans le panier")
    public void seRendreDansLePanier() {
        cartLink.click();
    }
}
