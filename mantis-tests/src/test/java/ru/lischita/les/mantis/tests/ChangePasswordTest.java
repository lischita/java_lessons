package ru.lischita.les.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.lischita.les.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

  //@BeforeMethod  // включаем если встроеный если внешний выключаем
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws MessagingException, IOException {

    String user="user1630327388961";
    String oldpassword="password";
    String newpassword="pass";
    app.registration().change(user);
   // List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);  // встроеный почтовый сервер
    List<MailMessage> mailMessages = app.james().waitForMail(user,oldpassword,60000); // внешний почтовый сервер
    String confirmationLink = findConfirmationLink(mailMessages, user+"@localhost");
    app.registration().finish(confirmationLink, newpassword);
    assertTrue(app.newSession().login(user, newpassword));

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


