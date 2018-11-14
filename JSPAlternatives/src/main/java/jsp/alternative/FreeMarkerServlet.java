package jsp.alternative;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import jsp.alternative.bean.Product;

@WebServlet(urlPatterns= {"/freemarker"})
public class FreeMarkerServlet extends HttpServlet {
    private Logger LOGGER = LogManager.getLogger();

    private static final long serialVersionUID = 3908L;
    
    private Template template_;

    @Override
    public void init(ServletConfig config) throws ServletException {
	ServletContext sc = config.getServletContext();
	Configuration cfg = (Configuration) sc.getAttribute("freemarkerConfig");
	
	try {
	    this.template_ = cfg.getTemplate("product.ftlh");
	} catch (TemplateNotFoundException e) {
	    e.printStackTrace();
	    LOGGER.error(e);
	} catch (MalformedTemplateNameException e) {
	    e.printStackTrace();
	    LOGGER.error(e);
	} catch (ParseException e) {
	    e.printStackTrace();
	    LOGGER.error(e);
	} catch (IOException e) {
	    e.printStackTrace();
	    LOGGER.error(e);
	}
    }

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String userName = "John";
	Product p0 = new Product("https://www.google.com/", "Google");
	Product p1 = new Product("https://www.yahoo.com/", "Yahoo!");
	List<Product> products = Arrays.asList(new Product[] {p0, p1});

	Map<String, Object> map = new LinkedHashMap<>();
	map.put("userName", userName);
	map.put("products", products);
	
	try {
	    PrintWriter writer = resp.getWriter();
	    this.template_.process(map, writer);
	} catch (TemplateException e) {
	    e.printStackTrace();
	    LOGGER.error(e);
	    throw new ServletException(e);
	}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	this.doGet(req, resp);
    }

    
    

}
