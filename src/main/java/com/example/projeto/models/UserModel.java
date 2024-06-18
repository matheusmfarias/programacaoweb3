package com.example.projeto.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.projeto.enums.TipoUsuario;
import com.example.projeto.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UserModel implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    private boolean admin;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    private boolean blocked;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private Integer tipoUsuario;

    @OneToMany(mappedBy = "userModel")
    @JsonManagedReference
    private List<ImovelModel> imoveis;

    @OneToMany(mappedBy = "userModel")
    @JsonManagedReference
    private List<OfertaModel> ofertas;

    @OneToMany(mappedBy = "userModel")
    @JsonManagedReference("usuario-contrato")
    private List<ContratoModel> contratos;

    public UserModel(String nome, String password) {
        this.nome = nome;
        this.password = password;
    }

    public UserModel(String nome, String email, String password, UserRole role) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.userRole = role;
    }

    // Implementação dos métodos de UserDetails...

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public TipoUsuario getTipoUsuario() {
        return TipoUsuario.toEnum(tipoUsuario);
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario.getCodigo();
    }
}

