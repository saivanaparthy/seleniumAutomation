package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Base.Baseproject;
import Base.Common;
import utility.Excelutility;

public class SignupPage extends Common {
	public By firstName = By.id("inputFirstName");
	public By LastName = By.id("inputLastName");
	public By Email = By.id("inputEmail");
	public By countryCodedropdown = By.xpath("//div[@class='selected-dial-code']");

	public By countrycode(String code) {
		return By.xpath("//li[@data-dial-code='" + code + "']");
	}

	public By phoneNumb = By.id("inputPhone");
	public By company = By.id("inputCompanyName");
	public By StreetAdress1 = By.id("inputAddress1");
	public By StreetAdress2 = By.id("inputAddress2");
	public By city = By.id("inputCity");
	public By state = By.id("stateinput");
	public By postcode = By.id("inputPostcode");
	public By country = By.id("inputCountry");
	public By whatsup = By.id("customfield2");
	public By password = By.id("inputNewPassword1");
	public By confPassword = By.id("inputNewPassword2");

	public boolean verifySignup() throws Exception {

		Excelutility util = new Excelutility(System.getProperty("user.dir") + prop.getProperty("excelFilepath"),
				0);
		List<HashMap<String, String>> data = util.readExcelsheetdata();
		System.out.println(data.get(1).get("Firstname"));
		Homepage home = new Homepage();
		String mainwondow = driver.getWindowHandle();
		driver.findElement(home.signup).click();
		switchToChildwindow(mainwondow);
		driver.findElement(firstName).sendKeys(data.get(1).get("Firstname"));
		driver.findElement(LastName).sendKeys(data.get(1).get("Lastname"));
		driver.findElement(Email).sendKeys(data.get(1).get("Email"));
		//driver.findElement(countryCodedropdown).click();
		//driver.findElement(countrycode(data.get(1).get("CountryCode")));
		driver.findElement(phoneNumb).sendKeys(data.get(1).get("PhoneNo"));
		driver.findElement(company).sendKeys(data.get(1).get("Company"));
		driver.findElement(StreetAdress1).sendKeys(data.get(1).get("StreetAdress1"));
		driver.findElement(StreetAdress2).sendKeys(data.get(1).get("StreetAdress2"));
		driver.findElement(city).sendKeys(data.get(1).get("City"));
		driver.findElement(state).sendKeys(data.get(1).get("State"));
		driver.findElement(postcode).sendKeys(data.get(1).get("PostCode"));
		
		WebElement country2=driver.findElement(country);
	Select dropdown=new Select(country2);
	dropdown.selectByVisibleText(data.get(1).get("Country2"));
		driver.findElement(whatsup).sendKeys(data.get(1).get("WhatsupNo"));
		driver.findElement(password).sendKeys(data.get(1).get("Password"));
		driver.findElement(confPassword).sendKeys(data.get(1).get("ConfirmPassword"));
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
		driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@value='Register']")).click();
		return true;
	}
}
