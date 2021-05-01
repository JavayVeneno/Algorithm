import java.util.Objects;

public class Student implements Comparable<Student>{
    public Student(){

    }

    public Student(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    private Long id;
    private String name;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }


    @Override
    public int compareTo(Student o) {

        //比较无非就三种情况,小于等于大于,所以需要写三个if,但是我们可以直接return 两者的计算差值,这样更优雅

        return this.age - o.age;
    }

    @Override
    public String toString(){
        return String.format("Student[id:%d,name:%s,age:%d] %n",this.id,this.name,this.age);
    }
}
