import client.config.AppConfig;
import client.controladores.AuthorController;
import client.controladores.BookController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spark.Spark;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class Main {
    public static void main(String[] args) {
        var projectDir = System.getProperty("user.dir");
        staticFiles.externalLocation(projectDir + "/src/main/resources/templates");
//        staticFiles.location("/templates");
        port(8089);
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(BookController.class).init();
        context.getBean(AuthorController.class).init();
    }
}
