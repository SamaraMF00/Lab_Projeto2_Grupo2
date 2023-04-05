package vehicleRental.model;

import java.util.Date;
import javax.persistence.*;
import lombok.ToString;

@Entity
@ToString
@Table(name = "request")

public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long requestId;

    @OneToOne
    @JoinColumn(name = "custumer_id")
    private Customer custumer;

    @Column(name = "inclusion_date")
    private Date inclusionDate;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "state")
    private String state;

    public Request() {
    }

}
