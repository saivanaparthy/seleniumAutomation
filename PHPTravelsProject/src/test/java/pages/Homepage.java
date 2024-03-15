package pages;

import org.openqa.selenium.By;

import Base.Baseproject;

public class Homepage extends Baseproject {
public By loginBtn=By.xpath("//strong[text()='Login']");
public By signup=By.xpath("//strong[text()='Signup']");
public By demoBtn=By.xpath("//li/a[text()='Demo']");
}
