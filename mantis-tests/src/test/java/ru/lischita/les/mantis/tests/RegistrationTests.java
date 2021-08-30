package ru.lischita.les.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.lischita.les.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

 //@BeforeMethod  // включаем если встроеный если внешний выключаем
public void startMailServer(){
    app.mail().start();
  }


  @Test
  public void testRegistration() throws MessagingException, IOException {
    long now=System.currentTimeMillis();
    String user="user"+now;
    String password = "password";
    String email="user"+now+"@localhost"; //.localdomain";
    app.james().createUser(user,password); // формирование пользователя на внешнем почтовом червере
    app.registration().start(user,email);
   // List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);  // встроеный почтовый сервер
   List<MailMessage> mailMessages = app.james().waitForMail(user,password,60000); // внешний почтовый сервер
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex=VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return  regex.getText(mailMessage.text);

  }

 // @AfterMethod(alwaysRun = true) // включаем если встроеный если внешний выключаем
public void stopMailServer(){
  app.mail().stop();
}

}
