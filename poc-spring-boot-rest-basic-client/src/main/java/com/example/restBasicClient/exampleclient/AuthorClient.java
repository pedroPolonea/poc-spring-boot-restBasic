package com.example.restBasicClient.exampleclient;

import com.example.restBasicClient.dto.AuthorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class AuthorClient {
    final static String LOCA_HOST = "http://localhost:9000";
    final static String URL_AUTHORS_DEFAULT = LOCA_HOST.concat("/v1/authors");

    private RestTemplate restTemplate;
    private HttpEntity httpEntityHeaders;

    public JSONObject createJsonLogin(String name, String password) throws JSONException {
        JSONObject login = new JSONObject();
        login.put("password", password);
        login.put("name", name);

        return login;
    }

    public HttpEntity createHeaders(JSONObject login){
        restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = restTemplate.postForEntity(LOCA_HOST.concat("/login"), login.toString(), String.class ).getHeaders();
       return new HttpEntity<>(httpHeaders);
    }

    public List<AuthorDTO> getAuthor(HttpEntity headers){
        restTemplate = new RestTemplate();
        final ResponseEntity<List<AuthorDTO>> exchange = restTemplate.exchange(URL_AUTHORS_DEFAULT, HttpMethod.GET, headers, new ParameterizedTypeReference<List<AuthorDTO>>() {
        });

        return exchange.getBody();
    }

    public AuthorDTO postAuthor(HttpEntity headers, AuthorDTO authorDTO){
        restTemplate = new RestTemplate();

        final ResponseEntity<AuthorDTO> authorDTOResponseEntity = restTemplate.postForEntity(URL_AUTHORS_DEFAULT, new HttpEntity<>(authorDTO, headers.getHeaders()), AuthorDTO.class);

        return authorDTOResponseEntity.getBody();
    }

    public AuthorDTO putAuthor(HttpEntity headers, AuthorDTO authorDTO){
        restTemplate = new RestTemplate();

        final ResponseEntity<AuthorDTO> exchange = restTemplate.exchange(URL_AUTHORS_DEFAULT, HttpMethod.PUT,  new HttpEntity<>(authorDTO, headers.getHeaders()), new ParameterizedTypeReference<AuthorDTO>() {
        });
        return exchange.getBody();
    }

    public AuthorDTO getAuthorById(HttpEntity headers, Long id){
        if(id != null) {
            restTemplate = new RestTemplate();
            final ResponseEntity<AuthorDTO> exchange = restTemplate.exchange(URL_AUTHORS_DEFAULT.concat("/").concat(id.toString()), HttpMethod.GET, headers, new ParameterizedTypeReference<AuthorDTO>() {
            });

            return exchange.getBody();
        }

        return null;
    }

    public String deleteAuthor(HttpEntity headers, Long id) {
        if (id != null) {
            restTemplate = new RestTemplate();
            final ResponseEntity<String> exchange = restTemplate.exchange(URL_AUTHORS_DEFAULT.concat("/").concat(id.toString()), HttpMethod.DELETE, headers, String.class);

            return exchange.getStatusCode().toString();
        }

        return null;
    }
}
