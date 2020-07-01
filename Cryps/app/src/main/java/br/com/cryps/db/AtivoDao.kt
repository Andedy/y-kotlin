package br.com.cryps.db


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log

class AtivoDao(context: Context) {
    var banco = DBhelper(context)

    fun insert(ativo: Ativo): String {
        val db = banco.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ITEM_NOME, ativo.nome)
        contentValues.put(ITEM_SIGLA, ativo.sigla)
        contentValues.put(ITEM_VALOR, ativo.valor)
        contentValues.put(ITEM_QTD, ativo.qtd)
        contentValues.put(ITEM_TOTAL, ativo.total)
        contentValues.put(ITEM_DATA, ativo.data)

        var resp_id = db.insert(TABLE_ATIVOS,null,contentValues)
        val msg = if(resp_id != -1L){
            "inserido com sucesso"
        }else{
            "erro ao inserir"
            Log.e("Erro", db.insert(TABLE_ATIVOS,null,contentValues).toString())
        }
        db.close()
        return msg.toString()
    }

    fun getAll(): ArrayList<Ativo>{
        val db = banco.writableDatabase

        val sql = "SELECT * FROM " + TABLE_ATIVOS
        var cursor = db.rawQuery(sql,null)
        val ativos = ArrayList<Ativo>()
        while (cursor.moveToNext()){
            val ativo = ativoFromCursor(cursor)
            ativos.add(ativo)
        }
        cursor.close()
        db.close()
        return ativos
    }

    fun remove(ativo: Ativo):Int{
        val db = banco.writableDatabase
        return  db.delete(TABLE_ATIVOS,"ID=?", arrayOf(ativo.id.toString()))
    }

    fun ativoFromCursor(cursos: Cursor):Ativo{
        val id  = cursos.getInt(cursos.getColumnIndex(ITEM_ID))
        val nome = cursos.getString(cursos.getColumnIndex(ITEM_NOME))
        val sigla = cursos.getString(cursos.getColumnIndex(ITEM_SIGLA))
        val valor = cursos.getDouble(cursos.getColumnIndex(ITEM_VALOR))
        val qtd = cursos.getDouble(cursos.getColumnIndex(ITEM_QTD))
        val total = cursos.getDouble(cursos.getColumnIndex(ITEM_TOTAL))
        val data = cursos.getString(cursos.getColumnIndex(ITEM_DATA))
        return Ativo(id, nome, sigla, valor, qtd, total, data)
    }
}