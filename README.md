# APLICACIÓN MÓVIL PELÍCULAS
Este proyecto es una aplicación  de gestión de películas que voy a ir desarrollando con diferentes versiones a lo largo del curso en el módulo de Android.

## ÍNDICE
### 1. VERSIÓN 1.1
### 2. VERSION 1.2



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
# Archivos XML:
Contiene los mismos archivos xml que en la versión 1.1 pero con algunos cambios:
1. **activity_login.xml**: el diseño sigue exactamente igual.

2. **activity_main.xml**: Se han añadido nuevas películas y si deslizas hacia abajo se podrán ver. Hay un nuevo boton **Añadir Pelicula Nueva** que permite añadir películas nuevas en la que aparecerá un dialog y tendrás que rellenar los campos de título, género e imagen para poder crear una nueva. También hay unos nuevos botones en el cardview que explicaré en su apartado.
![Ejemplo de imagen](https://github.com/lucopa/EjerciciosJavaScript/blob/main/pantallazos/crudcompleto.png?raw=true)
El dialog es así:
![Ejemplo de imagen](https://github.com/lucopa/EjerciciosJavaScript/blob/main/pantallazos/add_film.png?raw=true)

3. **cardview_film.xml**: Se ha añadido el image Button de un lápiz en el que si pulsas en el te aparecerá un dialog que te permitirá editar la película:
![Ejemplo de imagen](https://github.com/lucopa/EjerciciosJavaScript/blob/main/pantallazos/edit_film.png?raw=true)
![Ejemplo de imagen](https://github.com/lucopa/EjerciciosJavaScript/blob/main/pantallazos/cardview.png?raw=true)
