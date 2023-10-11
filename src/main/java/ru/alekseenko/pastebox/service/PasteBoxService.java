package ru.alekseenko.pastebox.service;

import ru.alekseenko.pastebox.api.request.PasteBoxRequest;
import ru.alekseenko.pastebox.api.response.PasteBoxResponse;
import ru.alekseenko.pastebox.api.response.PasteBoxURLResponse;

import java.util.List;

public interface PasteBoxService {
    PasteBoxResponse getByHash(String hash);
    List<PasteBoxResponse> getPublicPasteBoxes();
    PasteBoxURLResponse create(PasteBoxRequest request);
}
