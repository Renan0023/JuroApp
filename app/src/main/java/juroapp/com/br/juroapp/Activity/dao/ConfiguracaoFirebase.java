package juroapp.com.br.juroapp.Activity.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Renan Dutra on 28/11/2017.
 */

public class ConfiguracaoFirebase {
    private static DatabaseReference referenceF;
    private static FirebaseAuth autenticacao;


    public static DatabaseReference getFirebase() {

        if (referenceF == null) {
            referenceF = FirebaseDatabase.getInstance().getReference();
        }
        return referenceF;
    }

    public static FirebaseAuth getAutenticacao() {
        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

}
