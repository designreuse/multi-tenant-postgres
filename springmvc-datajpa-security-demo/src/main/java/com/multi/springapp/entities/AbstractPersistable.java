package com.multi.springapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.domain.Persistable;

/**
 * Uma classe base para as outras classes.
 * 
 * @author silvano.pessoa
 * 
 * @param <T>
 */
@MappedSuperclass
public abstract class AbstractPersistable<T extends Serializable> implements Persistable<T> {

    /**
     * SERIAL VERSION UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * CODIGO 17 GERADO PELO SPRING DATA
     */
    private static final int HASHCODE17_SPRING = 17;

    /**
     * CODIGO 31 GERADO PELO SPRING DATA
     */
    private static final int HASHCODE31_SPRING = 31;

    /**
     * Não alterar!
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private T id;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Persistable#getId()
     */
    @XmlTransient
    public T getId() {
        return id;
    }

    /**
     * Sets the id of the entity.
     * 
     * @param id the id to set
     */
    public void setId(final T id) {
        this.id = id;
    }

    /**
     * Verifica se o ID das classes estão nulos. Em caso afirmativo, retorna true.
     * 
     * @author gabriel.marciano.
     */
    public boolean isNew() {
        return null == getId();
    }

    /**
     * Retorna uma representação de Strings do objeto AbstractPersistable. Deverá conter as informações sobre AbstractPersistable.
     * 
     * @author gabriel.marciano.
     */
    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }

    /**
     * Método que verifica se o objeto passado como parâmetro é o correto. Verifica também se o objeto passado foi null Retorna true se o objeto passado como parâmetro estiver certo e false caso
     * contrário ou se ele for null.
     * 
     * @author gabriel.marciano.
     */
    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        AbstractPersistable<?> that = (AbstractPersistable<?>) obj;

        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    /**
     * Obtém um código hash da classe AbstractPersistable.
     * 
     * @return código hash.
     * @author gabriel.marciano.
     */
    @Override
    public int hashCode() {
        int hashCode = HASHCODE17_SPRING;
        hashCode += null == getId() ? 0 : getId().hashCode() * HASHCODE31_SPRING;
        return hashCode;
    }

}
