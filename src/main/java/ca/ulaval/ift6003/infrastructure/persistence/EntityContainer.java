package ca.ulaval.ift6003.infrastructure.persistence;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("entites")
public class EntityContainer<T> {

    @XStreamImplicit
    public List<T> entites = new ArrayList<>();

    public EntityContainer() {

    }

    public EntityContainer(List<T> entites) {
        this.entites = entites;
    }

    public List<T> getEntites() {
        return entites;
    }
}
