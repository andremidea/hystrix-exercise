package com.andremidea.hystrixservice.controllers;

import com.andremidea.hystrixservice.model.Gists;
import com.andremidea.hystrixservice.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private GitHubService gitHubService;


    @RequestMapping("/")
    @ResponseBody
    public List<Gists> publicGists() {
        return gitHubService.publicGists();
    }

    @RequestMapping("/{user}")
    @ResponseBody
    public List<Gists> userGists(@PathVariable String user) {
        return gitHubService.userGists(user);
    }

}
