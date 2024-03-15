package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import Base.Baseproject;
import Base.Common;
import pages.SignupPage;

public class verifyRegistration extends Baseproject{
  @Test
  public void verifySignupTest() throws Exception {
  test=extent.createTest("verifySignupTest");
  SignupPage spage=new SignupPage();
boolean result=spage.verifySignup();
assertEquals(result, true,"Failed to validate the signup page");
System.out.println("successfully validateed signup page");
 
  }
}
