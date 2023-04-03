package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;

import javax.persistence.EntityManager;

public class CategoriaDao {
    private EntityManager em;

    public CategoriaDao(EntityManager em){
        this.em = em;
    }
    public void guardar(Categoria categoria){
        this.em.persist(categoria);
    }
    public void actualizar(Categoria categoria){
        this.em.merge(categoria);
    }
    public void remover(Categoria categoria){
        categoria = this.em.merge(categoria);
        this.em.remove(categoria);
    }
}
