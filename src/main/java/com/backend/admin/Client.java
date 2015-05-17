package com.backend.admin;

import com.backend.admin.dto.UserDTO;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import java.io.InputStream;

public class Client {

    private ObjectMapper mapper;
    private String baseUrl="http://localhost:8088/web/rest";
    private static final int NUMBER_OF_RETRIES = 3;

    private static final String USER_ALL = "/users";
    private static final String USER = "/users/id/%s";

    public Client() {
        initJsonMapper();
    }

    public UserDTO getUserById(Long userId) {
        String resource = String.format(baseUrl + USER, userId);
        System.out.println("resource "+resource);
        GetMethod method = createGetMethod(resource);

        try {
            // Execute the method.
            int statusCode = getClient().executeMethod(method);
            if (statusCode == HttpStatus.SC_NOT_FOUND) {
                return null;
            }
            assertResponseCodeOK(method, statusCode);
            // Read the response body.
            InputStream body = method.getResponseBodyAsStream();

            return parseJson(body, UserDTO.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            method.releaseConnection();
        }
    }

    private HttpClient getClient() {
        return new HttpClient();
    }

    private GetMethod createGetMethod(String resource) {
        GetMethod method = new GetMethod(resource);
        // Provide custom retry handler
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(NUMBER_OF_RETRIES, false));
        method.addRequestHeader("Connection", "close");
        return method;
    }

    private void assertResponseCodeOK(HttpMethodBase method, int statusCode) throws HttpException {
        if (statusCode != HttpStatus.SC_OK) {
            throw new HttpException("Method failed: " + method.getStatusLine());
        }
    }

    public static void main(String ... args){
        Client client = new Client();
        System.out.println(client.getUserById(1l));
    }

    private <T> T parseJson(InputStream data, Class<T> clazz) {
        try {
            return mapper.readValue(data, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void initJsonMapper() {
        mapper = new ObjectMapper();
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
        mapper.getDeserializationConfig().setAnnotationIntrospector(introspector);
        mapper.getSerializationConfig().setAnnotationIntrospector(introspector);
    }
}
