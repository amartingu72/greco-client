package com.alnura.greco.client.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.tomcat.util.http.parser.MediaTypeCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;



public class RestClientErrorHandler implements ResponseErrorHandler {

	protected boolean hasError(HttpStatus statusCode) {
		return (statusCode.series() == HttpStatus.Series.CLIENT_ERROR ||
				statusCode.series() == HttpStatus.Series.SERVER_ERROR);
	}
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		HttpStatus statusCode = response.getStatusCode();
		MediaType contentType = response.getHeaders().getContentType();
		Charset charset = contentType != null ? contentType.getCharset() : null;
		byte[] body = FileCopyUtils.copyToByteArray(response.getBody());
		//Raise a new exception which includes the body(JSON) 
		switch (statusCode.series()) {
			case CLIENT_ERROR:
				throw new HttpClientErrorException(statusCode, response.getStatusText(), body, charset);
			case SERVER_ERROR:
				throw new HttpServerErrorException(statusCode, response.getStatusText(), body, charset);
			default:
				throw new RestClientException("Unknown status code [" + statusCode + "]");
		}
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return hasError(response.getStatusCode());
	}

}
