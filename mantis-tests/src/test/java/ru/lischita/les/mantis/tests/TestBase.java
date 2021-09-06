package ru.lischita.les.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.protobuf.ServiceException;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lischita.les.mantis.appmanager.ApplicationManager;
import ru.lischita.les.mantis.appmanager.RestHelper;
import ru.lischita.les.mantis.appmanager.SoapHelper;
import ru.lischita.les.mantis.model.Issue;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {
  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception
  {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_defaults_inc.php"),"config_defaults_inc.php","config_defaults_inc.php.bak");
  }

  boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, javax.xml.rpc.ServiceException, RemoteException {
    MantisConnectPortType mc = SoapHelper.getMantisConnect();
    IssueData data = mc.mc_issue_get("administrator", "grot", BigInteger.valueOf(issueId));
   if(data.getStatus().getName().equals("resolved"))
    {return  false;}
    else{return true;}
  }

  boolean isIssueOpenRest(int issueId) throws IOException {
    String json= RestHelper.getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/"+issueId+".json")).returnContent().asString();// Если используем Rest запрос
    JsonElement parsed=new JsonParser().parse(json);
    JsonElement  issuesJ=parsed.getAsJsonObject().get("issue");
    Set<Issue> issues = new Gson().fromJson(issuesJ, new TypeToken<Set<Issue>>(){}.getType()); // Преобразовали список в множество модельных объектов Issue (в нем так же будет всего один элемент - наша задача)
    Issue issue = issues.iterator().next(); // Получили из множества нашу задачу
    String dd=issue.getState_name();
    if (issue.getState_name().equals("Resolved")) {
      return false;
    } else { return true;}
  }



  public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, javax.xml.rpc.ServiceException, RemoteException {
    if (isIssueOpen(issueId)) {
       throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public void skipIfNotFixedRest(int issueId) throws IOException, ServiceException, javax.xml.rpc.ServiceException {
    if (isIssueOpenRest(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }




  @AfterSuite (alwaysRun = true)
  public void tearDown() throws Exception
  {
    app.ftp().restore("config_defaults_inc.php.bak","config_defaults_inc.php");
    app.stop();
  }

}
