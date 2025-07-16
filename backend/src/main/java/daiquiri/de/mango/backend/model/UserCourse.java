package daiquiri.de.mango.backend.model;


import jakarta.persistence.*;

@Entity
@Table (name = "user_course")
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;

    @Column(name = "course_code", nullable = false, length = 20)
    private String courseCode;

    @Column(nullable = true)
    private Double grade;

    @Column(nullable = false)
    private boolean approved;

    @Column(length = 10)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_code", insertable = false, updatable = false)
    private Course course;

    public UserCourse() {
    }

    public UserCourse(Long id, String userId, String courseCode, Double grade, boolean approved, String status, User user, Course course) {
        this.id = id;
        this.userId = userId;
        this.courseCode = courseCode;
        this.grade = grade;
        this.approved = approved;
        this.status = status;
        this.user = user;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
