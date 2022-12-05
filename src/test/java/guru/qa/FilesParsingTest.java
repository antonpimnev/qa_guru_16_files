package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FilesParsingTest {
    ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    void zipParseTest() throws Exception {
        try (
                InputStream is = cl.getResourceAsStream("example.zip");
                ZipInputStream zis = new ZipInputStream(is)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("Digital Documents—All In One Place");
                } else if (entry.getName().contains(".xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(2).getCell(3).getStringCellValue()).contains("boss");
                } else if (entry.getName().contains(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(4)[1]).contains("2017");
                }
            }
        }
    }
}