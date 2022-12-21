//package com.opensearch;
//
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.opensearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.opensearch.action.delete.DeleteRequest;
//import org.opensearch.action.delete.DeleteResponse;
//import org.opensearch.action.get.GetRequest;
//import org.opensearch.action.get.GetResponse;
//import org.opensearch.action.index.IndexRequest;
//import org.opensearch.action.index.IndexResponse;
//import org.opensearch.action.support.master.AcknowledgedResponse;
//import org.opensearch.client.RequestOptions;
//import org.opensearch.client.RestClient;
//import org.opensearch.client.RestClientBuilder;
//import org.opensearch.client.RestHighLevelClient;
//import org.opensearch.client.indices.CreateIndexRequest;
//import org.opensearch.client.indices.CreateIndexResponse;
//import org.opensearch.common.settings.Settings;
//
//import java.io.IOException;
//import java.util.HashMap;
//
//public class RESTClientSample {
//
//    public static void main(String[] args) throws IOException {
//        //Point to keystore with appropriate certificates for security.
//        System.setProperty("javax.net.ssl.trustStore", "/Users/yudyang/server.keystore");
//        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
//
//        //Establish credentials to use basic authentication.
//        //Only for demo purposes. Don't specify your credentials in code.
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//
//        credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials("admin", "admin"));
//
//        //Create a client.
//        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "https"))
//                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//                    @Override
//                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
//                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//                    }
//                });
//        RestHighLevelClient client = new RestHighLevelClient(builder);
//
//        //Create a non-default index with custom settings and mappings.
//        CreateIndexRequest createIndexRequest = new CreateIndexRequest("custom-index");
//
//        createIndexRequest.settings(Settings.builder() //Specify in the settings how many shards you want in the index.
//                .put("index.number_of_shards", 4)
//                .put("index.number_of_replicas", 3)
//        );
//        //Create a set of maps for the index's mappings.
//        HashMap<String, String> typeMapping = new HashMap<String,String>();
//        typeMapping.put("type", "integer");
//        HashMap<String, Object> ageMapping = new HashMap<String, Object>();
//        ageMapping.put("age", typeMapping);
//        HashMap<String, Object> mapping = new HashMap<String, Object>();
//        mapping.put("properties", ageMapping);
//        createIndexRequest.mapping(mapping);
//        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//
//        //Adding data to the index.
//        IndexRequest request = new IndexRequest("custom-index"); //Add a document to the custom-index we created.
//        request.id("1"); //Assign an ID to the document.
//
//        HashMap<String, String> stringMapping = new HashMap<String, String>();
//        stringMapping.put("message:", "Testing Java REST client");
//        request.source(stringMapping); //Place your content into the index's source.
//        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
//
//        //Getting back the document
//        GetRequest getRequest = new GetRequest("custom-index", "1");
//        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
//
//        System.out.println(response.getSourceAsString());
//
//        //Delete the document
//        DeleteRequest deleteDocumentRequest = new DeleteRequest("custom-index", "1"); //Index name followed by the ID.
//        DeleteResponse deleteResponse = client.delete(deleteDocumentRequest, RequestOptions.DEFAULT);
//
//        //Delete the index
//        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("custom-index"); //Index name.
//        AcknowledgedResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
//
//        client.close();
//    }
//
//}
