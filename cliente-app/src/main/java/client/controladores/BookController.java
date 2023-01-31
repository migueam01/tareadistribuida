package client.controladores;

import client.config.ThymeleafTemplateEngine;
import client.modelos.Author;
import client.modelos.Book;
import client.servicios.AuthorService;
import client.servicios.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static client.config.Util.render;
import static spark.Spark.*;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;


    public void init() {
        path("/view", () -> {
            get("/books", (req, res) -> {

                Map<String, Object> model = new HashMap<>();
                model.put("books", bookService.getAll()); // arrays de books

                return render(model, "books");
            });

            get("/book/nuevo", (req, res) -> {

                Map<String, Object> model = new HashMap<>();
                var authors = authorService.getAll();
                var book = new Book();
                book.setId(-1);
                model.put("book", book);
                model.put("authors", authors);

                return render(model, "agregar_book");
            });

            get("/book/editar/:id", (req, res) -> {

                var book = bookService.findById(req.params(":id"));
                var authors = authorService.getAll();


                var model = new HashMap<String, Object>();
                model.put("book", book);
                model.put("authors", authors);

                return render(model, "agregar_book");
            });

            post("/book", (req, res) -> {
                String body = req.body();
                var book = new Book();
                var author = new Author();
                var id = "0";
                for (String str : body.split("&")) {
                    var index = str.indexOf("=");
                    var key = str.substring(0, index);
                    var value = str.substring(index + 1).replace("+", " ");
                    if (key.equals("title")) {
                        book.setTitle(value);
                    } else if (key.equals("isbn")) {
                        book.setIsbn(value);
                    } else if (key.equals("price")) {
                        book.setPrice(Double.parseDouble(value));
                    } else if (key.equals("id")) {
                        id = value;
                    } else if (key.equals("author_id")) {
                        author.setId(Integer.parseInt(value));
                    }
                }
                book.setAuthor(author);
                if (id.equals("-1")) {
                    bookService.save(book);
                } else {
                    bookService.update(Integer.parseInt(id), book);
                }
                res.redirect("/view/books");
                return null;

            }, new ThymeleafTemplateEngine());

            get("/book/eliminar/:id", (req, res) -> {
                bookService.delete(Integer.parseInt(req.params(":id")));
                res.redirect("/view/books");
                return null;

            }, new ThymeleafTemplateEngine());
        });
    }
}
