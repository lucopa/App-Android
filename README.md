# APLICACIÓN MÓVIL PELÍCULAS
Aplicación  de gestión de películas que voy a ir desarrollando con diferentes versiones aplicando todo el temario

## ÍNDICE
### 1. VERSIÓN 1.1
### 2. VERSION 1.2
### 3. VERSION 1.3
### 4. VERSION 1.4
### 5. VERSION 2.1






## Version 1.1 
En esta primera versión, se utiliza un RecyclerView con la funcionalidad de poder borrar. Además de un Login en el que introduciremos un usuario y contraseña, deberá acceder al RecyclerView cuando sean correctos y cuando sean incorrectos que nos indique que están mal.
Añadiremos nuestras propias clases POJO y Adaptadores.

## Estructura del Proyecto

 com.example.miappcurso: Paquete principal que contiene diferentes archivos Kotlin.
1. **Films.kt**: Contiene la definición de la clase Films utilizada para representar detalles de películas.
2. **FilmsProvider.kt**:  proporciona una lista de películas de ejemplo.
3. **LoginActivity.kt**: Actividad para el proceso de inicio de sesión.
4. **MainActivity.kt**: Actividad principal que muestra las películas disponibles.
   
# Archivos XML:
1. **activity_login.xml**: Diseño de la interfaz de usuario para la pantalla de inicio de sesión.
   
