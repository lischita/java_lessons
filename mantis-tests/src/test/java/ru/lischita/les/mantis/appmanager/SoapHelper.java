package ru.lischita.les.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.lischita.les.mantis.model.Issue;
import ru.lischita.les.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
  private final ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app=app;

  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL("http://localhost/mantisbt-2.25.2/api/soap/mantisconnect.php?wsdl"));
    return mc;
  }


  public Set<Project> getProjects() throws MalformedURLException, RemoteException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "grot");
    return Arrays.asList(projects).stream().map((p)->new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());  //intValue() преобразует в из long int в int
  }



  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories("administrator", "grot", BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()),issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add("administrator", "grot", issueData);
    IssueData createdIssueData=mc.mc_issue_get("administrator", "grot", issueId);
    return new Issue().withId(createdIssueData.getId().intValue()).withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue()).withName(createdIssueData.getProject().getName()));

  }
}
