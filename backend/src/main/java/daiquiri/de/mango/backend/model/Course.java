package daiquiri.de.mango.backend.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Table (name = "courses_tb")
public class Course {

    @Id
    @Column(length = 20)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String description;


    @ElementCollection
    @CollectionTable(name = "course_prerequisites", joinColumns = @JoinColumn(name = "course_code"))
    @Column(name = "prerequisite_code")
    private List<String> prerequisites;


    @OneToMany(mappedBy = "course")
    private List<UserCourse> userCourses;

    public Course() {
    }

    public Course(String code, String name, String description, List<String> prerequisites, List<UserCourse> userCourses) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.prerequisites = prerequisites;
        this.userCourses = userCourses;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public List<UserCourse> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(List<UserCourse> userCourses) {
        this.userCourses = userCourses;
    }
}
