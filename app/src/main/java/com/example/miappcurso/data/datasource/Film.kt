package com.example.miappcurso.data.datasource

object Film : List<String> {
    val peliculas: MutableList<List<String>> = mutableListOf(
        listOf("Get Out", "Terror y Suspense", "https://pics.filmaffinity.com/get_out-290175782-mmed.jpg"),
        listOf("Pretty Woman", "Amor y drama", "https://pics.filmaffinity.com/pretty_woman-629528349-mmed.jpg"),
        listOf("Pitch Perfect", "Musical y Comedia", "https://pics.filmaffinity.com/pitch_perfect-419842614-mmed.jpg"),
        listOf("Gone Girl", "Thriller y Suspense", "https://es.web.img3.acsta.net/c_310_420/pictures/14/09/08/15/34/309537.jpg"),
        listOf("Gorrión Rojo", "Acción y Drama", "https://es.web.img3.acsta.net/c_310_420/pictures/18/01/18/10/47/2319558.jpg"),
        listOf("Barbie", "Comedia y Fantasía", "https://pics.filmaffinity.com/barbie-295661697-mmed.jpg"),
        listOf("Oppenheimer", "Biopic y Suspense", "https://pics.filmaffinity.com/oppenheimer-828933592-mmed.jpg")
    )






















    override val size: Int
        get() = TODO("Not yet implemented")

    override fun get(index: Int): String {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<String> {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<String> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<String> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<String> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: String): Int {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: String): Int {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(element: String): Boolean {
        TODO("Not yet implemented")
    }
}
