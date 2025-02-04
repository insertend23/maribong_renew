package com.navangs.maribong.service.impl;

import com.navangs.maribong.service.ReviewModelRequestService;
import java.net.URI;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
public class ReviewModelRequestServiceImpl implements ReviewModelRequestService {
    private static final String REQUEST_DATA_NAME = "review";
    private static final List<String> DEFAULT_LABEL = List.of("라벨 없음");
    @Value("${REVIEW_MODEL_URL}")
    private String reviewModelUrl;

    @Override
    public List<String> getReaction(String content) {
        URI reviewModelUrl = URI.create(this.reviewModelUrl);
        HttpEntity<Map<String, String>> payload = getPayload(content);
        Label response = sendRequestAndGetLabel(reviewModelUrl, payload);

        return response.getLabels();
    }

    private HttpEntity<Map<String, String>> getPayload(String content) {
        Map<String, String> data = Map.of(REQUEST_DATA_NAME, content);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(data, headers);
    }

    private Label sendRequestAndGetLabel(URI reviewModelUrl, HttpEntity<Map<String, String>> payload) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.postForObject(reviewModelUrl, payload, Label.class);
        } catch (RestClientResponseException e) {
            return Label.getDefaultLabel();
        }
    }

    @Getter
    @Builder
    private static class Label {
        private List<String> labels;

        public static Label getDefaultLabel() {
            return Label.builder().labels(DEFAULT_LABEL).build();
        }
    }
}
