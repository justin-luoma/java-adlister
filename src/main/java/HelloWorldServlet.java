import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloWorldServlet", urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet {
    private int viewCount = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        this.viewCount ++;
        res.setContentType("text/html");
        String resetCount = req.getParameter("reset");
        if (resetCount != null) this.viewCount = 0;
        PrintWriter out = res.getWriter();
//        out.println("<h1>Hello, World!</h1>");
        String name = req.getParameter("name");
        if (name == null) out.println("<h1>Hello, World!</h1>");
        else out.printf("<h1>Hello, %s!</h1>", name);

        out.printf("<h2>View Count: %d</h2>", viewCount);
        out.println("<p>Reset page count with url parameter 'reset'");
//        out.printf("<h1>Hello, %s</h1>", name.equals("null") ? "World" : name);
    }

}
