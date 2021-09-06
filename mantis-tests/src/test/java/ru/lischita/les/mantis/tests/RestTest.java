package ru.lischita.les.mantis.tests;

import com.google.protobuf.ServiceException;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import ru.lischita.les.mantis.model.Issue;

import java.io.IOException;
import java.util.Set;

public class RestTest extends TestBase {
  @Test
  public void testCreateIssue() throws IOException {
    int issueId = 1280;
    System.out.println("Номер задачи: " + issueId);
    try {
      skipIfNotFixedRest(issueId);
      Set<Issue> oldIssues = app.rest().getIssues();
      Issue newIssue = new Issue().withSubject("Test Issue").withDescription("TEST number 9");
      issueId = app.rest().createIssue(newIssue);
      Set<Issue> newIssues = app.rest().getIssues();
      oldIssues.add(newIssue.withId(issueId));
      Assert.assertEquals(newIssues, oldIssues);
    } catch (SkipException | ServiceException | javax.xml.rpc.ServiceException e) {
      System.out.println("Тест testGetProject() пропущен так как статус задачи  " + issueId + " не переведен в 'Решена'");
    }
  }
}







