package sort;

import common.Student;


import java.util.Random;

public class CountingSort {


    public void sort(Student[] students){

        countSort(students,101);
    }

    private void countSort(Student[] students, int r) {
        int[] count = new  int[r];
        int[] index = new int[r+1];

        for (Student student : students) {
            count[student.getGrade()]++;
        }
        for (int i = 0; i+1 < index.length; i++) {
            // 下一个元素的起始索引 = 当前元素索引位置+加上当前元素数量count(i)
            index[i+1] = index[i]+count[i];
        }

        Student[] temp = new Student[students.length];

        for (Student student : students){
            // 通过元素的分数找到其对应的index 因为 index的范围 为数据的范围+1,所以不用担心index越界
            // index中 [第一种索引,第二种索引),[第n-1种索引,第n种索引)
            int pos = index[student.getGrade()];
            temp[pos] = student;
            index[student.getGrade()]++;
        }
        System.arraycopy(temp,0,students,0,students.length);
    }

    public static void main(String[] args) {



        Student[] students = new Student[26*26*26];
        Random random = new Random();
        int k = 0;
        for (char a = 'a'; a <='z' ; a++) {
            for (char b = 'a'; b <= 'z'; b++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    students[k] = new Student(random.nextInt(101),""+a+b+c);
                    k++;
                }
            }
        }



        CountingSort sort = new CountingSort();
        sort.sort(students);
        for (int i=0 ;i+1<students.length;i++){
            if(students[i].getGrade()>students[i+1].getGrade()){
                throw new RuntimeException("sort failed");
            }
            if(students[i].getGrade()==students[i+1].getGrade()){
                if(students[i].getName().compareTo(students[i+1].getName())>0){
                    throw new RuntimeException("No stable counting sort");
                }
            }

        }
        System.out.println(students);
    }
}
