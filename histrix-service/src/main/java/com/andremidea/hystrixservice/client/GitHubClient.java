package com.andremidea.hystrixservice.client;

import com.andremidea.hystrixservice.model.Gists;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "https://api.github.com")
public interface GitHubClient {

    @RequestMapping(method = RequestMethod.GET, value = "/gists")
    List<Gists> publicGists();

    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}/gists")
    List<Gists> userGists(@RequestParam("username") String username);

}