package dislinkt.jobofferservice.repositories.specification;

import org.springframework.data.jpa.domain.Specification;

import dislinkt.jobofferservice.entities.JobOffer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JobOfferSpecification implements Specification<JobOffer> {

    private String query;

    public JobOfferSpecification(String query) {
        this.query = query;
    }

    @Override
    public Predicate toPredicate(Root<JobOffer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate p = cb.disjunction();
        p.getExpressions().add(cb.notEqual(cb.locate(cb.lower(root.get("title")), this.query.toLowerCase()), 0));
        p.getExpressions().add(cb.notEqual(cb.locate(cb.lower(root.get("companyName")), this.query.toLowerCase()), 0));
        p.getExpressions().add(cb.notEqual(cb.locate(cb.lower(root.get("description")), this.query.toLowerCase()), 0));

        return p;
    }

}
