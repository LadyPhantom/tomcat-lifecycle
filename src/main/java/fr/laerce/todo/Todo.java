package fr.laerce.todo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Projet tomcat-lifecycle
 * Pour LAERCE SAS
 * <p>
 * Créé le  28/11/2017.
 *
 * @author fred
 */
public class Todo extends HttpServlet {
    List<String> todos = new ArrayList<String>();

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        // Après appel de la classe (via requete Post), on crée une liste de strings
        // dans laquelle on récupère les parametres de la session active.
        // (Chaque parametre = 1 string de la liste)
        List<String> todoSession = (List<String>) session.getAttribute("todos");

        // On vérifie si la session possède bel et bien des paramètres.
        // Si ce n'est pas le cas, on en crée une.
        if(todoSession == null){
            todoSession = new ArrayList<String>();
            session.setAttribute("todos", todoSession);
        }

        // attribut 'todos' = paramètres session (existante)?

        // todoSession = session (eventuelement existante) chargé
        //

        // On ajoute un nouvel élément aux paramètres déja existant
        String item = httpServletRequest.getParameter("afaire");
        todoSession.add(item);


        todos.add(item);

        // On fini en redirigeant la requete vers la classe (doGet)
        httpServletResponse.sendRedirect("/todo");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        int i;
        HttpSession session = httpServletRequest.getSession();
        List<String> todoSession = (List<String>) session.getAttribute("todos");
        if(todoSession == null){
            todoSession = new ArrayList<String>();
            session.setAttribute("todos", todoSession);
        }
        PrintWriter pw = httpServletResponse.getWriter();
        pw.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Servlet Lifecycle</title>\n" +
                "</head>\n" +
                "<body>\n<h1>Todos</h1>\n");
        pw.println("<h2>Global</h2>\n");
        pw.println("<ul>\n");
        for(i = 0; i < todos.size(); i++){
            System.out.println("<li>"+todos.get(i)+"</li>\n");
        }
        pw.println("</ul>\n");
        pw.println("<h2>Session</h2>\n");
        pw.println("<ul>\n");
        for(i = 0; i < todoSession.size(); i++){
            System.out.println("<li>"+todoSession.get(i)+"</li>\n");
        }
        pw.println("</ul>\n");
        pw.println("</body>\n</html>");
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}