package model;

import java.util.Objects;
import java.util.UUID;

public class Usuario {

    private String login;
    private transient String senha;
    private String id;
    private String nome;
    private TipoUser tipoUser;

    public Usuario(String login, String senha, String id, TipoUser tipoUser) {
        this.login = login;
        this.senha = senha;
        this.id = id;
        this.tipoUser = tipoUser;
    }

    public Usuario(String login, String senha, TipoUser tipo) {
        this(login, senha, UUID.randomUUID().toString(), tipo);
    }

    public boolean validarSenha( String senhaInformada){
        return this.senha.equals(senhaInformada);
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoUser getTipoUser() {
        return tipoUser;
    }
    public void setTipoUser(TipoUser tipoUser) {
        this.tipoUser = tipoUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(login, usuario.login);
    }
    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
    
}
