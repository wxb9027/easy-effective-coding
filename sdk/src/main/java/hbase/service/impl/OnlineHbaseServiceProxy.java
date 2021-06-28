package hbase.service.impl;

import hbase.handler.IAfterHandler;
import hbase.handler.IBeforeHandler;
import hbase.request.HbaseBaseRequest;
import hbase.service.IOnlineHbaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OnlineHbaseServiceProxy implements InvocationHandler {
    private static final Logger LOG = LoggerFactory.getLogger(OnlineHbaseServiceProxy.class);

    private IOnlineHbaseService target;
    //前置处理器
    private List<IBeforeHandler> beforeHandlers= new ArrayList();
    //后置处理器
    private List<IAfterHandler> afterHandlers= new ArrayList();

    public OnlineHbaseServiceProxy(IOnlineHbaseService target) {
        this.target = target;
        initHandler(target);
        LOG.info("Create OnlineHbaseServiceProxy successfully");
    }

    private void initHandler(IOnlineHbaseService target) {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        HbaseBaseRequest request = getBaseRequest(args);
        try {
            //加读写锁
            //前置处理
            //后置处理
        } finally {
            //是否开启链路跟踪
            //解锁
        }

        return null;
    }
}
