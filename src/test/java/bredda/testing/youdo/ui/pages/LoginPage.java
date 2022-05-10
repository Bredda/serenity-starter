package bredda.testing.youdo.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

public class LoginPage  extends UIInteractions {

    @FindBy(id="user-name")
    WebElementFacade usernameInput;

    @FindBy(id="password")
    WebElementFacade passwordInput;

    @FindBy(id="login-button")
    WebElementFacade loginButton;

    @FindBy(css="*[data-test='error']")
    WebElementFacade errorMessage;

    @FindBy(css=".error-message-container.error")
    WebElementFacade errorMessageContainer;

    @Step("Se connecter avec '{0}', '{1}'")
    public void SeConnecterAvec(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
