package com.generation.lojagames.service;

import com.generation.lojagames.model.UsuarioLogin;
import org.apache.tomcat.util.codec.binary.Base64;
import com.generation.lojagames.model.Usuario;
import com.generation.lojagames.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> cadastroUsuario(Usuario usuario){

        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            return Optional.empty();

        if (calcularIdade(usuario.getDataNascimento()) < 18)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Usuário é menor de 18 anos", null);

        if (usuario.getFoto().isBlank())
            usuario.setFoto("https://i.imgur.com/Zz4rzVR.png");

        usuario.setSenha(criptografarSenha(usuario.getSenha()));

        return Optional.ofNullable(usuarioRepository.save(usuario));
    }

    public Optional<Usuario> atualizarUsuario(Usuario usuario){
        return null;
    }


    public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
        return null;
    }

    private int calcularIdade(LocalDate dataNascimento){

        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    private String criptografarSenha(String senha){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(senha);
    }

    private boolean compararSenhas(String senhaDigitada, String senhaBanco){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(senhaDigitada, senhaBanco);
    }

    private String gerarBasicToken(String usuario, String senha){

        String token = usuario + ":" + senha;
        byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));

        return "Basic " + new String(tokenBase64);
    }

}
