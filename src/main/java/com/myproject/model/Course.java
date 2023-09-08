package com.myproject.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER) // EAGER loading for students
    private Set<Student> students = new HashSet<>();

    // Constructors, getters, setters, and other methods

    public Course() {
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

	public Course(Long id, String courseName, Set<Student> students) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.students = students;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

    // Getter and Setter methods
    // Other methods
}
