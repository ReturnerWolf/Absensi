package info.fahri.aplikasicurhat;

import java.io.Serializable;

public class Curhat implements Serializable {
    public String uid;
    public String userId;
    public String email;
    public String konten;

    public Curhat(){

    }

    public Curhat(String userId, String email, String konten) {
        this.userId = userId;
        this.email = email;
        this.konten = konten;
    }
}
