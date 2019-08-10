package io.hedwig.tcexecutor.cicd.gitlab;

import lombok.Data;

@Data
public class GitLabConfig {

    private String accessToken;
    private String url;
    private String userName;
    private String password;
}
