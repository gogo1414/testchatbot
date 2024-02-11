package com.testchatbot.domain.auth;

import com.testchatbot.domain.member.NaverMember;
import com.testchatbot.domain.member.OauthMember;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
public class NaverClient implements OauthClient{

    private final NaverClientInfo naverClientInfo;
    private static final RestTemplate restTemplate = new RestTemplate();


    public NaverClient(NaverClientInfo naverClientInfo) {
        this.naverClientInfo = naverClientInfo;
    }

    @Override
    public OauthProvider oauthProvider() {
        return OauthProvider.NAVER;
    }

    @Override
    public String getOauthLoginToken(OauthParams oauthParams) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = getAccessTokeninfo(oauthParams.makeBody());

        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(body, headers);

        NaverToken accessToken = restTemplate.postForObject(naverClientInfo.tokenUrl(), tokenRequest, NaverToken.class);

        return accessToken.getAccess_token();
    }

    @Override
    public OauthMember getMemberInfo(String accessToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> infoRequest = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(naverClientInfo.userUrl(), infoRequest, NaverMember.class);


    }

    public MultiValueMap<String, String> getAccessTokeninfo(MultiValueMap<String, String> body) {

        body.add("grant_type", naverClientInfo.grantType());
        body.add("client_id", naverClientInfo.clientId());
        body.add("client_secret", naverClientInfo.clientSecret());

        return body;
    }
}
