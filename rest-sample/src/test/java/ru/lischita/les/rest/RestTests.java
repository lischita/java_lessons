package ru.lischita.les.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestTests {

  @BeforeClass // Если используем RestAssured
  public void init(){
    RestAssured.authentication =RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490","");
  }

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues=getIssues();
    Issue newIssue=new Issue().withSubject("Test Issue").withDescription("TEST number 9");
    int issueId=createIssue(newIssue);
    Set<Issue> newIssues=getIssues();
    oldIssues.add(newIssue.withtId(issueId));
    Assert.assertEquals(newIssues,oldIssues);

  }

   private Set<Issue> getIssues() throws IOException {
   // String json= getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json")).returnContent().asString();// Если используем Rest запрос
     String json= RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();  // Если используем RestAssured
     JsonElement parsed=new JsonParser().parse(json);
     JsonElement issues=parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
  }

  private Executor getExecutor() {                        // Если используем Rest запрос
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
  }

  private int createIssue(Issue newIssue) throws IOException {
   // String json=getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")  // Если используем Rest запрос
   //         .bodyForm(new BasicNameValuePair("subject",newIssue.getSubject()),
   //                 new BasicNameValuePair("description", newIssue.getDescription())))
   //         .returnContent().asString();
    String json= RestAssured.given()    // Если используем RestAssured
            .param("subject", newIssue.getSubject())
            .param("description", newIssue.getDescription())
            .post("https://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsed=new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();

  }
}
