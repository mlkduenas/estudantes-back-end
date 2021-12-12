package com.example.alunos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.alunos.entity.Aluno;
import com.example.alunos.repository.AlunosRepository;

@RestController
@RequestMapping("/alunos")
@CrossOrigin
public class AlunoController {

    // Injeta um dependencia!!!!
    @Autowired
    private AlunosRepository repo;

    // End Point 1
    // (GET) http://localhost:8080/alunos
    @GetMapping
    public List<Aluno> getAlunos() {
        List<Aluno> list = repo.findAll();
        return list;
    }

    // End Point 2
    // (GET) http://localhost:8080/alunos/{id}
    @GetMapping("{id}")
    public Aluno getAluno(@PathVariable int id) {
        Optional<Aluno> op =  repo.findById(id);
        Aluno aluno = op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return aluno;
    }

    // EndPoint 3
    // Salva aluno, é necessário passar os dados dele no formato JSON
    @PostMapping
    public Aluno salvar(@RequestBody Aluno aluno) {
        aluno = repo.save(aluno);
        return aluno;
    }

     //EndPoint 4
    //Remove aluno, é necessário passar o id
    @DeleteMapping("{id}")
    public void remover(@PathVariable Integer id){
        Optional<Aluno> op =  repo.findById(id);
        Aluno aluno = op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repo.delete(aluno);
    }

       //EndPoint 5
    //Altera aluno, é necessário passar os dados do dele no formato JSON e o id
    @PutMapping("{id}")
    public Aluno alterar(@RequestBody Aluno updateAluno, @PathVariable Integer id){
        Optional<Aluno> op =  repo.findById(id);
        Aluno aluno = op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        aluno.setName(updateAluno.getName());
        repo.save(aluno);
        return aluno;
    }



}