![Ejemplo de imagen](https://github.com/lucopa/EjerciciosJavaScript/blob/main/pantallazos/Login.png?raw=true)

2. **activity_main.xml**: Diseño de la interfaz de usuario principal que muestra la lista de películas.
   
![Ejemplo de imagen](https://github.com/lucopa/EjerciciosJavaScript/blob/main/pantallazos/recyclerview.png?raw=true)

3. **cardview_film.xml**: Diseño de un elemento de la lista de películas utilizando CardView.

![Ejemplo de imagen](https://github.com/lucopa/EjerciciosJavaScript/blob/main/pantallazos/cardview1.png?raw=true)

## VERSIÓN 1.2
En esta segunda versión, se realiza el crud completo. Añadimos las funciones de editar y dar de alta nuevas películas.

## Estructura del Proyecto:
Contiene los mismos activitys que en la versión anterior. Dentro del **MainActivity.kt** se han añadido los métodos de editar y dar de alta:

Editar:

```kotlin
 private fun onEditItem(position: Int) {
        val dialogView = EditFilmDialogBinding.inflate(layoutInflater)
        val selectedItem = filmsMutableList[position]

        dialogView.editTitulo.setText(selectedItem.film)
        dialogView.editGenero.setText(selectedItem.genero)

        val dialog = AlertDialog.Builder(this)
        dialog.setView(dialogView.root)
        dialog.setTitle("Editar película")

        dialog.setPositiveButton("Guardar") { _, _ ->
            val newTitle = dialogView.editTitulo.text.toString()
            val newGenre = dialogView.editGenero.text.toString()

            selectedItem.film = newTitle
            selectedItem.genero = newGenre


            adapter.notifyItemChanged(position)

            Toast.makeText(this, "Película editada: $newTitle", Toast.LENGTH_SHORT).show()
        }

        dialog.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(this, "Edición cancelada", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }
```
Dar de alta:
```kotlin
  private fun showAddFilmDialog() {
        val dialogView = AddFilmDialogBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
        dialog.setView(dialogView.root)
        dialog.setTitle("Añadir Nueva Película")

        dialog.setPositiveButton("Guardar") { _, _ ->
            val newTitle = dialogView.editTitulo.text.toString()
            val newGenre = dialogView.editGenero.text.toString()
            val newPhotoUrl = dialogView.editPhotoUrl.text.toString()


            val newFilm = Films(newTitle, newGenre, newPhotoUrl)
            filmsMutableList.add(newFilm)
            adapter.notifyItemInserted(filmsMutableList.size - 1)

            Toast.makeText(this, "Nueva película añadida: $newTitle", Toast.LENGTH_SHORT).show()
        }

        dialog.setNegativeButton("Cancelar") { _, _ ->
            Toast.makeText(this, "Añadir película cancelado", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }
```
## Archivos XML:
Contiene los mismos archivos xml que en la versión 1.1 pero con algunos cambios:
1. **activity_login.xml**: el diseño sigue exactamente igual.

2. **activity_main.xml**: Se han añadido nuevas películas y si deslizas hacia abajo se podrán ver. Hay un nuevo boton **Añadir Pelicula Nueva** que permite añadir películas nuevas en la que aparecerá un dialog y tendrás que rellenar los campos de título, género e imagen para poder crear una nueva. También hay unos nuevos botones en el cardview que explicaré en su apartado.
   
![Ejemplo de imagen](https://github.com/lucopa/EjerciciosJavaScript/blob/main/pantallazos/crudcompleto.png?raw=true)

El dialog es así:

![Ejemplo de imagen](https://github.com/lucopa/EjerciciosJavaScript/blob/main/pantallazos/add_film.png?raw=true)

3. **cardview_film.xml**: Se ha añadido el image Button de un lápiz en el que si pulsas en el te aparecerá un dialog que te permitirá editar la película:

   
![Ejemplo de imagen](https://github.com/lucopa/EjerciciosJavaScript/blob/main/pantallazos/edit_film.png?raw=true)
![Ejemplo de imagen](https://github.com/lucopa/EjerciciosJavaScript/blob/main/pantallazos/cardview.png?raw=true)


## 3. VERSION 1.3
A esta versión, añadiremos un Navigation Drawer o Navigation Bottom.

## Estructura del Proyecto:
Dentro del main_activity, donde añadimos sus respectivomos métodos:
```kotlin
    - Navigation Drawer

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

```

```kotlin
    - Navigation Bottom
    
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

```



## Archivos XML:
Los nuevos archivos creados han sido:

1. Una carpeta llamada menú, en el que dentro de ella hay:
   
    ```xml
    - **bottom_menu.xml**:
    <menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/bottom_home"
        android:title="Home"
        android:icon="@drawable/baseline_home_24"/>
    <item
        android:id="@+id/bottom_profile"
        android:title="Perfil"
        android:icon="@drawable/baseline_subscriptions_24"/>
    <item
        android:title=""
        android:enabled="true"/>
    <item
        android:id="@+id/bottom_cart"
        android:title="Peliculas"
        android:icon="@drawable/baseline_info_24"/>
    <item
        android:id="@+id/bottom_menu"
        android:title="Premios"
        android:icon="@drawable/baseline_auto_awesome_24"/>

    </menu>
    ```
       
    ```xml
    - **nav_menu.xml**:

    <menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    tools:showIn = "navigation_view">

    <group
        android:checkableBehavior="single">

        <item
            android:id="@+id/nav_prime"
            android:icon="@drawable/baseline_admin_panel_settings_24"
            android:title="Premios Del cine"/>
        <item
            android:id="@+id/nav_electronics"
            android:title="Peliculas"
            android:icon="@drawable/baseline_library_24"/>
        <item
            android:id="@+id/nav_location"
            android:title="Inicio"
            android:icon="@drawable/baseline_home_24"/>
        <item
            android:id="@+id/nav_help"
            android:title="Ayuda"
            android:icon="@drawable/baseline_help_24"/>
    </group>

    </menu>
    ```
## 4. VERSION 1.4
En esta versió, realizaremos la adaptación de vuestro proyecto con mvvm e inyección de dependencias con
Hilt.

## Dependencias:
He tenido que añadir las siguientes dependencias:


## Estructura del Proyecto:
Ha habido que reorganizar la estructura del proyecto para poder adaptarla a la arquitectura mvvm. Principalmente lo he divido en 3 carpetas principales:
1. Data
2. Domain
3. Ui

## Archivos XML:
No se han tenido que crear archivos xml nuevos.

## 5. VERSION 2.1
Podremos validar el login sólo la primera vez y editar/añadir/eliminar con persistencia Room o Realm. (Crearemos una rama, ya que simularemos una mala decisión de Acceso a datos)

## Dependencias:
He tenido que añadir las siguientes dependencias:

## Estructura del Proyecto:


## Archivos XML:
