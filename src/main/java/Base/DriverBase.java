package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DriverBase {
    protected static WebDriver driver;
    public static WebDriverWait wait;
    final String baseUrl = "https://www.trendyol.com/";
    final String chromeBrowser = "chrome";
    final String fireFoxBrowser = "firefox";

    public void SetUp(String browserName){
        if(browserName.toLowerCase(Locale.ROOT).contains(chromeBrowser)){
            try{
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-popup-blocking");
                System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
                driver = new ChromeDriver(options);
                driver.manage().deleteAllCookies();

            }catch (Exception exception){
                exception.printStackTrace();
            }
        }else if(browserName.toLowerCase(Locale.ROOT).contains(fireFoxBrowser)){
            System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        wait = new WebDriverWait(driver,6);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(baseUrl);

    }

    public void NavigateToUrl(String url){
        driver.get(url);
    }

    public  void TearDown(){
        driver.close();
        driver.quit();
    }
}
