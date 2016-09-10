package model;

import java.util.List;

/**
 * Created by linlin on 8/23/16.
 */
public class HomeResult {
    private List<Advertisement> advs;
    private List<Navigate> navigates;
    private List<Topic> topics;
    private List<Product> tagProducts;

    public List<Advertisement> getAdvs() {
        return advs;
    }

    public void setAdvs(List<Advertisement> advs) {
        this.advs = advs;
    }

    public List<Navigate> getNavigates() {
        return navigates;
    }

    public void setNavigates(List<Navigate> navigates) {
        this.navigates = navigates;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Product> getTagProducts() {
        return tagProducts;
    }

    public void setTagProducts(List<Product> tagProducts) {
        this.tagProducts = tagProducts;
    }
}
