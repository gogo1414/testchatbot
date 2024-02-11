package com.testchatbot.web.service;

import com.testchatbot.domain.auth.OauthClient;
import com.testchatbot.domain.auth.OauthParams;
import com.testchatbot.domain.auth.OauthProvider;
import com.testchatbot.domain.member.OauthMember;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RequestOauthInfoService {

    private final Map<OauthProvider, OauthClient> clients;

    public RequestOauthInfoService(List<OauthClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(OauthClient::oauthProvider, Function.identity())
        );
    }

    public OauthMember request(OauthParams oauthParams) {
        OauthClient client = clients.get(oauthParams.oauthProvider());
        String accessToken = client.getOauthLoginToken(oauthParams);

        return client.getMemberInfo(accessToken);
    }
}
