package com.example.projeto.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.projeto.enums.TipoUsuario;
import com.example.projeto.enums.UserRole;

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
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class UserModel implements  UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private boolean admin;

    private String senha;

    private String password;

    @Column(unique=true)
    private String email;

    private boolean blocked;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private Integer tipoUsuario;

    @OneToMany(mappedBy = "userModel")
    private List<ImovelModel> imoveis;

    @OneToMany(mappedBy = "userModel")
    private List<OfertaModel> ofertas;

    @OneToMany(mappedBy = "userModel")
    private List<ContratoModel> contratos;



    public UserModel() {
    };

    public UserModel(int id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public UserModel(String nome, String password) {
        super();
        this.nome = nome;
        this.password = password;
    }

    public UserModel(String nome, String password, TipoUsuario tipoUsuario) {
        super();
        this.nome = nome;
        this.password = password;
        this.tipoUsuario = tipoUsuario.getCodigo();
    }

    public UserModel(String nome, String email, String password, UserRole role){
        this.email = email;
        this.password = password;
        this.email = email;
        this.userRole = role;
        this.senha = password; //ATENÇÃO -> GAMBIARRA PARA REMOVER NA LIMPEZA DO PROJETO
    }



     @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == userRole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        else {
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

   
    public TipoUsuario geTipoUsuario() {
        return TipoUsuario.toEnum(tipoUsuario);
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario.getCodigo();
    }

   
}