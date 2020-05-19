package com.myproject.Units;

/**
 * @author Dudka Maxym
 * @version 12.0.2
 * Class of companies
 */

public class Company {
    private int id;
    private String name;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Company other = (Company) obj;
        if (name == null) {
            return other.name == null;
        } else return name.equals(other.name);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Company [id=" + id + ", name=" + name + "]";
    }
}
