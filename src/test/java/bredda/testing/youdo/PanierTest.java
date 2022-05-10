package bredda.testing.youdo;

import bredda.testing.youdo.ui.Navigation;
import bredda.testing.youdo.ui.pages.CartPage;
import bredda.testing.youdo.ui.pages.InventoryPage;
import bredda.testing.youdo.ui.pages.LoginPage;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SerenityJUnit5Extension.class)
class PanierTest {

    @Managed
    WebDriver driver;

    @Steps
    Navigation navigate;
    @Steps
    LoginPage loginPage;
    @Steps
    InventoryPage inventoryPage;
    @Steps
    CartPage cart;

    @Test
    @Title("Devrait ajouter des produits au panier")
    void devraitPouvoirAjouterUnItemAuPanier() throws InterruptedException {
        List<String> produits = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Onesie");
        navigate.seRendreSurLaHome();
        loginPage.SeConnecterAvec("standard_user", "secret_sauce");
        inventoryPage.ajouterAuPanier(produits);
        navigate.seRendreDansLePanier();
        cart.verifierBadgePanier(produits.size());
        cart.verifierProduitsPanier(produits);
        cart.verifierTousProduitQte1();
    }
}
