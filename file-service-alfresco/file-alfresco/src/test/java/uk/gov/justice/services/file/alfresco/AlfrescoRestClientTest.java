package uk.gov.justice.services.file.alfresco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import java.io.InputStream;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlfrescoRestClientTest {

    @InjectMocks
    private AlfrescoRestClient alfrescoRestClient;

    @Mock
    private Client mockClient;

    @Mock
    private WebTarget mockWebTarget;

    @Mock
    private Invocation.Builder mockBuilder;

    @Mock
    private Response mockResponse;

    @Mock
    private InputStream mockInputStream;

    private static final String BASE_URI = "http://localhost:8080/alfresco";
    private static final String TEST_URI = "/test/path";
    private MultivaluedHashMap<String, Object> headers;

    @BeforeEach
    void setUp() {
        alfrescoRestClient.alfrescoBaseUri = BASE_URI;
        alfrescoRestClient.proxyType = "none";
        headers = new MultivaluedHashMap<>();
    }

    @Test
    void post() {
        // given
        Entity<String> entity = Entity.json("test-entity");

        when(mockClient.target(BASE_URI + TEST_URI)).thenReturn(mockWebTarget);
        when(mockWebTarget.request(APPLICATION_JSON_TYPE)).thenReturn(mockBuilder);
        when(mockBuilder.headers(headers)).thenReturn(mockBuilder);
        when(mockBuilder.post(entity)).thenReturn(mockResponse);

        // Mock the client() method to return our mock client
        alfrescoRestClient = spy(alfrescoRestClient);
        doReturn(mockClient).when(alfrescoRestClient).client();

        // when
        Response response = alfrescoRestClient.post(TEST_URI, APPLICATION_JSON_TYPE, headers, entity);

        // then
        assertNotNull(response);
        verify(mockClient).target(BASE_URI + TEST_URI);
        verify(mockWebTarget).request(APPLICATION_JSON_TYPE);
        verify(mockBuilder).headers(headers);
        verify(mockBuilder).post(entity);
    }

    @Test
    void delete() {
        // given
        when(mockClient.target(BASE_URI + TEST_URI)).thenReturn(mockWebTarget);
        when(mockWebTarget.request(APPLICATION_JSON_TYPE)).thenReturn(mockBuilder);
        when(mockBuilder.headers(headers)).thenReturn(mockBuilder);
        when(mockBuilder.delete()).thenReturn(mockResponse);

        // Mock the client() method to return our mock client
        alfrescoRestClient = spy(alfrescoRestClient);
        doReturn(mockClient).when(alfrescoRestClient).client();

        // when
        Response response = alfrescoRestClient.delete(TEST_URI, APPLICATION_JSON_TYPE, headers);

        // then
        assertNotNull(response);
        verify(mockClient).target(BASE_URI + TEST_URI);
        verify(mockWebTarget).request(APPLICATION_JSON_TYPE);
        verify(mockBuilder).headers(headers);
        verify(mockBuilder).delete();
    }

    @Test
    void getAsInputStream() {
        // given
        when(mockClient.target(BASE_URI + TEST_URI)).thenReturn(mockWebTarget);
        when(mockWebTarget.request(APPLICATION_JSON_TYPE)).thenReturn(mockBuilder);
        when(mockBuilder.headers(headers)).thenReturn(mockBuilder);
        when(mockBuilder.get(InputStream.class)).thenReturn(mockInputStream);

        // Mock the client() method to return our mock client
        alfrescoRestClient = spy(alfrescoRestClient);
        doReturn(mockClient).when(alfrescoRestClient).client();

        // when
        InputStream result = alfrescoRestClient.getAsInputStream(TEST_URI, APPLICATION_JSON_TYPE, headers);

        // then
        assertNotNull(result);
        assertEquals(mockInputStream, result);
        verify(mockClient).target(BASE_URI + TEST_URI);
        verify(mockWebTarget).request(APPLICATION_JSON_TYPE);
        verify(mockBuilder).headers(headers);
        verify(mockBuilder).get(InputStream.class);
    }

    @Test
    void getClientWithoutProxy() {
        // given
        alfrescoRestClient.proxyType = "none";

        // when
        Client client = alfrescoRestClient.client();

        // then
        assertNotNull(client);
        assertSame(client, alfrescoRestClient.client(), "Should return same client instance");
    }

    @Test
    void getClientWithProxy() {
        // given
        alfrescoRestClient.proxyType = "http";
        alfrescoRestClient.proxyHostname = "proxy.example.com";
        alfrescoRestClient.proxyPort = "8080";

        // when
        Client client1 = alfrescoRestClient.client();
        Client client2 = alfrescoRestClient.client();

        // then
        assertNotNull(client1);
        assertSame(client1, client2, "Should return same proxy client instance");
    }

    @Test
    void getClientCached() {
        // given
        alfrescoRestClient.proxyType = "none";

        // when
        Client client1 = alfrescoRestClient.client();
        Client client2 = alfrescoRestClient.client();
        Client client3 = alfrescoRestClient.client();

        // then
        assertNotNull(client1);
        assertSame(client1, client2, "Client should be cached");
        assertSame(client2, client3, "Client should be cached");
    }
    @Test
    void getClientCachedWithProxy() {
        // given
        alfrescoRestClient.proxyType = "http";
        alfrescoRestClient.proxyHostname = "proxy.example.com";
        alfrescoRestClient.proxyPort = "8080";

        // when
        Client client1 = alfrescoRestClient.client();
        Client client2 = alfrescoRestClient.client();
        Client client3 = alfrescoRestClient.client();

        // then
        assertNotNull(client1);
        assertSame(client1, client2, "Client should be cached");
        assertSame(client2, client3, "Client should be cached");
    }
}