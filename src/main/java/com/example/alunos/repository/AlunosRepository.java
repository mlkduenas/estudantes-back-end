package com.example.alunos.repository;

import com.example.alunos.entity.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunosRepository extends JpaRepository<Aluno,Integer> {
    
}
