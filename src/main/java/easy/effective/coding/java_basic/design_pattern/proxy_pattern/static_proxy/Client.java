package easy.effective.coding.java_basic.design_pattern.proxy_pattern.static_proxy;

import java.util.Date;

public class Client {

    public static void main(String[] args) {
        //定义一个痴迷玩家
        IGamePlayer player = new GamePlayer("张三");
        GamePlayerProxy proxy = new GamePlayerProxy(player);

        //开始打游戏，记得下时间戳
        System.out.println("开始时间是："+new Date());
        //登录
        proxy.login("zhangsan", "password");
        //开始打怪
        proxy.killBoss();
        //升级
        proxy.upgrade();

        System.out.println("结束时间是："+new Date());
    }

}
