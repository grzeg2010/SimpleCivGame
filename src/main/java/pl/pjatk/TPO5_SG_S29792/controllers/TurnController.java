package pl.pjatk.TPO5_SG_S29792.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.TPO5_SG_S29792.dto.PostTurnRequest;
import pl.pjatk.TPO5_SG_S29792.dto.GetTurnResponse;
import pl.pjatk.TPO5_SG_S29792.services.TurnService;

@RestController
@RequiredArgsConstructor
public class TurnController {
    private final TurnService turnService;

    @PostMapping("/turn")
    public GetTurnResponse endTurn(@RequestBody PostTurnRequest req) {
        return turnService.processTurn(req);
    }
}
