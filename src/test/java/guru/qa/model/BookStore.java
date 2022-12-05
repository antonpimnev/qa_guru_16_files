package guru.qa.model;

import java.util.ArrayList;

public class BookStore {
    public int id;
    public String store;
    public ArrayList<Books> books;
    public String address;

    public static class Books {
        public String name;
        public String author;
        }
}
