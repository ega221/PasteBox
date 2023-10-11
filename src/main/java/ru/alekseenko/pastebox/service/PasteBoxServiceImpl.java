package ru.alekseenko.pastebox.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.alekseenko.pastebox.api.request.PasteBoxRequest;
import ru.alekseenko.pastebox.api.request.PublicStatus;
import ru.alekseenko.pastebox.api.response.PasteBoxResponse;
import ru.alekseenko.pastebox.api.response.PasteBoxURLResponse;
import ru.alekseenko.pastebox.model.PasteBoxEntity;
import ru.alekseenko.pastebox.repository.PasteBoxRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PasteBoxServiceImpl implements PasteBoxService{

    private String host = "http://pastebox.boba.ru";
    private int publicListSize = 10;

    private final PasteBoxRepository repository;
    private AtomicInteger idGenerator = new AtomicInteger(0);


    @Override
    public PasteBoxResponse getByHash(String hash) {

        PasteBoxEntity pasteBoxEntity = repository.getByHash(hash);
        return new PasteBoxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic());
    }

    @Override
    public List<PasteBoxResponse> getPublicPasteBoxes() {
        List<PasteBoxEntity> list = repository.getListOfPublicAndAlive(publicListSize);

        return list.stream().map(pasteBoxEntity -> new PasteBoxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PasteBoxURLResponse create(PasteBoxRequest request) {
        int hash = generateId();

        PasteBoxEntity entity = new PasteBoxEntity();
        entity.setData(request.getData());
        entity.setId(hash);
        entity.setHash(Integer.toHexString(hash));
        entity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        entity.setLifeTime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(entity);

        return new PasteBoxURLResponse(host + "/" + entity.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
