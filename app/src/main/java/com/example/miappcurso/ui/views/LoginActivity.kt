
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.miappcurso.R
import com.example.miappcurso.domain.listener.OnUsuarioActionListener
import com.example.miappcurso.ui.dialogos.RegistroUsuarioDialogo
import com.example.miappcurso.ui.views.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.net.HttpURLConnection

class Login : AppCompatActivity(), OnUsuarioActionListener {
    private lateinit var logo: ImageView
    private lateinit var txtEmail: EditText
    private lateinit var txtPass: EditText
    private lateinit var shared: SharedPreferences
    private lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inicializarCampos()
        cargaPreferenciasCompartidas()
        cerrarSesion()
        if (islogeado()) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    private fun cargaPreferenciasCompartidas() {
        val fichPreferencias = getString(R.string.preferencias_fichero_login)
        shared = getSharedPreferences(fichPreferencias, Context.MODE_PRIVATE)
    }

    private fun inicializarCampos() {
        logo = findViewById(R.id.imagenLogo)
        val options = RequestOptions().centerCrop()
        val url = "https://www.diariodesevilla.es/especiales/20-lugares-imprescindibles-de-sevilla/imagenes/catedral-sevilla_1285682100_89371889_2121x1414.jpg"
        Glide.with(this)
            .load(url)
            .error(R.drawable.baseline_photo_camera_24)
            .apply(options)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable?>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(logo)
        txtEmail = findViewById(R.id.txt_email)
        txtPass = findViewById(R.id.txt_password)
        btnRegistrar = findViewById(R.id.btn_registro)
        btnRegistrar.setOnClickListener {
            val registroUsuarioDialogo = RegistroUsuarioDialogo(this)
            registroUsuarioDialogo.show(supportFragmentManager, "Registrar nuevo Usuario")
        }
    }

    private fun islogeado(): Boolean {
        val isLogin = shared.getBoolean(getString(R.string.preferencias_is_logeado), false)
        return isLogin
    }

    fun iniciarLogin(view: View?) {
        val email = txtEmail.text.toString()
        val pass = txtPass.text.toString()
        txtEmail.setText("")
        txtPass.setText("")
        if (!email.isEmpty() && !pass.isEmpty()) {
            val retrofit = MiApp.retrofit
            val apiServicioPueblo = retrofit.create(InterfaceApiPueblo::class.java)
            val loginBodyApi = LoginBodyApi(email, pass)
            val peticionLogin = apiServicioPueblo.login(loginBodyApi)
            peticionLogin.enqueue(object : Callback<RespuestaLogin> {
                override fun onResponse(call: Call<RespuestaLogin>, response: Response<RespuestaLogin>) {
                    if (response.code() == HttpURLConnection.HTTP_CREATED) {
                        val token = response.body()!!.token
                        val editor = shared.edit()
                        editor.putString(getString(R.string.preferencias_email), email)
                        editor.putString(getString(R.string.preferencias_token), token)
                        editor.putString("preferenciasAvatarUsuario", response.body()!!.imagen)
                        editor.putLong("preferenciasIdUsuario", response.body()!!.id)
                        editor.putString("preferenciasNombreUsuario", response.body()!!.nombre)
                        editor.putBoolean(getString(R.string.preferencias_is_logeado), true)
                        editor.commit()
                        val i = Intent(this@Login, MainActivity::class.java)
                        startActivity(i)
                    } else
                        Toast.makeText(this@Login, "Usuario y/o contraseña incorrecto", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<RespuestaLogin>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        } else
            Toast.makeText(this, "Email y/o password incorrectos", Toast.LENGTH_SHORT).show()
    }

    override fun registrarUsuario(email: String, password: String, nombre: String, imagen: String) {
        val retrofit = MiApp.retrofit
        val apiServicioPueblo = retrofit.create(InterfaceApiPueblo::class.java)
        val registroUsuario = apiServicioPueblo.registro(NuevoLoginApi(email, password, nombre, imagen, 1))
        registroUsuario.enqueue(object : Callback<RespuestaRegistro> {
            override fun onResponse(call: Call<RespuestaRegistro>, response: Response<RespuestaRegistro>) {
                val result = response.body()!!.result
                Toast.makeText(this@Login, "Usuario registrado correctamente, debe logearse", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<RespuestaRegistro>, t: Throwable) {
                Toast.makeText(this@Login, "Error en la llamada de creación de usuario", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun cerrarSesion() {
        val sharedPreferences = getSharedPreferences(
            getString(R.string.preferencias_fichero_login),
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
    }
}
