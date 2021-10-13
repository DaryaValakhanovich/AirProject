package servlets;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import utils.HibernateSessionFactoryUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/home"})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionFactory sessionFactory = null;
        try {
              Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Exception e) {
            System.out.println("Исключение!" + e);
        }
        System.out.println(sessionFactory);
        System.out.println("qqqqqqqqqqqqqq");
        System.out.println(HibernateSessionFactoryUtil.getSessionFactory());
        System.out.println(HibernateSessionFactoryUtil.getSessionFactory().openSession().toString());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/homeView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}