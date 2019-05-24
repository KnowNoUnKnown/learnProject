import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by liuyong
 * 2019-04-11  14-00
 */

public class Ttest {

    @Test
    public void TClassTest(){
        List<String> strings = Lists.newArrayList("1","2","3");

        List<Integer> integers = Lists.newArrayList(1,2,3,4,5);

        System.out.println(strings.getClass().getName());
        System.out.println(strings.get(0).getClass().getName());
        System.out.println(integers.get(0).getClass().getName());
        System.out.println(strings.getClass().equals(integers.getClass()));

     }
}