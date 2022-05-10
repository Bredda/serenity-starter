package bredda.testing.youdo.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPage extends UIInteractions {

    @FindBy(css=".shopping_cart_badge")
    WebElementFacade badge;

    @FindBy(css=".cart_item")
    List<WebElementFacade> items;

    @Step("Vérifier que le panier contient seulement les produits suivants: {0}")
    public void verifierProduitsPanier(List<String> produits) {
        assertThat(produits)
                .withFailMessage("Le nombre de produits dans le panier ne correspond pas au nombre attendu")
                .hasSameSizeAs(items);
        assertThat(produits).isEqualTo(extractCartContent());
    }
    @Step("Vérifier que tous les produits du panier ont une quantité de 1")
    public void verifierTousProduitQte1() {
        items.forEach(i ->
            assertThat(getItemQuantity(i)).isEqualTo(1).withFailMessage(
                    "Le produit '%s' a une quantité différente de 1", getItemName(i)
            )
        );
    }

    @Step("Vérifier que l'icone du panier affiche {0] produit(s)")
    public void verifierBadgePanier(int nbAttendu) {
        assertThat(Integer.parseInt(badge.getText())).isEqualTo(nbAttendu);
    }

    /**
     * Retourne la quantité affichée pour un WebElement produit du panier donné
     * @param item Item du panier ciblée
     * @return Quantité
     */
    private int getItemQuantity(WebElementFacade item) {
        return Integer.parseInt(item.findElement(By.cssSelector(".cart_quantity")).getText());
    }
    /**
     * Retourne le nom affiché pour un WebElement produit du panier donné
     * @param item Item du panier ciblée
     * @return Nom
     */
    private String getItemName(WebElementFacade item) {
        return item.findElement(By.cssSelector(".inventory_item_name")).getText();
    }

    /**
     * Renvoie la liste des produits présents dans le panier
     * @return Produits du panier
     */
    private List<String> extractCartContent() {
        List<String> content = new ArrayList<>();
        items.forEach(i -> {
            content.add(getItemName(i));
        });
        return content;
    }

}
