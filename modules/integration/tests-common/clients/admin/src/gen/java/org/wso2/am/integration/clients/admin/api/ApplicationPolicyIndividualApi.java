/*
 * WSO2 API Manager - Admin
 * This document specifies a **RESTful API** for WSO2 **API Manager** - **Admin Portal**. Please see [full OpenAPI Specification](https://raw.githubusercontent.com/wso2/carbon-apimgt/v6.7.206/components/apimgt/org.wso2.carbon.apimgt.rest.api.admin.v1/src/main/resources/admin-api.yaml) of the API which is written using [OAS 3.0](http://swagger.io/) specification.  # Authentication Our REST APIs are protected using OAuth2 and access control is achieved through scopes. Before you start invoking the the API you need to obtain an access token with the required scopes. This guide will walk you through the steps that you will need to follow to obtain an access token. First you need to obtain the consumer key/secret key pair by calling the dynamic client registration (DCR) endpoint. You can add your preferred grant types in the payload. A sample payload is shown below. ```   {   \"callbackUrl\":\"www.google.lk\",   \"clientName\":\"rest_api_admin\",   \"owner\":\"admin\",   \"grantType\":\"client_credentials password refresh_token\",   \"saasApp\":true   } ``` Create a file (payload.json) with the above sample payload, and use the cURL shown bellow to invoke the DCR endpoint. Authorization header of this should contain the base64 encoded admin username and password. **Format of the request** ```   curl -X POST -H \"Authorization: Basic Base64(admin_username:admin_password)\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://<host>:<servlet_port>/client-registration/v0.17/register ``` **Sample request** ```   curl -X POST -H \"Authorization: Basic YWRtaW46YWRtaW4=\" -H \"Content-Type: application/json\"   \\ -d @payload.json https://localhost:9443/client-registration/v0.17/register ``` Following is a sample response after invoking the above curl. ``` { \"clientId\": \"fOCi4vNJ59PpHucC2CAYfYuADdMa\", \"clientName\": \"rest_api_admin\", \"callBackURL\": \"www.google.lk\", \"clientSecret\": \"a4FwHlq0iCIKVs2MPIIDnepZnYMa\", \"isSaasApplication\": true, \"appOwner\": \"admin\", \"jsonString\": \"{\\\"grant_types\\\":\\\"client_credentials password refresh_token\\\",\\\"redirect_uris\\\":\\\"www.google.lk\\\",\\\"client_name\\\":\\\"rest_api_admin\\\"}\", \"jsonAppAttribute\": \"{}\", \"tokenType\": null } ``` Next you must use the above client id and secret to obtain the access token. We will be using the password grant type for this, you can use any grant type you desire. You also need to add the proper **scope** when getting the access token. All possible scopes for Admin REST API can be viewed in **OAuth2 Security** section of this document and scope for each resource is given in **authorizations** section of resource documentation. Following is the format of the request if you are using the password grant type. ``` curl -k -d \"grant_type=password&username=<admin_username>&password=<admin_passowrd>&scope=<scopes seperated by space>\" \\ -H \"Authorization: Basic base64(cliet_id:client_secret)\" \\ https://<host>:<gateway_port>/token ``` **Sample request** ``` curl https://localhost:8243/token -k \\ -H \"Authorization: Basic Zk9DaTR2Tko1OVBwSHVjQzJDQVlmWXVBRGRNYTphNEZ3SGxxMGlDSUtWczJNUElJRG5lcFpuWU1h\" \\ -d \"grant_type=password&username=admin&password=admin&scope=apim:admin apim:tier_view\" ``` Shown below is a sample response to the above request. ``` { \"access_token\": \"e79bda48-3406-3178-acce-f6e4dbdcbb12\", \"refresh_token\": \"a757795d-e69f-38b8-bd85-9aded677a97c\", \"scope\": \"apim:admin apim:tier_view\", \"token_type\": \"Bearer\", \"expires_in\": 3600 } ``` Now you have a valid access token, which you can use to invoke an API. Navigate through the API descriptions to find the required API, obtain an access token as described above and invoke the API with the authentication header. If you use a different authentication mechanism, this process may change.  # Try out in Postman If you want to try-out the embedded postman collection with \"Run in Postman\" option, please follow the guidelines listed below. * All of the OAuth2 secured endpoints have been configured with an Authorization Bearer header with a parameterized access token. Before invoking any REST API resource make sure you run the `Register DCR Application` and `Generate Access Token` requests to fetch an access token with all required scopes. * Make sure you have an API Manager instance up and running. * Update the `basepath` parameter to match the hostname and port of the APIM instance.  [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/f5ac2ca9fb22afef6ed6) 
 *
 * The version of the OpenAPI document: v2
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.admin.api;

import org.wso2.am.integration.clients.admin.ApiCallback;
import org.wso2.am.integration.clients.admin.ApiClient;
import org.wso2.am.integration.clients.admin.ApiException;
import org.wso2.am.integration.clients.admin.ApiResponse;
import org.wso2.am.integration.clients.admin.Configuration;
import org.wso2.am.integration.clients.admin.Pair;
import org.wso2.am.integration.clients.admin.ProgressRequestBody;
import org.wso2.am.integration.clients.admin.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.wso2.am.integration.clients.admin.api.dto.ApplicationThrottlePolicyDTO;
import org.wso2.am.integration.clients.admin.api.dto.ErrorDTO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationPolicyIndividualApi {
    private ApiClient localVarApiClient;

    public ApplicationPolicyIndividualApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ApplicationPolicyIndividualApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for throttlingPoliciesApplicationPolicyIdDelete
     * @param policyId Thorttle policy UUID  (required)
     * @param ifMatch Validator for conditional requests; based on ETag (Will be supported in future).  (optional)
     * @param ifUnmodifiedSince Validator for conditional requests; based on Last Modified header (Will be supported in future).  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Resource successfully deleted.  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call throttlingPoliciesApplicationPolicyIdDeleteCall(String policyId, String ifMatch, String ifUnmodifiedSince, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/throttling/policies/application/{policyId}"
            .replaceAll("\\{" + "policyId" + "\\}", localVarApiClient.escapeString(policyId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (ifMatch != null) {
            localVarHeaderParams.put("If-Match", localVarApiClient.parameterToString(ifMatch));
        }

        if (ifUnmodifiedSince != null) {
            localVarHeaderParams.put("If-Unmodified-Since", localVarApiClient.parameterToString(ifUnmodifiedSince));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call throttlingPoliciesApplicationPolicyIdDeleteValidateBeforeCall(String policyId, String ifMatch, String ifUnmodifiedSince, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'policyId' is set
        if (policyId == null) {
            throw new ApiException("Missing the required parameter 'policyId' when calling throttlingPoliciesApplicationPolicyIdDelete(Async)");
        }
        

        okhttp3.Call localVarCall = throttlingPoliciesApplicationPolicyIdDeleteCall(policyId, ifMatch, ifUnmodifiedSince, _callback);
        return localVarCall;

    }

    /**
     * Delete an Application Throttling policy
     * Deletes an application level throttling policy. 
     * @param policyId Thorttle policy UUID  (required)
     * @param ifMatch Validator for conditional requests; based on ETag (Will be supported in future).  (optional)
     * @param ifUnmodifiedSince Validator for conditional requests; based on Last Modified header (Will be supported in future).  (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Resource successfully deleted.  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public void throttlingPoliciesApplicationPolicyIdDelete(String policyId, String ifMatch, String ifUnmodifiedSince) throws ApiException {
        throttlingPoliciesApplicationPolicyIdDeleteWithHttpInfo(policyId, ifMatch, ifUnmodifiedSince);
    }

    /**
     * Delete an Application Throttling policy
     * Deletes an application level throttling policy. 
     * @param policyId Thorttle policy UUID  (required)
     * @param ifMatch Validator for conditional requests; based on ETag (Will be supported in future).  (optional)
     * @param ifUnmodifiedSince Validator for conditional requests; based on Last Modified header (Will be supported in future).  (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Resource successfully deleted.  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> throttlingPoliciesApplicationPolicyIdDeleteWithHttpInfo(String policyId, String ifMatch, String ifUnmodifiedSince) throws ApiException {
        okhttp3.Call localVarCall = throttlingPoliciesApplicationPolicyIdDeleteValidateBeforeCall(policyId, ifMatch, ifUnmodifiedSince, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Delete an Application Throttling policy (asynchronously)
     * Deletes an application level throttling policy. 
     * @param policyId Thorttle policy UUID  (required)
     * @param ifMatch Validator for conditional requests; based on ETag (Will be supported in future).  (optional)
     * @param ifUnmodifiedSince Validator for conditional requests; based on Last Modified header (Will be supported in future).  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Resource successfully deleted.  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call throttlingPoliciesApplicationPolicyIdDeleteAsync(String policyId, String ifMatch, String ifUnmodifiedSince, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = throttlingPoliciesApplicationPolicyIdDeleteValidateBeforeCall(policyId, ifMatch, ifUnmodifiedSince, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for throttlingPoliciesApplicationPolicyIdGet
     * @param policyId Thorttle policy UUID  (required)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resource (Will be supported in future).  (optional)
     * @param ifModifiedSince Validator for conditional requests; based on Last Modified header of the formerly retrieved variant of the resource (Will be supported in future).  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Policy returned  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Last-Modified - Date and time the resource has been modifed the last time. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Content-Type - The content type of the body.  <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call throttlingPoliciesApplicationPolicyIdGetCall(String policyId, String ifNoneMatch, String ifModifiedSince, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/throttling/policies/application/{policyId}"
            .replaceAll("\\{" + "policyId" + "\\}", localVarApiClient.escapeString(policyId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (ifNoneMatch != null) {
            localVarHeaderParams.put("If-None-Match", localVarApiClient.parameterToString(ifNoneMatch));
        }

        if (ifModifiedSince != null) {
            localVarHeaderParams.put("If-Modified-Since", localVarApiClient.parameterToString(ifModifiedSince));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call throttlingPoliciesApplicationPolicyIdGetValidateBeforeCall(String policyId, String ifNoneMatch, String ifModifiedSince, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'policyId' is set
        if (policyId == null) {
            throw new ApiException("Missing the required parameter 'policyId' when calling throttlingPoliciesApplicationPolicyIdGet(Async)");
        }
        

        okhttp3.Call localVarCall = throttlingPoliciesApplicationPolicyIdGetCall(policyId, ifNoneMatch, ifModifiedSince, _callback);
        return localVarCall;

    }

    /**
     * Get an Application Throttling Policy
     * Retrieves an application throttling policy. 
     * @param policyId Thorttle policy UUID  (required)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resource (Will be supported in future).  (optional)
     * @param ifModifiedSince Validator for conditional requests; based on Last Modified header of the formerly retrieved variant of the resource (Will be supported in future).  (optional)
     * @return ApplicationThrottlePolicyDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Policy returned  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Last-Modified - Date and time the resource has been modifed the last time. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Content-Type - The content type of the body.  <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public ApplicationThrottlePolicyDTO throttlingPoliciesApplicationPolicyIdGet(String policyId, String ifNoneMatch, String ifModifiedSince) throws ApiException {
        ApiResponse<ApplicationThrottlePolicyDTO> localVarResp = throttlingPoliciesApplicationPolicyIdGetWithHttpInfo(policyId, ifNoneMatch, ifModifiedSince);
        return localVarResp.getData();
    }

    /**
     * Get an Application Throttling Policy
     * Retrieves an application throttling policy. 
     * @param policyId Thorttle policy UUID  (required)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resource (Will be supported in future).  (optional)
     * @param ifModifiedSince Validator for conditional requests; based on Last Modified header of the formerly retrieved variant of the resource (Will be supported in future).  (optional)
     * @return ApiResponse&lt;ApplicationThrottlePolicyDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Policy returned  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Last-Modified - Date and time the resource has been modifed the last time. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Content-Type - The content type of the body.  <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ApplicationThrottlePolicyDTO> throttlingPoliciesApplicationPolicyIdGetWithHttpInfo(String policyId, String ifNoneMatch, String ifModifiedSince) throws ApiException {
        okhttp3.Call localVarCall = throttlingPoliciesApplicationPolicyIdGetValidateBeforeCall(policyId, ifNoneMatch, ifModifiedSince, null);
        Type localVarReturnType = new TypeToken<ApplicationThrottlePolicyDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get an Application Throttling Policy (asynchronously)
     * Retrieves an application throttling policy. 
     * @param policyId Thorttle policy UUID  (required)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resource (Will be supported in future).  (optional)
     * @param ifModifiedSince Validator for conditional requests; based on Last Modified header of the formerly retrieved variant of the resource (Will be supported in future).  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Policy returned  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Last-Modified - Date and time the resource has been modifed the last time. Used by caches, or in conditional requests (Will be supported in future).  <br>  * Content-Type - The content type of the body.  <br>  </td></tr>
        <tr><td> 304 </td><td> Not Modified. Empty body because the client has already the latest version of the requested resource (Will be supported in future).  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. The requested media type is not supported. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call throttlingPoliciesApplicationPolicyIdGetAsync(String policyId, String ifNoneMatch, String ifModifiedSince, final ApiCallback<ApplicationThrottlePolicyDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = throttlingPoliciesApplicationPolicyIdGetValidateBeforeCall(policyId, ifNoneMatch, ifModifiedSince, _callback);
        Type localVarReturnType = new TypeToken<ApplicationThrottlePolicyDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for throttlingPoliciesApplicationPolicyIdPut
     * @param policyId Thorttle policy UUID  (required)
     * @param contentType Media type of the entity in the body. Default is application/json.  (required)
     * @param applicationThrottlePolicyDTO Policy object that needs to be modified  (required)
     * @param ifMatch Validator for conditional requests; based on ETag (Will be supported in future).  (optional)
     * @param ifUnmodifiedSince Validator for conditional requests; based on Last Modified header (Will be supported in future).  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Policy updated.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional request.  <br>  * Last-Modified - Date and time the resource has been modifed the last time. Used by caches, or in conditional reuquests.  <br>  * Location - The URL of the newly created resource.  <br>  * Content-Type - The content type of the body.  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call throttlingPoliciesApplicationPolicyIdPutCall(String policyId, String contentType, ApplicationThrottlePolicyDTO applicationThrottlePolicyDTO, String ifMatch, String ifUnmodifiedSince, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = applicationThrottlePolicyDTO;

        // create path and map variables
        String localVarPath = "/throttling/policies/application/{policyId}"
            .replaceAll("\\{" + "policyId" + "\\}", localVarApiClient.escapeString(policyId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (contentType != null) {
            localVarHeaderParams.put("Content-Type", localVarApiClient.parameterToString(contentType));
        }

        if (ifMatch != null) {
            localVarHeaderParams.put("If-Match", localVarApiClient.parameterToString(ifMatch));
        }

        if (ifUnmodifiedSince != null) {
            localVarHeaderParams.put("If-Unmodified-Since", localVarApiClient.parameterToString(ifUnmodifiedSince));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "OAuth2Security" };
        return localVarApiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call throttlingPoliciesApplicationPolicyIdPutValidateBeforeCall(String policyId, String contentType, ApplicationThrottlePolicyDTO applicationThrottlePolicyDTO, String ifMatch, String ifUnmodifiedSince, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'policyId' is set
        if (policyId == null) {
            throw new ApiException("Missing the required parameter 'policyId' when calling throttlingPoliciesApplicationPolicyIdPut(Async)");
        }
        
        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            throw new ApiException("Missing the required parameter 'contentType' when calling throttlingPoliciesApplicationPolicyIdPut(Async)");
        }
        
        // verify the required parameter 'applicationThrottlePolicyDTO' is set
        if (applicationThrottlePolicyDTO == null) {
            throw new ApiException("Missing the required parameter 'applicationThrottlePolicyDTO' when calling throttlingPoliciesApplicationPolicyIdPut(Async)");
        }
        

        okhttp3.Call localVarCall = throttlingPoliciesApplicationPolicyIdPutCall(policyId, contentType, applicationThrottlePolicyDTO, ifMatch, ifUnmodifiedSince, _callback);
        return localVarCall;

    }

    /**
     * Update an Application Throttling policy
     * Updates an existing application level throttling policy. Upon a succesfull update, you will receive the updated application policy as the response. 
     * @param policyId Thorttle policy UUID  (required)
     * @param contentType Media type of the entity in the body. Default is application/json.  (required)
     * @param applicationThrottlePolicyDTO Policy object that needs to be modified  (required)
     * @param ifMatch Validator for conditional requests; based on ETag (Will be supported in future).  (optional)
     * @param ifUnmodifiedSince Validator for conditional requests; based on Last Modified header (Will be supported in future).  (optional)
     * @return ApplicationThrottlePolicyDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Policy updated.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional request.  <br>  * Last-Modified - Date and time the resource has been modifed the last time. Used by caches, or in conditional reuquests.  <br>  * Location - The URL of the newly created resource.  <br>  * Content-Type - The content type of the body.  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public ApplicationThrottlePolicyDTO throttlingPoliciesApplicationPolicyIdPut(String policyId, String contentType, ApplicationThrottlePolicyDTO applicationThrottlePolicyDTO, String ifMatch, String ifUnmodifiedSince) throws ApiException {
        ApiResponse<ApplicationThrottlePolicyDTO> localVarResp = throttlingPoliciesApplicationPolicyIdPutWithHttpInfo(policyId, contentType, applicationThrottlePolicyDTO, ifMatch, ifUnmodifiedSince);
        return localVarResp.getData();
    }

    /**
     * Update an Application Throttling policy
     * Updates an existing application level throttling policy. Upon a succesfull update, you will receive the updated application policy as the response. 
     * @param policyId Thorttle policy UUID  (required)
     * @param contentType Media type of the entity in the body. Default is application/json.  (required)
     * @param applicationThrottlePolicyDTO Policy object that needs to be modified  (required)
     * @param ifMatch Validator for conditional requests; based on ETag (Will be supported in future).  (optional)
     * @param ifUnmodifiedSince Validator for conditional requests; based on Last Modified header (Will be supported in future).  (optional)
     * @return ApiResponse&lt;ApplicationThrottlePolicyDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Policy updated.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional request.  <br>  * Last-Modified - Date and time the resource has been modifed the last time. Used by caches, or in conditional reuquests.  <br>  * Location - The URL of the newly created resource.  <br>  * Content-Type - The content type of the body.  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ApplicationThrottlePolicyDTO> throttlingPoliciesApplicationPolicyIdPutWithHttpInfo(String policyId, String contentType, ApplicationThrottlePolicyDTO applicationThrottlePolicyDTO, String ifMatch, String ifUnmodifiedSince) throws ApiException {
        okhttp3.Call localVarCall = throttlingPoliciesApplicationPolicyIdPutValidateBeforeCall(policyId, contentType, applicationThrottlePolicyDTO, ifMatch, ifUnmodifiedSince, null);
        Type localVarReturnType = new TypeToken<ApplicationThrottlePolicyDTO>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Update an Application Throttling policy (asynchronously)
     * Updates an existing application level throttling policy. Upon a succesfull update, you will receive the updated application policy as the response. 
     * @param policyId Thorttle policy UUID  (required)
     * @param contentType Media type of the entity in the body. Default is application/json.  (required)
     * @param applicationThrottlePolicyDTO Policy object that needs to be modified  (required)
     * @param ifMatch Validator for conditional requests; based on ETag (Will be supported in future).  (optional)
     * @param ifUnmodifiedSince Validator for conditional requests; based on Last Modified header (Will be supported in future).  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK. Policy updated.  </td><td>  * ETag - Entity Tag of the response resource. Used by caches, or in conditional request.  <br>  * Last-Modified - Date and time the resource has been modifed the last time. Used by caches, or in conditional reuquests.  <br>  * Location - The URL of the newly created resource.  <br>  * Content-Type - The content type of the body.  <br>  </td></tr>
        <tr><td> 400 </td><td> Bad Request. Invalid request or validation error. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found. The specified resource does not exist. </td><td>  -  </td></tr>
        <tr><td> 412 </td><td> Precondition Failed. The request has not been performed because one of the preconditions is not met. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call throttlingPoliciesApplicationPolicyIdPutAsync(String policyId, String contentType, ApplicationThrottlePolicyDTO applicationThrottlePolicyDTO, String ifMatch, String ifUnmodifiedSince, final ApiCallback<ApplicationThrottlePolicyDTO> _callback) throws ApiException {

        okhttp3.Call localVarCall = throttlingPoliciesApplicationPolicyIdPutValidateBeforeCall(policyId, contentType, applicationThrottlePolicyDTO, ifMatch, ifUnmodifiedSince, _callback);
        Type localVarReturnType = new TypeToken<ApplicationThrottlePolicyDTO>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
