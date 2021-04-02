package UITrendyolScenarios;

import Base.DriverBase;
import PageObjects.TrendyolLoginPageObject;
import PageObjects.TrendyolMainPageObject;
import PageObjects.TrendyolProductAndDetailPageObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TrendyolTestCase extends DriverBase {
    private final String eMail = "trendyoltesting@gmail.com";
    private final String password = "trendyoltest1";
    private final String desiredBrowser = "chrome";

    @BeforeTest
    public  void BeforeMethod(){
        super.SetUp(desiredBrowser);
    }

    @Test
    public  void TrendyolScenario(){
        TrendyolLoginPageObject loginPageObject = new TrendyolLoginPageObject(driver);
        TrendyolMainPageObject mainPageObject = new TrendyolMainPageObject(driver);
        TrendyolProductAndDetailPageObject productAndDetailPageObject = new TrendyolProductAndDetailPageObject(driver);

        loginPageObject.CloseStartPopUpIfExist();
        loginPageObject.ClickLoginButton();
        loginPageObject.CheckIfLoginPageOpened();
        loginPageObject.EnterUserNameAndPassword(eMail, password);
        loginPageObject.ClickLoginScreenSubmitButton();

        mainPageObject.CheckIfMainPageAfterLoginOpened();
        mainPageObject.NavigateAllTabsAndCheckImages();

        productAndDetailPageObject.ClickHouseholdAppliancesAndCheckIfImageLoaded();
        productAndDetailPageObject.CheckIfItemPageOpened();
        productAndDetailPageObject.ClickProductImage();
        productAndDetailPageObject.ClickAddToCart();
        productAndDetailPageObject.ClickMyCart();
        productAndDetailPageObject.DeleteItemFromMyCart();

    }

    @AfterTest
    public  void AfterMethod(){
        super.TearDown();
    }

}
