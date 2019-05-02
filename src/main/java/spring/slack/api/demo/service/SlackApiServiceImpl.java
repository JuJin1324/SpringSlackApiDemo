package spring.slack.api.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Yoo Ju Jin(yjj@hanuritien.com)
 * Created Date : 2019-05-02
 */

@Service
@Slf4j
public class SlackApiServiceImpl implements SlackApiService {
    private final static String apiAddr = "https://slack.com/api";
    private WebClient webClient;

    @Value("${slack.api.token}")
    private String token;
    @Value("${slack.api.channels")
    private String channels;

    public SlackApiServiceImpl() {
        webClient = WebClient.builder().baseUrl(apiAddr).build();
    }

    @Override
    public void uploadWithFile(String filePath) {
        try {
            FileSystemResource fileSystemResource = new FileSystemResource(filePath);
            LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
            params.add("token", token);
            params.add("file", fileSystemResource);
            params.add("filename", fileSystemResource.getFilename());
            params.add("channels", channels);

            webClient.post()
                    .uri("/files.upload")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .syncBody(params)
                    .exchange()
                    .subscribe(result -> {
                        log.info("result code : {}", result.statusCode());
                    });
        } catch (Exception e) {
            log.error("error - {}", e.getCause());
        }
    }
}
