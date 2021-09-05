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
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

  @BeforeMethod  // включаем если встроеный если внешний выключаем
  public void startMailServer()  {
    app.mail().start();

  }


  @Test
  public void testChangePassword() throws MessagingException, IOException {
   Users dbUsers = app.db().users();
    Iterator<UsersData> iter = dbUsers.iterator();
    UsersData selectuser=iter.next();
    boolean it = iter.hasNext();
    if (selectuser.getUsername().equals("administrator"))
       {if (it)
          {selectuser=iter.next();
          changePassword(selectuser.getUsername(), selectuser.getEmail(), selectuser.getPassword());
          assertTrue(app.newSession().login(selectuser.getUsername(), selectuser.getPassword()));
       }else
             {System.out.println("********************************************************************************** ");
               System.out.println("Зарегистрируйте пользователя, в данный момент зарегистрирован только administrator");
               System.out.println("********************************************************************************* ");
      }
    } else{
      changePassword(selectuser.getUsername(), selectuser.getEmail(), selectuser.getPassword());
      assertTrue(app.newSession().login(selectuser.getUsername(), selectuser.getPassword()));
    }

  }

  private static void changePassword(String user, String email, String password) throws MessagingException, IOException {
    app.registration().change(user);
   List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);  // встроеный почтовый сервер
   // List<MailMessage> mailMessages = app.james().waitForMail(user,password,70000); // внешний почтовый сервер
    String newconfirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(newconfirmationLink, password);
  }

  private static String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex=VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();  //
    return  regex.getText(mailMessage.text);
  }


  @AfterMethod(alwaysRun = true) // включаем если встроеный если внешний выключаем
   public void stopMailServer(){
    app.mail().stop();
  }
}


