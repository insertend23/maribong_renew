package com.navangs.maribong.service.impl;

import com.navangs.maribong.service.ReviewModelRequestService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReviewModelRequestServiceImpl implements ReviewModelRequestService {
    private static final String REQUEST_DATA_NAME = "review";
    @Value("${REVIEW_MODEL_URL}")
    private String reviewModelUrl;

    @Override
    public List<String> getReaction(String content) {
        URI reviewModelUrl = URI.create(this.reviewModelUrl);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, String>> payload = getPayload(content);
        Label response = restTemplate.postForObject(reviewModelUrl, payload, Label.class);
        if (response == null) {
            return new ArrayList<>();
        }

        return response.getLabels();
    }

    private HttpEntity<Map<String, String>> getPayload(String content) {
        Map<String, String> data = Map.of(REQUEST_DATA_NAME, content);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(data, headers);
    }

    @Data
    private static class Label {
        private List<String> labels;
    }
}
