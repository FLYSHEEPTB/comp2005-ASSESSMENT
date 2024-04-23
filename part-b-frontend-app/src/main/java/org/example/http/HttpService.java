package org.example.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class HttpService {
    private final OkHttpClient oKHttpClient;



    public HttpService(@Autowired OkHttpClient okHttpClient) {
        this.oKHttpClient = okHttpClient;
    }

    public String getRequest(Request request) {
        try {
            Response response = oKHttpClient.newCall( request ).execute();
            if ( response.isSuccessful() ) {
                if ( Objects.isNull( response.body() ) ) {
                    return "";
                }
                String httpResponse = response.body().string();
                return httpResponse;
            } else {
                log.error( "http request failed: {}", response.body().string() );
            }
        }catch ( Exception ex ) {
            log.error( "sendRequest failed: ", ex );
        }
        return "";
    }
}
