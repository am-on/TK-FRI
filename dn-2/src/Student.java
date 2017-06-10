import java.io.Serializable;

class StudentCompareNames implements java.util.Comparator<Student>
{
    @Override
    public int compare(Student o1, Student o2)
    {
        String name1 = o1.getLastName() + ", " + o1.getFirstName();
        String name2 = o2.getLastName() + ", " + o2.getFirstName();
        return name1.compareToIgnoreCase(name2);
    }
}

class StudentCompareID implements java.util.Comparator<Student>
{
    @Override
    public int compare(Student o1, Student o2)
    {
        String s1 = o1.getId();
        String s2 = o2.getId();

        s1 = s1.substring(s1.length()-8);
        s2 = s2.substring(s2.length()-8);
        return -s1.compareTo(s2);
    }
}

public class Student implements Serializable, Comparable {
    protected String firstName;
    protected String lastName;
    protected String id;
    protected String score;


    public Student(String id, String firstName, String lastName, String score) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }

    public Student(String[] data) {
        this.id = data[0];
        this.firstName = data[1];
        this.lastName = data[2];
        this.score = data[3];
    }

    public boolean validate() {
        if (id.length() == 0 || firstName.length() == 0 || lastName.length() == 0 || score.length() == 0) {
            return false;
        }

        try {
            float score = Float.parseFloat(this.score);
            if (score > 10 || score < 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        if (!id.matches("[0-9]+")) {
            return false;
        }


        return true;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getScore() {
        return score;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString()
    {
        // 63110159 | Anderlič, Boris| 5.3
        return id + " | " + lastName + ", " + firstName + " | " + id;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
