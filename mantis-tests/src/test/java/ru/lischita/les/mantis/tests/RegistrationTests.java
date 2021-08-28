package ru.lischita.les.mantis.tests;

import org.testng.annotations.Test;

import java.util.regex.MatchResult;

public class RegistrationTests extends TestBase {


  @Test
  public void testRegistration(){
    app.registration().start("user1","user1@localhost.localdomain");

  }



}
