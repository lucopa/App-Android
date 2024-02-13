import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class FilmsViewModel(private val filmsRepository: FilmsRepository) : ViewModel() {


    val filmsList = liveData(Dispatchers.IO) {
        val films = filmsRepository.getFilms()
        emit(films)
    }


    suspend fun addFilm(filmData: List<String>) {
        filmsRepository.addFilm(filmData)
    }


    suspend fun editFilm(oldFilmData: List<String>, newFilmData: List<String>) {
        filmsRepository.editFilm(oldFilmData)
    }


    suspend fun deleteFilm(filmData: List<String>) {
        filmsRepository.deleteFilm(filmData)
    }
}
