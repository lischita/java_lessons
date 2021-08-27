package ru.lischita.les.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lischita.les.mantis.appmanager.ApplicationManager;

public class TestBase {
  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception
  {
    app.init();
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() throws Exception
  {
    app.stop();
  }

}
