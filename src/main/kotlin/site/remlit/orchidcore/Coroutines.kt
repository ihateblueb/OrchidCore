package site.remlit.orchidcore

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Coroutines {
    val sharedScope = CoroutineScope(Dispatchers.Default + CoroutineName("OrchidCore"))
}