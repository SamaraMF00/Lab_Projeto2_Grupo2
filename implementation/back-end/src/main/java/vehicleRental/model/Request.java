package vehicleRental.model;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

@Entity
@ToString
@Data
@Table(name = "request")

public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "inclusion_date")
    private Date inclusionDate;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "state")
    private String state;

}
