package com.andremidea.hystrixservice.service;

import com.andremidea.hystrixservice.client.GitHubClient;
import com.andremidea.hystrixservice.client.GitHubFallbackClient;
import com.andremidea.hystrixservice.model.Gists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
@Lazy(false)
public class GitHubService {

    @Autowired
    private GitHubClient gitHubClient;

    private GitHubClient gitHubFallbackClient = new GitHubFallbackClient();

    @HystrixCommand(fallbackMethod = "publicFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public List<Gists> publicGists() {
        return gitHubClient.publicGists();
    }

    public List<Gists> publicFallback() {
        return gitHubFallbackClient.publicGists();
    }

    @HystrixCommand(fallbackMethod = "userFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public List<Gists> userGists(String username) {
        return gitHubClient.userGists(username);
    }

    public List<Gists> userFallback(String username) {
        return gitHubFallbackClient.userGists(username);
    }

}
