package ru.lischita.les.mantis.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;
import ru.lischita.les.mantis.model.Issue;
import ru.lischita.les.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends  TestBase {

  @Test
  public void testGetProject() throws MalformedURLException, ServiceException, RemoteException, com.google.protobuf.ServiceException {
    int issueId=0000001;
    System.out.println("Номер задачи: "+issueId);
    try {
      skipIfNotFixed(0000001);
      Set<Project> projects = app.soap().getProjects();
      System.out.println("Результат Теста testGetProject(): Пройден");
      System.out.println("Количество активных проектов: "+projects.size());
      for (Project project : projects) {
        System.out.println("Имя активного проекта: "+project.getName());
      }
    }catch (SkipException e){System.out.println("Тест testGetProject() пропущен так как статус задачи  "+issueId+" не переведен в 'Решена'");}
  }

 @Test
      public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException, com.google.protobuf.ServiceException {
      int issueId=0000002;
      System.out.println("Номер задачи: "+issueId);
       try {
        skipIfNotFixed(issueId);
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue").withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
        System.out.println("Результат Теста testCreateIssue(): Пройден");
      }catch (SkipException e){System.out.println("Тест testCreateIssue() пропущен так как статус задачи  "+issueId+" не переведен в 'Решена'");}
    }
 }

