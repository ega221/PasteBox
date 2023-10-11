package ru.alekseenko.pastebox;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.alekseenko.pastebox.api.response.PasteBoxResponse;
import ru.alekseenko.pastebox.exception.ClassNotFoundExceptionEntity;
import ru.alekseenko.pastebox.model.PasteBoxEntity;
import ru.alekseenko.pastebox.repository.PasteBoxRepository;
import ru.alekseenko.pastebox.service.PasteBoxService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PasteboxServiceTest {
    @Autowired
    private PasteBoxService pasteBoxService;

    @MockBean
    private PasteBoxRepository pasteBoxRepository;

    @Test
    public void notExistingHash() {
        when(pasteBoxRepository.getByHash(anyString())).thenThrow(ClassNotFoundExceptionEntity.class);
        Assertions.assertThrows(ClassNotFoundExceptionEntity.class, () -> pasteBoxService.getByHash("asdvsvv"));
    }

    @Test
    public void getExistingHash() {
        PasteBoxEntity entity =new PasteBoxEntity();
        entity.setHash("1");
        entity.setData("11");
        entity.setPublic(true);

        when(pasteBoxRepository.getByHash("1")).thenReturn(entity);

        PasteBoxResponse expected = new PasteBoxResponse("11", true);
        PasteBoxResponse actual = pasteBoxService.getByHash("1");

        Assertions.assertEquals(expected, actual);
    }

}
