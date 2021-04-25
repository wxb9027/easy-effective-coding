package easy.effective.coding.java_basic.design_pattern.proxy_pattern.static_proxy;

public class GamePlayer implements IGamePlayer {

    private String name = "";

    public GamePlayer(String _name){
        this.name = _name;
    }

    @Override
    public void login(String user, String password) {
        System.out.println("登录名为"+user+"的用户"+this.name+"登录成功");
    }

    @Override
    public void killBoss() {
        System.out.println(this.name + "正在打怪");
    }

    @Override
    public void upgrade() {
        System.out.println(this.name + "又升一级");
    }
}
