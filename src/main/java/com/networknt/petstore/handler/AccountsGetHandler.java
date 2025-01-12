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

import java.util.Deque;
import java.util.Map;

/**
 * This handler is responsible for getting all accounts from the database.
 */
public class AccountsGetHandler implements LightHttpHandler {

    
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        // HeaderMap requestHeaders = exchange.getRequestHeaders();
        // Map<String, Deque<String>> queryParameters = exchange.getQueryParameters();
        String responseBody = "[{\"accountNo\":123,\"userId\":\"sh35\",\"accountType\":\"C\",\"firstName\":\"Steve\",\"lastName\":\"Hu\",\"status\":\"O\"},{\"accountNo\":102,\"userId\":\"mc87\",\"accountType\":\"C\",\"firstName\":\"Michael\",\"lastName\":\"Carter\",\"status\":\"O\"},{\"accountNo\":103,\"userId\":\"kg17\",\"accountType\":\"C\",\"firstName\":\"Kevin\",\"lastName\":\"Grant\",\"status\":\"O\"},{\"accountNo\":456,\"userId\":\"sh35\",\"accountType\":\"B\",\"firstName\":\"Steve\",\"lastName\":\"Hu\",\"status\":\"O\"},{\"accountNo\":789,\"userId\":\"sh35\",\"accountType\":\"S\",\"firstName\":\"Steve\",\"lastName\":\"Hu\",\"status\":\"O\"},{\"accountNo\":101,\"userId\":\"sh35\",\"accountType\":\"S\",\"firstName\":\"Steve\",\"lastName\":\"Hu\",\"status\":\"C\"}]";
        exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        exchange.setStatusCode(HttpStatus.OK.value());
        exchange.getResponseSender().send(responseBody);
    }
}
