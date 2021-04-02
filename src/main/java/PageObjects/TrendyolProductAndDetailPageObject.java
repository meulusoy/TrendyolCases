package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TrendyolProductAndDetailPageObject extends  CommonObject{
    By btnHouseholdAppliances = By.xpath("//a//span[text() = 'Beyaz Eşya']");
    By imgHouseholdApplianceItem = By.xpath("//img[@class = 'p-card-img']");
    By btnItemDetailAddToCart = By.xpath("//button//div[text() = 'Sepete Ekle']");
    By btnMyCart = By.xpath("//a/p[text() = 'Sepetim']");
    By btnTrash = By.xpath("//button/i[@class = 'i-trash']");
    By btnDeleteConfirm = By.xpath("//span[text() ='Sil']");

    private final String itemCategoryPageRoute = "/beyaz-esya";

    public  TrendyolProductAndDetailPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public  void ClickHouseholdAppliancesAndCheckIfImageLoaded(){
        ClickElementWhenIsClickable(btnHouseholdAppliances);
        WaitMilliseconds(400);
        Assert.assertTrue("LOG Message: Image couldn't load!", CheckIfImageLoaded(driver.findElement(imgHouseholdApplianceItem)));
    }

    public  void ClickProductImage(){
        ClickElementWhenIsClickable(imgHouseholdApplianceItem);
    }

    public  void ClickAddToCart(){
        SwitchToLastWindow();
        WaitMilliseconds(1000);
        ClickElementWhenIsClickable(btnItemDetailAddToCart);
    }

    public  void ClickMyCart(){
        WaitMilliseconds(1000);
        ClickElementWhenIsClickable(btnMyCart);
    }

    public  void DeleteItemFromMyCart(){
        ClickElementWhenIsClickable(btnTrash);
        ClickElementWhenIsClickable(btnDeleteConfirm);
    }

    public  void CheckIfItemPageOpened(){
        CheckIfPageNavigated(itemCategoryPageRoute);
    }
}
