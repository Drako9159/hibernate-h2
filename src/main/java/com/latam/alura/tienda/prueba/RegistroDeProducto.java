package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDeProducto {
    public static void main(String[] args) {
        registrarProducto();
        EntityManager em = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        Producto producto = productoDao.consultaPorId(1l);
        System.out.println(producto.getNombre());

        List<Producto> productos = productoDao.consultarTodos();
        // option
        List<Producto> productoSingle = productoDao.consultaPorNombre("Samsung");
        // option
        List<Producto> productoCategoria = productoDao.consultaPorNombreDeCategoria("CELULARES");
        // option
        BigDecimal productoPrecio = productoDao.consultarPrecioPorNombreDeProducto("Samsung");
        productos.forEach(prod -> System.out.println(prod.getDescripcion()));

    }

    private static void registrarProducto() {
        Categoria celulares = new Categoria("CELULARES");
        Producto celular = new Producto("Samsung", "Telefono usado", new BigDecimal("1000"), celulares);

        EntityManager em = JPAUtils.getEntityManager();
        //ProductoDao productoDao = new ProductoDao(em);
        //CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        //productoDao.guardar(celular);
        //categoriaDao.guardar(celulares);
        em.persist(celulares);
        celulares.setNombre("LIBROS");
        em.flush();
        em.clear();
        celulares = em.merge(celulares);
        celulares.setNombre("SOFTWARES");
        //em.getTransaction().commit();
        //em.close();
        em.flush();
        em.clear();
        celulares = em.merge(celulares);
        em.remove(celulares);
        em.flush();
    }
}
