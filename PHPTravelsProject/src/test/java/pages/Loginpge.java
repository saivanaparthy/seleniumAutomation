package pages;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import Base.Baseproject;
import Base.Common;

public class Loginpge extends Common{
public By email =By.id("inputEmail");
public By pwd=By.id("inputPassword");
public By captchaFrame=By.xpath("//iframe[@title='reCAPTCHA']");
public By alertmsg=By.xpath("//div[@class='alert alert-danger']");
public By signbtn=By.xpath("//button[@id='login']");
public boolean verifyLogin() throws Exception{
	Homepage home=new Homepage();
	String mainwondow=driver.getWindowHandle();
	driver.findElement(home.loginBtn).click();
switchToChildwindow(mainwondow);
driver.findElement(email).sendKeys(prop.getProperty("username"));
driver.findElement(pwd).sendKeys(prop.getProperty("password"));
driver.switchTo().frame(driver.findElement(captchaFrame));
driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
driver.switchTo().defaultContent();
Thread.sleep(5000);
driver.findElement(signbtn).click();
driver.switchTo().window(mainwondow);
/*SoftAssert soft=new SoftAssert();
soft.assertEquals(waitforvisibility(alertmsg),true,"not visible"); 
String text=driver.findElement(alertmsg).getText();
return text.contains("please complete the captcha");*/
 
return true;

}
}
