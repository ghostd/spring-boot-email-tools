package it.ozimov.springboot.templating.mail.service;

import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Map;

public class TemplatingTestUtils {

    public static final String TEMPLATE = "email_template.html";
    public static final String NAME = "Titus";
    public static final Map<String, Object> MODEL_OBJECT = new ImmutableMap.Builder<String, Object>()
            .put("name", NAME)
            .build();

    public static String getExpectedBody() throws IOException {
        final File file = new File(ThymeleafTemplateServiceTest.class.getClassLoader()
                .getResource("templates" + File.separator + TEMPLATE).getFile());
        final String template = readFile(file);
        return template.replace("<em th:text=\"${{name}}\">", "<em>" + NAME);
    }

    private static String readFile(final File file) throws IOException {
        final byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, Charset.forName("UTF-8"));
    }


}
