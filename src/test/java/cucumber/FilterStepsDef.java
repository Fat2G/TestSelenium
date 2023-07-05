package cucumber;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FilterStepsDef {
    private WebDriver driver;
    private List<Double> productPrices;

    public FilterStepsDef(WebDriver driver) {
        this.driver = driver;
    }

    public void jeSelectionneLOptionDeFiltrageParPrixCroissant() throws InterruptedException {
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.cssSelector("option[value='lohi']")).click();
        Thread.sleep(1000);
    }

    public void lesProduitsSontAffichesParPrixCroissant(){
       /* System.out.println("url inventory : " + driver.getCurrentUrl());
        System.out.println(driver.findElements(By.cssSelector("inventory_item_price")));*/

        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));

        productPrices = new ArrayList<>();

        for (WebElement element : priceElements) {
            String priceText = element.getText().substring(1);
            double price = Double.parseDouble(priceText);
            productPrices.add(price);
        }

        boolean isSorted = true;
        for (int i = 0; i < productPrices.size() - 1; i++) {
            if (productPrices.get(i) > productPrices.get(i + 1)) {
                isSorted = false;
                break;
            }
        }

        Assert.assertTrue("Les prix ne sont pas triés de manière croissante", isSorted);
    }
}
