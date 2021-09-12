package br.com.carv.sales.sales.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Name field is mandatory.")
    private String nameClient;

    @Column(nullable = false, length = 11)
    @NotEmpty(message = "Cpf Client field is mandatory.")
    @CPF(message = "Enter a valid CPF.")
    private String cpfClient;

    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Order> orders;

    public Client() {}

    public Client(Integer idClient, String nameClient) {
        this.idClient = idClient;
        this.nameClient = nameClient;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getCpfClient() { return cpfClient; }

    public void setCpfClient(String cpfClient) {
        this.cpfClient = cpfClient;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(idClient, client.idClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient);
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nameClient='" + nameClient + '\'' +
                '}';
    }
}
