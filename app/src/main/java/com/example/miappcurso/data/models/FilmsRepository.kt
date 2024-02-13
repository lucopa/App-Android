import com.example.miappcurso.data.models.FilmsRepositoryInterfaceDao


class FilmsRepository(private val filmsRepositoryDao: FilmsRepositoryInterfaceDao) {


    suspend fun getFilms(): List<List<String>> {
        return filmsRepositoryDao.getFilms()
    }


    suspend fun addFilm(film: List<String>) {
        filmsRepositoryDao.addFilm(film)
    }


    suspend fun editFilm(film: List<String>) {
        filmsRepositoryDao.editFilm(film)
    }


    suspend fun deleteFilm(film: List<String>) {
        filmsRepositoryDao.deleteFilm(film)
    }
}
