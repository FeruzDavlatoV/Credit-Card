package com.example.creditcard.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.creditcard.model.Card

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createCard(card: Card)

    @Query("UPDATE cards SET is_exist = :is_exist WHERE id = :id")
    fun updateCard(id: Int, is_exist: Boolean): Int

    @Query("select * from cards")
    fun getCards(): List<Card>

    @Query("Select * from cards where id =:id")
    fun getCard(id: Int): Card

    @Query("Delete from cards")
    fun clearCards()

    @Query("select * from cards where is_exist=0")
    fun getOfflineCards(): List<Card>

}