package au.com.barakode.jot.feature_note.presentation.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()

    override fun toString(): String {
        return when(this) {
            is Ascending -> "Ascending"
            is Descending -> "Descending"
        }
    }
}