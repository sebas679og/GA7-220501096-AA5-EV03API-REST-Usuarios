package com.sagmade.usuarios_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagmade.usuarios_api.model.Usuario;
import com.sagmade.usuarios_api.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
	@Autowired
    private UsuarioRepository usuarioRepository;

    // Método para crear un nuevo usuario
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para obtener un usuario por ID
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Método para actualizar un usuario
    public Usuario actualizarUsuario(Usuario usuarioActualizado) {
        return usuarioRepository.save(usuarioActualizado);
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
