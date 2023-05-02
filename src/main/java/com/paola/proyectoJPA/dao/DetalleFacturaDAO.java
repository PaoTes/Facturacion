package com.paola.proyectoJPA.dao;
import java.util.List;

import com.paola.proyectoJPA.model.DetalleFacturaModel;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DetalleFacturaDAO {
@Autowired
private DetalleFacturaModel detalleFacturaModel;
    public void agregarDetalleFactura(DetalleFacturaModel detalleFactura) {
        Transaction transaction = null;
        Cache HibernateUtil = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(detalleFactura);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<DetalleFacturaModel> obtenerDetallesFactura(int facturaId) {
        Cache HibernateUtil = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<DetalleFacturaModel> query = session.createQuery("from DetalleFactura df where df.factura.id = :facturaId", DetalleFacturaModel.class);
            query.setParameter("facturaId", facturaId);
            List<DetalleFacturaModel> detallesFactura = query.list();
            return detallesFactura;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
