
package Modelo;

/**
 *
 * @author Leandro
 */
public class Login {
    
    private int id;
    private String usuario;
    private String mail;
    private String pass;
    
    public Login(){
        
    }
    
    public Login(int id, String usuario, String mail, String pass){
        this.id = id;
        this.usuario = usuario;
        this.mail = mail;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
    
}
