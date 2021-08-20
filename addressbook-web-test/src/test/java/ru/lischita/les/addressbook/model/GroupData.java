package ru.lischita.les.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@XStreamAlias("group")// указываем что в xml использовать тег group"
@Entity // объявляет класс GroupData привязанным к базе когда используем HBConnectionTest
@Table  (name="group_list") // Если бы таблица именовалась также как класс, то все бы присоединилось автомоматом, а так тут указываем имя таблицы огда используем HBConnectionTest
public class GroupData {
  @XStreamOmitField   // указываем что следующее поле не записывать в xml
  @Id // используем эту аннтацию так как атрибут id использует как идентификатор когда используем HBConnectionTest
  @Column (name = "group_id") //привязка к столбцу таблицы когда используем HBConnectionTest, если бы название столбца совпадало с названием атрибуто, то это не нужно
  private  int id=Integer.MAX_VALUE;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  @Column (name = "group_name")
  private  String name;
  @Expose
  @Column (name = "group_header")
  @Type(type="text")
  private  String header;
  @Expose
  @Column (name = "group_footer")
  @Type(type="text")//когда используем HBConnectionTest иногда нужно ему подсказать с преобразованием типа
  private  String footer;

  /*  был до 5 занятия
   public GroupData(String name, String header, String footer) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public GroupData(int id, String name, String header, String footer) {
    this.id = id;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }*/

  public int getId() { return id; }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public GroupData withId(int id) {
        this.id = id;
        return this;
  }

  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id && Objects.equals(name, groupData.name);
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", header='" + header + '\'' +
            ", footer='" + footer + '\'' +
            '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
