package org.this_static;

/**
 * Created by java on 16-10-25.
 */
public class Test {
    int a;
    public static int b;

    public void Init(){
        a = 3;
        b = 4;
    }
    /*静态方法只能直接访问静态成员*/
    public static void Init2(){
       // a =3;
        b = 4;
    }
}
