package com.imcode.leetcode.mysql;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/1/26
 * @Description :
 */
public class HeapDemo {


    public static void main(String[] args) {

        BigDecimal value = BigDecimal.ZERO;

        value = value.add(BigDecimal.valueOf(0.9));
        value = value.add(BigDecimal.valueOf(0.1));

        System.out.println(value.compareTo(BigDecimal.valueOf(1)));

        System.out.println(BigDecimal.valueOf(1).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));


        PriorityQueue<Student> queue = new PriorityQueue<>(8);
        queue.add(new Student(1, "张三", 1));
        queue.add(new Student(2, "李四", 2));
        queue.add(new Student(3, "王五", 3));
        queue.add(new Student(4, "赵六", 4));
        queue.add(new Student(5, "孙七", 2));
        queue.add(new Student(6, "赵八", 2));

        queue.poll();
        queue.add(new Student(7, "吴九", 2));
        queue.poll();
        queue.add(new Student(8, "郑十", 2));



        Student stu;
        while (Objects.nonNull((stu = queue.poll()))) {
            System.out.println(stu);
        }


    }


    static class Student implements Comparable<Student> {

        private Integer id;

        private String name;

        private Integer createTime;

        public Student(Integer id, String name, Integer createTime) {
            this.id = id;
            this.name = name;
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", createTime=" + createTime +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Integer createTime) {
            this.createTime = createTime;
        }



        @Override
        public int compareTo(Student o) {
            if (o != null) {
                return o.createTime.compareTo(this.createTime);
                // return createTime.compareTo(((Student)o).getCreateTime());
            }
            return 0;
        }
    }

}
