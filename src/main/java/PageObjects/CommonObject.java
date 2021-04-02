package PageObjects;

import Base.DriverBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonObject {
    protected WebDriver driver;

    public  void ClickElementWhenIsClickable(By byValue){
        if(driver.findElements(byValue).size()>0) {
            DriverBase.wait.until(ExpectedConditions.elementToBeClickable(byValue));
            driver.findElement(byValue).click();
        }
    }

    public void SetTextToElementWhenIsClickable(By byValue, String text){
        if(driver.findElements(byValue).size()>0) {
            DriverBase.wait.until(ExpectedConditions.elementToBeClickable(byValue));
            driver.findElement(byValue).sendKeys(text);
            driver.findElement(byValue).sendKeys(Keys.TAB);
        }
    }

    public  void WaitUntilElementAppeared(By byValue){
        DriverBase.wait.until(ExpectedConditions.visibilityOfElementLocated(byValue));
    }

    public  boolean CheckIfImageLoaded(WebElement image){
        Boolean isImageLoaded = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", image);
        return isImageLoaded;
    }

    public  void WaitMilliseconds(int milliSeconds){
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void SwitchToLastWindow(){
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }

    public boolean CheckIfPageNavigated(String desiredUrlPart){
        if(driver.getCurrentUrl().contains(desiredUrlPart)){
            return  true;
        }
        else{
            return  false;
        }
    }
}
