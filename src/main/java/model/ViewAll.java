package model;

public class ViewAll {
    private Student student;
    private Lophoc lophoc;

    public ViewAll(Student student, Lophoc lophoc) {
        this.student = student;
        this.lophoc = lophoc;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lophoc getLophoc() {
        return lophoc;
    }

    public void setLophoc(Lophoc lophoc) {
        this.lophoc = lophoc;
    }
}
