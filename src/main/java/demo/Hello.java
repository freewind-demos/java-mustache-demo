package demo;

import com.github.freewind.lostlist.Lists;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.github.freewind.lostlist.Lists.list;

public class Hello {

    public static void main(String[] args) throws IOException {
        Page page = new Page(list(
                new Item("Item 1", "$19.99", list("good", "item", "new"), list(new Feature("New!"), new Feature("Awesome!"))),
                new Item("Item 2", "$29.99", list("old", "item", "bad"), list(new Feature("Old."), new Feature("Ugly.")))
        ));
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(mf.getReader("templates/hello.html"), "hello");
        mustache.execute(new PrintWriter(System.out), page).flush();
    }

}

class Page {
    final List<Item> items;
    public Page(List<Item> items) {
        this.items = items;
    }
}

class Feature {
    final String description;
    public Feature(String description) {
        this.description = description;
    }
}

class Item {
    final String name;
    final String price;
    final List<String> keywords;
    final List<Feature> features;
    public Item(String name, String price, List<String> keywords, List<Feature> features) {
        this.name = name;
        this.price = price;
        this.keywords = keywords;
        this.features = features;
    }
}
