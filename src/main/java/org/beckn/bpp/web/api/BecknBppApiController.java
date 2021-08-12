package org.beckn.bpp.web.api;

import lombok.extern.slf4j.Slf4j;
import org.beckn.bpp.common.Routes;
import org.beckn.bpp.dto.SearchRequest;
import org.beckn.bpp.service.BppApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BecknBppApiController {

    @Autowired
    private BppApplicationService bppApplicationService;

    @PostMapping(Routes.SEARCH_API)
    public ResponseEntity search(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader,
            @RequestHeader(value = HttpHeaders.PROXY_AUTHORIZATION, required = false) String proxyAuthHeader,
            @RequestBody SearchRequest request) {
        var response = bppApplicationService.search(request, authHeader, proxyAuthHeader);
        return ResponseEntity.ok(response);
    }

}
