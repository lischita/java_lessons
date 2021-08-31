package ru.lischita.les.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.lischita.les.mantis.model.MailMessage;
import ru.lischita.les.mantis.model.Users;
import ru.lischita.les.mantis.model.UsersData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

 @BeforeMethod  // включаем если встроеный если внешний выключаем
  public void startMailServer(){
    app.mail().start();
  }

  @Test

  public void testChangePassword() throws MessagingException, IOException {
    Users dbUsers = app.db().users();
    UsersData selectuser=dbUsers.iterator().next();
    String user=selectuser.getUsername();
    String email=selectuser.getEmail();
    String password=selectuser.getPassword();

   // String user="user1630423412744";
    //String password="password";

    app.registration().change(user);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);  // встроеный почтовый сервер
    //List<MailMessage> mailMessages = app.james().waitForMail(user,password,60000); // внешний почтовый сервер
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));

  }
  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex=VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();  //
    return  regex.getText(mailMessage.text);

  }

 @AfterMethod(alwaysRun = true) // включаем если встроеный если внешний выключаем
  public void stopMailServer(){
    app.mail().stop();
  }
}


