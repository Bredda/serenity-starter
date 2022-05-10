package bredda.testing.youdo.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.exceptions.TestCompromisedException;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class InventoryPage extends UIInteractions {

    @FindBy(css=".title")
    WebElementFacade title;

    @FindBy(css = ".inventory_item")
    List<WebElementFacade> items;

    @Step("Ajouter au panier le produit: {0}")
    public void ajouterAuPanier(String nomItem) {
        this.waitForAnyTextToAppear(nomItem);
        System.out.println(items.size());
        WebElementFacade item = items.stream().filter(e ->
            e.findElement(By.cssSelector(".inventory_item_name")).getText().equals(nomItem)
        ).findFirst().orElseThrow(() -> new TestCompromisedException(String.format("Produit %s non trouvé", nomItem)));

        item.findElement(By.cssSelector(".pricebar button")).click();
    }

    @Step("Ajouter au panier les produits suivants: {0}")
    public void ajouterAuPanier(List<String> produits) {
        produits.forEach(this::ajouterAuPanier);
    }
}
