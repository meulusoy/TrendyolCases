package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

public class TrendyolLoginPageObject extends CommonObject {
    By btnLoginHighlight = By.xpath("//div//p[text() = 'Giriş Yap']");
    By btnStartPopUpClose = By.xpath("//div//a[@title = 'Close']");
    By txtEmail = By.id("login-email");
    By txtPassword = By.xpath("//div//input[@type='password']");
    By btnLoginScreenSubmit = By.xpath("//div//button[@type = 'submit']/span[text() = 'Giriş Yap']");
    By btnMyAccount = By.xpath("//div/p[text() = 'Hesabım']");

    private final String loginPageRoutePart = "/giris";

    public TrendyolLoginPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public  void EnterUserNameAndPassword(String eMail, String password){
        SetTextToElementWhenIsClickable(txtEmail,eMail);
        SetTextToElementWhenIsClickable(txtPassword,password);
    }

    public  void CloseStartPopUpIfExist(){
        ClickElementWhenIsClickable(btnStartPopUpClose);
    }

    public  void ClickLoginButton(){
        WaitMilliseconds(800);
        ClickElementWhenIsClickable(btnLoginHighlight);
    }

    public  void ClickLoginScreenSubmitButton(){
        ClickElementWhenIsClickable(btnLoginScreenSubmit);
        WaitUntilElementAppeared(btnMyAccount);
    }

    public  void CheckIfLoginPageOpened(){
        CheckIfPageNavigated(loginPageRoutePart);
    }




}
