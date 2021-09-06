package ru.lischita.les.github;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {

  @Test
  public void testCommits() throws IOException {
    Github github=new RtGithub(" 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");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("lischita", "java_lessons")).commits();
    for (RepoCommit commit:commits.iterate(new ImmutableBiMap.Builder<String,String>().build())){
      System.out.println(commit);
      System.out.println(new RepoCommit.Smart(commit).message());
    }


  }



}
