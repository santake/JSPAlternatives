package jsp.alternative;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import jsp.alternative.bean.Product;

@WebServlet(urlPatterns= {"/thymeleaf"})
public class ThymeleafServlet extends HttpServlet {

    private static final long serialVersionUID = -1370262484897754860L;

    private TemplateEngine engine_;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
	this.engine_ = (TemplateEngine) config.getServletContext().getAttribute("thymeleafEngine");
    }
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String templateFileName = "product.html";
	
	String userName = "John";
	Product p0 = new Product("https://www.google.com/", "Google");
	Product p1 = new Product("https://www.yahoo.com/", "Yahoo!");
	List<Product> products = Arrays.asList(new Product[] {p0, p1});
	
	WebContext wc = new WebContext(req, resp, req.getServletContext());
	wc.setVariable("userName", userName);
	wc.setVariable("products", products);
	
	this.engine_.process(templateFileName, wc, resp.getWriter());
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	this.doGet(req, resp);
    }

    

}
