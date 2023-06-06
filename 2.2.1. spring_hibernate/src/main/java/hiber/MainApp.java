package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car lamaba = new Car("lamborgini", 2222);
      Car bmb = new Car("bmv", 111);
      Car ld = new Car("lam", 123);
      Car fer = new Car("ferari", 222);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", lamaba), lamaba);
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", bmb), bmb);
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", fer), fer);
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", ld), ld);



      List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println(user.toString());
      }

      List<User> userCar = userService.getByCarModelSeries("bmv", 111);
      for (User user : userCar) {
         System.out.println(user.toString());
      }

      context.close();
   }
}
