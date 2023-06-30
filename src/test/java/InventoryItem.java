import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InventoryItem {
	private String name;
	private double price;
	
	private WebElement fullElement;
	private WebElement addToCartButton;
	
	public InventoryItem (WebElement webElement) {
		this.fullElement = webElement;
		this.addToCartButton = webElement.findElement(By.className("btn_inventory"));
		String textPrice = webElement.findElement(By.className("inventory_item_price")).getText().substring(1);
		this.price = Double.parseDouble(textPrice);
		this.name = webElement.findElement(By.className("inventory_item_name")).getText();
	}
	
	public WebElement getAddToCartButton () {
		return addToCartButton;
	}
	
	public double getPrice () {
		return price;
	}
}
