package ru.lischita.les.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd=wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text!=null){
      String existingText=wd.findElement(locator).getAttribute("value");
      if(!text.equals(existingText)){
      wd.findElement(locator).clear();
      wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void select (By locator,String text) {
    click (locator);
    if (text!=null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        new Select(wd.findElement(locator)).selectByVisibleText(text);
      }
    }
    click (locator);
  }

  protected void closealertwindow(){
  wd.switchTo().alert().accept();}

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected boolean IsElementPresent (By locator) {
    try{
      wd.findElement(locator);
      return true;
    } catch(NoSuchElementException ex){
      return false;
    }
  }
}