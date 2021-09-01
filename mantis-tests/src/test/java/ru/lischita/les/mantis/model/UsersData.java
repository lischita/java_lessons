package ru.lischita.les.mantis.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


@Entity // объявляет класс  привязанным к базе когда используем HBConnectionTest
@Table  (name="mantis_user_table") // Если бы таблица именовалась также как класс, то все бы присоединилось автомоматом, а так тут указываем имя таблицы огда используем HBConnectionTest
public class UsersData {
  @Id// используем эту аннтацию так как атрибут id использует как идентификатор когда используем HBConnectionTest
  private  int id=Integer.MAX_VALUE;
  private  String username;
  private  String email;
  private  String password;

  public int getId() { return id; }
  public String getUsername() {
    return username;
  }
  public String getEmail() {return email;}
  public String getPassword() {
    return password;
  }

  public UsersData withId(int id) {
        this.id = id;
        return this;
  }
  public UsersData withUsername(String username) {
    this.username = username;
    return this;
  }

  public UsersData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UsersData withPassword(String password) {
    this.password = password;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UsersData usersData = (UsersData) o;
    return id == usersData.id && Objects.equals(username, usersData.username) && Objects.equals(email, usersData.email) && Objects.equals(password, usersData.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password);
  }

  @Override
  public String toString() {
    return "UsersData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
