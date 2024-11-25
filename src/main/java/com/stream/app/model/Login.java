package com.stream.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado
    private Long id;

    private String email;

    private String username;

    private String password; // Contraseña encriptada

    @OneToMany(mappedBy = "user") // Relación bidireccional
    private List<Favorites> favorites; // Lista de favoritos
}