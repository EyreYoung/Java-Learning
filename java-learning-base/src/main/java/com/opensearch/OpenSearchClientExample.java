//package com.opensearch;
//
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.opensearch.client.RestClient;
//import org.opensearch.client.json.jackson.JacksonJsonpMapper;
//import org.opensearch.client.opensearch.OpenSearchClient;
//import org.opensearch.client.opensearch.core.*;
//import org.opensearch.client.transport.OpenSearchTransport;
//import org.opensearch.client.transport.rest_client.RestClientTransport;
//import org.opensearch.client.opensearch.indices.*;
//
//import java.io.IOException;
//
//public class OpenSearchClientExample {
//
//    static class IndexData {
//        private String firstName;
//        private String lastName;
//
//        public IndexData(String firstName, String lastName) {
//            this.firstName = firstName;
//            this.lastName = lastName;
//        }
//
//        public String getFirstName() {
//            return firstName;
//        }
//
//        public void setFirstName(String firstName) {
//            this.firstName = firstName;
//        }
//
//        public String getLastName() {
//            return lastName;
//        }
//
//        public void setLastName(String lastName) {
//            this.lastName = lastName;
//        }
//
//        @Override
//        public String toString() {
//            return String.format("IndexData{first name='%s', last name='%s'}", firstName, lastName);
//        }
//    }
//
//    public static void main(String[] args) {
//        RestClient restClient = null;
//        try{
//            System.setProperty("javax.net.ssl.trustStore", "/Users/yudyang/server.keystore");
//            System.setProperty("javax.net.ssl.trustStorePassword", "123456");
//
//            //Only for demo purposes. Don't specify your credentials in code.
//            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            credentialsProvider.setCredentials(AuthScope.ANY,
//                    new UsernamePasswordCredentials("admin", "admin"));
//
//            //Initialize the client with SSL and TLS enabled
//            restClient = RestClient.builder(new HttpHost("localhost", 9200, "https")).
//                    setHttpClientConfigCallback(httpClientBuilder ->
//                            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)).build();
//            OpenSearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
//            OpenSearchClient client = new OpenSearchClient(transport);
//            System.out.println("Over!");
//
//            //Create the index
//            String index = "sample-index";
//            CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder().index(index).build();
//            client.indices().create(createIndexRequest);
//
//            //Add some settings to the index
//            IndexSettings indexSettings = new IndexSettings.Builder().autoExpandReplicas("0-all").build();
//            IndexSettings settingsBody = new IndexSettings.Builder().settings(indexSettings).build();
//            PutIndicesSettingsRequest putSettingsRequest = new PutIndicesSettingsRequest.Builder().index(index).settings(settingsBody).build();
//            client.indices().putSettings(putSettingsRequest);
//
//            //Index some data
//            IndexData indexData = new IndexData("first_name", "Bruce");
//            IndexRequest<IndexData> indexRequest = new IndexRequest.Builder<IndexData>().index(index).id("1").document(indexData).build();
//            client.index(indexRequest);
//
//            //Search for the document
//            SearchResponse<IndexData> searchResponse = client.search(s -> s.index(index), IndexData.class);
//            for (int i = 0; i< searchResponse.hits().hits().size(); i++) {
//                System.out.println(searchResponse.hits().hits().get(i).source());
//            }
//
//            //Delete the document
//            client.delete(b -> b.index(index).id("1"));
//
//            // Delete the index
//            DeleteIndexRequest deleteRequest = new DeleteIndexRequest.Builder().index(index).build();
//            DeleteIndexResponse deleteResponse = client.indices().delete(deleteRequest);
//            System.out.println(deleteResponse);
//
//        } catch (IOException e){
//            System.out.println(e.toString());
//        } finally {
//            try {
//                if (restClient != null) {
//                    restClient.close();
//                }
//            } catch (IOException e) {
//                System.out.println(e.toString());
//            }
//        }
//    }
//}
//
