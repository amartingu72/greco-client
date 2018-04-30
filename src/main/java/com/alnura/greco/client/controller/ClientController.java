package com.alnura.greco.client.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alnura.greco.client.model.UserDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path="/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
	@Value("${greco-boot.url}")
	private String url;
	
	@Value("${greco-boot.port}")
	private String port;
	
	@GetMapping("/{userId}")
	@ApiOperation(value = "Finds user by id",
    notes = "0,1,2...",
    response = UserDTO.class
    )
	@ApiResponses(value = { 
		      @ApiResponse(code = 400, message = "Invalid ID supplied"), 
		      @ApiResponse(code = 404, message = "User not found") })
	public UserDTO getUserById(
			 @ApiParam(value = "user id", required = true) @PathVariable int userId)  {
		String uri=String.format("%s:%s/v1/users/%d", url, port, userId);
		
		 
		
		 // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
 
        // 
        // Authentication
        //Caso Basic auth 
        //String auth = USER_NAME + ":" + PASSWORD;
        //byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        //String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", "alberto");
        // 
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
       
 
        // HttpEntity<String>: To get result as String.
        HttpEntity<String> entity = new HttpEntity<String>(headers);
 
        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestClientErrorHandler());
 
        // Send request with GET method, and Headers.
        ResponseEntity<UserDTO> response = restTemplate.exchange(uri, 
                HttpMethod.GET, entity, UserDTO.class);
 
        UserDTO result = response.getBody();
 
        
        return result;
		
    }
}
