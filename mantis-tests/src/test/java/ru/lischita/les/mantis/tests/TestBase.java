package ru.lischita.les.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.protobuf.ServiceException;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.lischita.les.mantis.appmanager.ApplicationManager;
import ru.lischita.les.mantis.appmanager.RestHelper;
import ru.lischita.les.mantis.appmanager.SoapHelper;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

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
    JsonElement  issues=parsed.getAsJsonObject().get("issue");

    // if(issues.getStatus().getName().equals("resolved"))
 //   {return  false;}
  //  else{return true;}
    return true;
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
