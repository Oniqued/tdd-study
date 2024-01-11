package ch08;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PaySyncTest {
    private PayInfoDao memoryDao = new MemoryPayInfo();

    @Test
    void allDataSaved() throws IOException {
        PaySync paySync = new PaySync();
        paySync.setPayInfoDao(memoryDao);

        paySync.sync("test_a.csv");

        List<PayInfo> savedInfos = memoryDao.getAll();
        assertEquals(2, savedInfos.size());
    }
}
