package com.example.miappcurso.ui.adapter

import com.example.miappcurso.data.models.Films

class FilmsProvider {
        companion object{
            val filmsList = listOf<Films>(
                Films(
                    "Get Out",
                    "Terror y Suspense",
                    "https://pics.filmaffinity.com/get_out-290175782-mmed.jpg"

                ),
                Films(
                    "Pretty Woman",
                    "Amor y drama",
                    "https://pics.filmaffinity.com/pretty_woman-629528349-mmed.jpg"
                ),
                Films(
                    "Pitch Perfect",
                    "Musical y Comedia",
                    "https://pics.filmaffinity.com/pitch_perfect-419842614-mmed.jpg"
                ),
                Films(
                        "Gone Girl",
                "Thriller y Suspense",
                "https://es.web.img3.acsta.net/c_310_420/pictures/14/09/08/15/34/309537.jpg"
                ),
                Films(
                    "Gorrión Rojo",
                    "Acción y Drama",
                    "https://es.web.img3.acsta.net/c_310_420/pictures/18/01/18/10/47/2319558.jpg"
                ),
                Films(
                    "Barbie",
                    "Comedia y Fantasía",
                    "https://pics.filmaffinity.com/barbie-295661697-mmed.jpg"
                ),
                Films(
                    "Oppenheimer",
                    "Biopic y Suspense",
                    "https://pics.filmaffinity.com/oppenheimer-828933592-mmed.jpg"
                )
            )

            fun getFilms(): List<Films> {
                return filmsList
            }
        }
    }
