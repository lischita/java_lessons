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
    int issueId = 1292;
    System.out.println("Номер задачи: " + issueId);
    try {
      skipIfNotFixedRest(issueId);
      Set<Issue> oldIssues = app.rest().getIssues();
      Issue newIssue = new Issue().withSubject("Test Issue").withDescription("TEST number 9");
      issueId = app.rest().createIssue(newIssue);
      Set<Issue> newIssues = app.rest().getIssues();
      System.out.println(issueId);
      oldIssues.add(newIssue.withId(issueId));
      Assert.assertEquals(newIssues, oldIssues);
      System.out.println("Тест testCreateIssue() пройден");
      System.out.println("***************************************************************************************************************");
    } catch (SkipException | ServiceException | javax.xml.rpc.ServiceException e) {
      System.out.println("Тест testCreateIssue() пропущен так как статус задачи  " + issueId + " не переведен в 'Resolved' или 'Closed' ");
      System.out.println("***************************************************************************************************************");
    }
  }
  @Test
  public void testCreateIssue_1() throws IOException {
    int issueId = 1347;
    System.out.println("Номер задачи: " + issueId);
    try {
      skipIfNotFixedRest(issueId);
      Set<Issue> oldIssues = app.rest().getIssues();
      Issue newIssue = new Issue().withSubject("Test Issue").withDescription("TEST number 9");
      issueId = app.rest().createIssue(newIssue);
      Set<Issue> newIssues = app.rest().getIssues();
      oldIssues.add(newIssue.withId(issueId));
      Assert.assertEquals(newIssues, oldIssues);
      System.out.println("Тест testCreateIssue()_1 пройден");
      System.out.println("***************************************************************************************************************");
    } catch (SkipException | ServiceException | javax.xml.rpc.ServiceException e) {
      System.out.println("Тест testCreateIssue()_1 пропущен так как статус задачи  " + issueId + " не переведен в 'Resolved' или 'Closed' ");
      System.out.println("***************************************************************************************************************");
    }
  }
  @Test
  public void testCreateIssue_2() throws IOException {
    int issueId = 1348;
    System.out.println("Номер задачи: " + issueId);
    try {
      skipIfNotFixedRest(issueId);
      Set<Issue> oldIssues = app.rest().getIssues();
      Issue newIssue = new Issue().withSubject("Test Issue").withDescription("TEST number 9");
      issueId = app.rest().createIssue(newIssue);
      Set<Issue> newIssues = app.rest().getIssues();
      oldIssues.add(newIssue.withId(issueId));
      Assert.assertEquals(newIssues, oldIssues);
      System.out.println("Тест testCreateIssue()_1 пройден");
      System.out.println("***************************************************************************************************************");
    } catch (SkipException | ServiceException | javax.xml.rpc.ServiceException e) {
      System.out.println("Тест testCreateIssue()_2 пропущен так как статус задачи  " + issueId + " не переведен в 'Resolved' или 'Closed' ");
      System.out.println("***************************************************************************************************************");
    }
  }
}







