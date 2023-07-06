package pl.knap.shop.admin.product.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UploadedFilesNameUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "test test.png, test-test.png",
            "hello world.png, hello-world.png",
            "ąęśćżźńłó.png, aesczznlo.png",
            "Produkt 1.png, produkt-1.png",
            "Produkt - 1.png, produkt-1.png",
            "Produkt  1.png, produkt-1.png",
            "Produkt__1.png, produkt-1.png"
    })
    void shouldSlugifyFileName(String in, String out) {
        String fileName = UploadedFilesNameUtils.slugifyFileName(in);
        assertEquals(fileName, out);
    }
}