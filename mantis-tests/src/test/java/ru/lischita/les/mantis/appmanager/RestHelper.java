package ru.lischita.les.mantis.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.lischita.les.mantis.model.Issue;

import java.io.IOException;
import java.util.Set;

public class RestHelper {
  private final ApplicationManager app;

  public RestHelper(ApplicationManager app) {
    this.app=app;
  }

  public Set<Issue> getIssues() throws IOException {
    String json= getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json")).returnContent().asString();// Если используем Rest запрос
    JsonElement parsed=new JsonParser().parse(json);
    JsonElement issues=parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
  }

  public static Executor getExecutor() {                        // Если используем Rest запрос
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
  }

  public int createIssue(Issue newIssue) throws IOException {
    String json=getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")  // Если используем Rest запрос
             .bodyForm(new BasicNameValuePair("subject",newIssue.getSubject()),
                     new BasicNameValuePair("description", newIssue.getDescription())))
             .returnContent().asString();
    JsonElement parsed=new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();

  }
}
