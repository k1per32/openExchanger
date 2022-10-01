package com.example.openexchanger.service;

import com.example.openexchanger.client.FeignGiphyClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Getter
@Setter
public class GiphyClientImpl {
    @Value("k8JKeXtdnT5Y9pEaYleuXFnbASdy8HWC")
    private String api_key;
    @Autowired
    private FeignGiphyClient feignGiphyClient;

    public ResponseEntity<Map> getGif(String tag) {
        return feignGiphyClient.getRandomGiphy(this.api_key, tag);
    }
}
