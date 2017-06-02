package test.arties;

import org.this_static.Abstract_Person;
import org.this_static.Arties;
import com.example.MyClass;
import org.this_static.Test;
/**
 * Created by java on 16-10-25.
 */
public class TestArties {
    public static void main(String args[]) {
        Arties arties = new Arties();
        for (int i = 0, j = 0; i < arties.num.length; i++) {
            arties.num[i] = j++;
        }
        arties.print();
   /*   挎包访问，访问静态成员，因为本方法main为静态方法无法直接访问访问非静态的成员*/
        Test.b = 4;

        Abstract_Person abstract_person = new Abstract_Person() {
            @Override
            public void eat() {
                System.out.println("xxx");
            }
        };
        abstract_person.eat();

        Student student = new Student();
        student.eat();
/*原来主函数也可以被调用，类似我们自己的主函数被系统调用*/
        MyClass myClass = new MyClass();
        String[] strings = new String[10];
        myClass.main(strings);
    }
}
