package ru.lischita.les.mantis.tests;

import org.openqa.selenium.By;
import ru.lischita.les.mantis.appmanager.ApplicationManager;
import ru.lischita.les.mantis.appmanager.HelperBase;

public class RegistrationHelper extends HelperBase {



  public RegistrationHelper(ApplicationManager app) {
    super(app);
 }

  public void start(String username, String email) {
   wd.get(app.getPropetry("web.baseURL")+"/signup_page.php");
    type(By.name("username"),username);
    type(By.name("email"),email);
   //click(By.cssSelector("inpit[value='Зарегистрироваться']"));  // регистрация
   click(By.cssSelector("input[class='width-40 pull-right btn btn-success btn-inverse bigger-110']"));
 }

  public void finish(String confirmationLink, String password) {
   wd.get(confirmationLink);
   type(By.name("password"),password);
   type(By.name("password_confirm"),password);
   //click(By.cssSelector("a[class='width-40 btn btn-inverse bigger-110 btn-success']")); // Подтверждение
   click(By.cssSelector("button[class='width-100 width-40 pull-right btn btn-success btn-inverse bigger-110']")); //Изменение пароля без ввода
 }

  public void change(String user) {
  wd.get(app.getPropetry("web.baseURL")+"login_page.php");
  type(By.name("username"),"administrator");
  click(By.cssSelector("input[class='width-40 pull-right btn btn-success btn-inverse bigger-110']"));
  type(By.name("password"),"grot");
  click(By.cssSelector("input[class='width-40 pull-right btn btn-success btn-inverse bigger-110']"));
   wd.get(app.getPropetry("web.baseURL")+"manage_user_page.php");
  //click(By.cssSelector("li[a[href='/mantisbt-2.25.2/manage_user_page.php']]"));
//   click(By.cssSelector("a[href='/mantisbt-2.25.2/manage_user_page.php']"));//для ром
   //click(By.cssSelector("ul/li[6]/a/i"));
   //click(By.cssSelector("li[class='active']"));
  //click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i"));//------ работал только для Fire и IE
   //click(By.cssSelector("i[class='fa fa-gears menu-icon']"));
  //click(By.cssSelector("i.fa.fa-gears.menu-icon"));
   //click(By.xpath("//a[contains(@href,'/mantisbt-2.25.2/manage_user_page.php']"));
  //click(By.cssSelector("a[href='/mantisbt-2.25.2/manage_user_page.php']"));
  //click(By.className("fa fa-gears menu-icon"));
  //click(By.xpath("//li[6]/a/i"));
 // click(By.xpath("//div[2]/div[2]/div/ul/li[2]/a"));
  click(By.linkText(user));
 // click(By.xpath(String.format("//a[contains(text(),'%s']",user)));
  //click(By.xpath("//div[@id='main-container']/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[11]/td/a"));
  //click(By.cssSelector("input[class='btn btn-primary btn-white btn-round']"));
  click(By.cssSelector("input[value='Сбросить пароль']"));
  }



 }

