/*
 * WSO2 API Manager - Publisher API
 * This document specifies a **RESTful API** for WSO2 **API Manager** - **Publisher**.  # Authentication Our REST APIs are protected using OAuth2 and access control is achieved through scopes. Before you start invoking the the API you need to obtain an access token with the required scopes. This guide will walk you through the steps that you will need to follow to obtain an access token. First you need to obtain the consumer key/secret key pair by calling the dynamic client registration (DCR) endpoint. You can add your preferred grant types in the payload. A Sample payload is shown below. ```   {   \"callbackUrl\":\"www.google.lk\",   \"clientName\":\"rest_api_publisher\",   \"owner\":\"admin\",   \"grantType\":\"client_credentials password refresh_token\",   \"saasApp\":true   } ``` Create a file (payload.json) with the above sample payload, and use the cURL shown bellow to invoke the DCR endpoint. Authorization header of this should contain the base64 encoded admin username and password. **Format of the request** ```   curl -X POST -H \"Authorization: Basic Base64(admin_username:admin_password)\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://<host>:<servlet_port>/client-registration/v0.17/register ``` **Sample request** ```   curl -X POST -H \"Authorization: Basic YWRtaW46YWRtaW4=\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://localhost:9443/client-registration/v0.17/register ``` Following is a sample response after invoking the above curl. ``` { \"clientId\": \"fOCi4vNJ59PpHucC2CAYfYuADdMa\", \"clientName\": \"rest_api_publisher\", \"callBackURL\": \"www.google.lk\", \"clientSecret\": \"a4FwHlq0iCIKVs2MPIIDnepZnYMa\", \"isSaasApplication\": true, \"appOwner\": \"admin\", \"jsonString\": \"{\\\"grant_types\\\":\\\"client_credentials password refresh_token\\\",\\\"redirect_uris\\\":\\\"www.google.lk\\\",\\\"client_name\\\":\\\"rest_api123\\\"}\", \"jsonAppAttribute\": \"{}\", \"tokenType\": null } ``` Next you must use the above client id and secret to obtain the access token. We will be using the password grant type for this, you can use any grant type you desire. You also need to add the proper **scope** when getting the access token. All possible scopes for publisher REST API can be viewed in **OAuth2 Security** section of this document and scope for each resource is given in **authorization** section of resource documentation. Following is the format of the request if you are using the password grant type. ``` curl -k -d \"grant_type=password&username=<admin_username>&password=<admin_passowrd&scope=<scopes seperated by space>\" \\ -H \"Authorization: Basic base64(cliet_id:client_secret)\" \\ https://<host>:<gateway_port>/token ``` **Sample request** ``` curl https://localhost:8243/token -k \\ -H \"Authorization: Basic Zk9DaTR2Tko1OVBwSHVjQzJDQVlmWXVBRGRNYTphNEZ3SGxxMGlDSUtWczJNUElJRG5lcFpuWU1h\" \\ -d \"grant_type=password&username=admin&password=admin&scope=apim:api_view apim:api_create\" ``` Shown below is a sample response to the above request. ``` { \"access_token\": \"e79bda48-3406-3178-acce-f6e4dbdcbb12\", \"refresh_token\": \"a757795d-e69f-38b8-bd85-9aded677a97c\", \"scope\": \"apim:api_create apim:api_view\", \"token_type\": \"Bearer\", \"expires_in\": 3600 } ``` Now you have a valid access token, which you can use to invoke an API. Navigate through the API descriptions to find the required API, obtain an access token as described above and invoke the API with the authentication header. If you use a different authentication mechanism, this process may change.  # Try out in Postman If you want to try-out the embedded postman collection with \"Run in Postman\" option, please follow the guidelines listed below. * All of the OAuth2 secured endpoints have been configured with an Authorization Bearer header with a parameterized access token. Before invoking any REST API resource make sure you run the `Register DCR Application` and `Generate Access Token` requests to fetch an access token with all required scopes. * Make sure you have an API Manager instance up and running. * Update the `basepath` parameter to match the hostname and port of the APIM instance.  [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/a09044034b5c3c1b01a9) 
 *
 * The version of the OpenAPI document: v2
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.publisher.api.v1.dto;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonCreator;
/**
* MockResponsePayloadInfoDTO
*/

