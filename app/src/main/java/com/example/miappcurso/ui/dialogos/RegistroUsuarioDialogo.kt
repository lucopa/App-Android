package com.example.miappcurso.ui.dialogos


import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.content.ContextCompat
import com.example.miappcurso.R
import com.example.miappcurso.domain.listener.OnUsuarioActionListener
import com.example.miappcurso.ui.camara.Camara


class RegistroUsuarioDialogo(context: Context) : AppCompatDialogFragment() {
    private val contexto: Context = context
    private val mListener: OnUsuarioActionListener = context as OnUsuarioActionListener
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextNombre: EditText
    private lateinit var imageViewCapturaFoto: ImageView
    private lateinit var imagenViewFoto: ImageView
    private lateinit var shared: SharedPreferences
    private lateinit var inicioActividadCamara: ActivityResultLauncher<Intent>
    private var bitMap: Bitmap? = null
    private var permisosCamaraConcedidos = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(contexto, R.style.CustomAlertDialog)
        val inflater: LayoutInflater = requireActivity().layoutInflater
        val vista: View = inflater.inflate(R.layout.formulario_registro_usuario, null)
        inicializarCampos(vista)
        permisosCamaraConcedidos = compruebaPermisosCamara()
        crearInicioActividadCamara()
        val fichPreferencias: String = getString(R.string.preferencias_fichero_login)
        shared = contexto.getSharedPreferences(fichPreferencias, Context.MODE_PRIVATE)
        builder.setView(vista)
            .setTitle("REGISTRO")
            .setNegativeButton("CANCELAR") { dialogInterface: DialogInterface?, i: Int ->
                Toast.makeText(contexto, "Registro Cancelado", Toast.LENGTH_SHORT).show()
                dialogInterface?.dismiss()
            }
            .setPositiveButton("REGISTRATE") { dialogInterface: DialogInterface?, i: Int ->
                val email: String = editTextEmail.text.toString()
                val pass: String = editTextPassword.text.toString()
                val nombre: String = editTextNombre.text.toString()
                if (email.isEmpty() || pass.isEmpty() || nombre.isEmpty()) {
                    Toast.makeText(contexto, "Tienes que rellenar todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    if (compruebaPermisosCamara()) {
                        val editor: SharedPreferences.Editor = shared.edit()
                        editor.putBoolean(getString(R.string.preferencias_permiso_camara), true)
                        editor.commit()
                    }
                    var imagenBase64: String? = null
                    var urlImagenBase64: String? = null
                    bitMap?.let {
                        imagenBase64 = Camara.convert(it)
                        urlImagenBase64 = "data:image/jpeg;base64,$imagenBase64"
                    }
                    urlImagenBase64?.let { mListener.registrarUsuario(email, pass, nombre, it) }
                }
                dialogInterface?.dismiss()
            }
        val dialog: AlertDialog = builder.create()
        val titleId: Int = resources.getIdentifier("alertTitle", "id", "android")
        val titleTextView: TextView? = dialog.findViewById(titleId)
        titleTextView?.gravity = Gravity.CENTER
        dialog.setOnShowListener {
            val buttonPositive: Button? = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val buttonNegative: Button? = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        }
        return dialog
    }

    private fun compruebaPermisosCamara(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return when {
                ContextCompat.checkSelfPermission(
                    contexto, Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> true
                shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    Toast.makeText(contexto, "Esta app necesita la camara para el perfil del usuario.", Toast.LENGTH_LONG).show()
                    false
                }
                else -> {
                    peticionPermisoCamara.launch(Manifest.permission.CAMERA)
                    false
                }
            }
        }
        return true
    }

    private fun inicializarCampos(vista: View) {
        editTextEmail = vista.findViewById(R.id.edit_email)
        editTextPassword = vista.findViewById(R.id.edit_password)
        editTextNombre = vista.findViewById(R.id.edit_nombre)
        imageViewCapturaFoto = vista.findViewById(R.id.imag_view_cap_foto2)
        imagenViewFoto = vista.findViewById(R.id.text_view_imagen_foto2)
        imageViewCapturaFoto.setOnClickListener { e: View? ->
            val intentCamara = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            inicioActividadCamara.launch(intentCamara)
        }
    }

    private val peticionPermisoCamara: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { result: Boolean ->
        if (result) {
            val editor: SharedPreferences.Editor = shared.edit()
            editor.putBoolean(getString(R.string.preferencias_permiso_camara), true)
            editor.commit()
            permisosCamaraConcedidos = true
        } else {
            Toast.makeText(contexto, "No podrás utilizar la camara.", Toast.LENGTH_LONG).show()
            permisosCamaraConcedidos = false
        }
    }

    private fun crearInicioActividadCamara() {
        inicioActividadCamara = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            bitMap = result.data?.extras?.get("data") as Bitmap?
            imagenViewFoto.setImageBitmap(bitMap)
        }
    }
}
