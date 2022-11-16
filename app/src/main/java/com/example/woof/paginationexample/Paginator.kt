package com.example.woof.paginationexample

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}