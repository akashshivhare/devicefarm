package adp.mobile.automation.utilities;

import javax.ws.rs.core.Cookie;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ServiceCallResponse {

    private static String sResponse;
    private WebResource baseResourceFull;
    private Client restClient;

    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private static final String DELETE_METHOD = "DELETE";
    private ClientResponse clientResponse;

    /**
     * Default constractor.
     */
    public ServiceCallResponse() {
        super();
        this.restClient = Client.create();
    }

    /**
     * Get the response.
     *
     * @return response
     */
    public static String getsResponse() {
        return sResponse;
    }

    /**
     * set the response.
     *
     * @param sResponse response
     */
    public static void setsResponse(String sResponse) {
        ServiceCallResponse.sResponse = sResponse;
    }


    public String getResponse(String baseUrl,String methodType, String requestMediaType, String responseMediaType, String cookies, String postData) {
        String tempResponse = null;
        WebResource.Builder builder;
        baseResourceFull = restClient.resource(baseUrl);

        if(cookies != ""){
            builder = baseResourceFull.getRequestBuilder().cookie(new Cookie(cookies.split("=")[0], cookies.split("=")[1]));
        }else{
            builder = baseResourceFull.getRequestBuilder();
        }
        switch (methodType) {

            case GET_METHOD:
                clientResponse = builder.accept(responseMediaType).get(ClientResponse.class);
                System.out.println("GET Request: {}"+ baseResourceFull.getURI());
                break;

            case PUT_METHOD:
                clientResponse = builder.type(requestMediaType).accept(responseMediaType)
                        .put(ClientResponse.class, postData);
                System.out.println("PUT Request: {}"+ baseResourceFull.getURI());
                System.out.println("PUT Data: {}"+ postData);
                break;

            case POST_METHOD:
                clientResponse = builder.type(requestMediaType).accept(responseMediaType)
                        .post(ClientResponse.class, postData);
                System.out.println("POST Request URL:  "+ baseResourceFull.getURI());
                System.out.println("POST Request Data: "+ postData);
                break;

            case DELETE_METHOD:
                clientResponse = builder.type(requestMediaType).accept(responseMediaType)
                        .delete(ClientResponse.class);
                System.out.println("DELETE Request: "+ baseResourceFull.getURI());

                break;

            default:
                tempResponse = "BAD METHOD";
        }

        tempResponse = clientResponse.getEntity(String.class);
        setsResponse(tempResponse);
        String responseInfo = "POST Response Data: " + getsResponse().toString();
        System.out.println(responseInfo);
        return tempResponse;

    }
    
    public String getResponseForDocufree(String baseUrl,String methodType, String requestMediaType, String responseMediaType, String cookies, String autorization, String postData) {
        String tempResponse = null;
        WebResource.Builder builder;
        baseResourceFull = restClient.resource(baseUrl);

        if(cookies != ""){
            builder = baseResourceFull.getRequestBuilder().cookie(new Cookie(cookies.split("=")[0], cookies.split("=")[1]));
        }else{
            builder = baseResourceFull.getRequestBuilder();
        }
        switch (methodType) {

            case GET_METHOD:
                clientResponse = builder.accept(responseMediaType).header("Authorization",autorization).get(ClientResponse.class);
                System.out.println("GET Request: {}"+ baseResourceFull.getURI());
                break;

            case PUT_METHOD:
                clientResponse = builder.type(requestMediaType).accept(responseMediaType).header("Authorization",autorization)
                        .put(ClientResponse.class, postData);
                System.out.println("PUT Request: {}"+ baseResourceFull.getURI());
                System.out.println("PUT Data: {}"+ postData);
                break;

            case POST_METHOD:
                clientResponse = builder.type(requestMediaType).accept(responseMediaType).header("Authorization",autorization)
                        .post(ClientResponse.class, postData);
                System.out.println("POST Request URL:  "+ baseResourceFull.getURI());
                System.out.println("POST Request Data: "+ postData);
                break;

            case DELETE_METHOD:
                clientResponse = builder.type(requestMediaType).accept(responseMediaType).header("Authorization",autorization)
                        .delete(ClientResponse.class);
                System.out.println("DELETE Request: "+ baseResourceFull.getURI());

                break;

            default:
                tempResponse = "BAD METHOD";
        }

        tempResponse = clientResponse.getEntity(String.class);
        setsResponse(tempResponse);
        String responseInfo = "POST Response Data: " + getsResponse().toString();
        System.out.println(responseInfo);
        return tempResponse;

    }
    
}
