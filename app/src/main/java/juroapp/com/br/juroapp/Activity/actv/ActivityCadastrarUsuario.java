package juroapp.com.br.juroapp.Activity.actv;

import android.app.Activity;
import android.graphics.drawable.RippleDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import juroapp.com.br.juroapp.Activity.VO.ItemListaOrganicosVO;
import juroapp.com.br.juroapp.R;

public class ActivityCadastrarUsuario extends Activity {
    private DatabaseReference root;
    private Button novoUsuario;
    private android.support.v7.app.AlertDialog alerta;
    private Button listarUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
        novoUsuario = (Button) findViewById(R.id.cadastrarUsuario);
        listarUsuarios = (Button) findViewById(R.id.listarUsuarios);
        novoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogCadastrar();

            }
        });
        listarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogListarUsuariosFirebase();

            }
        });


    }

    private void alertDialogListarUsuariosFirebase() {
        final LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.alert_listar_usuarios, null);
        final Button atualziar = (Button) alertLayout.findViewById(R.id.atualizar);
        final LinearLayout lienar = (LinearLayout) alertLayout.findViewById(R.id.linearNomeUsuarios);
        atualziar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseList(lienar);
            }
        });
        firebaseList(lienar);
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setView(alertLayout);
        alerta = builder.create();
        alerta.show();
    }

    private void firebaseList(final LinearLayout lienar) {


        root = FirebaseDatabase.getInstance().getReference().child("/Produtos/Usuarios/");

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pegarFirebase(dataSnapshot, lienar);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                pegarFirebase(dataSnapshot, lienar);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                pegarFirebase(dataSnapshot, lienar);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                pegarFirebase(dataSnapshot, lienar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("", "");
            }
        });
//        //LISTAFIREBASE

    }

    private void pegarFirebase(DataSnapshot dataSnapshot, LinearLayout lienar) {
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {


            try {
                TextView nomeUsuario = new TextView(this);
                nomeUsuario.setText((String) ((DataSnapshot) i.next()).getKey());
                String senha = (String) ((DataSnapshot) i.next()).getValue().toString();
                lienar.addView(nomeUsuario);
            } catch (Exception e) {


                Log.e("erro ActvChatRom", e.getMessage());

            }


        }
        Log.d("", "");


    }

    public void alertDialogCadastrar() {
        final LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.alert_cadastrar_login, null);

        final EditText nomeNovo = (EditText) alertLayout.findViewById(R.id.nomeNovoUsuario);

        final EditText senha1Novo = (EditText) alertLayout.findViewById(R.id.senha1NovoUsuario);
        final EditText senha2Novo = (EditText) alertLayout.findViewById(R.id.senha2NovoUsuario);
        Button enviarCadastro = (Button) alertLayout.findViewById(R.id.cadastrarNovoUsuario);


        enviarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = String.valueOf(nomeNovo.getText());

                String senha1 = String.valueOf(senha1Novo.getText());
                String senha2 = String.valueOf(senha2Novo.getText());
                Map<String, Object> map2 = null;
                if (!nome.equals("") &&
                        !senha1.equals("") &&
                        !senha2.equals("")) {
                    if (senha1.equals(senha2)) {
                        for (int i = 0; i <= 1; i++) {
                            root = FirebaseDatabase.getInstance().getReference().child("/Produtos/Usuarios/1/");
                            if (i == 1) {
                                map2 = new HashMap<String, Object>();
                                map2.put(nome + "1", senha1);
                            } else {
                                map2 = new HashMap<String, Object>();
                                map2.put(nome, senha1);
                            }
                            root.updateChildren(map2);
                        }
                        Toast.makeText(inflater.getContext(), "CADASTRADO COM SUCESSO !", Toast.LENGTH_SHORT).show();
                        alerta.dismiss();
                    } else {

                        Toast.makeText(inflater.getContext(), R.string.senhaInvalida, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(inflater.getContext(), R.string.todosOsCamposPreenchidos, Toast.LENGTH_SHORT).show();
                }
            }
        });


        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setView(alertLayout);
        alerta = builder.create();
        alerta.show();
    }
}
