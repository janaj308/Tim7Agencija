package managers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Tim7Destination;
import model.Tim7Offer;
import model.Tim7Traveleroffer;
import model.Tim7User;

public class OfferManager {

	public List<Tim7Offer> getAllOffers() {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			TypedQuery<Tim7Offer> tq = em.createQuery("select o from Tim7Offer o", Tim7Offer.class);
			return tq.getResultList();

		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<>();

		} finally {

			em.close();

		}

	}

	public List<Tim7Offer> getOffersByAge(Date birth) {

		EntityManager em = JPAUtil.getEntityManager();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String b= sdf.format(birth);
			String year= b.substring(0, 4);
			int yearFrom= Integer.parseInt(year)-2;
			int yearTo= Integer.parseInt(year)+2;
			Date from = sdf.parse(yearFrom + "-01-01");
			Date to = sdf.parse(yearTo + "-12-31");
			TypedQuery<Tim7Offer> tq = em.createQuery("select o from Tim7Offer o where o.tim7User.dateofbirth between :from and :to ", Tim7Offer.class);
			tq.setParameter("from", from);
			tq.setParameter("to", to);
			return tq.getResultList();

		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<>();

		} finally {

			em.close();

		}

	}

	public float getMaxPrice() {
		try {
			List<Tim7Offer> l = getAllOffers();
			float max = 0;
			for (Tim7Offer o : l) {
				if (o.getPrice() > max) {
					max = o.getPrice();
				}
			}
			return max;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<Tim7Offer> getOfferForDestination(String name) {
		try {
			TypedQuery<Tim7Offer> tq = JPAUtil.getEntityManager().createQuery(
					"select o from Tim7Offer o join fetch o.tim7Destination d where " + "d.destinationname=:destName",
					Tim7Offer.class);
			tq.setParameter("destName", name);
			return tq.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<Tim7Offer> getOfferForStartingPoint(String start) {
		try {
			TypedQuery<Tim7Offer> tq = JPAUtil.getEntityManager()
					.createQuery("select o from Tim7Offer o where o.startingpoint=:start", Tim7Offer.class);
			tq.setParameter("start", start);
			return tq.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<Tim7Offer> getOfferForPriceRange(float priceLow, float priceHigh) {
		try {
			TypedQuery<Tim7Offer> tq = JPAUtil.getEntityManager().createQuery(
					"select o from Tim7Offer o where o.price between :priceS and :priceF", Tim7Offer.class);
			tq.setParameter("priceS", priceLow);
			tq.setParameter("priceF", priceHigh);
			return tq.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public Tim7Offer findOffer(int offerId) {

		return JPAUtil.getEntityManager().find(Tim7Offer.class, offerId);

	}

	public List<Tim7Destination> getAllDestinations() {
		try {
			TypedQuery<Tim7Destination> tq = JPAUtil.getEntityManager()
					.createQuery("select d from Tim7Destination d order by d.destinationname", Tim7Destination.class);
			return tq.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public boolean addNewDestination(Tim7Destination dest) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(dest);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}

	}

	public List<Tim7Offer> searchOff(String destName, String startPoint, float priceLow, float priceHigh) {
		try {
			if (destName != null && startPoint == null && priceHigh == 0.0 && priceLow == 0.0) {
				return getOfferForDestination(destName);
			}
			if (destName == null && startPoint != null && priceHigh == 0.0 && priceLow == 0.0) {
				System.out.println("Pokrenuo sam pretragu");
				return getOfferForStartingPoint(startPoint);
			}
			if (destName == null && startPoint == null && priceHigh != 0.0 && priceHigh > priceLow) {
				return getOfferForPriceRange(priceLow, priceHigh);
			}
			if (destName != null && startPoint != null && priceHigh == 0.0 && priceLow == 0.0) {
				TypedQuery<Tim7Offer> tq = JPAUtil.getEntityManager()
						.createQuery("select o from Tim7Offer o join fetch o.tim7Destination d where "
								+ "d.destinationname=:destName and o.startingpoint=:start", Tim7Offer.class);
				tq.setParameter("destName", destName);
				tq.setParameter("start", startPoint);
				return tq.getResultList();
			}
			if (destName != null && startPoint == null && priceHigh != 0.0 && priceHigh > priceLow) {
				TypedQuery<Tim7Offer> tq = JPAUtil.getEntityManager().createQuery(
						"select o from Tim7Offer o join fetch o.tim7Destination d where "
								+ "d.destinationname=:destName and o.price between :priceL and :priceH",
						Tim7Offer.class);
				tq.setParameter("destName", destName);
				tq.setParameter("priceL", priceLow);
				tq.setParameter("priceH", priceHigh);
				return tq.getResultList();
			}
			if (destName == null && startPoint != null && priceHigh != 0.0 && priceHigh > priceLow) {
				System.out.println("Pokrenuo sam ovo");
				TypedQuery<Tim7Offer> tq = JPAUtil.getEntityManager().createQuery("select o from Tim7Offer o where "
						+ "o.startingpoint=:start and o.price between :priceL and :priceH", Tim7Offer.class);
				tq.setParameter("start", startPoint);
				tq.setParameter("priceL", priceLow);
				tq.setParameter("priceH", priceHigh);
				return tq.getResultList();
			}
			if (destName != null && startPoint != null && priceHigh != 0 && priceHigh > priceLow) {
				TypedQuery<Tim7Offer> tq = JPAUtil.getEntityManager().createQuery(
						"select o from Tim7Offer o join fetch o.tim7Destination d where "
								+ "d.destinationname=:destName and o.startingpoint=:start and o.price between :priceL and :priceH",
						Tim7Offer.class);
				tq.setParameter("destName", destName);
				tq.setParameter("start", startPoint);
				tq.setParameter("priceL", priceLow);
				tq.setParameter("priceH", priceHigh);
				return tq.getResultList();
			}
			return new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public boolean postOffer(Tim7Offer of) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(of);
			em.getTransaction().commit();
			return true;

		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}

	}

	public List<Tim7User> getTravelers(int offerId) {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			TypedQuery<Tim7Traveleroffer> tq = em.createQuery(
					"select o from Tim7Traveleroffer o where o.tim7Offer.idoffer = :id", Tim7Traveleroffer.class);
			tq.setParameter("id", offerId);

			List<Tim7User> travelers = new ArrayList<>();
			for (Tim7Traveleroffer o : tq.getResultList()) {

				travelers.add(o.getTim7User());

			}

			return travelers;

		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<>();

		} finally {

			em.close();

		}

	}

	public void acceptOffer(Tim7User user, Tim7Offer offer) {

		EntityManager em = JPAUtil.getEntityManager();

		try {

			em.getTransaction().begin();
			Tim7Traveleroffer to = new Tim7Traveleroffer();
			to.setTim7User(user);
			to.setTim7Offer(offer);
			em.merge(offer);
			em.persist(to);
			em.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			em.close();

		}

	}

}
