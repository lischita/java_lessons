package ru.lischita.les.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.lischita.les.addressbook.appmanager.ApplicationManager;

public class TestBase {

  protected static ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  //@BeforeMethod(alwaysRun = true)
  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  //@AfterMethod(alwaysRun = true)
  @AfterSuite
  public void tearDown() throws Exception {
    app.stop();

  }

}
