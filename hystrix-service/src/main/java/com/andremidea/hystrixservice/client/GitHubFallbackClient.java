package com.andremidea.hystrixservice.client;

import com.andremidea.hystrixservice.model.Gists;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static java.util.Arrays.asList;

public class GitHubFallbackClient implements GitHubClient {

    @Override
    public List<Gists> publicGists() {
        return asList(new Gists("From Fallback"));
    }

    @Override
    public List<Gists> userGists(@RequestParam("username") String username) {
        return asList(new Gists("From Fallback"));
    }
}
