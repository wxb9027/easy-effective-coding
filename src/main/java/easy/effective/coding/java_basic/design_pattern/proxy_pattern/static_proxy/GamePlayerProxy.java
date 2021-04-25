package easy.effective.coding.java_basic.design_pattern.proxy_pattern.static_proxy;

public class GamePlayerProxy implements IGamePlayer{

    IGamePlayer player = null;

    //通过构造函数来传递要对谁进行代理
    public GamePlayerProxy(IGamePlayer _player){
        this.player = _player;
    }

    @Override
    public void login(String user, String password) {
        this.player.login(user, password);
    }

    @Override
    public void killBoss() {
        this.player.killBoss();
    }

    @Override
    public void upgrade() {
        this.player.upgrade();
    }
}
