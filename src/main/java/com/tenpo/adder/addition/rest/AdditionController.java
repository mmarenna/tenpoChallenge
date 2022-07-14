package com.tenpo.adder.addition.rest;

import com.tenpo.adder.addition.rest.dto.AdditionRequest;
import com.tenpo.adder.addition.rest.dto.AdditionResponse;
import com.tenpo.adder.addition.service.AdditionService;
import com.tenpo.adder.history.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.tenpo.adder.utils.ApiTenpoConstants.ADDITION_URI;

@Slf4j
@RestController
@RequestMapping("/api/addition")
public class AdditionController {

    private final AdditionService additionService;
    private final HistoryService historyService;

    public AdditionController(AdditionService additionService, HistoryService historyService) {
        this.additionService = additionService;
        this.historyService = historyService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public ResponseEntity<AdditionResponse> calculateAddition(@Valid @RequestBody AdditionRequest additionRequest) {
        log.info("Calculation addition for: {} and {}", additionRequest.getInputA(), additionRequest.getInputB());

        final AdditionResponse additionResponse = AdditionResponse.builder()
                .result(this.additionService.calculateAddition(additionRequest.getInputA(), additionRequest.getInputB())).build();

        this.historyService.createHistory(ADDITION_URI, additionResponse.toString());
        return ResponseEntity.status(HttpStatus.OK)
                .body(additionResponse);
    }
}
