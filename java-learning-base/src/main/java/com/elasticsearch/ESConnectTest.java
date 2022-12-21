package com.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import java.io.IOException;

@Slf4j
public class ESConnectTest {

    private static final String INDEX_NAME = "customer";

    private static synchronized ElasticsearchClient makeConnection() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "wgNYaDk89tE9AwuQb*x3"));

        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200))
                .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

        RestClient restClient = builder.build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        ElasticsearchClient client = new ElasticsearchClient(transport);
        ElasticsearchAsyncClient asyncClient = new ElasticsearchAsyncClient(transport);

        return client;

    }

    public static void main(String[] args) throws IOException {

        ElasticsearchClient client = ESConnectTest.makeConnection();

        Product product = new Product("abc", "Bag", 42);

        IndexRequest<Object> indexRequest = new IndexRequest.Builder<>()
                .index("products")
                .id("abc")
                .document(product)
                .build();

        client.index(indexRequest);

        Product product1 = new Product("efg", "Bag", 43);

        client.index(builder -> builder
                .index("products")
                .id(product.getId())
                .document(product1));


    }

}
