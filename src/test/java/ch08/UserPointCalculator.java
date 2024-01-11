package ch08;

import ch07.user.User;

import java.time.LocalDate;

public class UserPointCalculator {
    private PointRule pointRule = new PointRule();
    private SubscriptionDao subscriptionDao;
    private ProductDao productDao;

    public UserPointCalculator(SubscriptionDao subscriptionDao, ProductDao productDao) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    public void setPointRule(PointRule pointRule) {
        this.pointRule = pointRule;
    }

    public int calculatePoint(User user) {
        Subscription s = subscriptionDao.selectByUser(user.getUserId());
        if (s == null) {
            throw new NoSubscriptionException();
        }
        Product p = productDao.selectById(s.getProductId());
        LocalDate now = LocalDate.now();
        return new PointRule().calculate(s, p, now);
    }
}
