package ru.alekseenko.pastebox.repository;

import ru.alekseenko.pastebox.model.PasteBoxEntity;

import java.util.List;

public interface PasteBoxRepository {

    PasteBoxEntity getByHash(String hash);

    List<PasteBoxEntity> getListOfPublicAndAlive(int amount);

    void add(PasteBoxEntity entity);

}
