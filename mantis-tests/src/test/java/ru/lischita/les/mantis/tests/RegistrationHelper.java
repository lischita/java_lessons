package ru.lischita.les.mantis.tests;

import org.openqa.selenium.WebDriver;
import ru.lischita.les.mantis.appmanager.ApplicationManager;

public class RegistrationHelper {
  private final ApplicationManager app;
  private WebDriver wd;


  public RegistrationHelper(ApplicationManager app) {
    this.app=app;
    wd=app.getDriver();
  }

  public void start(String username, String email) {
   wd.get(app.getPropetry("web.baseURL")+"/signup_page.php");
  }
}
