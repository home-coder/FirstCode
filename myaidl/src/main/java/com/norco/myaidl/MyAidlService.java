package com.norco.myaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by java on 16-10-24.
 */
public class MyAidlService extends Service {
    @Override
    public IBinder onBind(Intent arg0) {
        // 把内部类的对象返回给客户端使用
        return new MyServiceImpl();
    }

    // 创建一个继承自IMyService.Stub的内部类
    public class MyServiceImpl extends IMyAidlInterface.Stub {

        // 必须实现AIDL文件中的接口
        public String getValue() throws RemoteException {
            return null;
        }
    }
}
