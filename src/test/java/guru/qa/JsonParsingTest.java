package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.BookStore;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonParsingTest {
    @Test
    void jsonParsTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File fileJson = new File("src/test/resources/jsonExample.json");
        BookStore bookStore = mapper.readValue(fileJson, BookStore.class);

        assertThat(bookStore.id).isEqualTo(1);
        assertThat(bookStore.store).isEqualTo("First");
        assertThat(bookStore.address).isEqualTo("Lenina str.");
        assertThat(bookStore.books.get(0).name).isEqualTo("Intresting");
        assertThat(bookStore.books.get(0).author).isEqualTo("Carl");
        assertThat(bookStore.books.get(1).name).isEqualTo("Strange");
        assertThat(bookStore.books.get(1).author).isEqualTo("Marks");
    }
}