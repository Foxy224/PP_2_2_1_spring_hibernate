package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user, Car car) {
      sessionFactory.getCurrentSession().save(car);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> getByCarModelSeries(String model, int series) {

      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("SELECT c.id FROM Car c where model = :model and series = :series", Car.class);
      query.setParameter("model", model);
      query.setParameter("series", series);



      TypedQuery<User> query1 = sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u where u.car.id = :id", User.class);
      query1.setParameter("id", query.getSingleResult());

      return query1.getResultList();

   }

}
