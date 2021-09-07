package ru.lischita.les.mantis.model;

import java.util.Objects;

public class Issue {
  private int id;
  private String summary;
  private String description;
  private Project project;
  private String subject;  // использую для Rest запроса
  private String state_name; // использую для Rest запроса

  public int getId() {
    return id;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Issue issue = (Issue) o;
    return id == issue.id && Objects.equals(summary, issue.summary) && Objects.equals(description, issue.description) && Objects.equals(project, issue.project) && Objects.equals(subject, issue.subject);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, summary, description, project, subject);
  }

  public String getSubject() {
    return subject;
  }

  public Issue withSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getState_name() {
    return state_name;
  }

  public Issue withState_name(String state_name) {
    this.state_name = state_name;
    return this;
  }

  public String getSummary() {
    return summary;
  }

  public Issue withSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Project getProject() {
    return project;
  }

  public Issue withProject(Project project) {
    this.project = project;
    return this;
  }
}
