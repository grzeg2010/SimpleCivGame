/*
 * SimpleCivGame: Turn-based strategy game inspired by Sid Meier's Civilization V, built with Spring Boot.
 * Copyright (C) 2026 Grzegorz Śnieżko
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
