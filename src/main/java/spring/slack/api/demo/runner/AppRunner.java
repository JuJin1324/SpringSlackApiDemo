package spring.slack.api.demo.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.slack.api.demo.service.SlackApiService;

/**
 * Created by Yoo Ju Jin(yjj@hanuritien.com)
 * Created Date : 2019-05-02
 */

@Component
@Slf4j
public class AppRunner implements CommandLineRunner {
    @Autowired
    SlackApiService slackApiService;

    @Override
    public void run(String... args) throws Exception {
        slackApiService.uploadWithFile("attachment/한글테스트.txt");
    }
}
