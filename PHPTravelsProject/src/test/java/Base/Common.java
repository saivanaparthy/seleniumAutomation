package Base;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.Homepage;

public class Common extends Baseproject {
public boolean waitforvisibility(By locator){
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	} catch (Exception e) {
		// TODO: handle exception
	System.out.println("Failed to find the visibility of element: "+locator);
	}
	return driver.findElement(locator).isDisplayed();
}
public void switchToChildwindow(String mainwondow) {
	Set<String>winds=driver.getWindowHandles();
	String childwindow=null;
	Set<String>windows=driver.getWindowHandles();
for(String wind:windows) {
if(!wind.equals(mainwondow)) {
	childwindow=wind;
  }	
}
	driver.switchTo().window(childwindow);

}
}
