package ru.lischita.les.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
  private CloseableHttpClient httpClient;
  private ApplicationManager app;

  public HttpSession(ApplicationManager app){
    this.app=app;
    httpClient= HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }

  public boolean login (String username,String password) throws IOException{
    HttpPost post=new HttpPost(app.getPropetry("web.baseURL")+"/login.php");
    List<NameValuePair> params=new ArrayList<>();
    params.add(new BasicNameValuePair("username",username));
    params.add(new BasicNameValuePair("password",password));
    params.add(new BasicNameValuePair("secure_session","on"));
    params.add(new BasicNameValuePair("return","index.php"));
    post.setEntity(new UrlEncodedFormEntity(params));
    CloseableHttpResponse response=httpClient.execute(post);
    String body=getTextFrom(response);
    return body.contains(String.format("<span class=\"user-info\">%s</span>",username));


  }

  public String getTextFrom(CloseableHttpResponse response) throws IOException{
    try{
      return EntityUtils.toString(response.getEntity());

    }finally {response.close(); }
  }

  public boolean isLoggenInAs(String username) throws IOException{
    HttpGet get=new HttpGet(app.getPropetry("web.baseURL")+"/login.php");
    CloseableHttpResponse response=httpClient.execute(get);
    String body=getTextFrom(response);
    return body.contains(String.format("<span class=\"user-info\">%s</span>",username));

  }


}
