package com.huawei.vsp.po;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import junit.framework.TestCase;

/**
 * 功能描述
 *
 * @since 2020-08-15
 */
public class SitcomTest extends TestCase {
    private SessionFactory sessionFactory;

    @Override
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
    }

    @Override
    protected void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public void testBasicUsage() {
        // create a couple of events...
        Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		session.save( new Event( "Our very first event!", new Date() ) );
//		session.save( new Event( "A follow up event", new Date() ) );
//		session.getTransaction().commit();
//		session.close();

        // now lets pull events from the database and list them
        session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria vodCriteria = session.createCriteria(Vod.class, "vod");
        
        DetachedCriteria sitcomCriterias = DetachedCriteria.forClass(SitcomRelation.class, "sitcoms");
        sitcomCriterias.add(Property.forName("vod.objectId").eqProperty("sitcoms.sitcomId"));
        vodCriteria.add(Subqueries.exists(sitcomCriterias.setProjection(Projections.property("sitcoms.sitcomId"))));
        
        DetachedCriteria userGroupCirteria = DetachedCriteria.forClass(VodUserGroup.class, "group");
        userGroupCirteria.add(Restrictions.in("groupId", new String[]{"110"}));
        userGroupCirteria.add(Property.forName("sitcoms.itemId").eqProperty("group.vodId"));
        sitcomCriterias.add(Subqueries.exists(userGroupCirteria.setProjection(Projections.property("group.vodId"))));

        List results =  vodCriteria.list();
        System.out.println(results);
        session.getTransaction().commit();
        session.close();
    }
}
