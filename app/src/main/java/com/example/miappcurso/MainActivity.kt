package com.example.miappcurso

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.miappcurso.SharedPreferences.UserTermsAplication.Companion.prefs
import com.example.miappcurso.databinding.ActivityMainBinding
import com.example.miappcurso.databinding.NavHeaderBinding
import com.example.miappcurso.fragments.DetailFragment
import com.example.miappcurso.fragments.FragmentRecycler
import com.example.miappcurso.fragments.RewardFragment
import com.example.miappcurso.fragmentsBYN.ActorFragment
import com.example.miappcurso.fragmentsBYN.HelpFragment
import com.example.miappcurso.fragmentsBYN.ProfileFragment
import com.example.miappcurso.toolBar.MyToolBar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingnav: NavHeaderBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()



        fragmentManager = supportFragmentManager

        MyToolBar().show(this, "PELÍCULAS", true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DetailFragment())
            .commit()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbarPeliculas.toolbar,
            R.string.open_nav,
            R.string.close_nav
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_home->{
                    openFragment(DetailFragment())
                    Toast.makeText(this, "Recomendaciones de Peliculas", Toast.LENGTH_SHORT).show()
                }
                R.id.bottom_profile->{
                    openFragment(ProfileFragment())
                    Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()

                }
                R.id.bottom_cart->{
                    openFragment(FragmentRecycler())
                    Toast.makeText(this, "Lista de Peliculas", Toast.LENGTH_SHORT).show()

                }
                R.id.bottom_menu-> {
                    openFragment(RewardFragment())
                    Toast.makeText(this, "Premios del Cine", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }


    }

    fun initUI(){

        bindingnav = NavHeaderBinding.bind(binding.navigationDrawer.getHeaderView(0))

        binding.btnCerrarSesion.setOnClickListener{
            prefs.restaurar()
            onBackPressed()
            Toast.makeText(this, "Vas a Cerrar Sesión", Toast.LENGTH_SHORT).show()

        }
        val username = prefs.getName()

        bindingnav.idusuario.text = "Bienvenido $username"
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.option_one -> {
                val fragment = FragmentRecycler()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit()
                Toast.makeText(this, "Lista de Peliculas", Toast.LENGTH_SHORT).show()

                return true
            }
            R.id.option_four-> {
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(this, "Volviste al Login", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.option_five -> {
                val fragment = RewardFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit()
                Toast.makeText(this, "Premios del Cine", Toast.LENGTH_SHORT).show()

                return true
            }



        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_prime -> {
                openFragment(RewardFragment())
                Toast.makeText(this, "Premios del Cine", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_electronics-> {
                Toast.makeText(this, "Lista de Peliculas", Toast.LENGTH_SHORT).show()
                openFragment(FragmentRecycler())}
            R.id.nav_location-> {
                openFragment(DetailFragment())
                Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show()}
            R.id.nav_help->{
                openFragment(HelpFragment())
                Toast.makeText(this, "Ayuda", Toast.LENGTH_SHORT).show()}
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onBackPressed(){
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()

    }


}