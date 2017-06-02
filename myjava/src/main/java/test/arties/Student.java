package test.arties;

import org.this_static.Abstract_Person;

/**
 * Created by java on 16-10-25.
 */
public class Student extends Abstract_Person {
    String mAdress = "学校食堂";
    @Override
    public void eat(){
        System.out.println("eat" + this.mAdress);
    }
}
