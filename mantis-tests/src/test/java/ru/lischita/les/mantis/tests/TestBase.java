package ru.lischita.les.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lischita.les.mantis.appmanager.ApplicationManager;

import java.io.File;

public class TestBase {
  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception
  {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_defaults_inc.php"),"config_defaults_inc.php","config_defaults_inc.php.bak");
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() throws Exception
  {
    app.ftp().restore("config_defaults_inc.php.bak","config_defaults_inc.php");
    app.stop();
  }

}
