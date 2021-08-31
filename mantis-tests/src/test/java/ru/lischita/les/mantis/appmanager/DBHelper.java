package ru.lischita.les.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.lischita.les.mantis.model.Users;
import ru.lischita.les.mantis.model.UsersData;

import java.util.List;

public class DBHelper {

  private final SessionFactory sessionFactory;

   public DBHelper ()  // создаем соединение с БД
  {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

  }

  public Users users(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UsersData> result = session.createQuery( "from UsersData" ).list(); // извелкаем объект OQL ( object query language )
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

}
