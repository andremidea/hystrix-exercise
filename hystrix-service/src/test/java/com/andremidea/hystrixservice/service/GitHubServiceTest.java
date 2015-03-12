package com.andremidea.hystrixservice.service;

import com.andremidea.hystrixservice.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@IntegrationTest
public class GitHubServiceTest {

    @Autowired
    private GitHubService gitHubService;

    @Test
    public void shouldReturnListOfGists() throws Exception {
        assertThat(gitHubService.publicGists(), hasSize(greaterThan(0)));
    }

    @Test
    public void shouldReturnEmptyListForUserAndremidea() throws Exception {
        assertThat(gitHubService.userGists("andremidea"), hasSize(0));
    }

    @Test
    public void shouldReturnAListOfGistsForUncleBob() throws Exception {
        assertThat(gitHubService.userGists("unclebob"), hasSize(greaterThan(10)));
    }
}