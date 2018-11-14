package jsp.alternative;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@WebListener
public class ServerConfig implements ServletContextListener {

    
    public void contextInitialized(ServletContextEvent sce) {
	ServletContext scntxt = sce.getServletContext();
	
	//Freemarker configuration:
	Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
	cfg.setDefaultEncoding("utf-8");
	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	cfg.setLogTemplateExceptions(false);
	cfg.setWrapUncheckedExceptions(true);
	try {
	    String templatePath = scntxt.getRealPath("/WEB-INF/templates/freemarker/");
	    cfg.setDirectoryForTemplateLoading(new File(templatePath));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	scntxt.setAttribute("freemarkerConfig", cfg);
	
	
	// Thymeleaf configuration:
	ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(scntxt);
	resolver.setTemplateMode(TemplateMode.HTML);
	resolver.setPrefix("/WEB-INF/templates/thymeleaf/");
	resolver.setSuffix(".html");
	resolver.setCacheable(false);
	resolver.setCharacterEncoding("utf-8");
	TemplateEngine engine = new TemplateEngine();
	engine.setTemplateResolver(resolver);
	scntxt.setAttribute("thymeleafEngine", engine);
    }
    

}
