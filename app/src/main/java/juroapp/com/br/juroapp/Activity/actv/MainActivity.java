package juroapp.com.br.juroapp.Activity.actv;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;

import juroapp.com.br.juroapp.Activity.VO.ItemListaOrganicosVO;
import juroapp.com.br.juroapp.Activity.VO.ItemListaUsuariosFireBase;
import juroapp.com.br.juroapp.Activity.dao.ConfiguracaoFirebase;
import juroapp.com.br.juroapp.Activity.entidades.Usuarios;
import juroapp.com.br.juroapp.R;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private EditText login;
    private EditText senha;
    private Button entrar;
    private TextView novoUsuario;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;
    private DatabaseReference root;
    private ArrayList<ItemListaUsuariosFireBase> listaUsuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);
        entrar = (Button) findViewById(R.id.btnEntrar);
        novoUsuario = (TextView) findViewById(R.id.criarConta);
        validarligin();


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!login.getText().equals("") && !senha.getText().equals("")) {
                    usuarios = new Usuarios();
                    usuarios.setEmail(login.getText().toString());
                    usuarios.setSenha(senha.getText().toString());
//                    usuarios.setEmail("renan@gmail.com");
//                    usuarios.setSenha("123456");

                    for (int i = 0; i < listaUsuarios.size(); i++) {
                        if (usuarios.getEmail().equals(listaUsuarios.get(i).getLogin())) {
                            Intent abrirTelaPrincipal = new Intent(MainActivity.this, ActivityPrincipal.class);
                            startActivity(abrirTelaPrincipal);
                        } else {
                            Log.d("", "");
                        }
                    }


                    //TODO AQUI TRATAR COM O SERVIÇO ANTES DE ABRIR A TELA PRINCIPAL

                }
            }
        });

        novoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                alert.setView(showCadastrar());

                AlertDialog dialog = alert.create();

                dialog.show();
            }
        });

    }

    private void verificarUsuariosFirebase() {


        root = FirebaseDatabase.getInstance().getReference().child("/Produtos/Usuarios/");

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("", "");
            }
        });
//        //LISTAFIREBASE

    }

    private void append_chat_conversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {

            ItemListaUsuariosFireBase mItemListaUsuariosFireBase = new ItemListaUsuariosFireBase();
            try {
                mItemListaUsuariosFireBase.setLogin((String) ((DataSnapshot) i.next()).getKey());
                mItemListaUsuariosFireBase.setSenha((String) ((DataSnapshot) i.next()).getValue().toString());
                listaUsuarios.add(mItemListaUsuariosFireBase);

            } catch (Exception e) {


                Log.e("erro ActvChatRom", e.getMessage());

            }


        }
        Log.d("", "");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void validarligin() {

        autenticacao = ConfiguracaoFirebase.getAutenticacao();

        autenticacao.signInWithEmailAndPassword("renan@gmail.com", "123456").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("", "");



                    Toast.makeText(MainActivity.this, "AUTENTICOU FIREBASE", Toast.LENGTH_LONG).show();
                    verificarUsuariosFirebase();
                } else {
                    Log.d("", "");
                }
            }
        });
    }

    private View showCadastrar() {

        final LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.alert_novo_usuario, null);
        final EditText nomeNovo = (EditText) alertLayout.findViewById(R.id.nomeNovoUsuario);
        final EditText sobreNomeNovo = (EditText) alertLayout.findViewById(R.id.sobreNomeNovoUsuario);
        final EditText emailNovo = (EditText) alertLayout.findViewById(R.id.emailNovoUsuario);
        final EditText profissaoNovo = (EditText) alertLayout.findViewById(R.id.profissaoNovoUsuario);
        final EditText senha1Novo = (EditText) alertLayout.findViewById(R.id.senha1NovoUsuario);
        final EditText senha2Novo = (EditText) alertLayout.findViewById(R.id.senha2NovoUsuario);
        Button enviarCadastro = (Button) alertLayout.findViewById(R.id.cadastrarNovoUsuario);


        enviarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = String.valueOf(nomeNovo.getText());
                String sobreNome = String.valueOf(sobreNomeNovo.getText());
                String email = String.valueOf(emailNovo.getText());
                String profissao = String.valueOf(profissaoNovo.getText());
                String senha1 = String.valueOf(senha1Novo.getText());
                String senha2 = String.valueOf(senha2Novo.getText());

                if (!nome.equals("") &&
                        !sobreNome.equals("") &&
                        !email.equals("") &&
                        !profissao.equals("") &&
                        !senha1.equals("") &&
                        !senha2.equals("")) {
                    if (senha1.equals(senha2)) {

                        //TODO AQUI ENVIA PRO SERVIDOR
                    } else {
                        Toast.makeText(inflater.getContext(), R.string.senhaInvalida, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(inflater.getContext(), R.string.todosOsCamposPreenchidos, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //teste//

        return alertLayout;

    }
}
