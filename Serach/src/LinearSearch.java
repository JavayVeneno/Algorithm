public class LinearSearch {
   private  LinearSearch(){

   }
    public static <E> int search(E[] data ,E target){

        for(int i = 0;i<data.length;i++){
            if(data[i].equals(target)){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args){
//        Integer[] data = {24,18,12,9,16,66,32,4};
//        int res = LinearSearch.search(data,16);
//        int res2 = search(data,666);
//        System.out.println(res);
//        System.out.println(res2);

//        Student s1 = new Student(1L,"老刘",30);
//        Student s2 = new Student(2L,"胜英",29);
//        Student s3 = new Student(3L,"云神",28);
//        Student s4 = new Student(4L,"海军",27);
//        Student s5 = new Student(4L,"张海军",26);
//        Student s6 = new Student(5L,"王波",26);
//        Student s7 = new Student(6L,"志伟",24);
//        Student[] ennovations = {s1,s2,s3,s4,s5,s6,s7};
//        Student longjie  = new Student(7L,"龙杰",27);
//        Student who  = new Student();
//        who.setId(4L);
//        int indexofhaijun = search(ennovations,who);
//        int indexoflongjie = search(ennovations,longjie);
//        System.out.println(indexofhaijun);
//        System.out.println(indexoflongjie);
//        System.out.println(s4.equals(s5));

//        int n = 10_000_000;
//        10000000条数据,run 100次耗时 1.364255s
//        1000000条数据,run 100次耗时 0.129091s 从这个结果说明,时间复杂度与数据规模成正比;
        int[] pool = {10_000_000,1_000_000};
        for(int n :pool){
            Integer[] arr = ArrayGenerator.generatorOrderArray(n);
            long start = System.nanoTime();
            for (int i = 0; i <100 ; i++) {
                search(arr,n);
            }
            long end  = System.nanoTime();
            double use  = (end - start) / 1_000_000_000.000000000;
            System.out.printf("%d条数据,run %d次耗时 %fs %n",n,100,use);
        }




    }
}
