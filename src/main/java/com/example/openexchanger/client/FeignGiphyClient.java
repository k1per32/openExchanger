package com.example.openexchanger.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Giphy", url = "api.giphy.com/v1/gifs/random")
public interface FeignGiphyClient {

    @GetMapping()
    public void getRandomGiphy(@RequestParam("api_key") String appId,
                               @RequestParam("tag") String tag);
}
