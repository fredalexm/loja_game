package com.generation.lojagames.controller;

import com.generation.lojagames.model.Usuario;
import com.generation.lojagames.repository.UsuarioRepository;
import com.generation.lojagames.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAll(){

        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @PostMapping

    @PutMapping

    @DeleteMapping

}
