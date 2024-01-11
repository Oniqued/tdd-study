package ch08;

import java.util.List;

public interface PayInfoDao {
    void insert(PayInfo payInfo);

    List<PayInfo> getAll();
}
