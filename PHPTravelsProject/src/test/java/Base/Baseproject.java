package Base;

import org.testng.annotations.BeforeMethod;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterSuite;

public class Baseproject {
  public static WebDriver driver;
  public static WebDriverWait wait;
  public static Actions actions;
  public static Properties prop;
  public  File file;
  public FileInputStream fis;
  public ExtentHtmlReporter Htmlreport;
  public ExtentReports extent;
  public ExtentTest test;
  @BeforeMethod
  public void beforeMethod() {
	  String browser=prop.getProperty("browser");
	  if(browser.equalsIgnoreCase("chrome")) {
		  driver=new ChromeDriver();
	  }else if(browser.equalsIgnoreCase("edge")) {driver=new EdgeDriver();}
	  else if(browser.equalsIgnoreCase("firefox")) {driver=new FirefoxDriver();}
  driver.manage().window().maximize();
  driver.get(prop.getProperty("url"));
  wait=new WebDriverWait(driver, Duration.ofSeconds(30));
  actions=new Actions(driver);
  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
  }

  @AfterMethod
  public void afterMethod(ITestResult result)throws Exception  {
  if(result.getStatus()==ITestResult.FAILURE) {
	  test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"-Test case Failed", ExtentColor.RED));
	  test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable()+"-Test case Failed", ExtentColor.RED));
  String pathString=Baseproject.screenshot(driver, result.getName());
  test.addScreenCaptureFromPath(pathString);
  }
  else if(result.getStatus()==ITestResult.SKIP) {
	  test.log(Status.SKIP, "skipped Test Case is: "+result.getName());
  }else if(result.getStatus()==ITestResult.SUCCESS) {
	  test.log(Status.PASS, "pass Test Case is: "+result.getName());
  }
  driver.quit();
  }

  @BeforeSuite
  public void beforeSuite() throws Exception {
	  file=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\config.properties");
  fis=new FileInputStream(file);
  prop=new Properties();
  prop.load(fis);
  Htmlreport=new ExtentHtmlReporter(System.getProperty("user.dir")+"/target/ExtentReport/myReport.html");
  Htmlreport.config().setDocumentTitle("Automation Test Report");
  Htmlreport.config().setReportName("PHP Travels Test Automation Report");
  Htmlreport.config().setTheme(Theme.STANDARD);
  extent=new ExtentReports();
  extent.attachReporter(Htmlreport);
  extent.setSystemInfo("HostName", "MyHost");
  extent.setSystemInfo("Projectname", "php Travels");
  extent.setSystemInfo("Tester", "");
  extent.setSystemInfo("Os", "Win11");
  extent.setSystemInfo("Browser", prop.getProperty("browser"));
  
  }

  @AfterSuite
  public void afterSuite() throws Exception {
 fis.close();
 extent.flush();
  }
  public static String screenshot(WebDriver driver,String filename) {
	String dateName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	  TakesScreenshot takescreenshot=(TakesScreenshot) driver;
		File source=takescreenshot.getScreenshotAs(OutputType.FILE);
		String destination=System.getProperty("user.dir")+"\\screenshot\\"+filename+"_"+dateName+".png";
	  File finalDestination=new File(destination);
	  try {
		FileUtils.copyFile(source, finalDestination);
	} catch (Exception e) {
		// TODO: handle exception
	e.getMessage();
	}
		return destination;
			}

}
