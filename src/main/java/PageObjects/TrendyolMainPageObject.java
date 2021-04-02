package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TrendyolMainPageObject extends  CommonObject{
    private int ShownImagesLimit = 10;
    private int WaitMillisecondsForImageLoad = 2300;
    private  final  String mainPageAfterLoginRoutePart = "/butik";
    ArrayList<By> lstTabs;

    By tabWomen = By.xpath("//a[text()='KADIN']");
    By tabMen = By.xpath("//a[text()='ERKEK']");
    By tabChildren = By.xpath("//a[text()='ÇOCUK']");
    By tabHome = By.xpath("//a[text()='EV & YAŞAM']");
    By tabGrocery = By.xpath("//a[text()='SÜPERMARKET']");
    By tabCosmetic = By.xpath("//a[text()='KOZMETİK']");
    By tabShoesBags = By.xpath("//a[text()='AYAKKABI & ÇANTA']");
    By tabWatchesAccessories = By.xpath("//a[text()='SAAT & AKSESUAR']");
    By tabElectronic = By.xpath("//a[text()='ELEKTRONİK']");
    By imgStores = By.xpath("//a/img[@loading='lazy']");
    By btnStoreImagesNext = By.xpath("//button[@class = 'slick-arrow slick-next']");

    public TrendyolMainPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);

        lstTabs = new ArrayList<By>();
        lstTabs.add(tabWomen);
        lstTabs.add(tabMen);
        lstTabs.add(tabChildren);
        lstTabs.add(tabHome);
        lstTabs.add(tabGrocery);
        lstTabs.add(tabCosmetic);
        lstTabs.add(tabShoesBags);
        lstTabs.add(tabWatchesAccessories);
        lstTabs.add(tabElectronic);
    }

    public void ClickTabsWithIndex(int index){
        ClickElementWhenIsClickable(lstTabs.get(index));
    }

    public void NavigateAllTabsAndCheckImages(){
        for(int i = 0 ; i < lstTabs.size() ; i++){
            ClickTabsWithIndex(i);
            CheckAdaptivelyIfListOfImagesLoaded();
        }
    }

    public  void CheckAdaptivelyIfListOfImagesLoaded(){
        List<WebElement> imagesList = driver.findElements(imgStores);
        WaitMilliseconds(400);
        for(int i=0; i< imagesList.size(); i++){
            if(i!=0 && i%(ShownImagesLimit-2) == 0 && imagesList.size() > ShownImagesLimit){
                ClickElementWhenIsClickable(btnStoreImagesNext);
                WaitMilliseconds(WaitMillisecondsForImageLoad);
            }
            Assert.assertTrue("LOG Message: Images couldn't load less than " + WaitMillisecondsForImageLoad + " milliseconds!",CheckIfImageLoaded(imagesList.get(i)));
        }
    }

    public  void CheckIfMainPageAfterLoginOpened(){
        CheckIfPageNavigated(mainPageAfterLoginRoutePart);
    }
}
