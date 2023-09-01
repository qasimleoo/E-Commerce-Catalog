package com.practice.project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class SQLScriptRunner implements ApplicationRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        executeSQLScript("create_roles.sql");
        executeSQLScript("create_users.sql");
        executeSQLScript("create_user_role.sql");
    }

    private void executeSQLScript(String scriptName) {
        try {
            ClassPathResource resource = new ClassPathResource(scriptName);
            InputStreamReader reader = new InputStreamReader(resource.getInputStream());
            String sqlScript = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));
            jdbcTemplate.execute(sqlScript);
        } catch (IOException e) {
            throw new RuntimeException("Error executing SQL script: " + scriptName, e);
        }
    }
}
