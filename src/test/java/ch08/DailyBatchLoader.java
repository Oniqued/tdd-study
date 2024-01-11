package ch08;

import lombok.Setter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
public class DailyBatchLoader {
    private String basePath = ".";
    private Times times = new Times();

    public int load() {
        LocalDate date = times.today();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Path batchPath = Paths.get(basePath, date.format(formatter), "batch.txt");

        return 3;
    }
}
