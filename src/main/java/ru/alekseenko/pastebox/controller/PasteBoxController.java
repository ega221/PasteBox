package ru.alekseenko.pastebox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alekseenko.pastebox.api.request.PasteBoxRequest;
import ru.alekseenko.pastebox.api.response.PasteBoxResponse;
import ru.alekseenko.pastebox.api.response.PasteBoxURLResponse;
import ru.alekseenko.pastebox.service.PasteBoxService;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PasteBoxController {

    private final PasteBoxService pasteBoxService;

    @GetMapping("/{hash}")
    public PasteBoxResponse getByHash(@PathVariable String hash) {
        return pasteBoxService.getByHash(hash);
    }

    @GetMapping("/")
    public List<PasteBoxResponse> getPublicPasteList() {
        return pasteBoxService.getPublicPasteBoxes();
    }

    @PostMapping("/")
    public PasteBoxURLResponse add(@RequestBody PasteBoxRequest request) {
        return pasteBoxService.create(request);
    }
}
