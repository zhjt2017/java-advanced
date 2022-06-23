package demo.jsr269;

import myjsr269.ToFastjsonString;

/**
 * dto demo
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-21 10:39:30
 */
@ToFastjsonString
public class Student {
    private String name;
    private String city;
    private Integer id;
}