public class MockResponsePayloadInfoDTO {
        public static final String SERIALIZED_NAME_PATH = "path";
        @SerializedName(SERIALIZED_NAME_PATH)
            private String path;

        public static final String SERIALIZED_NAME_CONTENT = "content";
        @SerializedName(SERIALIZED_NAME_CONTENT)
            private String content;

        public static final String SERIALIZED_NAME_VERB = "verb";
        @SerializedName(SERIALIZED_NAME_VERB)
            private String verb;


        public MockResponsePayloadInfoDTO path(String path) {
        
        this.path = path;
        return this;
        }

    /**
        * path of the resource
    * @return path
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "/menu", value = "path of the resource")
    
    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }


        public MockResponsePayloadInfoDTO content(String content) {
        
        this.content = content;
        return this;
        }

    /**
        * new modified code
    * @return content
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "var accept = \"\\\"\"+mc.getProperty('AcceptHeader')+\"\\\"\"; var responseCode = mc.getProperty('query.param.responseCode'); var responseCodeStr = \"\\\"\"+responseCode+\"\\\"\"; var responses = [];  if (!responses[200]) {  responses [200] = []; } responses[200][\"application/json\"] =  [ {   \"price\" : \"string\",   \"description\" : \"string\",   \"name\" : \"string\",   \"image\" : \"string\" } ]  /_*if (!responses[304]) {   responses[304] = []; } responses[304][\"application/(json or xml)\"] = {}/<>*_/  if (!responses[406]) {  responses [406] = []; } responses[406][\"application/json\"] =  {   \"message\" : \"string\",   \"error\" : [ {     \"message\" : \"string\",     \"code\" : 0   } ],   \"description\" : \"string\",   \"code\" : 0,   \"moreInfo\" : \"string\" }  responses[501] = []; responses[501][\"application/json\"] = { \"code\" : 501, \"description\" : \"Not Implemented\"} responses[501][\"application/xml\"] = <response><code>501</code><description>Not Implemented</description></response>;  if (!responses[responseCode]) {  responseCode = 501; }  if (responseCode == null) {  responseCode = 200;  responseCodeStr = \"200\"; }  if (accept == null || !responses[responseCode][accept]) {  accept = \"application/json\"; }  if (accept === \"application/json\") {  mc.setProperty('CONTENT_TYPE', 'application/json');  mc.setProperty('HTTP_SC', responseCodeStr);  mc.setPayloadJSON(responses[responseCode][\"application/json\"]); } else if (accept === \"application/xml\") {  mc.setProperty('CONTENT_TYPE', 'application/xml');  mc.setProperty('HTTP_SC', responseCodeStr);  mc.setPayloadXML(responses[responseCode][\"application/xml\"]); }", value = "new modified code")
    
    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


        public MockResponsePayloadInfoDTO verb(String verb) {
        
        this.verb = verb;
        return this;
        }

    /**
        * Get verb
    * @return verb
    **/
        @javax.annotation.Nullable
      @ApiModelProperty(example = "POST", value = "")
    
    public String getVerb() {
        return verb;
    }


    public void setVerb(String verb) {
        this.verb = verb;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
        return true;
        }
        if (o == null || getClass() != o.getClass()) {
        return false;
        }
            MockResponsePayloadInfoDTO mockResponsePayloadInfo = (MockResponsePayloadInfoDTO) o;
            return Objects.equals(this.path, mockResponsePayloadInfo.path) &&
            Objects.equals(this.content, mockResponsePayloadInfo.content) &&
            Objects.equals(this.verb, mockResponsePayloadInfo.verb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, content, verb);
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append("class MockResponsePayloadInfoDTO {\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    verb: ").append(toIndentedString(verb)).append("\n");
sb.append("}");
return sb.toString();
}

/**
* Convert the given object to string with each line indented by 4 spaces
* (except the first line).
*/
private String toIndentedString(Object o) {
if (o == null) {
return "null";
}
return o.toString().replace("\n", "\n    ");
}

}

