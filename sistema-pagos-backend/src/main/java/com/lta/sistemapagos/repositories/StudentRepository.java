package com.lta.sistemapagos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lta.sistemapagos.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByStudent(String codigo);

    List<Student> findByProgramaId(String programaId);
}
