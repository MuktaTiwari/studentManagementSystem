import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private String college;
    
    @Column
    private String address;
    
    @Column
    private String contactNo;
    
    @Column
    private String parentContactNo;
    
    @Column
    private String course;
    
    @Column
    private Double fees;
    
    @Column
    private Double paidFees;
    
    @Column
    private Double balanceFees;

    @PrePersist
    @PreUpdate
    private void trimFields() {
        if (name != null) {
            name = name.trim();
        }
        if (course != null) {
            course = course.trim();
        }
        if (address != null) {
            address = address.trim();
        }
    }
}
