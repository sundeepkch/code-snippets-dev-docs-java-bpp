package org.beckn.bpp.web.api;

import lombok.extern.slf4j.Slf4j;
import org.beckn.bpp.dto.*;
import org.beckn.bpp.service.BppApplicationService;
import org.beckn.bpp.web.api.common.Routes;
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
            @RequestHeader HttpHeaders headers,
            @RequestBody SearchRequest request) {
        var response = bppApplicationService.search(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.SELECT_API)
    public ResponseEntity select(
            @RequestHeader HttpHeaders headers,
            @RequestBody SelectRequest request) {
        var response = bppApplicationService.select(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.INIT_API)
    public ResponseEntity init(
            @RequestHeader HttpHeaders headers,
            @RequestBody InitRequest request) {
        var response = bppApplicationService.init(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.CONFIRM_API)
    public ResponseEntity confirm(
            @RequestHeader HttpHeaders headers,
            @RequestBody ConfirmRequest request) {
        var response = bppApplicationService.confirm(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.STATUS_API)
    public ResponseEntity status(
            @RequestHeader HttpHeaders headers,
            @RequestBody StatusRequest request) {
        var response = bppApplicationService.status(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.TRACK_API)
    public ResponseEntity track(
            @RequestHeader HttpHeaders headers,
            @RequestBody TrackRequest request) {
        var response = bppApplicationService.track(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.CANCEL_API)
    public ResponseEntity cancel(
            @RequestHeader HttpHeaders headers,
            @RequestBody CancelRequest request) {
        var response = bppApplicationService.cancel(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.UPDATE_API)
    public ResponseEntity update(
            @RequestHeader HttpHeaders headers,
            @RequestBody UpdateRequest request) {
        var response = bppApplicationService.update(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.RATING_API)
    public ResponseEntity rating(
            @RequestHeader HttpHeaders headers,
            @RequestBody RatingRequest request) {
        var response = bppApplicationService.rating(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.SUPPORT_API)
    public ResponseEntity support(
            @RequestHeader HttpHeaders headers,
            @RequestBody SupportRequest request) {
        var response = bppApplicationService.support(request, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.CANCELLATION_REASONS_API)
    public ResponseEntity getCancellationReasons(
            @RequestHeader HttpHeaders headers,
            @RequestBody Context context) {
        var response = bppApplicationService.getCancellationReasons(context, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.RETURN_REASONS_API)
    public ResponseEntity getReturnReasons(
            @RequestHeader HttpHeaders headers,
            @RequestBody Context context) {
        var response = bppApplicationService.getReturnReasons(context, headers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Routes.RATING_CATEGORIES_API)
    public ResponseEntity getRatingCategories(
            @RequestHeader HttpHeaders headers,
            @RequestBody Context context) {
        var response = bppApplicationService.getRatingCategories(context, headers);
        return ResponseEntity.ok(response);
    }
}
