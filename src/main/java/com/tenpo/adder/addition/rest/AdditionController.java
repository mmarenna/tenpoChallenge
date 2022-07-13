package com.tenpo.adder.addition.rest;

import com.tenpo.adder.addition.rest.dto.AdditionResponse;
import com.tenpo.adder.addition.service.AdditionService;
import com.tenpo.adder.history.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping()
    public ResponseEntity<AdditionResponse> calculateAddition(@RequestParam(value = "inputA") Long inputA,
                                                              @RequestParam(value = "inputB") Long inputB) {
        log.info("Calculation addition for: {} and {}", inputA, inputB);

        Long result = additionService.calculateAddition(inputA, inputB);

        this.historyService.createHistory(ADDITION_URI, String.valueOf(result));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new AdditionResponse(result));
    }
}
