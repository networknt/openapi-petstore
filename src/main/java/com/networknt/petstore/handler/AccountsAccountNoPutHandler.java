package com.networknt.petstore.handler;

import com.networknt.body.BodyHandler;
import com.networknt.config.Config;

import com.networknt.handler.LightHttpHandler;
import com.networknt.http.HttpMethod;
import com.networknt.http.HttpStatus;
import com.networknt.http.MediaType;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HeaderMap;
import com.networknt.petstore.model.Account;
import java.util.Deque;
import java.util.Map;

/**
For more information on how to write business handlers, please check the link below.
https://doc.networknt.com/development/business-handler/rest/
*/
public class AccountsAccountNoPutHandler implements LightHttpHandler {

    
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        // HeaderMap requestHeaders = exchange.getRequestHeaders();
        // Map<String, Deque<String>> queryParameters = exchange.getQueryParameters();
        Map<String, Object> bodyMap = (Map<String, Object>)exchange.getAttachment(BodyHandler.REQUEST_BODY);
        Account requestBody = Config.getInstance().getMapper().convertValue(bodyMap, Account.class);
        String responseBody = "{}";
        exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        exchange.setStatusCode(HttpStatus.OK.value());
        exchange.getResponseSender().send(responseBody);
    }
}
