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

import juroapp.com.br.juroapp.Activity.dao.ConfiguracaoFirebase;
import juroapp.com.br.juroapp.Activity.entidades.Usuarios;
import juroapp.com.br.juroapp.R;

public class MainActivity extends AppCompatActivity {
    private EditText login;
    private EditText senha;
    private Button entrar;
    private TextView novoUsuario;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);
        entrar = (Button) findViewById(R.id.btnEntrar);
        novoUsuario = (TextView) findViewById(R.id.criarConta);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!login.getText().equals("") && !senha.getText().equals("")) {
                    usuarios = new Usuarios();
//                    usuarios.setEmail(login.getText().toString());
//                    usuarios.setSenha(senha.getText().toString());
                    usuarios.setEmail("renan@gmail.com");
                    usuarios.setSenha("123456");
                    validarligin();


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

    private void validarligin() {

        autenticacao = ConfiguracaoFirebase.getAutenticacao();

        autenticacao.signInWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("", "");

                    Intent abrirTelaPrincipal = new Intent(MainActivity.this, ActivityPrincipal.class);
                    startActivity(abrirTelaPrincipal);

                    Toast.makeText(MainActivity.this, "AUTENTICOU FIREBASE", Toast.LENGTH_LONG).show();

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

        return alertLayout;

    }
}
