package log;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InitServlet", urlPatterns = "/InitServlet", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     ** Este metodo se iniciara antes que todo
    *
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.setProperty("log4j.configurationFile", "resources/log4j2.xml");
        System.out.println("____________________________________________________");

        //req.getRequestDispatcher(jsp).forward(req, resp);
        //LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        //File file = new File("resources/log4j2.xml");
        //context.setConfigLocation(file.toURI());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsp = resp.encodeRedirectURL("/");
        resp.sendRedirect(jsp);
    }
}
