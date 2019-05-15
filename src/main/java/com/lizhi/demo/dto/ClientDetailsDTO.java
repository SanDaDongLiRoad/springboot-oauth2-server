package com.lizhi.demo.dto;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * @author 10133
 */
public class ClientDetailsDTO extends BaseClientDetails {

    public ClientDetailsDTO(String clientId, String clientSecret,String resourceIds, String scopes, String grantTypes, String authorities) {
        super(clientId, resourceIds, scopes, grantTypes, authorities);
        super.setClientSecret(clientSecret);
    }
}
