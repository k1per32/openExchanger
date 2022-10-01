package com.example.openexchanger.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "Giphy", url = "api.giphy.com/v1/gifs")
public interface FeignGiphyClient {

    @GetMapping("/random")
    public ResponseEntity<Map> getRandomGiphy(@RequestParam("api_key") String api_key,
                                              @RequestParam("tag") String tag);
}
