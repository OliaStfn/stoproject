package servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@WebFilter(filterName = "LanguageFilter")
public class LanguageFilter implements Filter {
    private Map<String, Properties> properties = new HashMap<String, Properties>();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        if (request.getParameterMap().containsKey("language")) {
            String language = request.getParameter("language");
            session.setAttribute("text", properties.get(language));
        } else {
            if (session.getAttribute("text") == null) {
                String lang = request.getHeader("Accept-Language").split(",")[0];
                if (properties.containsKey(lang))
                    session.setAttribute("text", properties.get(lang));
                else
                    session.setAttribute("text", properties.get("en"));
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        try {
            InputStream fis = null;
            String english = "language-en.properties";
            String ukrainian = "language-ua.properties";
            String russian = "language-ru.properties";

            Properties property = new Properties();
            fis = getClass().getClassLoader().getResourceAsStream(english);
            property.load(fis);
            properties.put("en", property);

            property = new Properties();
            fis = getClass().getClassLoader().getResourceAsStream(ukrainian);
            property.load(fis);
            properties.put("ua", property);

            property = new Properties();
            fis = getClass().getClassLoader().getResourceAsStream(russian);
            property.load(fis);
            properties.put("ru", property);
        } catch (IOException e) {
            System.out.println("Error with loading resources in language filter");
        }
    }
}
