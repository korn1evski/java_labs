package lab0;

public class Car{
    final private int year;
    final private String name;

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public Car(int year, String name){
        this.name = name;
        this.year = year;
    }

    public void printInfo(){
        System.out.println(this.name + " appeared in " + this.year);
    }
}
