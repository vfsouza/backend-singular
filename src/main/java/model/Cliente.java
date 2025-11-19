package model;

public class Cliente {
	private int id;
	private String nome;
	private String email;
	private String senha;
    private boolean isAdmin;
	
	public Cliente() {
	}
	
	public Cliente(String nome, String email, String senha, boolean isAdmin) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
        this.isAdmin = isAdmin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
}
