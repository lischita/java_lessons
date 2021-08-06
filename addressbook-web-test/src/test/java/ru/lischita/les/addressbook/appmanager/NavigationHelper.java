package ru.lischita.les.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if(IsElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && IsElementPresent(By.name("new")))
    { return; }
    click(By.linkText("groups"));
  }


  public void gotoHomePage() {
    if(IsElementPresent(By.id("maintable"))) {return;}
    click(By.linkText("home"));}

  }

