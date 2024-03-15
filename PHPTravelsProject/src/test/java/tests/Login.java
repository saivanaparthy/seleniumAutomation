package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import Base.Baseproject;
import pages.Loginpge;

public class Login extends Baseproject {
  @Test
  public void verifyLogin() throws Exception {
	  test=extent.createTest("loginTest");
	  Loginpge login=new Loginpge();
  boolean result=login.verifyLogin();
  assertEquals(result, true,"Failed to validate the login page");
  System.out.println("successfully validateed Login page");
  }
}
