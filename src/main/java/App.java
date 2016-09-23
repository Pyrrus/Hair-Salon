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

    get("/salon",  (request, response) -> {
      Map <String, Object> model = new HashMap <String, Object>();
      model.put("template", "templates/salon.vtl");
      model.put("title", "List of the stylist work in Salon");
      model.put("stylists", Stylist.all());
      model.put("header", header);
      model.put("css", "");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/list",  (request, response) -> {
      Map <String, Object> model = new HashMap <String, Object>();
      model.put("template", "templates/list.vtl");
      model.put("title", "List of the stylist and client");
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("header", header);
      model.put("css", "");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
   
   	get("/stylist/new",  (request, response) -> {
      Map <String, Object> model = new HashMap <String, Object>();
      model.put("template", "templates/stylist-new.vtl");
      model.put("title", "Make new stylist for Adam's Hair Salon");
      model.put("header", header);
      model.put("css", "");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/new",  (request, response) -> {
      Map <String, Object> model = new HashMap <String, Object>();
      String name = request.queryParams("name");
      String style = request.queryParams("styles");
      String skills = request.queryParams("skills");
      Stylist newStylist = new Stylist(name, style, skills);
      newStylist.save();
      response.redirect("/list");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/stylist/:id/edit",  (request, response) -> {
    //   Map <String, Object> model = new HashMap <String, Object>();
    //   Stylist found = Stylist.find(Integer.parseInt(request.params(":id")));
    //   model.put("template", "templates/stylist-edit.vtl");
    //   model.put("title", "Edit the stylist: " + found.getName());
    //   model.put("stylist", found);
    //   model.put("header", header);
    //   model.put("css", "");
    // }, new VelocityTemplateEngine());

    // post("/stylist/:id/edit",  (request, response) -> {
    //   Map <String, Object> model = new HashMap <String, Object>();
    //   Stylist found = Stylist.find(Integer.parseInt(request.params(":id")));
    //   String name = request.queryParams("name");
    //   String style = request.queryParams("styles");
    //   String skills = request.queryParams("skills");
    //   found.update(name, style, skills);
    //   response.redirect("/list");
    // }, new VelocityTemplateEngine());

    // post("/stylist/:id/delete",  (request, response) -> {
    //   Map <String, Object> model = new HashMap <String, Object>();
    //   Stylist found = Stylist.find(Integer.parseInt(request.params(":id")));
    //   found.delete();
    //   response.redirect("/list");
    // }, new VelocityTemplateEngine());

    get("/client/new",  (request, response) -> {
      Map <String, Object> model = new HashMap <String, Object>();
      model.put("template", "templates/client-new.vtl");
      model.put("title", "Make new Client for Adam's Hair Salon");
      model.put("stylists", Stylist.all());
      model.put("header", header);
      model.put("css", "");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/new",  (request, response) -> {
      Map <String, Object> model = new HashMap <String, Object>();
      String name = request.queryParams("name");
      String like = request.queryParams("likes");
      int styleId = Integer.parseInt(request.queryParams("style"));
      Client newClient = new Client(name, like, styleId);
      newClient.save();
      response.redirect("/list");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/client/:id/edit",  (request, response) -> {
      Map <String, Object> model = new HashMap <String, Object>();
      Client found = Client.find(Integer.parseInt(request.params(":id")));
      model.put("template", "templates/client-edit.vtl");
      model.put("title", "Edit the client: " + found.getName());
      model.put("stylists", Stylist.all());
      model.put("client", found);
      model.put("header", header);
      model.put("css", "");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/:id/edit",  (request, response) -> {
      Map <String, Object> model = new HashMap <String, Object>();
      String name = request.queryParams("name");
      String like = request.queryParams("likes");
      int styleId = Integer.parseInt(request.queryParams("style"));
      Client found = Client.find(Integer.parseInt(request.params(":id")));
      found.update(name, like, styleId);
      response.redirect("/list");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/:id/delete",  (request, response) -> {
      Map <String, Object> model = new HashMap <String, Object>();
      Client found = Client.find(Integer.parseInt(request.params(":id")));
      found.delete();
      response.redirect("/list");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
