package spring.slack.api.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.*;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {
    private final static String        apiAddr = "https://slack.com/api/files.upload";
    private              WebTestClient webTestClient;

    @Value("${slack.api.token}")
    private String token;

    @Test
    public void contextLoads() {

    }

    @Test
    public void syncBodyTest() {
        try {
            FileSystemResource fileSystemResource = new FileSystemResource("attachment/한글테스트.txt");
            LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
            params.add("token", token);
            params.add("file", fileSystemResource);
            params.add("filename", fileSystemResource.getFilename());
            params.add("channels", "ops");

            webTestClient = WebTestClient.bindToServer().baseUrl(apiAddr).build();
            webTestClient.post()
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .syncBody(params)
                    .exchange()
                    .expectBody(Void.class)
                    .consumeWith(result -> {
                        log.debug("[jujiny] result : {}", result);
                    });
        } catch (Exception e) {
            log.error("error - {}", e.getCause());
        }
    }
}
