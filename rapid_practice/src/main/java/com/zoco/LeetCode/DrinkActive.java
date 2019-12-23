package com.zoco.LeetCode;

/**
 * 阿里巴巴的食堂搞活动促销，已知某饮料1瓶3元钱，4个瓶盖可以换一瓶，2个空瓶可以换一瓶，则30元最多可以喝几瓶。
 * 输入：
 * A //A表示饮料单价
 * B //B表示瓶盖换瓶比
 * C //C表示空瓶换瓶比
 * D //D表示给定的钱数
 * 输出：S
 *
 * @author zoco
 * @creat 2019-11-03-15:09
 */
public class DrinkActive {
    //总饮料数
    private static int TOTAL_DRINK = 0;

    /**
     * @param price
     * @param pg
     * @param kp
     * @param money
     * @return
     */
    public static int drinkNum(int price, int pg, int kp, int money, int count) {

        int drinkNum = 0;
        int reaminKp = 0;
        int remainPg = 0;

        drinkNum += (money / price);
        money = money % price;
        drinkNum += (pg / 4);
        drinkNum += (kp / 2);

        remainPg += drinkNum;
        reaminKp += drinkNum;

        remainPg += (pg % 4);
        reaminKp += (kp % 2);

        if (money >= 3 || remainPg >= 4 || reaminKp >= 2) {
            drinkNum += drinkNum(price, remainPg, reaminKp, money, ++count);
        }

        return drinkNum;
    }

    public static void main(String[] args) {
        System.out.println(drinkNum(3, 0, 0, 30, 1));
    }
}
