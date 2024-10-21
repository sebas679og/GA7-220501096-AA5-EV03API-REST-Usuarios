package com.sagmade.usuarios_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sagmade.usuarios_api.model.Usuario;
import com.sagmade.usuarios_api.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	@Autowired
    private UsuarioService usuarioService;

    // Endpoint para crear un nuevo usuario (POST)
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los usuarios (GET)
    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    // Endpoint para obtener un usuario por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para actualizar un usuario (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetalles) {
        Optional<Usuario> usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuarioActualizado = usuarioExistente.get();
            usuarioActualizado.setUsuario(usuarioDetalles.getUsuario());
            usuarioActualizado.setContrasena(usuarioDetalles.getContrasena());
            usuarioActualizado.setDocumento(usuarioDetalles.getDocumento());
            usuarioActualizado.setTelefono(usuarioDetalles.getTelefono());
            usuarioActualizado.setCorreo(usuarioDetalles.getCorreo());
            usuarioActualizado.setDireccion(usuarioDetalles.getDireccion());
            usuarioService.actualizarUsuario(usuarioActualizado);
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un usuario (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
