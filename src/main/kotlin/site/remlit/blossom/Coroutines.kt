package site.remlit.blossom

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object Coroutines {
    val sharedScope = CoroutineScope(Dispatchers.Default + CoroutineName("Blossom"))
}