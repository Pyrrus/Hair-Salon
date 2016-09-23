import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Arrays;
import java.util.*;

public class App {
  public static void main(String[] args) {

  	staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    String header = "templates/header.vtl";

    get("/", (request, response) -> {
      Map <String, Object> model = new HashMap <String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("title", "Adam Hair Salon");
      model.put("header", header);
      model.put("css", "");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    
   
  }
}
