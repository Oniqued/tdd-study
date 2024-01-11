package ch08;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Setter
@Getter
public class PaySync {
    private String filePath = "test.csv";
    private PayInfoDao payInfoDao;

    public PaySync() {
    }

    public PaySync(PayInfoDao payInfoDao) {
        this.payInfoDao = payInfoDao;
    }

    public void sync(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<PayInfo> payInfos = Files.lines(path)
                .map(line -> {
                    String[] data = line.split(",");
                    return new PayInfo(data[0], data[1], Integer.parseInt(data[2]));
                }).toList();
        payInfos.forEach(pi -> payInfoDao.insert(pi));
    }
}
