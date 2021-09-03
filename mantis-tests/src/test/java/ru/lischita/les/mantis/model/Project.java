package ru.lischita.les.mantis.model;

public class Project {

  private  int Id;
  private  String name;

  public Project withId(int id) {
    Id = id;
    return this;
  }

  public Project  withName(String name) {
    this.name = name;
    return this;
  }
  public int getId() {
    return Id;

  }

  public String getName() {
    return name;
  }
}
